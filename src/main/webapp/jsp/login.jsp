<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
	<meta charset="ISO-8859-1">
    <head>
        <title>Login</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script type="text/javascript" src="resources/js/common.js"></script>
        <link href="resources/css/login.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
    </head>
<head>
<body>
	<div class="container">
		<h2>City Bank Login</h2><br> 
		<!-- Login form -->
		<form:form id="loginForm" modelAttribute="login" action="loginProcess"
			method="post">
			<table align="center">
				<tr>
					<td><form:label path="loginId">Username: </form:label></td>
					<td><form:input class="uname" path="loginId" name="loginId" id="loginId" /></td>
				</tr>
				<tr>
					<td><form:label path="password">Password:</form:label></td>
					<td><form:password class="pass" path="password" name="password"
							id="password" /></td>
				</tr>
				<tr>
					<td colspan="2" align="left"><form:button class="btn" id="login" name="login">Login</form:button></td>
				</tr>
				<tr></tr>
				<!--  <tr>-->
					<!--  <td></td> -->
					<!-- Link to Home page -->
					<!--  <td><a href="<%=request.getContextPath()%>/index">Home</a></td> -->
				<!--  </tr> -->
			</table>
		</form:form>
		<!-- Display error if login failed -->
		<table align="center">
			<tr>
				<td class="error">${message}</td>
			</tr>
		</table>
	</div>
</body>
</html>