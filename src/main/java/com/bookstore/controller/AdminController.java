package com.bookstore.controller;

import com.bookstore.bean.Admin;
import com.bookstore.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @PostMapping("/saveadmin")
    public Admin saveAdmin(@RequestBody Admin admin){
        Admin adminRes = new Admin();
        try{
            adminRes = adminService.saveAdmin(admin);
        }catch (Exception e){
            logger.error("Exception while saving admin details :: "+e.getMessage());
        }
        return adminRes;
    }
}
