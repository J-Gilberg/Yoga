package com.jordangilberg.yoga.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
    
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jordangilberg.yoga.models.Course;
import com.jordangilberg.yoga.models.Student;

import com.jordangilberg.yoga.services.CourseService;
import com.jordangilberg.yoga.services.StudentService;
    
@Controller
public class YogaController {
    
    private final StudentService studentService;
    private final CourseService courseService;
    
    private String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private List<String> weekdays = Arrays.asList(days);
    
    public YogaController(StudentService studentService, CourseService courseService) {
    	this.studentService = studentService;
    	this.courseService = courseService;
    }
    
    @GetMapping("/home")
    public String toMainPage() {
    	return "redirect:/classes";
    }
    
    @GetMapping("/classes")
    public String index(HttpSession session, Model model) {
    	if(session.getAttribute("user_id") == null) return "redirect:/";
    	model.addAttribute("userName",session.getAttribute("userName"));
    	model.addAttribute("user_id",session.getAttribute("user_id"));
    	List<Course> courses = courseService.allCourses();
        model.addAttribute("courses", courses);
        return "classes.jsp";
    }
    
    @GetMapping("/classes/{id}")
    public String showCourse(@PathVariable("id") Long id, HttpSession session, Model model){
    	if(session.getAttribute("user_id") == null) return "redirect:/";
    	model.addAttribute("user_id",session.getAttribute("user_id"));
    	Course course = courseService.findCourse(id);
    	model.addAttribute("course",course);
    	Student student = new Student();
    	model.addAttribute(student);
    	List<Student> students = course.getStudents();
    	model.addAttribute("students", students);
    	
    	List<Student> remStudents = course.getStudents();
    	model.addAttribute("remStudents", remStudents);
    	return "show.jsp";
    }
    
    @GetMapping("/classes/edit/{id}")
    public String editCourse(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Course course, BindingResult bindingResult,  HttpSession session, Model model){
    	if(session.getAttribute("user_id") == null) return "redirect:/";
    	Course courseValues = courseService.findCourse(id);
    	model.addAttribute("weekdays", weekdays);
    	if( !courseValues.getUser().getId().equals(session.getAttribute("user_id"))) {
    		return "redirect:/classes";
    	}else {
        	model.addAttribute("course",courseValues);
        	return "editCourse.jsp";
    	}
    }
    
    @GetMapping("/classes/new")
    public String newCourse(@Valid @ModelAttribute("newCourse") Course course, BindingResult bindingResult, HttpSession session, Model model){
    	if(session.getAttribute("user_id") == null) return "redirect:/";
    	model.addAttribute("user_id",session.getAttribute("user_id"));
    	model.addAttribute("weekdays", weekdays);
    	return "newCourse.jsp";
    }
    
	 @PostMapping("/classes/save")
	 public String saveCourse(@Valid @ModelAttribute("newCourse") Course course, BindingResult bindingResult , HttpSession session, Model model) {
		 
		 if(session.getAttribute("user_id") == null) return "redirect:/";
		 if (bindingResult.hasErrors()) {
			 if(course.getId() != null){
				 model.addAttribute("weekdays", weekdays);
				 return "editCourse.jsp";
			 }else {
				 model.addAttribute("weekdays", weekdays);
				 return "newCourse.jsp";
			 }
		 } else {
		 	for(int i = 0; i < weekdays.size();++i) {
			 	if(course.getDay().equals(weekdays.get(i))){
				   	courseService.saveCourse(course);
				    return "redirect:/classes";
			 	}
		 	}
			 if(course.getId() != null){
				 model.addAttribute("weekdays", weekdays);
				 return "redirect:/classes";
			 }else {
				 model.addAttribute("weekdays", weekdays);
				 return "redirect:/classes";
			 }
		 }
	 }
	 
	 @PostMapping("/students/save/{course_id}")
	 public String saveStudent(@PathVariable("course_id") Long course_id, @Valid @ModelAttribute("student") Student student, BindingResult bindingResult , HttpSession session) {
		 
		 if(session.getAttribute("user_id") == null) return "redirect:/";
		 if (bindingResult.hasErrors()) {
			 return "redirect:/classes/"+course_id;
		 } else {
			 if(student.getId() != null){
			    Course thisCourse = courseService.findCourse(course_id);
			    Student thisStudent = studentService.findStudent(student.getId());
			    thisCourse.getStudents().add(thisStudent);
			    courseService.saveCourse(thisCourse);
			    return "redirect:/classes/"+course_id;
			 }else {
			    Course thisCourse = courseService.findCourse(course_id);
			    System.out.println(thisCourse.getDay());
			    Student newStudent = studentService.saveStudent(student);
			    Student thisStudent = studentService.findStudent(newStudent.getId());
			    thisCourse.getStudents().add(thisStudent);
			    courseService.saveCourse(thisCourse);
			    return "redirect:/classes/"+course_id;
			 }
		 }
	 }

	 
	 
    @GetMapping("/classes/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id, HttpSession session, Model model){
    	if(session.getAttribute("user_id") == null) return "redirect:/";
    	Course course = courseService.findCourse(id);
    	if(course.getUser().getId() == session.getAttribute("user_id")) {
    		courseService.deleteCourse(id);
    		return "redirect:/classes";
    	}else {
    		return "redirect:/classes";
    	}
    }
}
