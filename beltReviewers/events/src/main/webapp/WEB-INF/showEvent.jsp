<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><c:out value="${event.name}"/></title>
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
			<div class="main">
				<div class="mainLeft">
					<p>Host: <c:out value="${owner.firstName}"/> <c:out value="${owner.lastName}"/></p>
					<p>Date: <c:out value="${event.eventDate}"/></p>
					<p>Location: <c:out value="${event.locationCity}"/>, <c:out value="${event.locationState}"/></p>
					<p>Number of people attending this event: <c:out value="${event.users.size()}"/></p>
					<table>
						<thead>
							<tr>
								<th>Name</th>
								<th>Location</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${event.users}" varStatus="loop">
								<tr>
									<td>
										<p><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>
									</td>
									<td>
										<p><c:out value="${user.locationCity}"/></p>
									</td>
								</tr>	
							</c:forEach>							
						</tbody>
					</table>
				</div>
				<div class="mainRight">
					<h3>Message Wall</h3>
					<fieldset class="messages">
						<c:forEach var="message" items="${messages}">
							<p><c:out value="${message.user.firstName}"/> <c:out value="${message.user.lastName}"/> says: <c:out value="${message.comment}"/></p>
							<p>**************************************************************</p>
						</c:forEach>
					</fieldset>
					<div class="addComment">
						<p><form:errors class="error" path="message.*"/></p>
						<form:form method="POST" action="/events/${event.id}" modelAttribute="message">
				            <p><form:label path="comment"><strong>Add comment:</strong></form:label></p>
				            <p><form:input class="commentInput" path="comment"/></p>
					        <input class="floatRight" type="submit" value="Comment"/>
					    </form:form>
				    </div>
				</div>
			</div>
		</div>
	</body>
</html>