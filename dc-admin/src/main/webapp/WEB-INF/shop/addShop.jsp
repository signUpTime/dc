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
				<th width="120">名称</th>
				<td><input id="name" type="text" class="input_data input_edit" maxlength="64"/></td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input id="address" type="text" class="input_data input_edit"  /></td>
			</tr>
			<tr>
				<th>主页地址</th>
				<td><input id="sourceUrl" type="text" class="input_data input_edit"  maxlength="1024"/></td>
			</tr>
			<tr>
				<th>电话</th>
				<td><input id="phone" type="text" class="input_data input_edit"  maxlength="1024"/></td>
			</tr>
			<tr>
				<th>简介</th>
				<td>
					<textarea id="description" rows="8" cols="80" ></textarea>
				</td>
			</tr>
		</table>
		<div style="margin-top: 15px;text-align: center">
				<input type="button" id="saveAdd" value="保存" class="button" style="width: 100px;" onclick="saveShop()"></input>
				<input type="button" id="cancelSaveAdd" value="取消" class="button_cancel" style="width: 100px;" onclick="cancelAdd()"></input>
		</div>
		<input id="path"  style="display:none;" value="<%=request.getContextPath()%>"/>
	</div>
</body>
<script type="text/javascript">
	var path = $("#path").val();
	function cancelAdd() {
		parent.layer.closeAll();
	}
	
	
	function saveShop() {
		var dataObj = {};
		var name = $("#name").val();
		if(name == '') {
			layer.alert("商家名称");
			return;
		}
		var address = $("#address").val();
		if(address == '') {
			layer.alert("请输地址");
			return;
		}
		var sourceUrl = $("#sourceUrl").val();
		var description = $("#description").val();
		dataObj.name = name;
		dataObj.address = address;
		dataObj.phone = $("#phone").val();
		dataObj.sourceUrl = sourceUrl;
		dataObj.description = description;
		$.ajax({
			url: path+"/shop/addShop.do",
			type : "post",
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(dataObj),
			success : function(data) {
				if(data.result == true) {
					parent.query(1);
					parent.layer.closeAll();
				} else {
					if(data.resultMsg) {
						layer.alert(data.resultMsg);
					}
				}
			}
		});
	}
	
</script>

</html>