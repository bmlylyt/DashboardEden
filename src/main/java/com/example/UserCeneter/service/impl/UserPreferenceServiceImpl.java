package com.example.UserCeneter.service.impl;

import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.common.ResultCode;
import com.example.UserCeneter.RPCDomain.response.UserPreferenceResponse;
import com.example.UserCeneter.dao.UserPreferenceDao;
import com.example.UserCeneter.model.User;
import com.example.UserCeneter.model.UserPreference;
import com.example.UserCeneter.service.UserPreferenceService;
import com.example.UserCeneter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPreferenceServiceImpl extends BaseServiceImpl<UserPreference, String>
        implements UserPreferenceService {

    @Autowired
    private UserPreferenceDao userPreferenceDao;

    @Autowired
    private UserService userService;

    @Override
    protected JpaRepository getJpaRepository() {
        return userPreferenceDao;
    }

    @Override
    public ResponseResult getNoticeByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return new ResponseResult(ResultCode.PARAM_IS_BLANK);
        }
        Optional<User> userOptional = userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseResult(ResultCode.USER_NOT_EXIST);
        }
        UserPreference userPreference = userPreferenceDao.findByUserId(userId);
        UserPreferenceResponse userPreferenceRes = new UserPreferenceResponse();
        BeanUtils.copyProperties(userPreference, userPreferenceRes);
        return new ResponseResult(ResultCode.SUCCESS, userPreferenceRes);
    }

    @Override
    public ResponseResult updateNoticeByUserId(String userId, UserPreference userPreferenceReq) {
        UserPreference userPreference = new UserPreference();
        BeanUtils.copyProperties(userPreferenceReq, userPreference);
        userPreference.setUserId(userId);
        userPreferenceDao.save(userPreference);
        return new ResponseResult(ResultCode.SUCCESS);
    }
}
