<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Guest Show Pool</title>
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
		    <div class="showPool">
		    	<div class="showPoolLeft">
		    		<h4><c:out value="${pool.address}"/></h4>
		    		<p><c:out value="${pool.description}"/></p>
		    	</div>
		    	<div class="showPoolLeft">
		    		<h4>Email: <c:out value="${pool.user.username}"/></h4>
		    		<h4>Name: <c:out value="${pool.user.firstName}"/> <c:out value="${pool.user.lastName}"/></h4>
		    		<h4>Pool Size: <c:out value="${pool.size}"/></h4>
		    		<h4>Cost Per Night: $<c:out value="${pool.cost}"/></h4>
		    	</div>
		    </div>
		    <a class="floatRight" href="/guest/${pool.id}/review">Leave a Review!</a>
			<c:choose>
				<c:when test="${pool.ratingCount == 0}">
					<h4>Reviews: (No Ratings)</h4>
				</c:when>
				<c:otherwise>
					<h4>Reviews: (<c:out value="${pool.ratingSum/pool.ratingCount}"/>/5)</h4>
				</c:otherwise>
			</c:choose>
		    <h4>Search Results: <c:out value="${pool.ratingCount}"/></h4>
		    <fieldset class="reviews">
				<c:forEach var="review" items="${reviews}">
					<p><c:out value="${review.user.firstName}"/> <c:out value="${review.user.lastName}"/> says: <c:out value="${review.content}"/></p>
					<p>**************************************************************</p>
				</c:forEach>
			</fieldset>
		</div>
	</body>
</html>