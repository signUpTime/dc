<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="qqtag" uri="/WEB-INF/tld/qq-tag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/common/common.jsp"/>
<head>
	<style type="text/css">
		.foodItem{
			position:relative;
			float:left;
			width: 30%;
			height:102px;
			align:center;
			margin:20px;
			border: 1px solid #eee;
			background-color: #fff;
		}
	</style>
</head>
<body>
	<div id="queryResult">
		<c:if test="${foodList == null || fn:length(foodList) == 0}">
			<tr bgcolor="#FFFFFF">
				<td align="center" colspan="20"><br>&nbsp;</br> <br>&nbsp;</br>
					没有符合条件的记录 <br>&nbsp;</br> <br>&nbsp;</br></td>
			</tr>
		</c:if>
		<c:forEach items="${foodList}" var="result" varStatus="loop">
			<c:choose>
				<c:when test="${result.id == myTodayOrder.goodsId}">
					<div class="foodItem" id="${result.id}" style="background:#9BCD9B">
				</c:when>
				<c:otherwise>
					<div class="foodItem" id="${result.id}">
				</c:otherwise>
			</c:choose>
				<div style="float: left;width: 100px;height: 100px;">
					<img alt="" src="../images/foods/${result.pic}">
				</div>
				<div style="margin-top: 8px;margin-left:14px; float:left">
					<span title="${result.description}" style="cursor:pointer;"><h3 ><b>${result.name}</b></h3></span>
					<%--  <div style="color: red;margin-top:40px;">
						<h3><b>￥${result.price}</b></h3> 
					</div> --%>
				</div>
				<div style="margin-top:8px;margin-right:14px;float:right;">
					<span ><h3><b>${result.shopName}</b></h3></span>
					<div style="margin-top:35px;margin-right:5px;float:right;">
						<c:choose>
							<c:when test="${result.id == myTodayOrder.goodsId}">
								<input type="button" name="food" onclick="cancelOrder(${myTodayOrder.id})" price="${result.price}" checked="checked" value="取消" class="button_cancel" />
							</c:when>
							<c:otherwise>
								<input type="button" name="${result.id}" onclick="orderFood(this)" price="${result.price}" value="订餐" class="button"/>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
<script type="text/javascript">
</script>

</html>