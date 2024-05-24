package com.staff.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.staff.entity.StaffAttendance;

public interface StaffAttendanceRepository extends JpaRepository<StaffAttendance, Long>{
	
	@Query(value = "select * from staff_attendance where school_code = :School_Code AND date = :date" , nativeQuery = true)
	Optional<List<StaffAttendance>> getStaffAttendanceByDate(@Param ("School_Code") String schoolCode, @Param ("date") LocalDate date);

}
