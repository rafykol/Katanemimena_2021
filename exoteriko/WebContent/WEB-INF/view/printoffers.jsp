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
<title>Print Offers</title>
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
				<h3>Print Offers</h3>
				<div class="secondwrapper">
					<div id="login4">
						<table>
							<tr>
								<th id="curve1">ID</th>
								<th>Lab h.</th>
								<th>Supervising h.</th>
								<th>Papers</th>
								<th>Status</th>
								<th>Proffessor</th>
								<th id="curve2">Student</th>
							</tr>
							<!-- loop over and print our customers -->

						<c:forEach var="tempOffer" items="${offers}">
								<tr>
									<td>${tempOffer.id}</td>
                                                <td>${tempOffer.labHours}</td>
                                                <td>${tempOffer.superHours}</td>
                                                <td>${tempOffer.tests}</td>
                                                <td>${tempOffer.status}</td>
                                                <td>${tempOffer.proffessorID.id}. ${tempOffer.proffessorID.firstName} ${tempOffer.proffessorID.lastName}</td>
                                                <td>${tempOffer.studentID.id}. ${tempOffer.studentID.firstName} ${tempOffer.studentID.lastName}</td>
								</tr>
						</c:forEach>
						</table>
					</div>
				</div>
			</div>

			<jsp:include page="include/footer.jsp" />
		</div>


	</div>
</body>
</html>
