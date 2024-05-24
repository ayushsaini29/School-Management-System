package com.student.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.entity.Student;
import com.student.entity.StudentAttendance;

import jakarta.transaction.Transactional;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long>{
	
	@Query(value = "select * from student_attendance where standard = :standard AND section = :section AND school_code = :School_Code AND date = :date" , nativeQuery = true)
	Optional<List<StudentAttendance>> getStudentAttendanceByStandardAndDate(@Param ("standard") String standard, @Param ("section") String section, @Param ("School_Code") String schoolCode, @Param ("date") LocalDate date);

//	Optional<StudentAttendance> findBySchoolCode(String schoolCode);
	
//	@Modifying
//	@Query(value = "update student_attendance set attendanceStatus = :attendance_status, date = :date where sr_no = :sr_no", nativeQuery = true)
//	@Transactional
//	Student updateStudentAttendance(@Param ("sr_no") Long srNo, @Param ("attendance_status") String attendanceStatus, @Param ("date")LocalDate date);

}
