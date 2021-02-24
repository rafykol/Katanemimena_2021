<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<meta charset="ISO-8859-1" />
<title>Deleted</title>
</head>
<body>
	<div>

		<jsp:include page="include/sidemenu.jsp"/>

		<div id="divwrapper">
			
		<jsp:include page="include/mainmenu.jsp"/>
			<div class="maindiv">
				<h2>Welcome <c:out value="${username}"/></h2>
				<br />
			</div>
			<div class="maindiv" id="bluediv">
				<h3>Deleted Successfully</h3>
					<div id="login2">
				<div class="secondwrapper">
					<p><c:out value="${user}"/></p>
					</div>
				</div>
			</div>
			
		<jsp:include page="include/footer.jsp"/>
		</div>


	</div>
</body>
</html>>