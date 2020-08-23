package com.example.demo.service;

import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.model.UserPreference;
import org.springframework.stereotype.Service;

@Service
public interface UserPreferenceService extends BaseService<UserPreference, String>{
    ResponseResult getNoticeByUserId(String userId);

    ResponseResult updateNoticeByUserId(String userId, UserPreference userPreference);
}
