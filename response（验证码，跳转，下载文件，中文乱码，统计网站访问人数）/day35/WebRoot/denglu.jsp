<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<!-- WebRoot里面存储的是和页面有关的文件，WEB-INF里面存储的是无法通过浏览器直接访问到的文件，因而只能存放配置文件。另外注意相对路径的写法 -->
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#tijiao").click(function(){
		$("form").submit();
	});
	
	
});
$(function(){
	$("img").click(function(){
		$(this).prop("src","${pageContext.request.contextPath }/app/VerifyServlet?t="+new Date().getTime());
	});
});
</script>
</head>
<body>
<!-- 注意将.html后缀转换成.jsp -->
<!-- 全路径要有项目名和包名，文件名没有.java后缀 -->
<!-- 下面还可以写成相对路径形式 <form action="day35/a">-->
<form action="http://localhost:8080/day35/app/DengLuServlet">
用户名：<input type="text" name="username" id="username"><br>
密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" id="password"><br>
验证码：<input type="text" name="verifyCode" />
<img  src="${pageContext.request.contextPath }/app/VerifyServlet"><br />
<input id="tijiao" type="submit" value="点击提交">
</form>
</body>
</html>