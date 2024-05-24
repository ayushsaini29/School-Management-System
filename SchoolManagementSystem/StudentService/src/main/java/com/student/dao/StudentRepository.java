package com.student.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.entity.Student;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query(value = "select * from student where standard = :standard AND section = :section AND school_code = :School_Code", nativeQuery = true)
	Optional<List<Student>> getStudentsByStandard(@Param ("standard")String standard, @Param ("section")String section, @Param ("School_Code") String schoolCode);
	
	@Query(value = "select * from student where school_code = :School_Code", nativeQuery = true)
	Optional<List<Student>> getAllStudentBySchool(@Param ("School_Code") String schoolCode);

	Optional<Student> findBySchoolCode(String schoolCode);
	
//	@Modifying
//	@Query(value = "update student set standard=:standard, section=:section, attendanceStatus=:attendanceStatus, date=:date where id = :id", nativeQuery = true)
//	@Transactional
//	Student updateStudentDetails(@Param ("id") Long id, Student student);
    

}


