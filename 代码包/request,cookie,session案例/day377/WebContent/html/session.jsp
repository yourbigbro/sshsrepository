<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=function(){
		document.getElementsByTagName("input")[3].onclick=function(){
			document.getElementsByTagName("form")[0].submit();
		}
		
		document.getElementsByTagName("img")[0].onclick=function(){
			this.src="/day377/servlet/VerifyCodeServlet?t="+new Date().getTime();
		}
	}
</script>
</head>
<body>
	<form action="/day377/servlet/LoginServlet"><!-- 前台的相对路径相对于webapp文件夹 -->
		用户名：<input type="text" name="username"/><br>
		密&nbsp;&nbsp;&nbsp;码：<input type="text" name="username"/><br>
		验证码：<input type="text" name="verifycode"/>
		<img  src="/day377/servlet/VerifyCodeServlet"/><br><!-- 只要是servlet都要配置xml文件或者在类文件中添加注释 -->
		<input type="button" value="点击提交"/>
	</form>
</body>
</html>