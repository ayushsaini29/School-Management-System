package com.parent.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parent.dao.CollegeRepository;
import com.parent.dao.SchoolRepository;
import com.parent.dao.UniversityRepository;
import com.parent.entity.College;
import com.parent.entity.School;
import com.parent.entity.University;

import java.util.List;

@Service
public class InstitutionService {
	
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public List<School> getTopRatedSchools() {
        return schoolRepository.findTop5ByOrderByRatingDesc();
    }

    public List<College> getTopRatedColleges() {
        return collegeRepository.findTop5ByOrderByRatingDesc();
    }

    public List<University> getTopRatedUniversities() {
        return universityRepository.findTop5ByOrderByRatingDesc();
    }
    
    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }

    public College saveCollege(College college) {
        return collegeRepository.save(college);
    }

    public University saveUniversity(University university) {
        return universityRepository.save(university);
    }
}

