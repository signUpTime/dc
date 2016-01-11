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
            	<font>当前位置：</font>点餐-菜单列表
            	<!-- <input type="button" class="button" value="订餐" onclick="orderFood()"/> -->
            </td>
            <td nowrap="nowrap" class="right">
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
	request.pageNum = pageNum;
	$.ajax({
		url : path + "/foods/queryFoodsList.do",
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

function orderFood(obj) {
	/* var goodsId = $("input[type='radio'][name='food']:checked").val(); */
	var goodsId = $(obj).attr('name');
	var totalPrice = $(obj).attr('price');
	var index = layer.confirm("是否确定订餐？",function(){
		layer.close(index);
		if(goodsId) {
			var orderParam = {};
			orderParam.goodsId = goodsId;
			orderParam.totalPrice = totalPrice;
			$.ajax({
				url : path + "/order/bookFood.do",
				type : "post",
				dataType : "json",
				contentType : "application/json",
				data : JSON.stringify(orderParam),
				success : function(data) {
					if(data.result == true) {
						alert("订餐成功！");
						query(1);
					} else {
						if(data.resultMsg) {
							alert(data.resultMsg);
						}
					}
				}
			});
		} else {
			alert("您未选择菜品");
			return;
		}
	});
}

function cancelOrder(orderId) {
	var index = layer.confirm("是否确定取消订餐？",function(){
		layer.close(index);
		$.ajax({
				url : path + "/order/cancelOrder.do?orderId=" + orderId,
				type : "post",
				dataType : "json",
				contentType : "application/json",
				success : function(data) {
					if (data.result == true) {
						alert("取消订餐成功！");
						query(1);
					} else {
						if (data.resultMsg) {
							alert(data.resultMsg);
						}
					}
				}
			});
		});
	}
</script>
</html>