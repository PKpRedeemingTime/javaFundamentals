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
		<form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input style="background:none; border:none; color:#0066ff; text-decoration:underline; cursor:pointer" type="submit" value="Logout!" />
	    </form>
	    <c:if test="${currentUser.getRoles().size() > 1}"><a href="/admin">Admin Page</a></c:if>
		<h1>Welcome, <c:out value="${currentUser.firstName}"/>!</h1>
		<fieldset style="width:300px">
			<p>First Name: <c:out value="${currentUser.firstName}"/></p>
			<p>Last Name: <c:out value="${currentUser.lastName}"/></p>
			<p>Email: <c:out value="${currentUser.username}"/></p>
			<p>Sign up date: <c:out value="${currentUser.createdAt}"/></p>
		</fieldset>
	</body>
</html>