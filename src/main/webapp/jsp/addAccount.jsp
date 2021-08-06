<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- User registration form -->
<form:form id="regForm" modelAttribute="user" action="registerProcess"
	method="post">
	<table align="center" class="reg-table">
		<tr>
			<td><form:label path="login.loginId">Username:</form:label></td>
			<td><form:input path="login.loginId" name="loginId"
					id="loginId" /></td>
		</tr>
		<tr>
			<td><form:label path="login.password">Password:</form:label></td>
			<td><form:password path="login.password" name="password"
					id="password" /></td>
		</tr>
		<tr>
			<td><form:label path="firstName">First Name:</form:label></td>
			<td><form:input path="firstName" name="firstName"
					id="firstName" /></td>
		</tr>
		<tr>
			<td><form:label path="lastName">Last Name:</form:label></td>
			<td><form:input path="lastName" name="lastName" id="lastName" /></td>
		</tr>
		<tr>
			<td><form:label path="email">Email:</form:label></td>
			<td><form:input path="email" name="email" id="email" /></td>
		</tr>
		<tr>
			<td><form:label path="gender">Gender:</form:label></td>
			<td><form:radiobutton path="gender" value="male" />Male <form:radiobutton
					path="gender" value="female" />Female</td>
		</tr>
		<tr>
			<td><form:label path="address.line1">Line1:</form:label></td>
			<td><form:input path="address.line1" name="address"
					id="address" /></td>
		</tr>
		<tr>
			<td><form:label path="address.line2">Line2:</form:label></td>
			<td><form:input path="address.line2" name="address"
					id="address" /></td>
		</tr>
		<tr>
			<td><form:label path="address.city">City:</form:label></td>
			<td><form:input path="address.city" name="address" id="address" /></td>
		</tr>
		<tr>
			<td><form:label path="address.state">State:</form:label></td>
			<td><form:input path="address.state" name="address"
					id="address" /></td>
		</tr>
		<tr>
			<td><form:label path="address.country">Country:</form:label></td>
			<td><form:input path="address.country" name="address"
					id="address" /></td>
		</tr>
		<tr>
			<td><form:label path="address.zip">Zip:</form:label></td>
			<td><form:input path="address.zip" name="address" id="address" /></td>
		</tr>
		<tr>
			<td><form:label path="phoneNumber">Phone:</form:label></td>
			<td><form:input path="phoneNumber" name="phoneNumber"
					id="phoneNumber" /></td>
		</tr>
		<tr>
			<td></td>
			<td><form:button id="register" name="register">Register</form:button></td>
		</tr>
	</table>
</form:form>
<!-- Display error if login failed -->
<table align="center">
	<tr>
		<td style="font-style: italic; color: red;">${message}</td>
	</tr>
</table>