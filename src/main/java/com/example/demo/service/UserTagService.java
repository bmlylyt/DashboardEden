package com.example.demo.service;

import com.example.demo.model.UserTag;
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
