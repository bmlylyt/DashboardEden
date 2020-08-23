package com.example.demo.service.impl;

import com.example.demo.RPCDomain.req.LoginRequest;
import com.example.demo.RPCDomain.req.RegisterRequest;
import com.example.demo.common.response.ResponseResult;
import com.example.demo.common.ResultCode;
import com.example.demo.common.strategy.ContextMapper;
import com.example.demo.common.strategy.OperatorStrategyEnum;
import com.example.demo.component.validator.ReqValidateManager;
import com.example.demo.dao.UserDao;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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

//    @Autowired
//    private UserTagService userTagService;

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
