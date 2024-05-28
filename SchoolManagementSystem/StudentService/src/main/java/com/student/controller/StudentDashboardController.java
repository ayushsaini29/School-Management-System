package com.student.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.entity.StudentAttendance;
import com.student.service.StudentAttendanceService;
import com.student.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/student")
@Tag(name = "Student Controller")
public class StudentDashboardController {
    
    private final StudentService studentService;
    private final StudentAttendanceService studentAttendanceService;

    public StudentDashboardController(StudentService studentService, StudentAttendanceService studentAttendanceService) {
        this.studentService = studentService;
        this.studentAttendanceService = studentAttendanceService;
    }
    
    @GetMapping("/dashboard/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to Student Dashboard!");
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
    
    /************ Student Details and Attendance **************/
    
    @PostMapping("/details/addstudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student addStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(addStudent);					
    } 
    
    
    @GetMapping("/details/allstudent/")
    public ResponseEntity<List<Student>> getAllStudentBySchool(@RequestParam String schoolCode) {
        List<Student> allStudentDetails = studentService.getAllStudentBySchool(schoolCode);
        return ResponseEntity.ok(allStudentDetails);
        						
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student studentDetails = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDetails);
    }
    
    @GetMapping("/details/")
    public ResponseEntity<List<Student>> getStudentsByStandard(@RequestParam String standard, @RequestParam String section,  @RequestParam String schoolCode) {
        List<Student> studentDetails = studentService.getStudentsByStandard(standard, section, schoolCode);
        return ResponseEntity.ok(studentDetails);
    }
    
    @PutMapping("/details/updatedetails/{id}")
    public ResponseEntity<ResponseEntity<Student>> updateStudentDetails(@PathVariable Long id, @RequestBody Student student) {
        ResponseEntity<Student> updateStudentDetails = studentService.updateStudentById(id, student);
        return ResponseEntity.ok(updateStudentDetails);
    }
    
    @PostMapping("/attendance/addstudentattendance")
    public ResponseEntity<StudentAttendance> addStudentAttendance(@RequestBody StudentAttendance studentAttendance) {
    	StudentAttendance addStudentAttendance = studentAttendanceService.addStudentAttendance(studentAttendance);
        return ResponseEntity.ok(addStudentAttendance);
    }  
    
    @GetMapping("/attendance/")
    public ResponseEntity<List<StudentAttendance>> getStudentAttendanceByStandard(@RequestParam String standard, @RequestParam String section, @RequestParam String schoolCode, @RequestParam LocalDate date) {
        List<StudentAttendance> studentDetails = studentAttendanceService.getStudentAttendanceByStandardAndDate(standard, section, schoolCode, date);
        return ResponseEntity.ok(studentDetails);
    }
    
    @PutMapping("/attendance/updatestudentattendance/{id}")
    public ResponseEntity<ResponseEntity<StudentAttendance>> updateStudentAttendance(@PathVariable Long id, @RequestBody boolean attendanceStatus, @RequestParam LocalDate date) {
    	ResponseEntity<StudentAttendance> studentAttendance = studentAttendanceService.updateStudentAttendanceById(id, attendanceStatus, date);
        return ResponseEntity.ok(studentAttendance);
    }
}


