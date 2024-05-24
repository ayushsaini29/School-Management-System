package com.student.service;

import java.util.List;
import org.springframework.util.StringUtils;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.student.dao.StudentAttendanceRepository;
import com.student.dao.StudentRepository;
import com.student.entity.Student;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudentBySchool(String schoolCode) {
    	return studentRepository.getAllStudentBySchool(schoolCode)
			.orElseThrow(() -> new IllegalArgumentException("Invalid School Code: " + schoolCode));
    }

    public Student getStudentById(Long id) {
    	return studentRepository.findById(id)
    	.orElseThrow(() -> new IllegalArgumentException("Invalid Student id: " + id));
    }
    
    public List<Student> getStudentsByStandard(String standard, String section, String schoolCode) {
         return studentRepository.getStudentsByStandard(standard, section, schoolCode)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid School Code: " + schoolCode));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public ResponseEntity<Student> updateStudentById(Long id, Student studentDetails) {
    	
    	Student student = studentRepository.findById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid Student id: " + id));
    	
    	if (id.equals(student.getId())) {
            if (StringUtils.hasText(studentDetails.getName())) {
                student.setName(studentDetails.getName());
            }
            if (studentDetails.getSrNo() != null) {
                student.setSrNo(studentDetails.getSrNo());
            }
            if (StringUtils.hasText(studentDetails.getStandard())) {
                student.setStandard(studentDetails.getStandard());
            }
            if (StringUtils.hasText(studentDetails.getSection())) {
                student.setSection(studentDetails.getSection());
            }
            if (StringUtils.hasText(studentDetails.getClassTeacherName())) {
                student.setClassTeacherName(studentDetails.getClassTeacherName());
            }
  		  return ResponseEntity.ok(studentRepository.save(student));
      	} else {
      		return ResponseEntity.notFound().build();
      	}
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}

