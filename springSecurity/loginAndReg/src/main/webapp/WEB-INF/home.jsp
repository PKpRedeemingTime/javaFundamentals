<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Dashboard</title>
	</head>
	<body>
		<h1>Welcome, <c:out value="${currentUser.firstName}"/>!</h1>
		<a href="/logout">Logout</a>
		<fieldset>
			<p>First Name: <c:out value="${currentUser.firstName}"/></p>
			<p>Last Name: <c:out value="${currentUser.lastName}"/></p>
			<p>Email: <c:out value="${currentUser.username}"/></p>
			<p>Sign up date: <c:out value="${currentUser.createdAt}"/></p>
		</fieldset>
	</body>
</html>