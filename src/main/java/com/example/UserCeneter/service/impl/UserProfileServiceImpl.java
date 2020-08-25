package com.example.UserCeneter.service.impl;

import com.example.UserCeneter.RPCDomain.req.UserProfileRequest;
import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.RPCDomain.response.UserProfileResponse;
import com.example.UserCeneter.common.ResultCode;
import com.example.UserCeneter.dao.UserDao;
import com.example.UserCeneter.dao.UserProfileDao;
import com.example.UserCeneter.model.Address;
import com.example.UserCeneter.model.User;
import com.example.UserCeneter.model.UserPreference;
import com.example.UserCeneter.model.UserProfile;
import com.example.UserCeneter.service.AddressService;
import com.example.UserCeneter.service.UserPreferenceService;
import com.example.UserCeneter.service.UserProfileService;
import com.example.UserCeneter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, String>
        implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Override
    protected JpaRepository getJpaRepository() {
        return userProfileDao;
    }

    @Override
    public ResponseResult getUserProfileById(String userId) {
        Optional<User> userOptional = userDao.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseResult(ResultCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        Address address = addressService.findById(user.getId()).get();
        UserPreference userPreference = userPreferenceService.findById(user.getId()).get();
        UserProfile userProfile = userProfileDao.findById(user.getId()).get();
        UserProfileResponse userProfileResponse = new UserProfileResponse();

        BeanUtils.copyProperties(user, userProfileResponse);
        BeanUtils.copyProperties(userPreference, userProfileResponse);
        BeanUtils.copyProperties(address, userProfileResponse);
        BeanUtils.copyProperties(userProfile, userProfileResponse);

        return new ResponseResult(ResultCode.SUCCESS, userProfileResponse);
    }

    @Override
    public ResponseResult updateProfileByUserId(String userId, UserProfileRequest userProfileRequest) {
        Optional<User> userOptional = userDao.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseResult(ResultCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        Address address = addressService.findById(user.getId()).get();
        UserPreference userPreference = userPreferenceService.findById(user.getId()).get();
        UserProfile userProfile = userProfileDao.findById(user.getId()).get();

        BeanUtils.copyProperties(userProfileRequest, user);
        BeanUtils.copyProperties(userProfileRequest, userPreference);
        BeanUtils.copyProperties(userProfileRequest, address);
        BeanUtils.copyProperties(userProfileRequest, userProfile);

        userService.save(user);
        userPreferenceService.save(userPreference);
        addressService.save(address);
        userProfileDao.save(userProfile);

        return new ResponseResult(ResultCode.SUCCESS);
    }
}
