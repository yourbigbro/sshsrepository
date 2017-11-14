<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 只有登录才能显示下面的按钮 -->
	<shiro:authenticated>
		<a href="www.baidu.com">呵呵1</a>
	</shiro:authenticated>
	
	<!-- 只有不登录才能显示下面的按钮(登陆之后就不能再显示了) -->
	<shiro:notAuthenticated>
		<a href="www.baidu.com">呵呵2</a>
	</shiro:notAuthenticated>
	
	<!-- 只有为aaa的角色才能显示下面的按钮 -->
	<shiro:hasRole name="aaa">
		<a href="www.baidu.com">呵呵3</a>
	</shiro:hasRole>
	
	<!-- 需要当前用户有bbb权限才能显示下面的按钮(用特定权限进行权限校验是最常用的，其他的都不常用) -->
	<shiro:hasPermission name="bbb">
		<a href="www.baidu.com">呵呵3</a>
	</shiro:hasPermission>

</body>
</html>