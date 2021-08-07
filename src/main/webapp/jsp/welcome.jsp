<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Register User</title>
	<link href="resources/css/main.css" rel="stylesheet" type="text/css">
	<link href="resources/css/register.css" rel="stylesheet" type="text/css">
	<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="selected" value="Register User" />
	</jsp:include>
	<div style="padding-left:20px; margin-top: 50px;" class="reg-container">
		<jsp:directive.include file = "register.jsp" />
	</div>
</body>
</html>