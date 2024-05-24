package com.staff.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

import com.staff.dao.StaffRepository;
import com.staff.entity.Staff;
import com.staff.entity.Student;
import com.staff.entity.StudentAttendance;

import jakarta.ws.rs.HttpMethod;

@Service
public class StaffService {
    
    private final RestClient restClient;
    
    private final StaffRepository staffRepository;

    public StaffService(RestClient restClient, StaffRepository staffRepository ) {
        this.restClient = restClient;
        this.staffRepository = staffRepository;
    }
   
    /********** Student Details and Attendance ***********/

    public ResponseEntity<List<Student>> getAllStudents(String schoolCode) {
    	List<Student> studentDetails = restClient.get()
    		    .uri(uriBuilder -> uriBuilder
    		    		.path("/api/student/details/allstudent/")
    		            .queryParam("schoolCode", schoolCode)
    		            .build())
        		.retrieve()
        		.body(new ParameterizedTypeReference<List<Student>>() {});
		return ResponseEntity.ok(studentDetails);
    }

    public Student getStudentById(Long id) {
    	Student studentDetailsById = restClient.get()
        		.uri("/api/student/details/{id}", id)
        		.retrieve()
        		.body(Student.class);
		return studentDetailsById;
    }
    
    public List<Student> getStudentsByStandard(String standard, String section, String schoolCode) {
    	List<Student> studentDetails = restClient.get()
        		.uri(uriBuilder -> uriBuilder
        				.path("/api/student/details/")
        				.queryParam("standard", standard)
        		        .queryParam("section", section)
        		        .queryParam("schoolCode", schoolCode)
        		        .build())
        		.retrieve()
        		.body(new ParameterizedTypeReference<List<Student>>() {});
		return studentDetails;
    }

    public Student updateStudentDetails(Long id,  Student studentDetails) {   	
    	Student updateStudentDetails = restClient.put()
        		.uri("/api/student/details/updatedetails/{id}", id)
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(studentDetails)
        		.retrieve()
        		.body(Student.class);
//        		.toBodilessEntity();
		return updateStudentDetails;
    }
    
    public StudentAttendance addStudentAttendance(StudentAttendance studentAttendance) {   	
    	StudentAttendance stuAttendance = restClient.post()
        		.uri("/api/student/attendance/addstudentattendance")
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(studentAttendance)
        		.retrieve()
        		.body(StudentAttendance.class);
//        		.toBodilessEntity();
		return stuAttendance;
    }
     
    public List<StudentAttendance> getStudentAttendanceByStandardAndDate(String standard, String section, LocalDate date, String schoolCode) {
    	List<StudentAttendance> studentAttendance = restClient.get()
        		.uri(uriBuilder -> uriBuilder
        				.path("/api/student/attendance/")
        				.queryParam("standard", standard)
        		        .queryParam("section", section)
        		        .queryParam("schoolCode", schoolCode)
        		        .queryParam("date", date)
        		        .build())
        		.retrieve()
        		.body(new ParameterizedTypeReference<List<StudentAttendance>>() {});
		return studentAttendance;
    }

    public StudentAttendance updateStudentAttendanceById(Long id, boolean attendanceStatus, LocalDate date) {

    	StudentAttendance updateAttendanceDetail = restClient.put()
        		.uri(uriBuilder -> uriBuilder
        				.path("/api/student/attendance/updatestudentattendance/"+ id)
       		         	.queryParam("date", date)
       		         	.build())
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(attendanceStatus)
        		.retrieve()
        		.body(StudentAttendance.class);
		return updateAttendanceDetail;
    }
    
    /************ Staff Details and Attendance ************/
    
    public List<Staff> getAllStaff(String schoolCode) {
        return staffRepository.getAllStaff(schoolCode)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid School Code: " + schoolCode));
    }

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
            	.orElseThrow(() -> new IllegalArgumentException("Invalid Student id: " + id));
    }

    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }
    
    public ResponseEntity<Staff> updateStaffById(Long id, Staff staffDetails) {
    	Staff staff = staffRepository.findById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid Staff Employee Id" + id));
    	if (id.equals(staff.getId())) {
//    		staff.setName(staffDetails.getName());
//    		staff.setId(staffDetails.getId());
//    		staff.setPosition(staffDetails.getPosition());
//    		staff.setSubject(staffDetails.getSubject());
//    		staff.setPhoneNumber(staffDetails.getPhoneNumber());
//    		staff.setClassTeacher(staffDetails.getClassTeacher());
//    		staff.setClassTimeTable(staffDetails.getClassTimeTable());
            if (StringUtils.hasText(staffDetails.getName())) {
            	staff.setName(staffDetails.getName());
            }
            if (staffDetails.getId() != null) {
            	staff.setId(staffDetails.getId());
            }
            if (StringUtils.hasText(staffDetails.getPosition())) {
            	staff.setPosition(staffDetails.getPosition());
            }
            if (StringUtils.hasText(staffDetails.getSubject())) {
            	staff.setSubject(staffDetails.getSubject());
            }
            if (staffDetails.getPhoneNumber() != null) {
            	staff.setPhoneNumber(staffDetails.getPhoneNumber());
            }
            if (StringUtils.hasText(staffDetails.getClassTeacher())) {
            	staff.setClassTeacher(staffDetails.getClassTeacher());
            }
            if (staffDetails.getClassTimeTable() != null) {
            	staff.setClassTimeTable(staffDetails.getClassTimeTable());
            }
  		  return ResponseEntity.ok(staffRepository.save(staff));
      	} else {
      		return ResponseEntity.notFound().build();
      	}
    }

    public String deleteStaffById(Long id) {
    	staffRepository.findById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid Staff Employee Id" + id));
    	if (id.equals(id)) {
    		staffRepository.deleteById(id);
    		return "Staff Record Deleted Successsfully" + id;
    	} else {
    		return "Staff Record is not deleted " + id;
    	}

    }
}

