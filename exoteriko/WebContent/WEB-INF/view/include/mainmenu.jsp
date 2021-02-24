<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="loginmenu">
				<ul>
				<sec:authorize access="hasRole('ADMIN')">
				<div class="dropdown">
					<button class="dropbtn">
						Users 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/admin/createuser' />">Create User</a> 
						<a href="<c:url value='/admin/searchusers' />">Search User</a> 
						<a href="<c:url value='/admin/printusers' />">Print All Users</a>
					</div>
					</div>
					<div class="dropdown">
					<button class="dropbtn">
						Courses 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/admin/createcourse' />">Create Course</a> 
						<a href="<c:url value='/admin/searchcourses' />">Search Course</a> 
						<a href="<c:url value='/admin/printcourses' />">Print All Courses</a>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn">
						Offers 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/admin/createoffer' />">Create Offer</a> 
						<a href="#">Search Offer</a> 
						<a href="<c:url value='/admin/printoffers' />">Print All Offers</a>
					</div>
				</div>
				</sec:authorize>
				<sec:authorize access="hasRole('PROF')">
				<div class="dropdown">
					<button class="dropbtn">
						Offers 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/proffessor/newoffer' />">Create Offer</a> 
					</div>
					</div>
					<div class="dropdown">
					<button class="dropbtn">
						View Offers 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/proffessor/printsubmittedoffers' />">Submitted</a> 
						<a href="<c:url value='/proffessor/printaccepteddoffers' />">Accepted</a> 
						<a href="<c:url value='/proffessor/printrejectedoffers' />">Rejected</a> 
						<a href="<c:url value='/proffessor/printoffers' />">All</a> 
					</div>
					</div>
					<div class="dropdown">
					<button class="dropbtn">
						Search Offers 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='#' />">By Student</a> 
						<a href="<c:url value='#' />">By ID</a> 
					</div>
					</div>
				</sec:authorize>
				<sec:authorize access="hasRole('MGA')">
				<div class="dropdown">
					<button class="dropbtn">
						View Offers 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/mga/printoffers' />">Pending</a> 
					</div>
					</div>
					<div class="dropdown">
					<button class="dropbtn">
						Search Offers 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='#' />">By Student</a> 
						<a href="<c:url value='#' />">By Proffessor</a> 
						<a href="<c:url value='#' />">By ID</a> 
					</div>
					</div>
				</sec:authorize>
				<sec:authorize access="hasRole('SEC')">
				<div class="dropdown">
					<button class="dropbtn">
						View Offers 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/sec/printoffers' />">Pending</a> 
					</div>
					</div>
					<div class="dropdown">
					<button class="dropbtn">
						Search Offers 
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='#' />">By Student</a> 
						<a href="<c:url value='#' />">By Proffessor</a> 
						<a href="<c:url value='#' />">By ID</a> 
					</div>
					</div>
				</sec:authorize>
				<sec:authorize access="hasRole('STUDENT')">
				<div class="dropdown">
					<button class="dropbtn">
						Tasks
					</button>
					<div class="dropdown-content">
						<a href="<c:url value='/proffessor/printoffers' />">Labs</a> 
						<a href="<c:url value='/proffessor/printoffers' />">Supervising</a> 
						<a href="<c:url value='/proffessor/printoffers' />">Grading</a> 
					</div>
					</div>
				</sec:authorize>
					<li><a href="<c:url value='/logout' />">Log Out</a></li>
				</ul>
			</div>
			
			
</body>
</html>