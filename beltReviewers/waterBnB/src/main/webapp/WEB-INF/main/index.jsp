<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Homepage</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<c:if test="${empty currentUser}">
				<a href="/loginReg">Sign-In/Register</a>
			</c:if>
			<c:if test="${not empty currentUser}">
				<form id="logoutForm" method="POST" action="/logout">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			        <input style="background:none; border:none; color:#0066ff; text-decoration:underline; cursor:pointer" type="submit" value="Logout!" />
			    </form>
		    </c:if>
			<c:if test="${currentUser.role == 'host'}">
				<a href="/host/dashboard">Hello, <c:out value="${currentUser.firstName}"/>! Click here to visit your dashboard!</a>
			</c:if>
			<h3>Find places to swim and sleep on WaterBnB!!</h3>
			<form:form action="/search" method="POST">
				<input type="text" placeholder="Desired Location" name="address" />
				<input type="submit" value="Search"/>
			</form:form>
		</div>
	</body>
</html>