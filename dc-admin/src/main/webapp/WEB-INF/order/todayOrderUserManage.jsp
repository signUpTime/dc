<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="qqtag" uri="/WEB-INF/tld/qq-tag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/common/common.jsp"/>
<body>
      <div class="subnav">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td nowrap="nowrap" class="left">
            	<font>当前位置：</font>点餐-订单信息-今日订餐人员
            	<input type="button" class="button" value="取餐" onclick="receiveFood()"/>
            </td>
            <td nowrap="nowrap" class="right">
            	部门:
            	<input type="text" id="department" class="input_data"/>
            	姓名：
            	<input type="text" id="name" class="input_data" />
            	餐名：
            	<input type="text" id="goodsName" class="input_data"/>
            	取餐状态：
            	<select id="status" class="select">
            		<option value="-1">全部</option>
            		<option value="0">未取餐</option>
            		<option value="1">已取餐</option>
            	</select>
            	<input id="query" type="button" name="Submit" value="开始查询"
					class="button" onclick="query(1)" />
            </td>
          </tr>
        </table>
      </div>
	  <div id="queryResult"></div>
	  <div id="pageContent" style="width: 300px; float: right;"></div>
      <input id="path"  style="display:none;" value="<%=request.getContextPath()%>"/>
</body>
<script type="text/javascript">
var path = $("#path").val();

$(document).ready(function(){
	query(1);
});

window.alert = function(msg) {
	layer.alert(msg);
}

function query(pageNum){
	var request = {};
	request.department = $("#department").val();
	request.name = $("#name").val();
	request.goodsName = $("#goodsName").val();
	request.status = $("#status option:selected").val();
	$.ajax({
		url : path + "/order/queryTodayOrderUserList.do",
		type : "post",
		dataType : "html",
		contentType : "application/json",
		data : JSON.stringify(request),
		beforeSend : function() {
			$("#queryResult").html("数据加载中...");
		},
		success : function(data) {
			$("#pageContent").empty();
			$("#queryResult").html(data);
			if ($("#totalPageNum").val() > 1) {
				$("#pageContent").paginate({
					count : $("#totalPageNum").val(),
					start : $("#currentPageNum").val(),
					display : 10,
					border : false,
					text_color : '#888',
					background_color : '#EEE',
					text_hover_color : 'black',
					background_hover_color : '#CFCFCF'
				});
			}
		}
	});
}

function receiveFood(){
	var checkedBoxes = $("input[type='checkbox']:checked");
	var orderIds = "";
	if(checkedBoxes.length > 0) {
		for(var i=0;i<checkedBoxes.length-1;i++) {
			var orderId = $(checkedBoxes[i]).val();
			if(orderId !='') {
				orderIds += orderId+","
			}
		}
		var lastOrderId = $(checkedBoxes[checkedBoxes.length-1]).val();
		if(lastOrderId != '') {
			orderIds += lastOrderId 
		}
	} else {
		alert("请选择取餐人员！")
		return;
	}
	takeFood(orderIds);
}

function takeFood(orderIds) {
	var index = layer.confirm("是否确定取餐？",function(){
		layer.close(index);
		$.ajax({
			url : path + "/order/updateTodayOrderTakeFoodStatus.do?orderIds=" + orderIds+"&status=1",
			type : "post",
			dataType : "json",
			success : function(data) {
				if(data.result == true) {
					layer.closeAll();
					query(1);
				} else {
					if(data.resultMsg) {
						alert(data.resultMsg);
					}
				}
			}
		});
	});
}


</script>
</html>