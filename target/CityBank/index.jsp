<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>City Bank Home Page</title>
<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
</head>
<body>

	<table align="center">
		<tr>
			<!-- Links to login and register pages -->
			<td><a href="<%=request.getContextPath()%>/login">Login</a></td>
			<td><a href="<%=request.getContextPath()%>/register">Register</a></td>
		</tr>
	</table>

</body>
</html>