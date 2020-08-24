package com.example.demo.dao;

import com.example.demo.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTagDao extends JpaRepository<UserProfile, String> {

    @Query("select tagName from UserTag where userId=?1")
    List<String> getUserTagList(String userId);

}
