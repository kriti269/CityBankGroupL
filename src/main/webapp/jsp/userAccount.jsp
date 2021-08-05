<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Books</title>
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
<link href="resources/css/userAccount.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:directive.include file = "header.jsp" />
	<%-- <c:if test="${sessionScope.user_id != null}">
		<h1>Welcome ${sessionScope.user_id}</h1>
	</c:if> --%>
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