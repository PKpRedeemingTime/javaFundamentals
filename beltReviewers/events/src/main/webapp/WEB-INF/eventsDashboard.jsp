<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Events</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<form id="logoutForm" method="POST" action="/logout">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input style="background:none; border:none; color:#0066ff; text-decoration:underline; cursor:pointer" type="submit" value="Logout!" />
		    </form>
		    <h1>Welcome, <c:out value="${currentUser.firstName}"/>!</h1>
		    <h3>Here are some of the events in your area:</h3>
		    <table>
				<thead>
					<tr>
						<th>Name</th>
						<th>Date</th>
						<th>Location</th>
						<th>Host</th>
						<th>Action/Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="event" items="${events}">
						<c:if test="${event.locationState == currentUser.locationState}">
							<tr>
								<td>
									<a href="/events/${event.id}"><c:out value="${event.name}"/></a>
								</td>
								<td>
									<fmt:formatDate value="${event.eventDate}" pattern="MMMM dd, yyyy" />
								</td>
								<td>
									<c:out value="${event.locationCity}"/>
								</td>
								<td>
									<c:out value="${event.users[0].firstName}"/>
								</td>
								<td>
									<p>
										<c:if test="${event.owner == currentUser.username}">
											<a href="/events/${event.id}/edit">Edit Event</a> / 
											<a href="/events/${event.id}/delete">Delete Event</a>
										</c:if> / 
										<c:if test="${event.users.contains(currentUser) && event.owner != currentUser.username}">
											<a href="/events/${event.id}/leave">Leave Event</a>
										</c:if>
										<c:if test="${!event.users.contains(currentUser)}">
											<a href="/events/${event.id}/join">Join Event</a>
										</c:if>
									</p>
								</td>
							</tr>
						</c:if>
					</c:forEach>							
				</tbody>
			</table>
		    <h3>Here are some of the events in other states:</h3>
		    <table>
				<thead>
					<tr>
						<th>Name</th>
						<th>Date</th>
						<th>City</th>
						<th>State</th>
						<th>Host</th>
						<th>Action/Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="event" items="${events}">
						<c:if test="${event.locationState != currentUser.locationState}">
							<tr>
								<td>
									<a href="/events/${event.id}"><c:out value="${event.name}"/></a>
								</td>
								<td>
									<fmt:formatDate value="${event.eventDate}" pattern="MMMM dd, yyyy" />
								</td>
								<td>
									<c:out value="${event.locationCity}"/>
								</td>
								<td>
									<c:out value="${event.locationState}"/>
								</td>
								<td>
									<c:out value="${event.users[0].firstName}"/>
								</td>
								<td>
									<p>
										<c:if test="${event.owner == currentUser.username}">
										<a href="/events/${event.id}/edit">Edit Event</a> / 
											<a href="/events/${event.id}/delete">Delete Event</a>
										</c:if> / 
										<c:if test="${event.users.contains(currentUser) && event.owner != currentUser.username}">
											<a href="/events/${event.id}/leave">Leave Event</a>
										</c:if>
										<c:if test="${!event.users.contains(currentUser)}">
											<a href="/events/${event.id}/join">Join Event</a>
										</c:if>
									</p>
								</td>
							</tr>
						</c:if>
					</c:forEach>							
				</tbody>
			</table>
		    <h3>Create an Event</h3>
			<p><form:errors class="error" path="event.*"/></p>
		    <form:form class="newEvent" method="POST" action="/events" modelAttribute="event">
		        <p>
		            <form:label path="name">Event Name</form:label>
		            <form:input class="floatRight" path="name"/>
		        </p>
		        <p>
		        	<form:label path="eventDate">Event Date</form:label>
		            <form:input class="floatRight" path="eventDate" type="date"/>
		        </p>
		        <p>
					<form:label path="locationCity">Location:</form:label>
					<form:input class="locationCity" path="locationCity" placeholder="City"/>
					<form:select class="floatRight" path="locationState">
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
				<form:input type="hidden" path="owner" value="${currentUser.username}"/>
		        <input class="floatRight" type="submit" value="Create Event"/>
		    </form:form>
	    </div>
	</body>
</html>