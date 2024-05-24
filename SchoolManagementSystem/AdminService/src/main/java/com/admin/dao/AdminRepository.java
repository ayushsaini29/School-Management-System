package com.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Add custom query methods if needed
}

