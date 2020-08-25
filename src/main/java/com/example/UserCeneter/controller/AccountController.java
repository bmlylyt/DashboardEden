package com.example.UserCeneter.controller;

import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.common.utils.JwtTokenUtil;
import com.example.UserCeneter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("account")
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/center", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult center(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String header) {
        return userService.getAcountCenterInfo(JwtTokenUtil.getUserIdByAuthorHead(header));
    }
}
