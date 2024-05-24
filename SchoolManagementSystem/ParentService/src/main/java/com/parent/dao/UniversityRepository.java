package com.parent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parent.entity.University;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findTop5ByOrderByRatingDesc();
}

