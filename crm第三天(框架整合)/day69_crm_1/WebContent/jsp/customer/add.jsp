﻿<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/addCustomer.action" method=post>
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
								<TD class=manageHead>当前位置：客户管理 &gt; 添加客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<TR>
								<td>客户名称：</td>
								<td>
									<INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="custName">
								</td>
								<td>所属行业 ：</td>
								<td>
									<INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="custIndustry">
								</td>
							</TR>							
							<TR>	
								<td>信息来源 ：</td>
								<td>
									<!-- 当点击新增客户的按钮时会跳转到这个页面并从值栈中取custSources，只有EL表达式才会被赋值，因此name属性不会被赋值，
									他只会在提交表单时作为name属性传到后台，并用模型驱动和属性驱动自动封装和对应。还要注意是custSource.dictId而不是custSources.dictId，
									 因为custSource是属性，是对象，而custSources是集合，他没有dictId属性。custSource.dictId到后台之后会自动封装到BaseDict对象中
									 进而封装到Customer对象中(因为前者会作为后者的成员变量)-->
									<select name="custSource.dictId" class="textbox" id="sChannel2" style="WIDTH: 180px">
										<option value="non">---请选择---</option>
										<c:forEach var="source" items="${custSources}">
											<!-- 下面的只是设置了表单的默认值，用户修改了之后是会改变的。 -->
											<option value="${source.dictId}">${source.dictItemName}</option>
										</c:forEach>
									</select>
									<%-- <s:select name="custSource.dictId" theme="simple" list="custSource" listKey="dictId" listValue="dictItemName" headerKey="" headerValue="--请选择--" class="textbox" id="sChannel2" style="WIDTH: 180px"></s:select> --%>
								</td>
								<td>客户级别：</td>
								<td>
									<select name="custLevel.dictId" class="textbox" id="sChannel2" style="WIDTH: 180px">
										<option value="non">---请选择---</option>
										<c:forEach var="level" items="${custLevels}">
											<option value="${level.dictId}">${level.dictItemName}</option>
										</c:forEach>
									</select>								
								</td>
							</TR>
							<TR>
								<td>联系地址 ：</td>
								<td>
									<INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="custAddress">
								</td>
								<td>联系电话 ：</td>
								<td>
									<INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="custPhone">
								</td>
							</TR>
							<tr>
								<td rowspan=2>
									<INPUT class=button id=sButton2 type=submit value=" 保存 " name=sButton2>
								</td>
							</tr>
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
					<TD align="center" width="100%" background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"	border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
