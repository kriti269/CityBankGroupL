<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Users</title>
<link href="resources/css/main.css" rel="stylesheet" type="text/css">
<link href="resources/css/users.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="selected" value="View Users" />
	</jsp:include>
	<div class="users-container">
		<div class="users-bar">
			<div class="users-head"></div>
			<div class="users-body">
				<table align="center" class="users-table">
					<tr>
						<th>
							Login Id
						</th>
						<th>
							Email
						</th>
						<th>
							User Type
						</th>
						<th>
							Name
						</th>
						<th>
							Phone
						</th>
						<th>
							Gender
						</th>
						<th>
							Address
						</th>
						<th>
							City 
						</th>
						<th>
							State 
						</th>
						<th>
							Zip Code 
						</th>
					</tr>
					<c:forEach items="${users_list}" var="user" varStatus="tagStatus">
						<tr>
							<td>
								${user.login.loginId}
							</td>
							<td>
								${user.email}
							</td>
							<td>
								<c:if test="${user.isAdmin == 'true'}">
									Administrator
								</c:if>
								<c:if test="${user.isAdmin == 'false'}">
									Customer
								</c:if>
							</td>
							<td>
								${user.firstName} ${user.lastName}
							</td>
							
							<td>
								${user.phoneNumber}
							</td>
							<td class="caps">
								${user.gender}
							</td>
							<td>
								${user.address.line1}
							</td>
							<td>
								${user.address.city}
							</td>
							<td>
								${user.address.state}
							</td>
							<td>
								${user.address.zip}
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>