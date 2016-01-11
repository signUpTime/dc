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
</head>
<body>
	<div id="queryResult">
		<table border="0" cellspacing="0" cellpadding="0" class="frame"
			id="tableContent">
			<tr>
				<th width="50px" ><input type="checkbox" id="checkAll" value=""/></th>
				<th>名称</th>
				<th>部门</th>
				<th>餐名</th>
				<th>商家</th>
				<th>取餐状态</th>
				<th>订餐时间</th>
			</tr>
			<c:if test="${orderUserList == null || fn:length(orderUserList) == 0}">
				<tr bgcolor="#FFFFFF">
					<td align="center" colspan="20"><br>&nbsp;</br> <br>&nbsp;</br>
						没有符合条件的记录 <br>&nbsp;</br> <br>&nbsp;</br></td>
				</tr>
			</c:if>
			<c:forEach items="${orderUserList}" var="result" varStatus="loop">
				<tr align="center">
					<td width="50px" ><input type="checkbox" value="${result.orderId}" /></td>
					<td>${result.userName}</td>
					<td>${result.department}</td>
					<td>${result.goodsName}</td>
					<td>${result.shopName}</td>
					<td>
						<%-- <c:if test="${result.status == 0}">未取餐</c:if>
						<c:if test="${result.status == 1}">已取餐</c:if> --%>
					</td>
					<td>
						<qqtag:writeFormatDate value="${result.createTime}" format="yyyy-MM-dd HH:mm:ss"></qqtag:writeFormatDate>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<script type="text/javascript">
$("#checkAll").change(function(){
	if($(this).prop("checked") == true) {
		$("input[type='checkbox']").each(function(){
			$(this).prop("checked",true);
		});
	} else {
		$("input[type='checkbox']").each(function(){
			$(this).prop("checked",false);
		});
	}
})

</script>

</html>