package com.example.demo.dao;

import com.example.demo.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceDao extends JpaRepository<UserPreference, String> {
}
