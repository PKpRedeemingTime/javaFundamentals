<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Questions Dashboard</title>
		<link rel="stylesheet" href="/static/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<h1>Questions Dashboard</h1>
			<table>
				<thead>
					<tr>
						<th>Question</th>
						<th>Tags</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="question" items="${questions}">
						<tr>
							<td>
								<a href="/${question.id}"><c:out value="${question.question}"/></a>
							</td>
							<td>
								<c:forEach var="tag" items="${question.tags}" varStatus="loop">
									<c:out value="${tag.subject}"/><c:if test="${loop.last}">,</c:if>
								</c:forEach>
							</td>
						</tr>	
					</c:forEach>							
				</tbody>
			</table>
			<a href="/new">New Question</a>
		</div>
	</body>
</html>