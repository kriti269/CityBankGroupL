<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Transactions</title>
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
<link href="resources/css/billpayments.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="selected" value="View Bills" />
	</jsp:include>
	<div class="container">
		<div class="pb-bar">
			<div class="pb-head">View Bills</div>
			<div class="pb-body">
				<table align="center">
					<tr>
						<th>
							Bill Id
						</th>
						<th>
							Transaction Date
						</th>
						<th>
							Amount
						</th>
						<th>
							Merchant Account
						</th>
						<th>
							Merchant Name
						</th>
						<th>
							Account Type
						</th>
					</tr>
					<c:forEach items="${listOfBillPayments}" var="billpayment" varStatus="tagStatus">
						<tr>
							<td>
								${billpayment.bill.billId}
							</td>
							<td>
								${billpayment.transaction.txDateTime}
							</td>
							<td>
								${billpayment.transaction.amount}
							</td>
							<td>
								${billpayment.bill.merchantAccount}
							</td>
							<td>
								${billpayment.bill.merchantName}
							</td>
							<td>
								${billpayment.bill.account.accountType.typeName}
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>