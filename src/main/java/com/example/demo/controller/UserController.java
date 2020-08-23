package com.example.demo.controller;

import com.example.demo.RPCDomain.req.LoginRequest;
import com.example.demo.RPCDomain.req.RegisterRequest;
import com.example.demo.common.ResponseResult;
import com.example.demo.common.ResultCode;
import com.example.demo.common.utils.JwtTokenUtil;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "get/captcha", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult getCaptcha(@RequestBody RegisterRequest registerRequest) {
        return userService.beforeRegister(registerRequest);
    }

    @PostMapping(value = "register", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult register(@RequestBody RegisterRequest registerRequest) {
        try {
            if (!userService.checkCaptcha(registerRequest)) {
                return new ResponseResult(ResultCode.WRONG_CAPTCHA);
            }
        } catch (NullPointerException e) {
            return new ResponseResult(ResultCode.REGISTER_CODE_IS_EMPTY);
        }
        return userService.register(registerRequest);
    }

    @PostMapping(value = "login", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult login(HttpServletResponse httpServletResponse, @RequestBody LoginRequest loginRequest) {
        User user = userService.getUserByUsername(loginRequest.getUsername());
        if (!userService.checkVerified(user)) {
            return new ResponseResult(ResultCode.USER_INVALID);
        }
        if (userService.checkPassword(user, loginRequest)) {
            return new ResponseResult(ResultCode.WRONG_PASSWORD);
        }

        String token = JwtTokenUtil.createJWT(user.getUsername(), user.getId());
        logger.info("#####User: " + user.getUsername() +" logged in");

        httpServletResponse.setHeader(JwtTokenUtil.AUTH_HEADER_KEY,
                JwtTokenUtil.TOKEN_PREFIX + token);

        Map<String, String> map = new HashMap<>();
        map.put("token", JwtTokenUtil.TOKEN_PREFIX + token);

        return new ResponseResult(ResultCode.SUCCESS, map);
    }

}