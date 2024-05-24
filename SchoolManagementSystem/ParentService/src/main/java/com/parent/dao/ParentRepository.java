package com.parent.dao;

import org.springframework.data.jpa.repository.JpaRepository;



import com.parent.entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}

