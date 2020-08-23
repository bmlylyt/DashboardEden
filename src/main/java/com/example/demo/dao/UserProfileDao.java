package com.example.demo.dao;

import com.example.demo.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, String> {
}
