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
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
<link href="resources/css/userAccount.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<jsp:directive.include file = "header.jsp" />
	<div class="container">
			<c:forEach items="${accounts}" var="account" varStatus="tagStatus">
			<div class="account-bar">
				<div class="account-head">${account.accountType.typeName}</div>
				<div class="account-body">
					CBS0000${account.accountId}
					${account.balance}
				</div>
			</div>
			</c:forEach>

	</div>
</body>
</html>