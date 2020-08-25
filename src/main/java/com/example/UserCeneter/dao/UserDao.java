package com.example.UserCeneter.dao;

import com.example.UserCeneter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {

    /**
     * Get user entity by username
     * @param username
     * @return
     */
    User findByUsername(String username);

}
