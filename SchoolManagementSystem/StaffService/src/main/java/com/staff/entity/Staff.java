package com.staff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Emp_Id")
    private Long id;
    
//	@Column(name="Emp_Id")
//    private Long empId;

    private String email;
    private String password;
    private String name;
    
	@Column(name="School_Name")
    private String schoolName;
	
	@Column(name="School_Code")
	private String schoolCode;
	
    private String position;
    
	@Column(name="Class_Teacher")
    private String classTeacher;
	
    private String subject;
    private String[] classTimeTable;
    private Long phoneNumber;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String[] getClassTimeTable() {
		return classTimeTable;
	}
	public void setClassTimeTable(String[] classTimeTable) {
		this.classTimeTable = classTimeTable;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public Long getEmpId() {
//		return empId;
//	}
//	public void setEmpId(Long empId) {
//		this.empId = empId;
//	}
	public String getClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(String classTeacher) {
		this.classTeacher = classTeacher;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

}
