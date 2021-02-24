<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<meta charset="ISO-8859-1" />
<title>User Created</title>
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
				<h3>
					User
					<c:out value="${msg}" />
				</h3>
				<div class="secondwrapper">
					<div id="login2">
						<table>
							<tr>
								<th id="curve1">ID</th>
								<th>Username</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Email</th>
								<th>Role</th>
								<th id="curve2">Enabled</th>
							</tr>
							<!-- loop over and print our customers -->


							<tr>
								<td>${user.id}</td>
								<td>${user.username}</td>
								<td>${user.firstName}</td>
								<td>${user.lastName}</td>
								<td>${user.email}</td>
								<td>${user.authority}</td>
								<td>${user.enabled}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>

			<jsp:include page="include/footer.jsp" />
		</div>


	</div>
</body>
</html>