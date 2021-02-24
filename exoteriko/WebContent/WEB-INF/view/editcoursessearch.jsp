<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<meta charset="ISO-8859-1" />
<title>Print Courses</title>
</head>
<body>
	<div>

		<jsp:include page="include/sidemenu.jsp" />

		<div id="divwrapper">

			<jsp:include page="include/mainmenu.jsp" />
			<div class="maindiv">
				<h2>
					Welcome
					<c:out value="${username}" />
				<br />
			</div>
			<div class="maindiv" id="bluediv">
				<h3>Print Courses</h3>
				<div class="secondwrapper">
				<form:form action="editcourse" method="post" modelAttribute="editcourse" >
					<div id="login2">
																<c:choose>
<c:when test="${empty courses}">
<p>No Match Found</p>
</c:when>
<c:otherwise>
						<table>
							<tr>
								<th id="curve1">ID</th>
								<th>Name</th>
								<th>Proffessor</th>
								<th></th>
								<th id="curve2"></th>
							</tr>
							<!-- loop over and print our customers -->

						<c:forEach var="tempCourse" items="${courses}">
								<tr>
									<td>${tempCourse.id}</td>
                                                <td>${tempCourse.name}</td>
                                                <td>${tempCourse.proffessor.id}. ${tempCourse.proffessor.firstName} ${tempCourse.proffessor.lastName}</td>
                                                <td><button id="editbutton" type="submit" name="EditButton2" value="${tempCourse.id}">Edit</button></td>
									<td><button id="editbutton" type="submit" name="DeleteButton2" value="${tempCourse}">Delete</button></td>
								</tr>
						</c:forEach>
						</table>
												
</c:otherwise>
</c:choose>
					</div>
					</form:form>
				</div>
			</div>
			</div>

			<jsp:include page="include/footer.jsp" />
		</div>


</body>
</html>