package com.example.demo.dao;

import com.example.demo.model.RegisterRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

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
