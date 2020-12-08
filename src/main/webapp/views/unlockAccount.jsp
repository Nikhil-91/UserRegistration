<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unlock Account</title>
</head>
<body>
	<div align="Center">

		<h3>Unlock Account Form</h3>
		<font color="red">${errMsg}</font> <font color="green">${succMsg}</font>
		<form:form action="unlockAccount?email=${unlockAcc.email}" method="POST" modelAttribute="unlockAcc">
			<table>
				<tr>
					<td>Email:</td>
					<td>${unlockAcc.email}</td>
				</tr>
				<tr>
					<td>Temporary Password:</td>
					<td><form:password path="tmpPwd" /></td>
				</tr>
				<tr>
					<td>New Password:</td>
					<td><form:password path="newPwd" /></td>
				</tr>
				<tr>
					<td>Confirm Password:</td>
					<td><form:password path="cnfrmPwd" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="submit" id="submitBtn" /></td>
				</tr>


			</table>
		</form:form>
</div>
</body>
</html>