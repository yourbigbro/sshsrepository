<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		alert(page);
		if(page){
			//为id为page的input表单赋值。Action页面中应该有一个私有变量page接收这个页面传来的page变量
			$("#currentPage").val(page);
			
		}
		//看来筛选就是提交一个表单。customerForm是表单的id
		document.customerForm.submit();	
	}
	
	function delCustomer(custId){
		if(confirm("是否确定删除此客户？")){
			location.href = "${pageContext.request.contextPath }/removeCustomer.action?custId="+custId;
		}
	}
</SCRIPT>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<!-- chooseCustomer.action -->
	<FORM id="customerForm" name="customerForm" action="${pageContext.request.contextPath }/listCustomer.action" method=post>	
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg" border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg" height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg">
						<IMG src="${pageContext.request.contextPath }/images/new_022.jpg" border=0>
					</TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
<TBODY>
	<TR>
		<TD>客户名称：</TD>
		<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="custName"></TD>
		<TD>客户来源：</TD>
		<TD>
			<%-- <s:select name="dictSource.dictId" theme="simple" list="dictSources" listKey="dictId" listValue="dictItemName" headerKey="" headerValue="--请选择--" class="textbox" id="sChannel2" ></s:select> --%>
			<select name="custSource.dictId" class="textbox" id="sChannel2" style="WIDTH: 180px">
				<!-- 请选择的value值设置为空字符串即可 -->
				<option value="">---请选择---</option>
				<c:forEach var="source" items="${custSources}">
					<!-- 下面的只是设置了表单的默认值，用户修改了之后是会改变的。 -->
					<option value="${source.dictId}">${source.dictItemName}</option>
				</c:forEach>
			</select>
		</TD>
		<TD>客户级别：</TD>
		<TD>
			<%-- <s:select name="dictLevel.dictId" theme="simple" list="dictLevels" listKey="dictId" listValue="dictItemName" headerKey="" headerValue="--请选择--" class="textbox" id="sChannel2" ></s:select> --%>
			<select name="custLevel.dictId" class="textbox" id="sChannel2" style="WIDTH: 180px">
				<option value="">---请选择---</option>
				<c:forEach var="level" items="${custLevels}">
					<option value="${level.dictId}">${level.dictItemName}</option>
				</c:forEach>
			</select>
		</TD>
		<TD>客户行业：</TD>
		<TD>
			<INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="custIndustry">
		</TD>
		<TD><INPUT class=button id=sButton2 type="button"
			onclick="to_page('1')" value=" 筛选 " name=sButton2></TD>
	</TR>
</TBODY>
</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=grid style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc" cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>所属行业</TD>
													<TD>联系地址</TD>
													<TD>联系电话</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${pageBean.list}" var="customer">
												<TR style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${customer.custName }</TD>
													<TD>${customer.custLevel.dictItemName }</TD>
													<TD>${customer.custSource.dictItemName }</TD>
													<TD>${customer.custIndustry }</TD>
													<TD>${customer.custAddress }</TD>
													<TD>${customer.custPhone }</TD>
													<TD>
													<a href="${pageContext.request.contextPath }/editCustomerUI.action?custId=${customer.custId}">修改</a>
													&nbsp;&nbsp;
													<!-- 可见删除的时候可以利用传过来的id再回写回去，便于删除时封装Customer对象 -->
													<a href="javascript:delCustomer('${customer.custId }')">删除</a>
													</TD>
												</TR>	
												</c:forEach>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										<SPAN id=pagelink>
										<!-- 传到后台去的是pageSize和currentPage -->
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${pageBean.count}</B>]条记录,共[<B>${pageBean.totalPage}</B>]页
												,每页显示
												<select name="pageSize" onchange="to_page()">
												
												<option value="1" <c:if test="${pageBean.pageSize==1 }">selected</c:if>>1</option>
												<option value="2" <c:if test="${pageBean.pageSize==2 }">selected</c:if>>2</option>
												</select>
												条
												[<A href="javascript:to_page(${pageBean.prePage})">前一页</A>]
												<!-- 下面是当前页 -->
												<B>${pageBean.currentPage}</B>
												[<A href="javascript:to_page(${pageBean.nextPage})">后一页</A>] 
												到
												<!-- 当前页 -->
												<input type="text" size="3" id="currentPage" name="currentPage" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
										</SPAN>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
						<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg" border=0></TD>
					<TD align=middle width="100%" background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
