<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<meta charset="ISO-8859-1" />
<title>LogIn</title>
</head>
<body>

	<div>

		<jsp:include page="include/sidemenu.jsp" />

		<div id="divwrapper">
			<div class="loginmenu">
				<ul>
					<li><a href="<c:url value='/login' />">Log In</a></li>
				</ul>
			</div>
			<div class="maindiv">
				<h2>Monitoring the Doctoral Candidate Projects</h2>
				<br />
			</div>
			<div class="maindiv" id="bluediv">
				<div class="secondwrapper">
					<c:url value="/login" var="loginUrl" />
					<form id="login" name='loginForm' action="${loginUrl}"
						method="post">
						<c:if test="${param.error != null}">
							<div id="error_div">Invalid username and password.</div>
						</c:if>

						<h3>Log In</h3>
						<div class="container">
							<label for="uname"><b>Username</b></label> <input type="text"
								placeholder="Enter Username" name="username" required> <label
								for="psw"><b>Password</b></label> <input type="password"
								placeholder="Enter Password" name="password" required> <input
								type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<button type="submit">Login</button>
							<label> <input type="checkbox" checked="checked"
								name="remember"> Remember me
							</label> <span class="psw">Forgot <a
								href="<c:url value='/forgotpassword' />">password?</a></span>
						</div>


					</form>
				</div>
			</div>
			<jsp:include page="include/footer.jsp" />
		</div>


	</div>
</body>
</html>