package com.bookstore.service;

import com.bookstore.bean.Admin;
import com.bookstore.dao.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

@FunctionalInterface
public interface AdminService{
    public Admin saveAdmin(Admin admin);
}
