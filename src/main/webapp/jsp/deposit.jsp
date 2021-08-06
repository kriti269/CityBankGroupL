<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
<link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/deposit.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="selected" value="Account" />
	</jsp:include>
	<div class="container"><%= request.getParameter("accountId") %></div>
	
</body>
</html>
