package com.example.demo.service.impl;

import com.example.demo.dao.UserProfileDao;
import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, String>
        implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return userProfileDao;
    }
}
