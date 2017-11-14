<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>封装数据到集合中:list</h1>
	<form action="${pageContext.request.contextPath }/demo4.action" method="post">
		用户名1：<input type="text" name="list[0].username"/>  
		密码1：<input type="password" name="list[0].password"/><br/>
		用户名2：<input type="text" name="list[1].username"/>
		密码2：<input type="password" name="list[1].password"/><br/>
		
		<input type="submit" value="提交"/>
	</form>
	
	<h1>封装数据到集合中:map</h1>
	<form action="${pageContext.request.contextPath }/demo5.action" method="post">
		用户名1：<input type="text" name="map['one'].username"/>  
		密码1：<input type="password" name="map['one'].password"/><br/>
		用户名2：<input type="text" name="map['two'].username"/>
		密码2：<input type="password" name="map['two'].password"/><br/>
		
		<input type="submit" value="提交"/>
	</form>
	
	
</body>
</html>