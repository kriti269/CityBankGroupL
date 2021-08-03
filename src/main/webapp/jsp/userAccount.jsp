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
</head>
<body>


	<%-- <c:if test="${sessionScope.user_id != null}">
		<h1>Welcome ${sessionScope.user_id}</h1>
	</c:if> --%>
	<table>
		<tr>
			<th>Account Type</th>
			<th>Balance</th>
			<th></th>
		</tr>

		<c:forEach items="${accounts}" var="account" varStatus="tagStatus">

			<tr>
				<td>${account.accountType.typeName}</td>
				<td>${account.balance}</td>





			</tr>
		</c:forEach>
	</table>

</body>
</html>