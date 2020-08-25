package com.example.UserCeneter.service;

import com.example.UserCeneter.model.UserTag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserTagService extends BaseService<UserTag, String> {
    /**
     * 获取用户标签
     *
     * @param userId
     * @return
     */
    List<String> getUserTagList(String userId);
}
