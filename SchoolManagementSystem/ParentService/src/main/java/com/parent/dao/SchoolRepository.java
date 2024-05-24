package com.parent.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parent.entity.School;


public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findTop5ByOrderByRatingDesc();
}

