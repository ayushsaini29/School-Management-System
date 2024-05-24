package com.parent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parent.entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
}


