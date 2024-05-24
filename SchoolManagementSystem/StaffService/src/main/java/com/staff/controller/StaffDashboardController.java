package com.staff.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

import com.staff.entity.Staff;
import com.staff.entity.StaffAttendance;
import com.staff.entity.Student;
import com.staff.entity.StudentAttendance;
import com.staff.service.StaffAttendanceService;
import com.staff.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffDashboardController {

    private final StaffService staffService;

    private final StaffAttendanceService staffAttendanceService;

    public StaffDashboardController(StaffService staffService, StaffAttendanceService staffAttendanceService) {
    	this.staffAttendanceService = staffAttendanceService;
        this.staffService = staffService;
    }

    @GetMapping("/dashboard/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to Staff Dashboard!");
    }

    @GetMapping("/dashboard/about-us")
    public ResponseEntity<String> aboutUs() {
        return ResponseEntity.ok("Welcome to Our School! We provide quality education.");
    }

    @GetMapping("/dashboard/contact-us")
    public ResponseEntity<String> contactUs() {
        return ResponseEntity.ok("Contact us at email@example.com or call +1234567890");
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
    
    /************ Student Details and Attendance ************/

    @GetMapping("/dashboard/studentdetails/{id}")
    public ResponseEntity<Student> getStudentDetailsById(@PathVariable Long id) {
        Student studentDetails = staffService.getStudentById(id);
        return ResponseEntity.ok(studentDetails);
    }
    
    @GetMapping("/dashboard/studentdetails/")
    public ResponseEntity<ResponseEntity<List<Student>>> getAllStudentDetailsBySchoolCode(@RequestParam String schoolCode) {
        ResponseEntity<List<Student>> allStudentDetails = staffService.getAllStudents(schoolCode);
        return ResponseEntity.ok(allStudentDetails);
    }
    
    @GetMapping("/dashboard/studentdetails/search/")
    public ResponseEntity<List<Student>> getStudentByStandard(@RequestParam String standard, @RequestParam String section, @RequestParam String schoolCode) {
    	List<Student> studentDetails = staffService.getStudentsByStandard(standard, section, schoolCode);
        return ResponseEntity.ok(studentDetails);
    }

    @PutMapping("/dashboard/studentdetails/updatedetails/{id}")
    public ResponseEntity<Student> updateStudentDetails(@PathVariable Long id, @RequestBody Student student) {
        Student studentDetails = staffService.updateStudentDetails(id, student);   
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDetails);
    }
    
    @PostMapping("/dashboard/attendance/student/addStudentAttendance")
    public ResponseEntity<StudentAttendance> addStudentAttendance(@RequestBody StudentAttendance studentAttendance) {
    	StudentAttendance addStudentAttendance = staffService.addStudentAttendance(studentAttendance);
        return ResponseEntity.ok(addStudentAttendance);
    }
    
    @GetMapping("/dashboard/attendance/student/search/")
    public ResponseEntity<List<StudentAttendance>> getStudentAttendanceByStandardAndDate(@RequestParam String standard, @RequestParam String section, @RequestParam LocalDate date, @RequestParam String schoolCode) {
    	List<StudentAttendance> getAllstudentDetails = staffService.getStudentAttendanceByStandardAndDate(standard, section, date, schoolCode);
        return ResponseEntity.ok(getAllstudentDetails);
    }

    @PutMapping("/dashboard/attendance/student/updateattendance/{id}")
    public ResponseEntity<StudentAttendance> updateStudentAttendance(@PathVariable Long id, @RequestBody boolean attendanceStatus, @RequestParam LocalDate date) {
    	StudentAttendance studentAttendance = staffService.updateStudentAttendanceById(id, attendanceStatus, date);
    	return ResponseEntity.ok(studentAttendance);
    }
    
    /************* Staff Details and Attendance *************/
    
    @PostMapping("/details/addstaff")
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff) {
    	Staff addStaff = staffService.saveStaff(staff);
        return ResponseEntity.ok(addStaff);					
    }
    
    @GetMapping("/details/allstaff/")
    public ResponseEntity<List<Staff>> getallstaffDetails(@RequestParam String schoolCode) {
        List<Staff> allstaffDetails = staffService.getAllStaff(schoolCode);
        return ResponseEntity.ok(allstaffDetails);//.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        						
    }
   
    @GetMapping("/details/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Staff staffDetails = staffService.getStaffById(id);
        return ResponseEntity.ok(staffDetails);
    }
    
    @PutMapping("/details/updatedetails/{id}")
    public ResponseEntity<ResponseEntity<Staff>> updateStaffDetails(@PathVariable Long id, @RequestBody Staff staff) {
        ResponseEntity<Staff> updateStaffDetails = staffService.updateStaffById(id, staff);
        return ResponseEntity.ok(updateStaffDetails);
    }
    
    @PostMapping("/attendance/addstaffattendance")
    public ResponseEntity<StaffAttendance> addStaffAttendance(@RequestBody StaffAttendance staffAttendance) {
    	StaffAttendance addStaffAttendance= staffAttendanceService.addStaffAttendance(staffAttendance);
        return ResponseEntity.ok(addStaffAttendance);					
    }
    
    @GetMapping("/attendance")
    public ResponseEntity<List<StaffAttendance>> getStaffAttendanceByDate(@RequestParam String schoolCode, @RequestParam LocalDate date) {
        List<StaffAttendance> staffAttendance = staffAttendanceService.getStaffAttendanceByDate(schoolCode, date);
        return ResponseEntity.ok(staffAttendance);
    }
    
    @PutMapping("/attendance/updatestaffattendance/{id}")
    public ResponseEntity<ResponseEntity<StaffAttendance>> updateStaffAttendance(@PathVariable Long id, @RequestBody boolean attendanceStatus, @RequestParam LocalDate date) {
    	ResponseEntity<StaffAttendance> updateStaffAttendance = staffAttendanceService.updateStaffAttendanceById(id, attendanceStatus, date);
        return ResponseEntity.ok(updateStaffAttendance);
    }
}


