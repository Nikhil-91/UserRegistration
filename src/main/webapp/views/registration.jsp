<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<div align="Center">

		<h3>Registration Form</h3>
		<font color="red">${errMsg}</font> <font color="green">${succMsg}</font>
		<form:form action="register" method="POST" modelAttribute="user">
			<table>
				<tr>
					<td>First Name:</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="userEmail" /></td>
					<td><span id="errEmail"></span></td>
				</tr>
				<tr>
					<td>PhNo:</td>
					<td><form:input path="userMobile" /></td>
				</tr>
				<tr>
					<td>Dob:</td>
					<td><form:input path="dob" /></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><form:radiobutton path="gender" value="M" /> Male <form:radiobutton
							path="gender" value="F" /> Female</td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><form:select path="countryId">
							<form:option value="">--Select--</form:option>
							<form:options items="${countries}" />
						</form:select></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><form:select path="stateId">
							<form:option value="">--Select--</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><form:select path="cityId">
							<form:option value="">--Select--</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td><input type="reset" value="Reset" /></td>
					<td><input type="submit" value="Register" id="submitBtn" /></td>
				</tr>


			</table>
		</form:form>
<a href="/">Login Page</a>
	</div>
	<script>
		$(document).ready(function() {
			
			$("#userEmail").blur(function() {
				$("#errEmail").text("");
				$.ajax({
				    type : "GET",
				    url : "uniqueMailCheck?email="+$("#userEmail").val(),
				    success: function(data){
				    	console.log(data)
				   		if(data == "DUPLICATE"){
				   			$("#errEmail").text("Duplicate Email");
				   			$("#submitBtn").prop("disabled", true);
						}else{
							$("#submitBtn").prop("disabled", false);
						}
				   		}
				    
				});
			})
		 
			$("#countryId").change(function(){
				$('#stateId option:not(:first)').remove();
				$('#cityId option:not(:first)').remove();
				$.ajax({
				    type : "GET",
				    url : "countryChange?countryId="+$("#countryId").val(),
				    success: function(data){
				    	$.each(data, function(key, value) {
				            $('#stateId').append($('<option>').text(value).attr('value', key));
				        });
				    }
				    
				});
				
			})
			
			$("#stateId").change(function(){
				$('#cityId option:not(:first)').remove();
				$.ajax({
				    type : "GET",
				    url : "stateChange?stateId="+$("#stateId").val(),
				    success: function(data){
				    	$.each(data, function(key, value) {
				            $('#cityId').append($('<option>').text(value).attr('value', key));
				        });
				    }
				    
				});
				
			})
		
		});

	</script>

</body>
</html>