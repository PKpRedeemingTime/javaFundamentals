<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Edit Event</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<form id="logoutForm" method="POST" action="/logout">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input style="background:none; border:none; color:#0066ff; text-decoration:underline; cursor:pointer" type="submit" value="Logout!" />
		    </form>
		    <a href="/">Events Dashboard</a>
			<h1><c:out value="${event.name}"/></h1>
			<h3>Edit Event:</h3>
			<p><form:errors class="error" path="event.*"/></p>
		    <form:form class="editEvent" method="POST" action="/events/${event.id}/edit" modelAttribute="event">
		        <p>
		            <form:label path="name">Event Name:</form:label>
		            <form:input path="name"/>
		        </p>
		        <p>
		        	<form:label path="eventDate">Event Date:</form:label>
		            <form:input path="eventDate" type="date"/>
		        </p>
		        <p>
					<form:label path="locationCity">Location:</form:label>
					<form:input path="locationCity" placeholder="City"/>
					<form:select path="locationState">
			            <form:option value="AL" label="Alabama"/>
			            <form:option value="AK" label="Alaska"/>
			            <form:option value="AZ" label="Arizona"/>
			            <form:option value="AR" label="Arkansas"/>
			            <form:option value="CA" label="California"/>
			            <form:option value="CO" label="Colorado"/>
			            <form:option value="CT" label="Connecticut"/>
			            <form:option value="DE" label="Delaware"/>
			            <form:option value="DC" label="District Of Columbia"/>
			            <form:option value="FL" label="Florida"/>
			            <form:option value="GA" label="Georgia"/>
			            <form:option value="HI" label="Hawaii"/>
			            <form:option value="ID" label="Idaho"/>
			            <form:option value="IL" label="Illinois"/>
			            <form:option value="IN" label="Indiana"/>
			            <form:option value="IA" label="Iowa"/>
			            <form:option value="KS" label="Kansas"/>
			            <form:option value="KY" label="Kentucky"/>
			            <form:option value="LA" label="Louisiana"/>
			            <form:option value="ME" label="Maine"/>
			            <form:option value="MD" label="Maryland"/>
			            <form:option value="MA" label="Massachusetts"/>
			            <form:option value="MI" label="Michigan"/>
			            <form:option value="MN" label="Minnesota"/>
			            <form:option value="MS" label="Mississippi"/>
			            <form:option value="MO" label="Missouri"/>
			            <form:option value="MT" label="Montana"/>
			            <form:option value="NE" label="Nebraska"/>
			            <form:option value="NV" label="Nevada"/>
			            <form:option value="NH" label="New Hampshire"/>
			            <form:option value="NJ" label="New Jersey"/>
			            <form:option value="NM" label="New Mexico"/>
			            <form:option value="NY" label="New York"/>
			            <form:option value="NC" label="North Carolina"/>
			            <form:option value="ND" label="North Dakota"/>
			            <form:option value="OH" label="Ohio"/>
			            <form:option value="OK" label="Oklahoma"/>
			            <form:option value="OR" label="Oregon"/>
			            <form:option value="PA" label="Pennsylvania"/>
			            <form:option value="RI" label="Rhode Island"/>
			            <form:option value="SC" label="South Carolina"/>
			            <form:option value="SD" label="South Dakota"/>
			            <form:option value="TN" label="Tennessee"/>
			            <form:option value="TX" label="Texas"/>
			            <form:option value="UT" label="Utah"/>
			            <form:option value="VT" label="Vermont"/>
			            <form:option value="VA" label="Virginia"/>
			            <form:option value="WA" label="Washington"/>
			            <form:option value="WV" label="West Virginia"/>
			            <form:option value="WI" label="Wisconsin"/>
			            <form:option value="WY" label="Wyoming"/>
					</form:select>
				</p>
				<form:input type="hidden" path="owner" value="${user.username}"/>
		        <input class="floatRight" type="submit" value="Update Event"/>
		    </form:form>
		</div>
	</body>
</html>