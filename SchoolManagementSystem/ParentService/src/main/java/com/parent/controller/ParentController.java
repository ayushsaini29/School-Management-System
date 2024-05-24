package com.parent.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parent.entity.College;
import com.parent.entity.Parent;
import com.parent.entity.School;
import com.parent.entity.University;
import com.parent.service.InstitutionService;
import com.parent.service.ParentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parent")
public class ParentController {
	
    @Autowired
    private InstitutionService institutionService;
    
    @Autowired
    private ParentService parentService;

    @GetMapping("/top-rated-schools")
    public ResponseEntity<List<School>> getTopRatedSchools() {
        List<School> schools = institutionService.getTopRatedSchools();
        return ResponseEntity.ok(schools);
    }

    @GetMapping("/top-rated-colleges")
    public ResponseEntity<List<College>> getTopRatedColleges() {
        List<College> colleges = institutionService.getTopRatedColleges();
        return ResponseEntity.ok(colleges);
    }

    @GetMapping("/top-rated-universities")
    public ResponseEntity<List<University>> getTopRatedUniversities() {
        List<University> universities = institutionService.getTopRatedUniversities();
        return ResponseEntity.ok(universities);
    }

    @GetMapping("/awards-certifications")
    public ResponseEntity<String> getAwardsCertifications() {
        String awardsCertifications = "List of Awards & Certifications";
        return ResponseEntity.ok(awardsCertifications);
    }

    @GetMapping("/reviews-ratings")
    public ResponseEntity<String> getReviewsRatings() {
        String reviewsRatings = "List of Reviews & Ratings";
        return ResponseEntity.ok(reviewsRatings);
    }

    @GetMapping("/contact-us")
    public ResponseEntity<String> getContactUs() {
        String contactUs = "Contact us at email@example.com or call +1234567890";
        return ResponseEntity.ok(contactUs);
    }

    @GetMapping("/career")
    public ResponseEntity<String> getCareer() {
        String career = "Career opportunities";
        return ResponseEntity.ok(career);
    }

    @GetMapping("/support")
    public ResponseEntity<String> getSupport() {
        String support = "Support information";
        return ResponseEntity.ok(support);
    }
    
    // Save operations for School, College, University entities
    @PostMapping("/addschool")
    public ResponseEntity<School> addSchool(@RequestBody School school) {
        School savedSchool = institutionService.saveSchool(school);
        return ResponseEntity.ok(savedSchool);
    }

    @PostMapping("/addcolleges")
    public ResponseEntity<College> addCollege(@RequestBody College college) {
        College savedCollege = institutionService.saveCollege(college);
        return ResponseEntity.ok(savedCollege);
    }

    @PostMapping("/adduniversity")
    public ResponseEntity<University> addUniversity(@RequestBody University university) {
        University savedUniversity = institutionService.saveUniversity(university);
        return ResponseEntity.ok(savedUniversity);
    }
    
    // CRUD operations for Parent entity

    @GetMapping("/parents")
    public ResponseEntity<List<Parent>> getAllParents() {
        List<Parent> parents = parentService.getAllParents();
        return ResponseEntity.ok(parents);
    }

    @GetMapping("/parents/{id}")
    public ResponseEntity<Parent> getParentById(@PathVariable Long id) {
        Optional<Parent> parent = parentService.getParentById(id);
        return parent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/parents")
    public ResponseEntity<Parent> addParent(@RequestBody Parent parent) {
        Parent savedParent = parentService.addParent(parent);
        return ResponseEntity.ok(savedParent);
    }

    @PutMapping("/parents/{id}")
    public ResponseEntity<Parent> updateParent(@PathVariable Long id, @RequestBody Parent parentDetails) {
        Parent updatedParent = parentService.updateParent(id, parentDetails);
        return ResponseEntity.ok(updatedParent);
    }

    @DeleteMapping("/parents/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}

