package com.example.UserCeneter.service;

import org.springframework.stereotype.Service;

@Service
public interface MailService {

    boolean sendSimpleMail(String to, String subjct, String content);

}
