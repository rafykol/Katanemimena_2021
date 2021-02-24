<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<div class="slideout-sidebar">
			<ul>
				<a href="<c:url value="/home"/>"><img
					src="<c:url value="/resources/img/logo_.png"/>"></a>
				<li><a class="active" href="<c:url value="/home"/>">Home</a></li>
				<li><a href="<c:url value="/classes"/>">Classes</a></li>
				<li><a href="<c:url value="/contact"/>">Contacts</a></li>
				<li><a href="<c:url value="/about"/>">About</a></li>
				<li><a href="<c:url value="/manual"/>">Manual</a></li>
			</ul>
		</div>
	</body>
</html>