<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 	<div>${param.error}</div> --%>
	<div>
		<form action="" method="post">
			<table>
				<tr>
					<td>username:</td>
					<td><input name="username"></td>
				</tr>
				<tr>
					<td>password:</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="envoyer"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>