package com.example.demo.controller;

import com.example.demo.common.response.ResponseResult;
import com.example.demo.common.utils.JwtTokenUtil;
import com.example.demo.model.UserPreference;
import com.example.demo.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/account/settings")
public class AcccountSettingController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @PostMapping(value = "/notice/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult showNotice(@RequestHeader(name= JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.getNoticeByUserId(userId);
    }

    @RequestMapping(value = "/notice/updates", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult updateNotice(@RequestBody UserPreference userPreference,
                                       @RequestHeader(name= JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {

        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.updateNoticeByUserId(userId, userPreference);
    }
}
