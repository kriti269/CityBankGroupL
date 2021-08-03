<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<table>
		<tr>
			<!-- first name from model set in controller class  -->
			<td>Welcome ${firstname}</td>
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>
		<tr>
			<!--  Link to Home page -->
			<td><a href="<%=request.getContextPath()%>/jsp/home.jsp">Home</a></td>
		</tr>
	</table>

</body>
</html>