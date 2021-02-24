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
<title>Create Course</title>
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
					<form:form action="createcourse" method="post" modelAttribute="courseForm" id="login">
					<h3>Create Course</h3>
					<div class="container">
						<label for="uname"><b>Course Name</b></label> 
						<form:input class="forminputcss" path="newcourse" required="required"/>  
						
						<label for="prof_id"><b>Professor</b></label>
						<form:select class="forminputcss" path="userid">
								<c:forEach items="${proflist}" var="proffessor">
									<option value="${proffessor.id}">${proffessor.id}. ${proffessor.firstName}
										${proffessor.lastName}</option>
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
