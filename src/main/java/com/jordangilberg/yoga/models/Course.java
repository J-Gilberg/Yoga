package com.jordangilberg.yoga.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotNull
    @Size(min = 2, max = 255, message="Name must be between 2-255 characters.")
    private String name;
	@NotNull
	private String day;
	@NotNull
//	@DateTimeFormat(pattern="hh:mm")
	private LocalTime time;
	@NotNull
	@DecimalMin("0.01")
	private float price;
	@NotNull
    @Size(min = 2, max = 255, message="Description must be between 2-255 characters.")
    private String description;

	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="courses_students"
	        ,joinColumns = @JoinColumn(name = "course_id")
	        ,inverseJoinColumns = @JoinColumn(name = "student_id")
    		)
    
    private List<Student> students;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Course() {
        
    }
	
    public Course(String name, String day, String time, String description, User user) {
        this.name = name;
        this.day = day;
        this.time = LocalTime.parse(time);
        this.description = description;
        this.user = user;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		if(time != null) {
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
			return time.format(timeFormatter);
		}
		return null;
	}

	public void setTime(String time) {
		this.time = LocalTime.parse(time);;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> student) {
		this.students = student;
	}
    


	
	
}