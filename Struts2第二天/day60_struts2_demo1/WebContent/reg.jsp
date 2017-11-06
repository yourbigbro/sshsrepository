<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>完全解耦合的方式访问Servlet的API</h1>
	<form action="${pageContext.request.contextPath }/demo1.action" method="post">
		用户名：<input type="text" name="username"/><br/>
		密码：<input type="password" name="password"/><br/>
		性别：<input type="radio" name="sex" value="男"/>男
			<input type="radio" name="sex" value="女"/>女<br/>
		爱好：<input type="checkbox" name="hobby" value="唱歌"/>唱歌
			<input type="checkbox" name="hobby" value="喝酒"/>喝酒
			<input type="checkbox" name="hobby" value="编码"/>编码<br/>
		生日：<input type="text" name="birthday"/><br/>
		<input type="submit" value="提交"/>
	</form>
	
	<!-- 并没有向demo2中输入数据 -->
	<a href="${pageContext.request.contextPath }/demo2.action">完全解耦合的方式访问Servlet的API</a><!-- 和demo1一样是完全解耦合 -->
	
	
	<h1>耦合的方式访问Servlet的API</h1>
	<form action="${pageContext.request.contextPath }/demo3.action" method="post">
		用户名：<input type="text" name="username"/><br/>
		密码：<input type="password" name="password"/><br/>
		性别：<input type="radio" name="sex" value="男"/>男
			<input type="radio" name="sex" value="女"/>女<br/>
		爱好：<input type="checkbox" name="hobby" value="唱歌"/>唱歌
			<input type="checkbox" name="hobby" value="喝酒"/>喝酒
			<input type="checkbox" name="hobby" value="编码"/>编码<br/>
		生日：<input type="text" name="birthday"/><br/>
		<input type="submit" value="提交"/>
	</form>
	
	<h1>实现某些接口的方式访问Servlet的API</h1>
	<form action="${pageContext.request.contextPath }/demo4.action" method="post">
		用户名：<input type="text" name="username"/><br/>
		密码：<input type="password" name="password"/><br/>
		性别：<input type="radio" name="sex" value="男"/>男
			<input type="radio" name="sex" value="女"/>女<br/>
		爱好：<input type="checkbox" name="hobby" value="唱歌"/>唱歌
			<input type="checkbox" name="hobby" value="喝酒"/>喝酒
			<input type="checkbox" name="hobby" value="编码"/>编码<br/>
		生日：<input type="text" name="birthday"/><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>