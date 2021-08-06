<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Account</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="resources/js/addAccount.js"></script>
<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />

</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="selected" value="Add Account" />
	</jsp:include>
	<div class="container">
		<!-- Add Account form -->
		<form id="addAccountForm">
			<table align="center" class="reg-table">
				<tr>
					<td>
						<label>Select a User:</label>
					</td>
					<td>
						<select id="user" name="user">
							<option value="">Choose a User</option>
							<c:forEach items="${all_users}" var="user" varStatus="tagStatus">
						    	<option value="${user.userId}">${user.login.loginId}</option>
						    </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<label>Select an Account Type:</label>
					</td>
					<td>
						<select id="accountType" name="accountType">
							<option value="">Choose an Account Type</option>
							<c:forEach items="${all_account_types}" var="account_type" varStatus="tagStatus">
						    	<option value="${account_type.accountTypeId}">${account_type.typeName}</option>
						    </c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><button class="btn" type="button" id="addAccount" name="addAccount">Add Account</button></td>
				</tr>
			</table>
		</form>
		<!-- Display error if login failed -->
		<table align="center">
			<tr>
				<td style="font-style: italic; color: red;"><label id="error"></label></td>
				<td style="font-style: italic; color: green;"><label id="success"></label></td>
			</tr>
		</table>
	</div>
