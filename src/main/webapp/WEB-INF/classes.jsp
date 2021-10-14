<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Courses</title>
</head>
<body>
    <h1>Namaste, ${userName}</h1>
    <p>Course Schedule</p>
    |<a href="/logout">logout</a>|
    
    <div>
    	<table>
    		<tr>
    			<td>Class Name</td>
    			<td>Instructor</td>
    			<td>Weekday</td>
    			<td>Price</td>
    			<td>Time</td>
    		</tr>
   			<c:forEach var="course" items="${courses}">
   			<tr>
   				<td>
   					<a href="/classes/${course.id}"><c:out value="${course.name}"/></a>|
   					<c:if test="${course.user.getId().equals(user_id)}">
   						<a href="/classes/edit/${course.id}">Edit Course</a>
   					</c:if>
   				</td>	
    			<td><c:out value="${course.user.getUserName()}"/></td>
    			<td><c:out value="${course.day}"/></td>
    			<td><c:out value="${course.time}"/></td>
    			<td><c:out value="${course.price}"/></td>
    		</tr>
   			</c:forEach>
    	</table>
    	|<a href="/classes/new">+ New Course</a>|
    </div>
</body>
</html>