package com.example.demo.service;

import com.example.demo.RPCDomain.req.LoginRequest;
import com.example.demo.RPCDomain.req.RegisterRequest;
import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseService<User, String> {

    ResponseResult beforeRegister(RegisterRequest registerRequest);

    boolean checkCaptcha(RegisterRequest registerRequest);

    ResponseResult register(RegisterRequest registerRequest);

    User getUserByUsername(String username);

    boolean checkVerified(User user);

    boolean checkPassword(User user, LoginRequest loginRequest);
}
