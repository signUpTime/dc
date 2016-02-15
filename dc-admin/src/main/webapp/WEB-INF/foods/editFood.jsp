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
		.input_edit{
			width: 300px;
		}
	</style>
</head>
<body>
	<div id="queryResult">
		<table border="0" cellspacing="0" cellpadding="0" class="frame"	id="tableContent">
			<tr>
				<th width="120">名称（餐名）</th>
				<td><input type="text" class="input_data input_edit" value="${food.name}" maxlength="64"/></td>
			</tr>
			<tr>
				<th>价钱</th>
				<td><input type="text" class="input_data input_edit" value="${food.price}" /></td>
			</tr>
			<tr>
				<th>商家</th>
				<td><input type="text" class="input_data input_edit"/ value="${food.shopName}"></td>
			</tr>
			<tr>
				<th>图片</th>
				<td><img src="<%=request.getContextPath()%>/images/foods/${food.pic}"/></td>
			</tr>
			<tr>
				<th>主页地址</th>
				<td><input type="text" class="input_data input_edit" value="${food.sourceUrl}" maxlength="1024"/></td>
			</tr>
			<tr>
				<th>简介</th>
				<td>
					<textarea rows="8" cols="80" >${food.description}</textarea>
				</td>
			</tr>
		</table>
		<div style="margin-top: 15px;text-align: center">
				<input type="button" id="saveEdit" value="保存" class="button" style="width: 100px;"></input>
				<input type="button" id="saveEdit" value="取消" class="button_cancel" style="width: 100px;" onclick="cancelEdit()"></input>
		</div>
	</div>
</body>
<script type="text/javascript">
	function cancelEdit() {
		parent.layer.closeAll();
	}
</script>

</html>