package com.example.UserCeneter.service.impl;

import com.example.UserCeneter.dao.RegisterRecordDao;
import com.example.UserCeneter.model.RegisterRecord;
import com.example.UserCeneter.service.RegisterRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterRecordServiceImpl extends BaseServiceImpl<RegisterRecord, String>
        implements RegisterRecordService {

    @Autowired
    private RegisterRecordDao registerRecordDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return registerRecordDao;
    }

    @Override
    public String getCaptcha(String username) throws NullPointerException {
        RegisterRecord registerRecord = registerRecordDao.findByUsername(username);
        return registerRecord.getCaptcha();
    }
}
