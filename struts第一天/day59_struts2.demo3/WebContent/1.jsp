<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Action方法的3种访问方式一：动态方法调用</h1>
	<a href="${pageContext.request.contextPath }/user!login.action">登录</a>
	<a href="${pageContext.request.contextPath }/user!register.action">注册</a>
	<!-- 只标出了类名没标出方法名，方法名在配置文件里面 -->
	<h1>Action方法的3种访问方式二：通过method属性配置</h1>
	<a href="${pageContext.request.contextPath }/loginUser.action">登录</a>
	<a href="${pageContext.request.contextPath }/registerUser.action">注册</a>
	
	<h1>Action方法的3种访问方式三：通过通配符的配置方式：</h1>
	<a href="${pageContext.request.contextPath }/loginUser.action">登录</a>
	<a href="${pageContext.request.contextPath }/registerUser.action">注册</a>
	
	<h1>Action方法的3种访问方式三：玩一玩</h1>
	<a href="${pageContext.request.contextPath }/login_User.action">登录</a>
	<a href="${pageContext.request.contextPath }/register_User.action">注册</a>
	<br/>
	<a href="${pageContext.request.contextPath }/add_Product.action">添加商品</a>
	<a href="${pageContext.request.contextPath }/update_Product.action">修改商品</a>
</body>
</html>