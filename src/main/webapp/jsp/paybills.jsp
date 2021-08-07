<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pay Bills</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
<link href="resources/css/payBill.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />

</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="selected" value="Pay Bill" />
	</jsp:include>
	<div class="container">
		<c:if test = "${accounts.size() > 0}">
			<!-- Pay Bill form -->
			<form id="payBillForm" action="<%=request.getContextPath()%>/payBill" method="post">
				<table align="center" class="reg-table">
					<tr>
						<td>
							<label>Enter Merchant Name:</label>
						</td>
						<td>
							<input id="merchantName" name="merchantName" value="${bill.merchantName}" required>
						</td>
					</tr>
					<tr>
						<td>
							<label>Enter Merchant Account Number:</label>
						</td>
						<td>
							<input id="merchantAccNo" name="merchantAccNo" value="${bill.merchantAccNo}" required>
						</td>
					</tr>
					<tr>
						<td>
							<label>Select an Account Type:</label>
						</td>
						<td>
							<select class="accountSelect" id="accountId" name="accountId">
								<option value="" ${bill.accountId == '' ? 'selected="selected"' : ''}>Choose an Account Type</option>
								<c:forEach items="${accounts}" var="account" varStatus="tagStatus">
							    	<option value="${account.accountId}" ${bill.accountId == account.accountId ? 'selected="selected"' : ''}>${account.accountType.typeName}</option>
							    </c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<label>Enter Bill Amount:</label>
						</td>
						<td>
							<input id="amount" name="amount" value="${bill.amount}" required>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><button class="btn" type="submit" id="payBill" name="payBill">Pay Bill</button></td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test = "${accounts.size() == 0}">
			<!-- Display error no account type exists -->
		<table align="center">
			<tr>
				<td>
					<p class="error-fixed">Please add a valid account type to Pay Bill.</p>
				</td>
			</tr>
		</table>
		</c:if>
		<!-- Display error if pay bill failed -->
		<table align="center">
			<tr>
				<td>
					<p class="error">${errorMessage}</p>
					<p class="success">${message}</p>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>