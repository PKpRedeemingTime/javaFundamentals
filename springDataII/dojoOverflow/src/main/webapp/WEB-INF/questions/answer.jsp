<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Show Question</title>
		<link rel="stylesheet" href="/static/css/style.css" />
	</head>
	<body>
		<div class="wrapper">
			<h1><c:out value="${question.question}"/></h1>
			<h3>
				Tags: 
				<c:forEach var="tag" items="${question.tags}">
					<button><c:out value="${tag.subject}"/></button>
				</c:forEach>
			</h3>
			<div class="main">
				<table class="answerTable">
					<thead>
						<tr>
							<th>Answers</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="answer" items="${question.answers}">
							<tr>
								<td>
									<c:out value="${answer.answer}"/>
								</td>
							</tr>	
						</c:forEach>							
					</tbody>
				</table>
				<p class='error'><form:errors path="questionModel.*"/></p>
				<form:form class="answerForm" action="/createAnswer/${question.id}" method="POST" modelAttribute="answerModel">
					<form:input type="hidden" path="question" value="${question.id}"/>
					<p>
						<form:label path="answer">Answer:</form:label>
						<form:input path="answer" type="text"/>
					</p>
					<input type="submit" value="Answer it!"/>
				</form:form>
			</div>
		</div>
	</body>
</html>