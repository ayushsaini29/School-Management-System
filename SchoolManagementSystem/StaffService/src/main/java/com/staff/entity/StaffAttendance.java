package com.staff.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "staff_attendance")
public class StaffAttendance {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Emp_Id")
    private Long id;
	
    private String name;
    
    @Column(name="attendance_status")
    private boolean attendanceStatus;
    
    private LocalDate date;
    
	@Column(name="School_Code")
	private String schoolCode;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(boolean attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
}
