package com.staff.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.staff.dao.StaffAttendanceRepository;
import com.staff.entity.StaffAttendance;

@Service
public class StaffAttendanceService {
	
    private final StaffAttendanceRepository staffAttendanceRepository;
    
    public StaffAttendanceService(StaffAttendanceRepository staffAttendanceRepository) {
        this.staffAttendanceRepository = staffAttendanceRepository;
    }
    
    public StaffAttendance addStaffAttendance(StaffAttendance staffAttendance) {
        return staffAttendanceRepository.save(staffAttendance);
    }
    
    public List<StaffAttendance> getStaffAttendanceByDate(String schoolCode, LocalDate date ) {
        return staffAttendanceRepository.getStaffAttendanceByDate(schoolCode, date)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid School Code: " + schoolCode));
    }
    
    public ResponseEntity<StaffAttendance> updateStaffAttendanceById(Long id, boolean attendanceStatus, LocalDate date) {
    	
    	StaffAttendance staffAttendance = staffAttendanceRepository.findById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid Staff id" + id));
    	
    	if (id.equals(staffAttendance.getId())) {
    		
    		staffAttendance.setAttendanceStatus(attendanceStatus);
    		staffAttendance.setDate(date);
  	      
  		    return ResponseEntity.ok(staffAttendanceRepository.save(staffAttendance));
      	} else {
      		return ResponseEntity.notFound().build();
      	}
    }

}
