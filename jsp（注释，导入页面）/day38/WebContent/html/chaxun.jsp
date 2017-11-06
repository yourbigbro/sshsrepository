<%@page import="domain.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<%
		List<Product> products=(List<Product>)pageContext.findAttribute("products");//注意转型，因为得到的类型是object
		if(products!=null&&products.size()>0){
			for(Product product:products){//注意必须加Product在product前面
				out.write("<tr align='center'>");
				out.write("<td>"+product.getPid()+"</td>");
				out.write("<td>"+product.getPname()+"</td>");
				out.write("<td>"+product.getPprice()+"</td>");
				out.write("<td>"+product.getPdesc()+"</td>");
				out.write("</tr>");
				
			}
		}
	%>
	</table>
</body>
</html>