<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/imagetable.css">
<style>
	a{
		text-decoration:none;
		color:gray;
	}
</style>
<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script>
	//添加商品
	function addProduct(){
		location.href="${pageContext.request.contextPath }/AddProductServlet";
	}
	//删除多个商品
	function deleteManyProduct(){
		var count=$("[type='checkbox']:checked").size();//用选择器显示删除的个数
		if(confirm("确认删除选中的"+count+"个商品吗？")){
			$("form")[0].submit();
		}else{
			//刷新页面
			location.reload();
		}
		
	}
	//编辑商品
	function editProduct(pid){//js里面参数里面的变量不用使用var声明
		location.href="${pageContext.request.contextPath}/EditProductServlet?pid="+pid;
	}
	//删除单个商品
	function deleteOneProduct(pid){
		location.href="${pageContext.request.contextPath}/DeleteOneProductServlet?pid="+pid;
	}
</script>
</head>
<body>
	<table border="1" width="40%" class="imagetable" align="center" >
		<tr>
			<th>商品列表</th>
			<th><a href="javascript:void(0);" onclick="addProduct()">添加商品</a></th>
		</tr>
	</table>
	<hr/>
	<table border="1" width="100%" class="imagetable">
	<!-- 该表格用于筛选商品 -->
	<form class="query" method="post" action="${pageContext.request.contextPath }/ChooseProductServlet">
		<font>选择商品分类</font>
		<select name="selectcname">
			<c:forEach var="category" items="${categorys }"><!-- 注意是${categorys }而不是categorys -->
				<option  value="${category.cid }">${category.cname }</option>
			</c:forEach>
		</select>
		<font>输入商品描述信息</font>
		<input type="text" name="describe">
		<input type="submit" value="点击查询" id="sele">
	</form>
	<!-- 用于设置分页 -->
		<c:if test="${not empty pageBean.data }">
		<!-- 上一页 --><!-- 还未进行页码元素功能的配置！！！！！！！！！！！！！！！！！！！！！！！！ -->
		<a <c:choose >
			<c:when test="${pageBean.pageNumber!=1 }">href="${pageContext.request.contextPath }/PageServlet?pageNumber=${pageBean.pageNumber-1 }"</c:when>
			<c:otherwise>href="javascript:void(0);"</c:otherwise>
		</c:choose>>上一页</a>
			<!-- 页码 -->
			<c:forEach begin="1" end="${pageBean.totalPage }" var="i"><!-- 分页的按钮个数，用属性不用方法？？？？？？？!!!!!!!!! -->
			<a <c:choose >
			       <c:when test="${pageBean.pageNumber!=i }">href="${pageContext.request.contextPath }/PageServlet?pageNumber=${i }"</c:when>
			       <c:otherwise>href="javascript:void(0);"</c:otherwise>
			</c:choose>>${i }&nbsp;&nbsp;&nbsp;</a>
			</c:forEach>
		<!-- 下一页 -->
		<a <c:choose >
			<c:when test="${pageBean.totalPage!=pageBean.pageNumber }">href="${pageContext.request.contextPath }/PageServlet?pageNumber=${pageBean.pageNumber+1 }"</c:when>
			<c:otherwise>href="javascript:void(0);"</c:otherwise>
		</c:choose>>下一页</a>
		</c:if>
	<!--该表格用于展示商品 -->
	  <form action="${pageContext.request.contextPath}/DeleteManyProductServlet" method="get">
	  	<tr>
			<th><a href="javascript:void(0);" onclick="deleteManyProduct()">删除商品</a></th>
			<th>商品序号</th>
			<th>商品名称</th>
			<th>商品图片</th>
			<th>商品价格</th>
			<th>商品描述</th>
			<th>修改商品</th>
		</tr>
		<c:if test="${not empty products }">
			<c:forEach items="${products }" var="product" varStatus="status">
			<tr>
				<td><input type="checkbox" name="delete" value="${product.pname }"></td>
				<td>${status.count }</td>
				<td>${product.pname }</td>
				<td ><img width="45px" height="45px" alt="" src="${pageContext.request.contextPath }/${product.pimage }"></td><!-- 注意是contextPath而不是ContextPath -->
				<td>${product.shop_price }</td>
				<td>${product.pdesc }</td>
				<td>
					<input type="button" value="修改商品" onclick="editProduct(${product.pid})">
					<input type="button" value="删除商品" onclick="deleteOneProduct(${product.pid})">
				</td>
			</tr>
			</c:forEach>
		</c:if>
	  </form>
	</table>
</body>
</html>