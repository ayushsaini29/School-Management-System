package com.admin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.entity.Staff;
import com.admin.entity.StaffAttendance;
import com.admin.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminDashboardController {
	
    private final AdminService adminService;
	
    public AdminDashboardController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to Admin Dashboard!");
    }

    @GetMapping("/dashboard/about-us")
    public ResponseEntity<String> aboutUs() {
        return ResponseEntity.ok("Welcome to Our School! We provide quality education.");
    }

    @GetMapping("/dashboard/contact-us")
    public ResponseEntity<String> contactUs() {
        return ResponseEntity.ok("Contact us at email@example.com or call +1234567890");
    }

    @GetMapping("/dashboard/attendance")
    public ResponseEntity<String> attendanceRecords() {
        // Fetch attendance records, implement as needed
        return ResponseEntity.ok("Attendance records will be displayed here");
    }

    @GetMapping("/dashboard/reviews")
    public ResponseEntity<String> reviews() {
        // Return reviews, implement as needed
        return ResponseEntity.ok("Reviews will be displayed here");
    }

    @GetMapping("/dashboard/query")
    public ResponseEntity<String> query() {
        // Handle queries, implement as needed
        return ResponseEntity.ok("Submit your query here");
    }
    
    /************ Staff Details and Attendance ************/

    @GetMapping("/dashboard/staffdetails/{id}")
    public ResponseEntity<Staff> getStaffDetailsById(@PathVariable Long id) {
    	Staff staffDetails = adminService.getStaffById(id);
        return ResponseEntity.ok(staffDetails);
    }
    
    @GetMapping("/dashboard/staffdetails/search/")
    public ResponseEntity<ResponseEntity<List<Staff>>> getAllStaff(@RequestParam String schoolCode) {
    	ResponseEntity<List<Staff>> staffDetails = adminService.getAllStaff(schoolCode);
        return ResponseEntity.ok(staffDetails);
    }

    @PutMapping("/dashboard/staffdetails/updatedetails/{id}")
    public ResponseEntity<Staff> updateStaffsDetails(@PathVariable Long id, @RequestBody Staff staff) {
    	Staff staffDetails = adminService.updateStaffDetails(id, staff);   
        return ResponseEntity.status(HttpStatus.CREATED).body(staffDetails);
    }
    
    @PostMapping("/dashboard/attendance/staff/addstaffattendance")
    public ResponseEntity<StaffAttendance> addStaffAttendance(@RequestBody StaffAttendance staffAttendance) {
    	StaffAttendance addStaffAttendance = adminService.addStaffAttendance(staffAttendance);
        return ResponseEntity.ok(addStaffAttendance);
    }
    
    @GetMapping("/dashboard/attendance/staff/search/")
    public ResponseEntity<List<StaffAttendance>> getStaffAttendanceByDate(@RequestParam LocalDate date, @RequestParam String schoolCode) {
    	List<StaffAttendance> getAllStaffAttendance = adminService.getStaffAttendanceByDate(date, schoolCode);
        return ResponseEntity.ok(getAllStaffAttendance);
    }

    @PutMapping("/dashboard/attendance/staff/updateattendance/{id}")
    public ResponseEntity<StaffAttendance> updateStaffAttendance(@PathVariable Long id, @RequestBody boolean attendanceStatus, @RequestParam LocalDate date) {
    	StaffAttendance staffAttendance = adminService.updateStaffAttendanceById(id, attendanceStatus, date);
    	return ResponseEntity.ok(staffAttendance);
    }
}


