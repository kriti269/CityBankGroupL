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

<script src="resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<script src="resources/js/bootstrap.bundle.min.js"></script>
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
					$${account.balance}
				</div>
				<div class="account-hidden">
				<input type="hidden" value="${account.accountId}" id="selectedAccountId">
				<input type="hidden" value="${account.balance}" id="selectedBalance">
				<input type="hidden" value="${account.accountType.typeName}" id="selectedAccType">
				</div>
				<div class="account-action">
					<a data-toggle="modal" data-target="#myDepositModal" class="deposit-modal-btn">Deposit</a>
					<c:if test="${account.accountType.typeName != 'Fixed Deposit'}">
					<a data-toggle="modal" data-target="#myWithdrawModal" class="withdraw-modal-btn">Withdraw</a>
					<a data-toggle="modal" data-target="#myTransferModal" class="transfer-modal-btn">Transfer</a>
					</c:if>
				</div>
				
			</div>
			</c:forEach>

	</div>
	<div class="modal" id="myDepositModal">
		<form class="form-deposit" action="<%=request.getContextPath()%>/deposit" method="post">
		<div class="modal-dialog">
		  <div class="modal-content">
		  
			<!-- Modal Header -->
			<div class="modal-header">
			  <h4 class="modal-title">Deposit</h4>
			  <button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body">
			  <div class="modal-desc"></div>
			  <div class="mt10">
				  <label for="amt">Enter Amount</label>
				  <input type="text" name="amount" class="deposit-amount"></input>
				  <input type="hidden" name="account_id" class="deposit-acc"></input>
				</div>
				<div class="text-danger deposit-error" style="display:none;">Invalid deposit amount!</div>
			</div>
			
			<!-- Modal footer -->
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			  <button type="button" id="deposit-btn" class="btn btn-primary">Deposit</button>
			</div>
			
		  </div>
		</div>
		</form>
	</div>
	
	<div class="modal" id="myWithdrawModal">
		<form class="form-withdraw" action="<%=request.getContextPath()%>/withdraw" method="post">
		<div class="modal-dialog">
		  <div class="modal-content">
		  
			<!-- Modal Header -->
			<div class="modal-header">
			  <h4 class="modal-title">Withdraw</h4>
			  <button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body">
			  <div class="modal-desc"></div>
			  <div class="mt10">
				  <label for="amt">Enter Amount</label>
				  <input type="text" name="amount" class="withdraw-amount"></input>
				  <input type="hidden" name="account_id" class="withdraw-acc"></input>
				</div>
				<div class="text-danger withdraw-error" style="display:none;">Invalid withdraw amount!</div>
			</div>
			
			<!-- Modal footer -->
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			  <button type="button" id="withdraw-btn" class="btn btn-primary">Withdraw</button>
			</div>
			
		  </div>
		</div>
		</form>
	</div>
	<c:if test="${alertMessage ne null}">
		<div ${alertMessageType.equals('success') ? 'class="alert-success"' : 'class="alert-error"'}>
		<span class="clsbtn" onclick="this.parentElement.style.display='none';">&times;</span>
		 	<label id="alert-message">${alertMessage}</label>
		</div>
	</c:if>
	<div class="modal" id="myTransferModal">
		<form class="form-transfer" action="<%=request.getContextPath()%>/transferFunds" method="post">
		<div class="modal-dialog">
		  <div class="modal-content">
		  
			<!-- Modal Header -->
			<div class="modal-header">
			  <h4 class="modal-title">Transfer</h4>
			  <button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<!-- Modal body -->
			<div class="modal-body">
			  <div class="modal-desc"></div>
			  <div class="mt10">
				  <label for="amount">Enter Amount: </label>
				  <input type="text" name="amount" class="transfer-amount"></input><br>
				  <label for="toAccount">To Account:	</label>
				  <select name="toAccount" class="accounts-dropdown">
				  </select>
				  <input type="hidden" name="fromAccount" class="transfer-from-account"></input>
				</div>
				<div class="text-danger transfer-error" style="display:none;">Invalid transfer amount!</div>
			</div>
			
			<!-- Modal footer -->
			<div class="modal-footer">
			  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			  <button type="button" id="transfer-btn" class="btn btn-primary">Transfer</button>
			</div>
			
		  </div>
		</div>
		</form>
	</div>
</body>
</html>