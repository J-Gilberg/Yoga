package com.jordangilberg.yoga.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jordangilberg.yoga.models.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
	List<Student> findAll();
}
