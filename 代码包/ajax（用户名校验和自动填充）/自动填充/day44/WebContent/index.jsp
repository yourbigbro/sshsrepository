<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1" />
		<title>传智播客--商城首页</title>
		<link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>	
		<script src="${pageContext.request.contextPath}/lib/jquery/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/main.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				//页面加载完成之后执行下面的代码
				$("#completeShow").slideUp(0);//初始化页面的时候收起列表
				$("#itemul").empty();//每次都先清空以前的信息，不能用html("")
				$("#searchsql").keyup(function(){
					$("#itemul").empty();//每次都先清空以前的信息，不能用html("")
					$("#completeShow").slideUp(0);
					var value=this.value;//得到表单中用户输入的值
					$.ajax({
						type:"post",
						url:"${pageContext.request.contextPath}/AjaxServlet",
						data:{productname:value},//注意用冒号间隔！！！！！！！！！
						success:function(data){
							
							if(data!=""){//证明data是一个json集合对象
								//alert(data.length);//得到的是数组
								
								for(i=0;i<data.length;i++){
									$("#itemul").append("<li class='list-group-item'><a href='#'>"+data[i].word+"</a></li>");
								}
								$("#completeShow").slideDown(100);
							}else{
								$("#completeShow").slideUp(100);
							}
						},//data是一个json对象，是返回的结果
						error:function(XMLHttpRequest,textStatus,errorThrown){
							$("#itemul").empty();
							$("#completeShow").slideUp(100);
						},
						dataType:"json"//注意是json
					});
				}).click(function(){
					return false;
				}).focus(function(){//区分focus和click
					if($("#itemul").html()!=""){
						$("#completeShow").slideDown(100);
					}else{
						$("#completeShow").slideUp(100);
					}
				});
				$(document).click(function(){//注意，document不用引号
						$("#completeShow").slideUp(100);
					
				});
			});
		</script>
	</head>

	<body style="height: 2000px;" data-spy="scroll" data-target="#elevator">
		<!--1.头信息 start-->
		<div id="f1" class="container hidden-xs topbar">
			<div class="row">
				<div class="col-md-3 col-xs-6">
					<img src="${pageContext.request.contextPath}/img/logo2.png"/>
				</div>
				<div class="col-md-6 visible-md visible-lg">
					<img src="${pageContext.request.contextPath}/img/header.jpg"/>
				</div>
				<div class="col-md-3 col-xs-6">
					<a href="#" class="btn btn-danger btn-sm">免费注册</a>
					<a href="#" class="btn btn-link btn-sm">注册</a>
					<a href="#" class="btn btn-link btn-sm">购物车</a>
				</div>
			</div>
		</div>
		<!--1.头信息 end-->
		<!--2.导航（navigate）start-->
		<div class="container nav-center" data-spy="affix" data-offset-top="60">
			<nav class="navbar navbar-default navbar-inverse">
				<div class="container-fluid">
				<!-- 当进入最小设备时，导航条将隐藏，显示“汉堡按钮”，点击时可以切面显示导航条隐藏的信息 
					* data-target ：编写jQuery选择器，用于确定需要显示div
				-->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">汉堡按钮</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" id="nav-brand-itheima" href="#" >
							首页
						</a>
					</div>
				
					<!-- 导航条详情，如果宽度到xs时，将自动隐藏，通过“汉堡按钮”点击显示 
						* sr-only : 辅助技术。例如：提供给屏幕阅读器screen reader(sr)更多信息。
					-->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">手机数码<span class="sr-only">(current)</span></a></li>
							<li><a href="#">电脑办公</a></li>
							<li><a href="#">电脑办公</a></li>
							<!--程序遍历时，从第三个开始添加“hidden-sm”，如果“分类过多” md和lg可能也会出现“更多”，需要js逻辑判断-->
							<li class="hidden-sm"><a href="#">电脑办公</a></li>
							<li class="dropdown visible-sm">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">更多 
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#">电脑办公</a></li>
									<li><a href="#">电脑办公</a></li>
									<li><a href="#">电脑办公</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="#">手机数码</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="#">其他</a></li>
								</ul>
							</li>
						</ul>
						<!--  -->
						<form class="navbar-form navbar-right" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search" id="searchsql"><!-- 这里 -->
							</div>
							<!--手机设备隐藏按钮，手机输入法“确定”按钮-->
							<button type="submit" class="btn btn-default hidden-xs">Submit</button>
							<!-- 显示查询信息的div -->
							<div id="completeShow">
								<ul id='itemul' class='list-group'>
									<!-- <li class='list-group-item'><a href='#'>查询结果1</a></li>
									<li class='list-group-item'><a href='#'>查询结果2</a></li>
									<li class='list-group-item'><a href='#'>查询结果3</a></li>
									<li class='list-group-item'><a href='#'>查询结果4</a></li>
									<li class='list-group-item'><a href='#'>查询结果5</a></li> -->
								</ul>
							</div>
						</form>
					</div><!-- /.navbar-collapse -->
				</div><!-- /.container-fluid -->
			</nav>
		</div>
		<!--2.导航（navigate）end-->
		<!--3.轮播图 start-->
		<div id="f2" class="container">
			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			  <!-- 指示器（Indicators）中间的小圆点，与图片的个数相同 
			  		* data-target：确定触发那个轮播，使用的jQuery id选择器
			  -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>
			
			<!-- 轮播展示 
				* item 表示一个图片，与“指示器”li的个数保持一致
			-->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="${pageContext.request.contextPath}/img/1.jpg" alt="第一张图片">
						<!--字幕，此处省略
						  <div class="carousel-caption">
							
						  </div>
						-->
					</div>
					<div class="item">
					  <img src="${pageContext.request.contextPath}/img/2.jpg" alt="第2张图片">
					</div>
					<div class="item">
					  <img src="${pageContext.request.contextPath}/img/3.jpg" alt="第3张图片">
					</div>
				</div>
			
				<!-- 左右控制区 Controls 
					* href 用于确定点击触发的那个轮播图
				-->
				<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<!--3.轮播图 end-->
		
		<!--4.热卖商品 start-->
		<div id="f3" class="container">
			<div class="row">
				<div class="col-md-12">
					<h2>热卖商品 <img src="${pageContext.request.contextPath}/img/title2.jpg"/></h2>
				</div>
			</div>
			<div class="row product">
				<div class="product-ad col-md-2 col-sm-4 hidden-xs">
					<img src="${pageContext.request.contextPath}/img/products/big01.jpg" style="width:100%;"/>
				</div>
				<div class="col-md-10 col-sm-8">
					<div class="product-item col-md-6 col-sm-12 col-xs-12" >
						<img src="${pageContext.request.contextPath}/img/products/middle01.jpg" style="height:100%;width:100%;"/>
					</div>
					<div class="product-item col-md-2 col-sm-4 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/products/small01.jpg"/>
						<p>电器</p>
						<p>$998.0</p>
					</div>
					<div class="product-item col-md-2 col-sm-4 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/products/small02.jpg"/>
						<p>电器</p>
						<p>$998.0</p>
					</div>
					<div class="product-item col-md-2 col-sm-4 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/products/small03.jpg"/>
						<p>电器</p>
						<p>$998.0</p>
					</div>
					
					<div class="product-item col-md-2 col-sm-4 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/products/small04.jpg"/>
						<p>电器</p>
						<p>$998.0</p>
					</div>
					<div class="product-item col-md-2 col-sm-4 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/products/small05.jpg"/>
						<p>电器</p>
						<p>$998.0</p>
					</div>
					<div class="product-item col-md-2 col-sm-4 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/products/small06.jpg"/>
						<p>电器</p>
						<p>$998.0</p>
					</div>
					<div class="product-item col-md-2 col-sm-4 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/products/small07.jpg"/>
						<p>电器</p>
						<p>$998.0</p>
					</div>
					<div class="product-item col-md-2 col-sm-4 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/products/small08.jpg"/>
						<p>电器</p>
						<p>$998.0</p>
					</div>
					<div class="product-item col-md-2 col-sm-4 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/products/small09.jpg"/>
						<p>电器</p>
						<p>$998.0</p>
					</div>
				</div>
			</div>
			<div class="row"></div>
		</div>
		<!--4.热卖商品 end-->
		
		<!--5.模块start-->
		<div id="f4" class="container">
			<div class="floor-tese">
				<div class="row">
					<div class="col-md-12">
						<div class="navbar-header">
					      <a class="navbar-brand" href="#">特色</a>
					    </div>
						<!-- Nav tabs -->
						 <ul class="nav nav-pills navbar-right clearfix" role="tablist" style="margin-right: 0px;">
							<li role="presentation" class="active"><a href="#cosmetics" aria-controls="cosmetics" role="tab" data-toggle="tab">个人护肤</a></li>
							<li role="presentation"><a href="#mobiles" aria-controls="mobiles" role="tab" data-toggle="tab">手机通讯</a></li>
							<li role="presentation"><a href="#livings" aria-controls="livings" role="tab" data-toggle="tab">居家生活</a></li>
							<li role="presentation"><a href="#sports" aria-controls="sports" role="tab" data-toggle="tab">运动健身</a></li>
						 </ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="tab-content">
						 	<!--个人护肤-->
							<div role="tabpanel" class="tab-pane active" id="cosmetics">
							   	<div class="product-item col-md-2 col-sm-4 col-xs-6">
									<img src="${pageContext.request.contextPath}/img/products/small09.jpg"/>
									<p>电器</p>
									<p>$998.0</p>
								</div>
								<div class="product-item col-md-2 col-sm-4 col-xs-6">
									<img src="${pageContext.request.contextPath}/img/products/small09.jpg"/>
									<p>电器</p>
									<p>$998.0</p>
								</div>
								<div class="product-item col-md-2 col-sm-4 col-xs-6">
									<img src="${pageContext.request.contextPath}/img/products/small09.jpg"/>
									<p>电器</p>
									<p>$998.0</p>
								</div>
							</div>
							<!--手机通讯-->
							<div role="tabpanel" class="tab-pane" id="mobiles">
							   	<div class="product-item col-md-2 col-sm-4 col-xs-6">
									<img src="${pageContext.request.contextPath}/img/products/small08.jpg"/>
									<p>电器</p>
									<p>$998.0</p>
								</div>
								<div class="product-item col-md-2 col-sm-4 col-xs-6">
									<img src="${pageContext.request.contextPath}/img/products/small08.jpg"/>
									<p>电器</p>
									<p>$998.0</p>
								</div>
								<div class="product-item col-md-2 col-sm-4 col-xs-6">
									<img src="${pageContext.request.contextPath}/img/products/small08.jpg"/>
									<p>电器</p>
									<p>$998.0</p>
								</div>
							   	
							</div>
							<!--居家生活-->
							<div role="tabpanel" class="tab-pane" id="livings">
							   							   	
							</div>
							<!--运动健身-->
							<div role="tabpanel" class="tab-pane" id="sports">
							   							   	
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--5.模块end-->
		
		<!--6.footer start-->
		<div class="container">
			<div class="row" id="itcast-ensure">  <!--担保-->
				<div class="col-md-12">
					<img src="${pageContext.request.contextPath}/img/footer.jpg" style="width: 100%;"/>
				</div>
			</div>
			<div class="row" id="itcast-copyright">
				<div class="col-md-7 col-md-offset-3">
					<ul class="list-inline ">
						<li>关于我们 </li>
						<li>联系我们</li>
						<li>招贤纳士 </li>
						<li>法律声明</li>
						<li>友情链接 </li>
						<li>支付方式</li>
						<li>配送方式</li>
						<li>服务声明 </li>
						<li>广告声明</li>
					</ul>
				</div>
			</div>
		</div>
		<!--6.footer end-->
				
		<!--elevator 电梯-->
		<div id="elevator" class="elevator visible-lg visible-md">
			<!-- Nav tabs -->
			 <ul class="nav nav-pills nav-stacked" role="tablist">
				<li class="active"><a href="#f1" ><span class="one">F1</span><span class="two">导航</span></a></li>
				<li><a href="#f2" ><span class="one">F2</span><span class="two">轮播</span></a></li>
				<li><a href="#f3" ><span class="one">F3</span><span class="two">新品</span></a></li>
				<li><a href="#f4" ><span class="one">F4</span><span class="two">特色</span></a></li>
			 </ul>
		</div>

	</body>

</html>