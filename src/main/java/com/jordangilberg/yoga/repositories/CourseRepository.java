package com.jordangilberg.yoga.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jordangilberg.yoga.models.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{
	List<Course> findAll();
}
