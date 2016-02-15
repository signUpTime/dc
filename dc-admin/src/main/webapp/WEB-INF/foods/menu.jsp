<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/common/common.jsp"/>

<body margin="0">
	<table align="left" valign="top"
		style="background-image: url(../images/leftnav_bg.png); width: 140;" height="100%">
		<tr>
			<td height="100%" valign="top">
				<ul class="leftnav">
					<li class="title"><a href="<%=request.getContextPath()%>/foods/foodsList.do" target="contentpage">菜单列表</a></li>
					<c:if test="${user.isAdmin == 1}">
						<li class="title">订单信息
							<ul class="list">
								<li><a href="<%=request.getContextPath()%>/order/todayOrderManage.do" target="contentpage">今日订餐信息</a></li>
								<li><a href="<%=request.getContextPath()%>/order/todayOrderUserManage.do" target="contentpage">今日订餐人员</a></li>
								<%-- <li><a href="<%=request.getContextPath()%>/order/myOrderList.do" target="contentpage">我的订单</a></li> --%>
							</ul>
						</li>
					</c:if>
					<c:if test="${user.isAdmin == 1}">
						<li class="title">餐品管理
							<ul class="list">
								<li><a href="<%=request.getContextPath()%>/foods/toFoodsAdminManage.do" target="contentpage">餐品列表</a></li>
							</ul>
							<ul class="list">
								<li><a href="<%=request.getContextPath()%>/shop/toShopManage.do" target="contentpage">商家列表</a></li>
							</ul>
						</li>
					</c:if>
				</ul>
			</td>
		</tr>
	</table>
</body>
</html>