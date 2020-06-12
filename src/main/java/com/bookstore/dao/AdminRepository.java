package com.bookstore.dao;

import com.bookstore.bean.Admin;
import com.bookstore.bean.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Query("SELECT a FROM Admin a WHERE a.name = ?1")
    Admin findAdminByName(String adminName);

}
