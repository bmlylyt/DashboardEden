package com.example.UserCeneter.service.impl;

import com.example.UserCeneter.RPCDomain.req.LoginRequest;
import com.example.UserCeneter.RPCDomain.req.RegisterRequest;
import com.example.UserCeneter.RPCDomain.response.ArticleResponse;
import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.RPCDomain.response.UserCenterVOResponse;
import com.example.UserCeneter.common.ResultCode;
import com.example.UserCeneter.common.strategy.ContextMapper;
import com.example.UserCeneter.common.strategy.OperatorStrategyEnum;
import com.example.UserCeneter.component.validator.ReqValidateManager;
import com.example.UserCeneter.dao.ArticleTagDao;
import com.example.UserCeneter.dao.UserDao;
import com.example.UserCeneter.model.*;
import com.example.UserCeneter.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String>
        implements UserService {

    @Autowired
    private ContextMapper contextMapper;

    @Autowired
    private ToolService toolService;

    @Autowired
    private ReqValidateManager reqValidateManager;

    @Autowired
    private RegisterRecordService registerRecordService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagDao articleTagDao;

    @Autowired
    private UserDao userDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return userDao;
    }

    @Override
    public ResponseResult beforeRegister(final RegisterRequest registerRequest) {

        // validate request parameter
        try {
            reqValidateManager.doExecute(registerRequest);
        } catch (Exception e) {
            return new ResponseResult(ResultCode.PARAM_IS_INVALID, e.getMessage());
        }

        // 策略模式，对于成功和失败，应进行不同策略strategy
        boolean isSend = toolService.sendRegisterMail(registerRequest);
        OperatorStrategyEnum strategyEnum = isSend
                    ? OperatorStrategyEnum.SUCCESS : OperatorStrategyEnum.EMAIL_FAIL;
        return contextMapper.loadProcessor(strategyEnum).doProcessor(registerRequest, strategyEnum);
    }

    @Override
    public boolean checkCaptcha(final RegisterRequest registerRequest) throws NullPointerException {
        String captcha = registerRecordService.getCaptcha(registerRequest.getUsername());
        return StringUtils.equals(captcha, registerRequest.getCaptcha());
    }

    @Override
    public ResponseResult register(final RegisterRequest registerRequest) {
        User user = userDao.findByUsername(registerRequest.getUsername());
        user.setVerified(Boolean.TRUE);
        userDao.save(user);
        initUserInfo(user);
        return new ResponseResult(ResultCode.REGISTERED_SUCCESS);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean checkVerified(User user) {
        return user != null && user.isVerified();
    }

    @Override
    public boolean checkPassword(User user, LoginRequest loginRequest) {
        return StringUtils.equals(user.getPasssword(), loginRequest.getPassword());
    }

    @Override
    public ResponseResult getAcountCenterInfo(String userId) {
        Optional<User> optionalUser = userDao.findById(userId);
        if (!optionalUser.isPresent()) {
            return new ResponseResult(ResultCode.USER_NOT_EXIST);
        }
        User user = optionalUser.get();
        UserCenterVOResponse userCenterVOResponse = new UserCenterVOResponse();
        userCenterVOResponse.setUsername(user.getUsername());
        Address address = addressService.findById(user.getId()).get();
        String provinceAndCity = (address.getProvince() == null ? "" : address.getProvince())
                + (address.getCity() == null ?  "" : address.getCity());
        userCenterVOResponse.setProvinceAndCity(provinceAndCity);
        userCenterVOResponse.setPersonalProfile(userProfileService.findById(userId).get().getPersonalProfile());
        userCenterVOResponse.setUserTagList(userTagService.getUserTagList(user.getId()));

        List<Article> articleList = articleService.getRecentArticles();
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleResponse articleResponse = new ArticleResponse();
            BeanUtils.copyProperties(article, articleResponse);

            List<String> articleTags = articleTagDao.findTagNamesByArticleId(article.getId());
            if (articleTags != null && articleTags.size() != 0) {
                articleResponse.setArticleTagList(articleTags);
            }
            articleResponseList.add(articleResponse);
        }
        userCenterVOResponse.setArticleList(articleResponseList);
        return new ResponseResult(ResultCode.SUCCESS, userCenterVOResponse);
    }

    private void initUserInfo(final User user) {
        String userId = user.getId();
        Address address = new Address();
        address.setUserId(userId);

        UserPreference userPreference = new UserPreference();
        userPreference.setUserId(userId);
        userPreference.setOtherUserMessageNotice("1");
        userPreference.setSysMessageNotice("1");
        userPreference.setTodoNotice("1");

//        UserTag userTag = new UserTag();
//        userTag.setId(userId);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);

        userPreferenceService.save(userPreference);
        addressService.save(address);
        userProfileService.save(userProfile);
//        userTagService.save(userTag);
    }
}
