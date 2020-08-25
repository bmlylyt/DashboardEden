package com.example.UserCeneter.service;

import com.example.UserCeneter.RPCDomain.req.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface ToolService {

    String getCaptcha();

    boolean sendRegisterMail(RegisterRequest registerRequest);

}
