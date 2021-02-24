<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<meta charset="ISO-8859-1" />
<title>Search Courses</title>



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
				<form:form action="searchcourses" method="post" modelAttribute="searchCourses" id="login">
					<h3>Search Courses</h3>
					<div class="container">
						<label for="uname"><b>Course Name</b></label> 
						<form:input path="name" class="forminputcss"  /> 
						<button type="submit">Search</button>

					</div>
				</form:form>
			</div>
		</div>
		<jsp:include page="include/footer.jsp"/>
	</div>
	</div>

</body>
</html>