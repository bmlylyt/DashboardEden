package com.example.UserCeneter.service.impl;

import com.example.UserCeneter.dao.AddressDao;
import com.example.UserCeneter.model.Address;
import com.example.UserCeneter.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, String>
                                implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return addressDao;
    }
}
