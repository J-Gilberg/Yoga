package com.jordangilberg.yoga.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordangilberg.yoga.models.Student;
import com.jordangilberg.yoga.repositories.StudentRepository;
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;
	
    public StudentService(StudentRepository studentRepo) {
    	this.studentRepo = studentRepo;	
    }
    
    public List<Student> allStudents(){
    		return studentRepo.findAll();
    }
    
    public Student findStudent(Long id) {
    	Optional<Student> studentFound = studentRepo.findById(id);
    	if(studentFound.isPresent()){
    		return studentFound.get();
    	}else {
    		return null;
    	}
    }
    
    public void deleteStudent(Long id) {
    	studentRepo.deleteById(id);
    	System.out.println(String.format("Student was deleted: %d", id));
    }
    
    public Student saveStudent(Student student) {
    	studentRepo.save(student);
    	return student;
    }
}
