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
<title>Print Users</title>
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
				<h3>Print Users</h3>
				<div class="secondwrapper">
					<form:form action="edituser" method="post" modelAttribute="edituser" >
					<div id="login4">
											<c:choose>
<c:when test="${empty users}">
<p>No Match Found</p>
</c:when>
<c:otherwise>
						<table>
							<tr>
								<th id="curve1">ID</th>
								<th>Username</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Email</th>
								<th>Role</th>
								<th>Enabled</th>
								<th></th>
								<th id="curve2"></th>
							</tr>
							<!-- loop over and print our customers -->

						<c:forEach var="tempUser" items="${users}">
								<tr>
									<td>${tempUser.id}</td>
									<td>${tempUser.username}</td>
									<td>${tempUser.firstName}</td>
									<td>${tempUser.lastName}</td>
									<td>${tempUser.email}</td>
									<td>${tempUser.authority}</td>
									<td>${tempUser.enabled}</td>
									<td><button id="editbutton" type="submit" name="EditButton" value="${tempUser.id}">Edit</button></td>
									<td><button id="editbutton" type="submit" name="DeleteButton" value="${tempUser}">Delete</button></td>
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