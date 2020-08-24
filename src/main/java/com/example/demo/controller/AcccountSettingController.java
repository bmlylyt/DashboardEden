package com.example.demo.controller;

import com.example.demo.RPCDomain.req.UserProfileRequest;
import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.common.utils.JwtTokenUtil;
import com.example.demo.model.UserPreference;
import com.example.demo.service.UserPreferenceService;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@RequestMapping("/account/settings")
public class AcccountSettingController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping(value = "/notice/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult showNotice(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.getNoticeByUserId(userId);
    }

    @PostMapping(value = "/notice/updates", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult updateNotice(@RequestBody UserPreference userPreference,
                                       @RequestHeader(name= JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.updateNoticeByUserId(userId, userPreference);
    }

    @PostMapping(value = "/profile/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult showProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headValue);
        return userProfileService.getUserProfileById(userId);
    }

    @PostMapping(value = "/profile/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult updateProfile(@RequestBody UserProfileRequest userProfileRequest,
                                        @RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headValue);
        return userProfileService.updateProfileByUserId(userId, userProfileRequest);
    }
}
