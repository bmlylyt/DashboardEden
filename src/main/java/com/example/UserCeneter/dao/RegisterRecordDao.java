package com.example.UserCeneter.dao;

import com.example.UserCeneter.model.RegisterRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRecordDao extends JpaRepository<RegisterRecord, String> {

    /**
     * Search based on email
     * @param email
     * @return
     */
    List<RegisterRecord> findByEmail(String email);

    /**
     * Search based on username
     * @param username
     * @return
     */
    RegisterRecord findByUsername(String username);
}
