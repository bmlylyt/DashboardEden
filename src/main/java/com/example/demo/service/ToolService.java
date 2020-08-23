package com.example.demo.service;

import com.example.demo.RPCDomain.req.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface ToolService {

    String getCaptcha();

    boolean sendRegisterMail(RegisterRequest registerRequest);

}
