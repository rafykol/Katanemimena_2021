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
<title>Create User</title>



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
					<form:form action="createuser" method="post"
						modelAttribute="userForm" id="login">
						<h3>Create User</h3>
						<c:if test="${errorp == 1}">
							<div id="error_div">Duplicate Username or Email!</div>
						</c:if>
						<div class="container">
							<label for="uname"><b>Username</b></label>
							<form:input class="forminputcss" path="username" required="required"/>
							<label for="psw"><b>Password</b></label>
							<form:password class="forminputcss" path="password" required="required"/>

							<label for="role"><b>Role</b></label>
								
								<form:select class="forminputcss" path="authority">
								<c:forEach items="${roleList}" var="role">
									<option value="${role}"><c:if
											test="${role == 'ROLE_ADMIN'}">
											<c:out value="Admin" />
										</c:if><c:if test="${role == 'ROLE_SEC'}">
											<c:out value="Secretery" />
										</c:if>
											<c:if test="${role == 'ROLE_STUDENT'}">
											<c:out value="Student" />
										</c:if>
										<c:if test="${role == 'ROLE_MGA'}">
											<c:out value="MGA" />
										</c:if>
										<c:if test="${role == 'ROLE_PROF'}">
											<c:out value="Proffessor" />
										</c:if></option>
								</c:forEach>
							</form:select>
								
								
								
							<label for="fname"><b>First Name</b></label>
							<form:input path="firstName" required="required" />
							<label for="lname"><b>Last Name</b></label>
							<form:input path="lastName" class="forminputcss" required="required"/>
							<label for="email"><b>Email</b></label>
							<form:input path="email" class="forminputcss" required="required"/>

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


							<button type="submit">Create</button>

						</div>
					</form:form>
				</div>
			</div>
			<jsp:include page="include/footer.jsp" />

		</div>
</body>
</html>