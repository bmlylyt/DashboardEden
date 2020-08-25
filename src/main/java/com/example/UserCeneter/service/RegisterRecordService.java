package com.example.UserCeneter.service;

import com.example.UserCeneter.model.RegisterRecord;
import org.springframework.stereotype.Service;

@Service
public interface RegisterRecordService extends BaseService<RegisterRecord, String> {
    String getCaptcha(String username) throws NullPointerException;
}
