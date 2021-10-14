package com.jordangilberg.yoga.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordangilberg.yoga.models.Course;
import com.jordangilberg.yoga.repositories.CourseRepository;
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;
	
    public CourseService(CourseRepository courseRepo) {
    	this.courseRepo = courseRepo;	
    }
    
    public List<Course> allCourses(){
    		return courseRepo.findAll();
    }
    
    public Course findCourse(Long id) {
    	Optional<Course> courseFound = courseRepo.findById(id);
    	if(courseFound.isPresent()){
    		return courseFound.get();
    	}else {
    		return null;
    	}
    }
    
    public void deleteCourse(Long id) {
    	courseRepo.deleteById(id);
    	System.out.println(String.format("Course was deleted: %d", id));
    }
    
    public Course saveCourse(Course course) {
    	courseRepo.save(course);
    	return course;
    }

}
