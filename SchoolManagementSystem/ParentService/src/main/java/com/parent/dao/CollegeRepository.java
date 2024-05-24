package com.parent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parent.entity.College;

import java.util.List;

public interface CollegeRepository extends JpaRepository<College, Long> {
    List<College> findTop5ByOrderByRatingDesc();
}


