package com.example.demo.service;

import com.example.demo.RPCDomain.req.UserProfileRequest;
import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.model.UserProfile;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService extends BaseService<UserProfile, String> {
    ResponseResult getUserProfileById(String userId);

    ResponseResult updateProfileByUserId(String userId, UserProfileRequest userProfileRequest);
}
