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
				<th>餐名</th>
				<th>商家</th>
				<th>数量</th>
				<th>状态</th>
			<!-- 	<th>订餐人员</th> -->
			</tr>
			<c:if test="${orderFoodList == null || fn:length(orderFoodList) == 0}">
				<tr bgcolor="#FFFFFF">
					<td align="center" colspan="20"><br>&nbsp;</br> <br>&nbsp;</br>
						没有符合条件的记录 <br>&nbsp;</br> <br>&nbsp;</br></td>
				</tr>
			</c:if>
			<c:forEach items="${orderFoodList}" var="result" varStatus="loop">
				<tr align="center">
					<td width="50px" ><input type="checkbox" value="${result.goodsId}" /></td>
					<td><a href="${result.goodsSource}">${result.goodsName}</a></td>
					<td><a href="${result.shopSource}">${result.shopName}</a></td>
					<td>${result.count}</td>
					<td>
						<qqtag:writeBookStatus value="${result.bookStatus}"></qqtag:writeBookStatus>
					</td>
					<!-- <td><a href="javascript:void(0)">查看</a></td> -->
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