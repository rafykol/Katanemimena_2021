<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<meta charset="ISO-8859-1" />
<title>Edit User</title>



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
				</h2>
				<br />
			</div>
			<div class="maindiv" id="bluediv">
				<div class="secondwrapper">
					<form:form action="edituser" method="post"
						modelAttribute="edituser" id="login">
						<h3>Edit User</h3>
						<div class="container">
							<label for="uname"><b>Username: </b> <c:out value="${user.username}" /></label>
							<form:input class="forminputcss" path="username" value="${user.username}" />

							<label for="role"><b>Role: </b><c:out value="${user.authority}" /></label>
							<form:select class="forminputcss" path="authority"
								items="${roleList}" />
							<label for="fname"><b>First Name: </b> <c:out value="${user.firstName}" /></label>
							<form:input path="firstName" value="${user.firstName}"/>
							<label for="lname"><b>Last Name: </b><c:out value="${user.lastName}" /></label>
							<form:input path="lastName" class="forminputcss" value="${user.lastName}" />
							<label for="email"><b>Email: </b><c:out value="${user.email}" /></label>
							<form:input path="email" class="forminputcss" value="${user.email}" />

							
							<label for="email"><b>Enabled: </b><c:out value="${user.enabled}" /></label>
							<form:select class="forminputcss" path="enabled">
								<c:forEach items="${enabledList}" var="enabled">
									<option value="${enabled}"><c:if
											test="${enabled == 1}">
											<c:out value="Enabled" />
										</c:if><c:if test="${enabled == 0}">
											<c:out value="Disabled" />
										</c:if></option>
								</c:forEach>
							</form:select>


							<button type="submit" name="SaveButton" value="${user.id}">Save</button>

						</div>
					</form:form>
				</div>
			</div>
			<jsp:include page="include/footer.jsp" />

	</div>
</body>
</html>