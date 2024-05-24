package com.admin.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.admin.dao.AdminRepository;
import com.admin.entity.Admin;
import com.admin.entity.Staff;
import com.admin.entity.StaffAttendance;

@Service
public class AdminService {
    
    private final AdminRepository adminRepository;
    private final RestClient restClient;

    public AdminService(AdminRepository adminRepository, RestClient restClient) {
        this.restClient = restClient;
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }
    
    /******** Staff Details and Attendance *********/

    public ResponseEntity<List<Staff>> getAllStaff(String schoolCode) {
    	List<Staff> staffDetails = restClient.get()
    		    .uri(uriBuilder -> uriBuilder
    		    		.path("/api/staff/details/allstaff/")
    		            .queryParam("schoolCode", schoolCode)
    		            .build())
        		.retrieve()
        		.body(new ParameterizedTypeReference<List<Staff>>() {});
		return ResponseEntity.ok(staffDetails);
    }

    public Staff getStaffById(Long id) {
    	Staff staffDetailsById = restClient.get()
        		.uri("/api/staff/details/{id}", id)
        		.retrieve()
        		.body(Staff.class);
		return staffDetailsById;
    }

    public Staff updateStaffDetails(Long id,  Staff staffDetails) {   	
    	Staff updateStaffDetails = restClient.put()
        		.uri("/api/staff/details/updatedetails/{id}", id)
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(staffDetails)
        		.retrieve()
        		.body(Staff.class);
//        		.toBodilessEntity();
		return updateStaffDetails;
    }
    
    public StaffAttendance addStaffAttendance(StaffAttendance staffsAttendance) {   	
    	StaffAttendance staffAttendance = restClient.post()
        		.uri("/api/staff/attendance/addstaffattendance")
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(staffsAttendance)
        		.retrieve()
        		.body(StaffAttendance.class);
//        		.toBodilessEntity();
		return staffAttendance;
    }
     
    public List<StaffAttendance> getStaffAttendanceByDate(LocalDate date, String schoolCode) {
    	List<StaffAttendance> staffAttendance = restClient.get()
        		.uri(uriBuilder -> uriBuilder
        				.path("/api/staff/attendance")
        		        .queryParam("schoolCode", schoolCode)
        		        .queryParam("date", date)
        		        .build())
        		.retrieve()
        		.body(new ParameterizedTypeReference<List<StaffAttendance>>() {});
		return staffAttendance;
    }

    public StaffAttendance updateStaffAttendanceById(Long id, boolean attendanceStatus, LocalDate date) {
    	
    	StaffAttendance updateAttendanceDetail = restClient.put()
        		.uri(uriBuilder -> uriBuilder
        				.path("/api/staff/attendance/updatestaffattendance/"+ id)
        		        .queryParam("date", date)
        		        .build())
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(attendanceStatus)
        		.retrieve()
        		.body(StaffAttendance.class);
		return updateAttendanceDetail;
    }

}

