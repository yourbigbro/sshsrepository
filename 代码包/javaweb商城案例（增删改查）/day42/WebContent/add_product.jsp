<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><!-- 添加商品 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/imagetable.css">

</head>
<body>
	<table border="1" width="50%" class="imagetable" align="center">
		<tr>
			<th>添加商品</th>
		</tr>
	</table>
	<hr/>
	<form align="center" action="${pageContext.request.contextPath}/CommitProductServlet" method="post">
		<table align="center" border="1" width="50%" class="imagetable">
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="pname"/></td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><input type="text" name="shop_price"/></td>
			</tr>
			<tr>
				<td>商品图片路径</td>
				<td><input type="text" name="pimage"/></td>
			</tr>
			<tr>
				<td>是否热门</td>
				<td><input type="radio" name="is_hot" value="0" checked="checked"/>不热门
				<input type="radio" name="is_hot" value="1"/>热门</td>
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
					<select name="cid"><!-- 注意name和value属性 -->
						<c:if test="${not empty categorys }">
							<c:forEach items="${categorys}" var="category">
								<option value="${category.cid}">${category.cname }</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="添加商品"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>