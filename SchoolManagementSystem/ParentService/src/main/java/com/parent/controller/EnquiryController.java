package com.parent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.parent.entity.Enquiry;
import com.parent.service.EnquiryService;

@RestController
@RequestMapping("/api/enquiry")
public class EnquiryController {
    @Autowired
    private EnquiryService enquiryService;

    @PostMapping("/submit")
    public void submitEnquiry(@RequestBody Enquiry enquiry) {
        enquiryService.saveEnquiry(enquiry);
    }
}

