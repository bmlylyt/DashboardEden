package com.example.UserCeneter.service;

import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.model.UserPreference;
import org.springframework.stereotype.Service;

@Service
public interface UserPreferenceService extends BaseService<UserPreference, String>{
    ResponseResult getNoticeByUserId(String userId);

    ResponseResult updateNoticeByUserId(String userId, UserPreference userPreference);
}
