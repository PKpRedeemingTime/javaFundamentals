<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Host Dashboard</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<a href="/">Search</a>
			<form id="logoutForm" method="POST" action="/logout">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input style="background:none; border:none; color:#0066ff; text-decoration:underline; cursor:pointer" type="submit" value="Logout!" />
		    </form>
		    <h3>Current Listings:</h3>
		    <table>
				<thead>
					<tr>
						<th>Address</th>
						<th>Pool Size</th>
						<th>Cost Per Night</th>
						<th>Rating</th>
						<th>Edit?</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pool" items="${currentUser.pools}">
						<tr>
							<td>
								<p><c:out value="${pool.address}"/></p>
							</td>
							<td>
								<p><c:out value="${pool.size}"/></p>
							</td>
							<td>
								<p><c:out value="${pool.cost}"/></p>
							</td>
							<td>
								<c:choose>
									<c:when test="${pool.ratingCount == 0}">
										<p>No Ratings</p>
									</c:when>
									<c:otherwise>
										<p><c:out value="${pool.ratingSum/pool.ratingCount}"/>/5</p>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<a href="/host/pool/${pool.id}">Edit</a>
							</td>
						</tr>	
					</c:forEach>							
				</tbody>
			</table>
			<fieldset class="newPool">
				<legend>New Listing</legend>
				<form:form method="POST" action="/host/pool" modelAttribute="pool">
			        <p>
			            <form:label path="address">Address:</form:label>
			            <form:input class="floatRight" path="address"/>
			        </p>
			        <p>
			            <form:label path="description">Description:</form:label>
			            <form:textarea class="floatRight" path="description"/>
			        </p>
			        <p>
			            <form:label path="cost">Cost Per Night:</form:label>
			            $<form:input type="number" class="floatRight" path="cost"/>
			        </p>
			        <p>
			        	<form:label path="size">Pool Size:</form:label>
						<form:select path="size">
				            <form:option value="Small" label="Small"/>
				            <form:option value="Medium" label="Medium"/>
				            <form:option value="Large" label="Large"/>
						</form:select>
					</p>
			        <input class="floatRight" type="submit" value="Add Listing"/>
			    </form:form>
			</fieldset>
		</div>
	</body>
</html>