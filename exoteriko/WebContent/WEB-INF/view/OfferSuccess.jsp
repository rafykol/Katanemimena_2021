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
<title>Offer Created</title>
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
				<h3>Offer Created Successfully</h3>
				<div class="secondwrapper">
					<div id="login2">
						<table>
							<tr>
								<th id="curve1">ID</th>
								<th>Lab h.</th>
								<th>Supervising h.</th>
								<th>Papes</th>
								<th>Status</th>
								<th>Proffessor</th>
								<th id="curve2">Student</th>
							</tr>
							<!-- loop over and print our customers -->


							<tr>
								<td>${offer.id}</td>
								<td>${offer.labHours}</td>
								<td>${offer.superHours}</td>
								<td>${offer.tests}</td>
								<td>${offer.status}</td>
								<td>${offer.proffessorID.id}. ${offer.proffessorID.firstName} ${offer.proffessorID.lastName}</td>
								<td>${offer.studentID.id}. ${offer.studentID.firstName} ${offer.studentID.lastName}</td>

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