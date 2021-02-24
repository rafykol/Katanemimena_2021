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
<title>Edit Course</title>



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
					<form:form action="editcourse" method="post" modelAttribute="editcourse" id="login">
					<h3>Create Course</h3>
					<div class="container">
						<label for="uname"><b>Course Name: </b><c:out value="${course.name}" /></label> 
						<form:input class="forminputcss" path="newcourse"/>  
						
						<label for="prof_id"><b>Professor: </b><c:out value="${course.proffessor.id} ${course.proffessor.firstName} ${course.proffessor.lastName}" /></label>
						<form:select class="forminputcss" path="userid">
								<c:forEach items="${proflist}" var="proffessor">
									<option value="${proffessor.id}">${proffessor.id}. ${proffessor.firstName}
										${proffessor.lastName}</option>
								</c:forEach>
							</form:select>


							<button type="submit" name="SaveButton2" value="${course.id}">Save</button>

					</div>
				</form:form>
				</div>
			</div>
			<jsp:include page="include/footer.jsp" />

	</div>
</body>
</html>