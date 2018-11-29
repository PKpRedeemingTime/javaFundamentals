<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin Page</title>
	</head>
	<body>
		<form id="logoutForm" method="POST" action="/logout">
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <input style="background:none; border:none; color:#0066ff; text-decoration:underline; cursor:pointer" type="submit" value="Logout!" />
	    </form>
	    <a href="/dashboard">Dashboard</a>
		<h1>Welcome, <c:out value="${currentUser.firstName}"/>!</h1>
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>
							<p><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></p>
						</td>
						<td><c:out value="${user.username}"/></td>
						<td>
							<a href="/delete/${user.id}">Delete</a><c:if test="${user.getRoles().size() < 2}"> | <a href="/addAdmin/${user.id}">make-admin</a></c:if>		
						</td>
					</tr>	
				</c:forEach>							
			</tbody>
		</table>
	</body>
</html>