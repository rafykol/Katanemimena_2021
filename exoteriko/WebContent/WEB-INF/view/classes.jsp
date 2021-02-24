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
<title>Classes</title>
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
				<h3>Classes</h3>
				<div class="secondwrapper">
					<div id="login2">
					<table>
                                <tr>
                                        <th id="curve1">Name</th>
                                        <th id="curve2">Proffessor</th>
                                </tr>
                                <!-- loop over and print our customers -->
<c:forEach var="tempCourse" items="${courses}">

                                        <tr>
                                                <td>${tempCourse.name}</td>
                                                <td>${tempCourse.proffessor.firstName} ${tempCourse.proffessor.lastName}</td>
                                        </tr>
                                </c:forEach>
                        </table>



				</div>
				</div>
			</div>
			
		<jsp:include page="include/footer.jsp"/>
		</div>


	</div>
</body>
</html>