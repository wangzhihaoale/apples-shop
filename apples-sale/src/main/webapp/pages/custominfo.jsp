<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="aStar Fashion Template Project">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>信息</title>
<style type="text/css">
	
	label{
		color:red;
		font-size:20px;
	}

</style>
</head>
<body>

<table>
	<tr>
		<td>
			ID:${user.aid}<br>
		</td>
		<td>
			名称:${user.aname}<br>
		</td>
		<td>
			密码:${user.apass}<br>
		</td>
	</tr>

</table>


</body>

</html>