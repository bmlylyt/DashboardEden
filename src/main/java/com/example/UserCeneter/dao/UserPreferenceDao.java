package com.example.UserCeneter.dao;

import com.example.UserCeneter.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceDao extends JpaRepository<UserPreference, String> {

    UserPreference findByUserId(String userId);
}
