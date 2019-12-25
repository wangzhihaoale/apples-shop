<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="aStar Fashion Template Project">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录</title>
<style type="text/css">
	
	label{
		color:red;
		font-size:20px;
	}

</style>
</head>
<body>
<form action="/login" method="post">
	账号：<input name="aname" type="text"><br/>
	密码：<input name="apass" type="password"><br/>
	<label>${errorMsg}</label><br/>
	<input type="submit" value="登录">
</form>
	<%--<form action="login" method="post">

		name:<input type="text" name="aacount"/> <br />
		pass:<input type="password" name="apass"/> <br />
		<label>${errorMsg}</label><br/>
		<input type="submit" value="登录">
	</form>--%>
</body>

</html>