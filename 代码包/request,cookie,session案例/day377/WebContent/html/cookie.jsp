<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=function(){
		document.getElementsByTagName("input")[1].onclick=function(){
			document.getElementsByTagName("form")[0].submit();
		}
	}
</script>
</head>
<body>
	<form action="/day377/servlet/CookieServlet">
		输入用户名：<input type="text" name="username" value="hehe"/>
		<input type="button" value="点击提交"/>
	</form>
</body>
</html>