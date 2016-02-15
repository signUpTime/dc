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
				<th>价钱</th>
				<th>创建时间</th>
				<!-- <th>操作</th> -->
			</tr>
			<c:if test="${foodList == null || fn:length(foodList) == 0}">
				<tr bgcolor="#FFFFFF">
					<td align="center" colspan="20"><br>&nbsp;</br> <br>&nbsp;</br>
						没有符合条件的记录 <br>&nbsp;</br> <br>&nbsp;</br></td>
				</tr>
			</c:if>
			<c:forEach items="${foodList}" var="result" varStatus="loop">
				<tr align="center">
					<td width="50px" ><input type="checkbox" value="${result.id}" /></td>
					<td><a href="${result.sourceUrl}" style="color:black;">${result.name}</a></td>
					<td>${result.shopName}</td>
					<td>${result.price}</td>
					<td><qqtag:writeFormatDate value="${result.createTime}" format="yyyy-MM-dd HH:mm:ss"></qqtag:writeFormatDate></td>
					<%-- <td>
						<span><a href="javascript:void(0);" onclick="eidtFoodInfo(${result.id})">编辑</a></span>
					</td> --%>
				</tr>
			</c:forEach>
		</table>
	</div>
	<input id="path" type="hidden" value="<%=request.getContextPath()%>"/>
</body>
<script type="text/javascript">
var path = $("#path").val();

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

function eidtFoodInfo(id) {
	var url = path + "/foods/toEditFoodInfo.do?id="+id;
	alertDealPage(url);
}

</script>

</html>