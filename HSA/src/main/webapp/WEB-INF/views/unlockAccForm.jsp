<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
	function validatePwds() {
		var newPwd = $('#newPwd').val();
		var confirmPwd = $('#confirmPwd').val();
		if (newPwd != confirmPwd) {
			$('#errId').text('New Password & Confirm Password Should Be Same');
			return false;
		}
		return true;
	}
</script>

</head>
<body>




	<h2>Unlock Account Here</h2>

<p style="color:blue;">${msg}</p>

	<form:form action="unlockUserAcc" modelAttribute="unlockAcc"
		method="POST">

		<font color='red'><span id="errId"></span></font>

		<table>
			<tr>
				<td>Your Email :</td>
				<td><form:input path="email" value="${email}"/>
			</tr>
			<tr>
				<td>Enter Temporary Password :</td>
				<td><form:password path="tempPwd" /></td>
			</tr>
			<tr>
				<td>Enter New Password :</td>
				<td><form:password path="newPwd" /></td>
			</tr>
			<tr>
				<td>Confirm New Password :</td>
				<td><form:password path="confirmPwd" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Unlock"
					onclick="javascript:return validatePwds()" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>