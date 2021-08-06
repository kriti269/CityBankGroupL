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

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="resources/js/account.js"></script>
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="selected" value="Account" />
	</jsp:include>
	<div class="container">
			<c:forEach items="${accounts}" var="account" varStatus="tagStatus">
			<div class="account-bar">
				<div class="account-head">${account.accountType.typeName}</div>
				<div class="account-body">
					CBS0000${account.accountId}
					${account.balance}
				</div>
				<input type="hidden" value="${account.accountId}" id="selectedAccountId">
				<input type="hidden" value="${account.balance}" id="selectedBalance">
				<div class="account-action">
					<a data-toggle="modal" data-target="#myDepositModal" class="deposit-modal-btn">Deposit</a>
					<a data-toggle="modal" data-target="#myWithdrawModal" class="withdraw-modal-btn">Withdraw</a>
				</div>
				
			</div>
			</c:forEach>

	</div>
	<div class="modal" id="myDepositModal">
		<div class="modal-dialog">
		  <div class="modal-content">
		  
			<!-- Modal Header -->
			<div class="modal-header">
			  <h4 class="modal-title"></h4>
			  <button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body">
			  <div class="modal-desc"></div>
			  <div class="mt10">
				  <label for="amt">Enter Amount</label>
				  <input type="text" name="amount" class="deposit-amount" ></input>
				</div>
			</div>
			
			<!-- Modal footer -->
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			  <button type="button" id="deposit-btn" class="btn btn-primary" data-dismiss="modal">Deposit</button>
			</div>
			
		  </div>
		</div>
	</div>
	
	<div class="modal" id="myWithdrawModal">
		<div class="modal-dialog">
		  <div class="modal-content">
		  
			<!-- Modal Header -->
			<div class="modal-header">
			  <h4 class="modal-title"></h4>
			  <button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body">
			  <div class="modal-desc"></div>
			  <div class="mt10">
				  <label for="amt">Enter Amount</label>
				  <input type="text" name="amount" class="withdraw-amount"></input>
				</div>
			</div>
			
			<!-- Modal footer -->
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			  <button type="button" id="withdraw-btn" class="btn btn-primary" data-dismiss="modal">Withdraw</button>
			</div>
			
		  </div>
		</div>
	</div>
</body>
</html>