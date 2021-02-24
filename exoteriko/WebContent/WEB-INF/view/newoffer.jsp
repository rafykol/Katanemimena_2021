<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<meta charset="ISO-8859-1" />
<title>Make Offer</title>
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
					<form:form action="newoffer" method="post"
						modelAttribute="newoffer" id="login">
						<h3>Make Offer</h3>
						<div class="container">
							<label for="uname"><b>Lab Hours</b></label>
							<form:input class="forminputcss" path="newoffer.labHours" />
							<label for="uname"><b>Supervising Hours</b></label>
							<form:input class="forminputcss" path="newoffer.superHours" />
							<label for="uname"><b>Tests to Grade</b></label>
							<form:input class="forminputcss" path="newoffer.tests" />

							<label for="prof_id"><b>Student</b></label>
							<form:select class="forminputcss" path="stdID">
								<c:forEach items="${studlist}" var="student">
									<option value="${student.id}">${student.id}. ${student.firstName}
										${student.lastName}</option>
								</c:forEach>
							</form:select>





							<button type="submit">Create</button>

						</div>
					</form:form>
				</div>
			</div>

			<jsp:include page="include/footer.jsp" />
		</div>


	</div>
</body>
</html>
