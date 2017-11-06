<%@page import="domain.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	欢迎你，现在的时间是：<%@ include file="time.jsp" %><br/><!-- 导入静态页面 -->
	<table align="center" border="1px solid green"><!-- center表示table居中 -->
	<tr align="center">
		<th>商品ID</th>
		<th>商品名称</th>
		<th>商品价格</th>
		<th>商品描述</th>
	</tr>
	<c:choose>
		<c:when test="${not empty products }">
			<c:forEach step="1" begin="0" var="product" items="${products}" varStatus="status">
				<tr align='center'>
					<td>${product.pid}</td>
					<td>${product.pname}</td>
					<td>${product.pprice}</td>
					<td>${product.pdesc}</td>
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
	</table>
</body>
</html>