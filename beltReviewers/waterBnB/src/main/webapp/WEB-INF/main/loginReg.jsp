<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login/Reg</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<c:if test="${logoutMessage != null}">
		        <c:out value="${logoutMessage}"></c:out>
		    </c:if>
		    <c:if test="${registered != null}">
		        <c:out value="${registered}"></c:out>
		    </c:if>
			<h1>Welcome!</h1>
			<div class="loginReg">
				<fieldset class="register">
				    <legend>Register</legend>
				    <p><form:errors class="error" path="user.*"/></p>
				    <form:form method="POST" action="/registration" modelAttribute="user">
				        <p>
				            <form:label path="firstName">First Name:</form:label>
				            <form:input class="floatRight" path="firstName"/>
				        </p>
				        <p>
				            <form:label path="lastName">Last Name:</form:label>
				            <form:input class="floatRight" path="lastName"/>
				        </p>
				        <p>
				            <form:label path="username">Email:</form:label>
				            <form:input class="floatRight" path="username"/>
				        </p>
				        <p>
				            <form:label path="password">Password:</form:label>
				            <form:password class="floatRight" path="password"/>
				        </p>
				        <p>
				            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
				            <form:password class="floatRight" path="passwordConfirmation"/>
				        </p>
				        <p>
							<form:select path="role">
					            <form:option value="host" label="Host"/>
					            <form:option value="guest" label="Guest"/>
							</form:select>
						</p>
				        <input class="floatRight" type="submit" value="Register"/>
				    </form:form>
			    </fieldset>
		    	<fieldset class="login">
				    <legend>Login</legend>
				    <p class="error">
					    <c:if test="${errorMessage != null}">
					        <c:out value="${errorMessage}"></c:out>
					    </c:if>
				    </p>
				    <form method="POST" action="/login">
				        <p>
				            <label for="username">Email:</label>
				            <input class="floatRight" type="text" id="username" name="username"/>
				        </p>
				        <p>
				            <label for="password">Password:</label>
				            <input class="floatRight" type="password" id="password" name="password"/>
				        </p>
				        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        <input class="floatRight" type="submit" value="Login"/>
				    </form>
		    	</fieldset>
		    </div>
		</div>
	</body>
</html>