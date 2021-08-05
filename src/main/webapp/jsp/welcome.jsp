<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Welcome</title>
	<link href="resources/css/main.css" rel="stylesheet" type="text/css">
	<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<jsp:directive.include file = "header.jsp" />
	<div style="padding-left:20px; margin-top: 50px;">
		<jsp:directive.include file = "register.jsp" />
	</div>
</body>
</html>