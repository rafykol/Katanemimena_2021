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
<title>About</title>
</head>
<body>
	<div>

		<jsp:include page="include/sidemenu.jsp"/>

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
					
				</div>
			</div>
			
		<jsp:include page="include/footer.jsp"/>
		</div>


	</div>
</body>
</html>