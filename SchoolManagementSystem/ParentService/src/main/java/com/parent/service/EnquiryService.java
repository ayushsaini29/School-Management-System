package com.parent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.parent.dao.EnquiryRepository;
import com.parent.entity.Enquiry;

@Service
public class EnquiryService {
    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private JavaMailSender mailSender;

    private static final String ADMIN_EMAIL = "ayushtest1129@gmail.com";

    public void saveEnquiry(Enquiry enquiry) {
        enquiryRepository.save(enquiry);
        sendEmail(enquiry);
    }

    private void sendEmail(Enquiry enquiry) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(ADMIN_EMAIL);
            message.setSubject("New Enquiry Received");
            message.setText("Name: " + enquiry.getName() + "\n" +
                            "Mobile: " + enquiry.getMobile() + "\n" +
                            "Email: " + enquiry.getEmail() + "\n" +
                            "Enquiry: " + enquiry.getEnquiryText());
            mailSender.send(message);
        } catch (MailException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}

