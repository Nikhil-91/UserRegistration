<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password Form</title>
</head>
<body>
	
	<div align="Center">
	<font color="red">${errMsg}</font> <font color="green">${succMsg}</font>
	<h3>Forgot Password Form</h3>
		<form action="recoverPwd" method="POST">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form>
		<a href="/">Login Page</a>
	</div>
</body>
</html>