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
<title>Insert title here</title>
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
				<div class="secondwrapper">
					<h3>Your Tasks</h3>
					<div id="login2">
					<p><b>Lab Hours: 50</b><p>
					<p><b>Supervising Hours: 10</b></p>
					<p><b>Grading Papers: 80</b></p>
					<p>~~~~~~~~~~~~~~~~~~~~~~~~~~~~</p>
					<p>You can navigate from the main menu, to add a completed task to your total.</p>
					</div>
				</div>
			</div>
			
		<jsp:include page="include/footer.jsp"/>
		</div>


	</div>
</body>
</html>