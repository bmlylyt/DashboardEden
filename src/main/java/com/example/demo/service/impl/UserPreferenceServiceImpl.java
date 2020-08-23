package com.example.demo.service.impl;

import com.example.demo.dao.UserPreferenceDao;
import com.example.demo.model.UserPreference;
import com.example.demo.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceServiceImpl extends BaseServiceImpl<UserPreference, String>
        implements UserPreferenceService {

    @Autowired
    private UserPreferenceDao userPreferenceDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return userPreferenceDao;
    }
}
