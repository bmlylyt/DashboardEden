package com.example.demo.service.impl;

import com.example.demo.dao.RegisterRecordDao;
import com.example.demo.model.RegisterRecord;
import com.example.demo.service.RegisterRecordService;
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
