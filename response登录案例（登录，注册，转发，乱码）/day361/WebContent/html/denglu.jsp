<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-1.11.0.min.js"></script>
<script>
	$(function(){
		$("#one").click(function(){
			$("form")[0].submit();
		});
		
		$("#two").click(function(){
			$("form")[1].submit();
		});
		
		$("img").click(function(){
			this.src="${pageContext.request.contextPath }/VerifyServlet?t="+new Date().getTime();//注意，该方法是js对象的方法而不是jquery对象的方法
		});
	})
</script>
</head>
<body>
<form action="../servlet/DengLu" >
	用户名：<input type="text" name="ausername"/><br>
	密&nbsp;&nbsp;&nbsp;码：<input type="password" name="apassword"/><br>
	验证码：<input type="text" name="verifyCode" /><img  src="${pageContext.request.contextPath }/VerifyServlet"><br />
	爱&nbsp;&nbsp;&nbsp;好：<input type="checkbox" name="hobby" value="chouyan"/>抽烟
							   <input type="checkbox" name="hobby" value="hejiu"/>喝酒
							   <input type="checkbox" name="hobby" value="tangtou"/>烫头<br>
	<input type="button" id="one" value="点击登录"/>
</form>

<form action="zhuce.html">
	<input type="button" value="点击注册" id="two"/>
</form>

</body>
</html>