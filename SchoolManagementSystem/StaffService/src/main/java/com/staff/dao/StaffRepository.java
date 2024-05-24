package com.staff.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.staff.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    // Add custom query methods if needed
	
//	@Query(value = "select * from Staff where standard = :standard AND section = :section AND school_code = :School_Code", nativeQuery = true)
//	List<Staff> getStaffsByStandard(@Param ("standard")String standard, @Param ("section")String section, @Param ("School_Code") String schoolCode);
	
	@Query(value = "select * from staff where school_code = :School_Code", nativeQuery = true)
	Optional<List<Staff>> getAllStaff(@Param ("School_Code") String schoolCode);
}

