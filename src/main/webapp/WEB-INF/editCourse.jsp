<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Book</title>
</head>
<body>
   <h1>Change your Entry</h1>
	<form:form action="/classes/save" method="post" modelAttribute="course">
   		<form:errors path="name" class="text-danger" />
		<form:errors path="day" class="text-danger" />
		<form:errors path="time" class="text-danger" />
		<form:errors path="price" class="text-danger" />
		<form:errors path="description" class="text-danger" />
		<div class="form-group">
		    <label>Name:</label>
		    <form:input path="name" class="form-control" value="${course.name}"/>
		</div>
		<div class="form-group">
		    <label>Day of Week:</label>
		    <form:select path="day" class="form-control" value="${course.day}">
	        	<c:forEach var="weekday" items="${weekdays}">
	        		<c:if test="${weekday.equals(course.day)}">
		        		<form:option value="${weekday}">
		        			<c:out value="${weekday}"/>
		        		</form:option>
	        		</c:if>
	        		<c:if test="${!weekday.equals(course.day)}">
		        		<form:option value="${weekday}">
		        			<c:out value="${weekday}"/>
		        		</form:option>
	        		</c:if>
	        	</c:forEach>
	        </form:select>
		</div>
		<div class="form-group">
		    <label>Time:</label>
		    <form:input path="time" type="time" class="form-control" value="${course.time}"/>
		</div>
		<div class="form-group">
		    <label>Price:</label>
		    <form:input path="price" class="form-control" value="${course.price}"/>
		</div>
		<div class="form-group">
		    <label>Description:</label>
		    <form:textarea path="description" class="form-control" value="${course.description}"/>
		</div><br />
		<form:hidden path="id" value="${course.id}" class="form-control"/>
		<form:hidden path="user" value="${course.user.getId()}" class="form-control"/>
		<input type="submit" value="Edit" class="btn btn-primary" />
	</form:form><br />
	<a href="/classes">Cancel</a>
   <a href="/classes/delete/${course.id}">Delete</a>
</body>
</html>