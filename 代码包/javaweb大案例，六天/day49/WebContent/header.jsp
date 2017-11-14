<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 这个标签不导的话下面的c标签会没效果 -->
<script type="text/javascript">
	$(function(){
		//查询商品分类信息作为小标题
		$.post("${pageContext.request.contextPath}/UserServlet",{"type":"showClassify"},function(data){//注意，键值对都加引号
			//解析和处理后台传回来的json数据
			$(data).each(function(i,m){//注意，data不加引号
				var temp="<li><a href='${pageContext.request.contextPath}/UserServlet?type=queryClassify&pageNumber=1&cid="+m.cid+"'>"+m.cname+"</a></li>";
				$(".navbar-nav").append(temp);
			});
		},"json");
		//查询热门商品
		$.post("${pageContext.request.contextPath}/UserServlet",{"type":"showHot"},function(data){//注意，键值对都加引号
			//解析和处理后台传回来的json数据
			$(data).each(function(i,m){//注意，data不加引号
				var temp="";
				temp+="<div class='col-md-2' style='text-align:center;height:200px;padding:10px 0px;'>";
				temp+="<a href='product_info.htm'>";
				temp+="<img src='${pageContext.request.contextPath}/"+m.pimage+"' width='130' height='130' style='display: inline-block;'>";
				temp+="</a>";	
				temp+="<p><a href='product_info.html' style='color:#666'>"+m.pname+"</a></p>";
				temp+="<p><font color='#E4393C' style='font-size:16px'>&yen;"+m.shop_price+"</font></p>";
				temp+="</div>";
				$("#hot").append(temp);
			});
		},"json");
		//查询最新商品
		$.post("${pageContext.request.contextPath}/UserServlet",{"type":"showNew"},function(data){//注意，键值对都加引号
			//解析和处理后台传回来的json数据
			$(data).each(function(i,m){//注意，data不加引号
				var temp="";
				temp+="<div class='col-md-2' style='text-align:center;height:200px;padding:10px 0px;'>";
				temp+="<a href='product_info.htm'>";
				temp+="<img src='${pageContext.request.contextPath}/"+m.pimage+"' width='130' height='130' style='display: inline-block;'>";
				temp+="</a>";	
				temp+="<p><a href='product_info.html' style='color:#666'>"+m.pname+"</a></p>";
				temp+="<p><font color='#E4393C' style='font-size:16px'>&yen;"+m.shop_price+"</font></p>";
				temp+="</div>";
				$("#new").append(temp);
			});
		},"json");
	})
</script>
<!--
         	描述：菜单栏
         -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5">
		<img src="${pageContext.request.contextPath}/img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
		<!-- 注意，下面的连接都通向页面而不是后台 -->
			<li><a href="${pageContext.request.contextPath}/login.jsp">登录</a></li>
			<li><a href="${pageContext.request.contextPath}/register.jsp">注册</a></li>
			<li><a href="${pageContext.request.contextPath}/cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath}/UserServlet?type=logout">退出登录</a></li>
			<li><a href="${pageContext.request.contextPath}/UserServlet?type=myOrder&pageNumber=1">我的订单</a></li>
		</ol>
		<c:if test="${not empty user}">
			欢迎您，${user.name } 
		</c:if>
	</div>
</div>
<!--
         	描述：导航条
         -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">首页</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</div>