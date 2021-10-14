<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Course</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="js/app.js"></script>
</head>
<body>
	<div>
		<h1><c:out value="${course.name}"/> with <c:out value="${course.user.getUserName()}"/></h1>
		<h2>Day: <c:out value="${course.day}"/></h2>
		<h2>Cost: <c:out value="${course.price}"/></h2>
		<h2>Time: <c:out value="${course.time}"/></h2>
		<p> <c:out value="${course.description}"/></p><br />
		<a href="/classes">Back to Course Schedule</a>
		<div>
			<ul>
				<c:forEach var="student" items="${students}">
				<li><c:out value="${student.name} - ${student.email}"/> </li>
				</c:forEach>
			</ul>
		</div><br />
		<div>
			<h1>Add Students</h1>
			<h2>New Student</h2>
			<form:form action="/students/save/${course.id}" method="post" modelAttribute="student">
		   		<form:errors path="name" class="text-danger" />
				<form:errors path="email" class="text-danger" />
				<div class="form-group">
				    <label>Name:</label>
				    <form:input path="name" class="form-control"/>
				    <label>Email:</label>
				    <form:input type="email" path="email" class="form-control"/>
					<input type="submit" value="Add"/>
				</div>
			</form:form><br />
			<h2>Existing Student</h2>
			<form:form action="/students/save/${course.id}" method="post" modelAttribute="student">
				<div class="form-group">
					<form:select path="id" class="form-control">
			        	<c:forEach var="student" items="${remStudents}">
			        		<form:option value="${student.id}">
			        			<c:out value="${student.name} - ${student.email}"/>
			        		</form:option>
			        	</c:forEach>
		        	</form:select>
					<input type="submit" value="Add"/>
				</div>
			</form:form><br />
		</div>
		

	</div>
	
</body>
</head>s