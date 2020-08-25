package com.example.UserCeneter.service.impl;

import com.example.UserCeneter.RPCDomain.req.RegisterRequest;
import com.example.UserCeneter.common.utils.RandomCaptcha;
import com.example.UserCeneter.service.MailService;
import com.example.UserCeneter.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private MailService mailService;

    @Override
    public String getCaptcha() {
        return null;
    }

    @Override
    public boolean sendRegisterMail(RegisterRequest registerRequest) {

        String captcha = RandomCaptcha.get();
        registerRequest.setCaptcha(captcha);

        StringBuilder content = new StringBuilder();
        content.append("Hello, ")
                .append(registerRequest.getUsername())
                .append(", your captcha is: ")
                .append(captcha);
        return mailService.sendSimpleMail(registerRequest.getEmail(), "User signed up", content.toString());
    }
}
