package com.example.demo.service;

import com.example.demo.model.RegisterRecord;
import org.springframework.stereotype.Service;

@Service
public interface RegisterRecordService extends BaseService<RegisterRecord, String> {
    String getCaptcha(String username) throws NullPointerException;
}
