<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Show Product</title>
		</head>
	<body>
		<h1><c:out value="${product.name}"/></h1>
		<h2>Categories</h2>
		<ul>
			<c:forEach var="cat" items="${product.categories}">
				<li><c:out value="${cat.name}"/></li>
			</c:forEach>
		</ul>
		<form action="/products/addCategory/${product.id}" method="POST">
			<label for="name">Add Category: </label>
			<select name="name">
				<c:forEach items="${categories}" var="category">
						<option value="${category.name}"><c:out value="${category.name}"/></option>
				</c:forEach>
			</select>
			<input type="submit" value="Add Category"/>
		</form>
	</body>
</html>