package com.staff.entity;

import java.time.LocalDate;

public class StudentAttendance {

    private Long id;
    private Long srNo;
    private String name;
    private String standard;
    private String section;
    private boolean attendanceStatus;
    private LocalDate date;
	private String schoolCode;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
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
	public Long getSrNo() {
		return srNo;
	}
	public void setSrNo(Long srNo) {
		this.srNo = srNo;
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
