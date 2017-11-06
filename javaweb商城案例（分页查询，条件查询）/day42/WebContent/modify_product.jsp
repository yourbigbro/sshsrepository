<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/imagetable.css">

</head>
<body>
	<table border="1" width="40%" class="imagetable" align="center">
		<tr>
			<th>修改商品</th>
		</tr>
	</table>
	<hr/>
	<form action="${pageContext.request.contextPath}/ModifyServlet" method="post">
		<table border="1" width="100%" class="imagetable">
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="pname" /></td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><input type="text" name="shop_price" /></td>
			</tr>
			<tr>
				<td>商品图片路径</td>
				<td><input type="text" name="pimage"/></td>
			</tr>
			<tr>
				<td>是否热门</td>
				<td><input type="radio" name="is_hot" value="0"   <c:if test="${product.is_hot==0}">checked="checkded"</c:if>  />不热门
				<input type="radio" name="is_hot" value="1" <c:if test="${product.is_hot==1}">checked="checkded"</c:if>/>热门</td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td>
					<textarea name="pdesc"></textarea>
				</td>
			</tr>
			<tr>
				<td>商品分类</td>
				<td>
					<select name="cid">
						
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="修改商品"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>