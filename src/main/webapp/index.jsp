<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>City Bank Home Page</title>
	<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
    <link href="resources/css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="myDiv">
		<div class="bg"></div>
		<!-- Links to login and register pages -->
		<div class="main">
			<img class="user" src="resources/images/user.png">
			<h1 class="heading">Welcome to City Bank</h1>
			<div class="welcome">
				<a href="<%=request.getContextPath()%>/login">Login</a>
				<!--  <a href="<%=request.getContextPath()%>/admin/login">Admin Login</a> -->
			</div>
		</div>
	</div>
</body>
</html>