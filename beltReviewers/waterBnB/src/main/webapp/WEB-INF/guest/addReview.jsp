<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Review</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<a href="/">Home</a>
			<form id="logoutForm" method="POST" action="/logout">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input style="background:none; border:none; color:#0066ff; text-decoration:underline; cursor:pointer" type="submit" value="Logout!" />
		    </form>
		    <p>Review for <strong><c:out value="${pool.address}"/></strong></p>
		    <p><form:errors class="error" path="message.*"/></p>
			<form:form method="POST" action="/guest/${pool.id}/addReview" modelAttribute="review">
	            <p><form:label path="content">Add review:</form:label></p>
	            <p><form:textarea path="content"/></p>
		        <p>
		        	<form:label path="pool.ratingSum">Rating:</form:label>
						<form:select path="pool.ratingSum">
			            <form:option value="1" label="1"/>
			            <form:option value="2" label="2"/>
			            <form:option value="3" label="3"/>
			            <form:option value="4" label="4"/>
			            <form:option value="5" label="5"/>
					</form:select>
		        	<input type="submit" value="Submit Review"/>
		        </p>
		    </form:form>
		</div>
	</body>
</html>