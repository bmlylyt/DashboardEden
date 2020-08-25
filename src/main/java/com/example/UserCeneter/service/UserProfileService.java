package com.example.UserCeneter.service;

import com.example.UserCeneter.RPCDomain.req.UserProfileRequest;
import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.model.UserProfile;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService extends BaseService<UserProfile, String> {
    ResponseResult getUserProfileById(String userId);

    ResponseResult updateProfileByUserId(String userId, UserProfileRequest userProfileRequest);
}
