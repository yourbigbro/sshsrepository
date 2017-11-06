<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 这个项目讲的是action类的三种启动方式 -->
	<!-- 访问的路径的后缀名是.action。但是不加后缀也行 -->
	<a href="${pageContext.request.contextPath }/demo1.action">方法一</a>
	<a href="${pageContext.request.contextPath }/demo2.action">方法二</a>
	<a href="${pageContext.request.contextPath }/demo3.action">方法三</a>
</body>
</html>