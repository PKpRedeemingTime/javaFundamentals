<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Guest Dashboard</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<a href="/">Home</a>
			<c:if test="${not empty currentUser}">
				<form id="logoutForm" method="POST" action="/logout">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			        <input style="background:none; border:none; color:#0066ff; text-decoration:underline; cursor:pointer" type="submit" value="Logout!" />
			    </form>
		    </c:if>
		    <h3>Find your pool!</h3>
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
					<c:forEach var="pool" items="${pools}">
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
								<a href="/show/${pool.id}">See More!</a>
							</td>
						</tr>	
					</c:forEach>							
				</tbody>
			</table>
		</div>
	</body>
</html>