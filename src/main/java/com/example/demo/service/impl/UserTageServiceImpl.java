package com.example.demo.service.impl;

import com.example.demo.dao.UserTagDao;
import com.example.demo.model.UserTag;
import com.example.demo.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserTageServiceImpl extends BaseServiceImpl<UserTag, String>
        implements UserTagService {

    @Autowired
    private UserTagDao userTagDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return userTagDao;
    }
}
