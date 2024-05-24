package com.student.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.student.dao.StudentAttendanceRepository;
import com.student.entity.StudentAttendance;

@Service
public class StudentAttendanceService {
	
    private final StudentAttendanceRepository studentAttendanceRepository;
    
    public StudentAttendanceService(StudentAttendanceRepository studentAttendanceRepository) {
        this.studentAttendanceRepository = studentAttendanceRepository;
    }
    
    public StudentAttendance addStudentAttendance(StudentAttendance studentAttendance) {
        return studentAttendanceRepository.save(studentAttendance);
    }
    
    public List<StudentAttendance> getStudentAttendanceByStandardAndDate(String standard, String section, String schoolCode, LocalDate date ) {
    	return studentAttendanceRepository.getStudentAttendanceByStandardAndDate(standard, section, schoolCode, date)
			.orElseThrow(() -> new IllegalArgumentException("Invalid School Code: " + schoolCode));
    }
    
    public ResponseEntity<StudentAttendance> updateStudentAttendanceById(Long id, boolean attendanceStatus, LocalDate date) {
    	
    	StudentAttendance stdAttendance = studentAttendanceRepository.findById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid Student id" + id));
    	
    	if (id.equals(stdAttendance.getId())) {
    		
    		stdAttendance.setAttendanceStatus(attendanceStatus);
    		stdAttendance.setDate(date);
  	      
  		    return ResponseEntity.ok(studentAttendanceRepository.save(stdAttendance));
      	} else {
      		return ResponseEntity.notFound().build();
      	}
    }

}
