package com.example.UserCeneter.service;

import com.example.UserCeneter.RPCDomain.req.LoginRequest;
import com.example.UserCeneter.RPCDomain.req.RegisterRequest;
import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseService<User, String> {

    ResponseResult beforeRegister(RegisterRequest registerRequest);

    boolean checkCaptcha(RegisterRequest registerRequest);

    ResponseResult register(RegisterRequest registerRequest);

    User getUserByUsername(String username);

    boolean checkVerified(User user);

    boolean checkPassword(User user, LoginRequest loginRequest);

    ResponseResult getAcountCenterInfo(String userIdByAuthorHead);
}
