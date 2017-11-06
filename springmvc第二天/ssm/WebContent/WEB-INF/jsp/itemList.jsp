<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<title>查询商品列表</title>
</head>
<body> 
<form action="${pageContext.request.contextPath }/deleteMany.action" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<!-- 该提交表单的按钮用于批量删除 -->
<td><input type="submit" value="批量修改和删除"/></td>
<!-- 注意引用的方法后面要加括号 -->
<td><input type="button" value="JsonToJava" onclick="jsonToJava()"/></td>
<script type="text/javascript">
	//定义方法
	function jsonToJava(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/testJsonToJava.action",
			//设置数据格式和数据编码格式
			contentType:"application/json;charset=utf-8",
			//注意，json外面必须加引号，变成字符串格式
			data:'{"id": 1,"name": "测试商品","price": 99.9,"detail": "测试商品描述","pic": "123456.jpg"}',
			//返回参数并解析
			success:function(data){
				alert(data);
			}
		});
	}
</script>
</tr>
</table>
商品列表：
<table width="100%" border=1>
<tr>
	<td>选择删除</td>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
<c:forEach items="${itemList }" var="item" varStatus="s">
<tr>
	<!-- 注意区分用于提交的信息和后台返回得到的信息，前者必须有索引，后者会被自动遍历和赋值，因此不用索引 -->
	<td><input type="checkbox" name="ids" value="${item.id }"/></td>
	<td><input name="itemList[${s.index}].id" type="hidden" value="${item.id }"/><input name="itemList[${s.index}].name" type="text" value="${item.name }"/></td>
	<td><input name="itemList[${s.index}].price" type="text" value="${item.price }"/></td>
	<td><input name="itemList[${s.index}].createtime" type="text" value="<fmt:formatDate value='${item.createtime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/></td>
	<td><input name="itemList[${s.index}].detail" type="text" value="${item.detail }"/></td>
	<td><a href="${pageContext.request.contextPath }/itemEdit.action?id=${item.id}">修改</a></td>

</tr>
</c:forEach>

</table>
</form>
</body>

</html>