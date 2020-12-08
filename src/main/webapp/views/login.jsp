<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	
	<div align="Center">
	<font color="red">${msg}</font>
	<h3>Sign In Here</h3>
		<form action="saveLogin" method="POST">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Sign In" /></td>
				</tr>
				<tr>
					<td><a href="recoverPwd">Forgot Password?</a></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp; <a href="loadRegForm">Sign-Up</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>