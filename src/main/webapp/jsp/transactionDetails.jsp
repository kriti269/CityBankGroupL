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
<link href="resources/css/transaction.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="selected" value="Transaction" />
	</jsp:include>
	<div class="container">
		<div class="tx-bar">
			<div class="tx-head">Transactions</div>
			<div class="tx-body">
				<table align="center">
					<tr>
						<th>
							Transaction Id
						</th>
						<th>
							Transaction Date
						</th>
						<th>
							Transaction Type
						</th>
						<th>
							Account Id
						</th>
						<th>
							Account Type
						</th>
						<th>
							Amount
						</th>
					</tr>
					<c:forEach items="${listOfTransactions}" var="transaction" varStatus="tagStatus">
						<tr>
							<td>
								${transaction.transactionId}
							</td>
							<td>
								${transaction.txDateTime}
							</td>
							<td>
								${transaction.txType}
							</td>
							<td>
								CBS0000${transaction.account.accountId}
							</td>
							<td>
								${transaction.account.accountType.typeName}
							</td>
							<td>
								$${transaction.amount}
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>