package com.example.demo.dao;

import com.example.demo.model.User;
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
