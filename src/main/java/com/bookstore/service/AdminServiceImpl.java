package com.bookstore.service;

import com.bookstore.bean.Admin;
import com.bookstore.dao.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin saveAdmin(Admin admin) {
        Admin adminResponse = null;
        try{
            adminResponse = adminRepository.save(admin);
        }catch (Exception e){
            logger.error("Exception while saving admin details :: "+e.getMessage());
        }
        return adminResponse;
    }
}
