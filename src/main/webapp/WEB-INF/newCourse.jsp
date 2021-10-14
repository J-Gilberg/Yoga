<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Book</title>
</head>
<body>
	<form:form action="/classes/save" method="post" modelAttribute="newCourse">
   		<form:errors path="name" class="text-danger" />
		<form:errors path="day" class="text-danger" />
		<form:errors path="time" class="text-danger" />
		<form:errors path="price" class="text-danger" />
		<form:errors path="description" class="text-danger" />
		<div class="form-group">
		    <label>Name:</label>
		    <form:input path="name" class="form-control" />
		</div>
		<div class="form-group">
		    <label>Day of Week:</label>
		    <form:select path="day" class="form-control">
		    	<form:option value="">
	        			<c:out value="Select A Day"/>
	        	</form:option>
	        	<c:forEach var="weekday" items="${weekdays}">
	        		<form:option value="${weekday}">
	        			<c:out value="${weekday}"/>
	        		</form:option>
	        	</c:forEach>
	        </form:select>
		</div>
		<div class="form-group">
		    <label>Time:</label>
		    <form:input type="time" path="time" class="form-control" />
		</div>
		<div class="form-group">
		    <label>Price:</label>
		    <form:input type="number" step="0.01" path="price" class="form-control" />
		</div>
		<div class="form-group">
		    <label>Description:</label>
		    <form:textarea path="description" class="form-control" />
		</div><br />
		<form:hidden path="user" value="${user_id}" class="form-control" />
		<input type="submit" value="Add" class="btn btn-primary" />
	</form:form><br />
	<a href="/classes">Cancel</a>
</body>
</html>