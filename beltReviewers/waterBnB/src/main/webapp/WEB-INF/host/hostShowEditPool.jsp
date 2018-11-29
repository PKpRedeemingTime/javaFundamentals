<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Host Show/Edit Pool</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<a href="/">Search</a>
			<a href="/host/dashboard">Home</a>
			<form id="logoutForm" method="POST" action="/logout">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input style="background:none; border:none; color:#0066ff; text-decoration:underline; cursor:pointer" type="submit" value="Logout!" />
		    </form>
		    <div class="poolEdit">
		    	<form:form method="POST" action="/host/pool/${pool.id}/edit" modelAttribute="pool">
		    		<form:hidden path="address" value="${pool.address}"/>
			    	<div class="poolEditLeft">
			    		<h4><c:out value="${pool.address}"/></h4>
			    		<p>
				            <form:label path="description">Description:</form:label>
				            <form:textarea class="updatePoolDescription" path="description"/>
				        </p>
				        <input class="updatePoolButton" type="submit" value="Update Listing"/>
			    	</div>
			    	<div class="poolEditRight">
			    		<p>Email: <c:out value="${pool.user.username}"/></p>
			    		<p>Name: <c:out value="${pool.user.firstName}"/> <c:out value="${pool.user.lastName}"/></p>
			    		<p>
				        	<form:label path="size">Pool Size:</form:label>
							<form:select class="floatRight" path="size">
					            <form:option value="Small" label="Small"/>
					            <form:option value="Medium" label="Medium"/>
					            <form:option value="Large" label="Large"/>
							</form:select>
						</p>
			    		<p>
			            <form:label path="cost">Cost Per Night:</form:label>
				            $<form:input type="number" class="updatePoolCost" path="cost"/>
				        </p>
			    	</div>
		    	</form:form>
		    </div>
		</div>
	</body>
</html>