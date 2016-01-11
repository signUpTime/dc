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
            	<font>当前位置：</font>点餐-订单信息-今日订餐信息
            	<input type="button" class="button" value="已定" onclick="updateBookStatus(1)"/>
            	<input type="button" class="button" value="已收" onclick="updateBookStatus(2)"/>
            </td>
            <td nowrap="nowrap" class="right">
            	商家:
            	<input type="text" id="shopName" class="input_data"/>
            	餐名：
            	<input type="text" id="goodsName" class="input_data" />
            	订餐状态：
            	<select id="bookStatus" class="select">
            		<option value="-1">全部</option>
            		<option value="0">未订餐</option>
            		<option value="1">已订餐</option>
            		<option value="2">已收到订餐</option>
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
	request.shopName = $("#shopName").val();
	request.goodsName = $("#goodsName").val();
	request.bookStatus = $("#bookStatus option:selected").val();
	$.ajax({
		url : path + "/order/queryTodayOrderFoodList.do",
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

function updateBookStatus(bookStatus){
	var checkedBoxes = $("input[type='checkbox']:checked");
	var goodsIds = "";
	if(checkedBoxes.length > 0) {
		for(var i=0;i<checkedBoxes.length-1;i++) {
			var goodsId = $(checkedBoxes[i]).val();
			if(goodsId !='') {
				goodsIds += goodsId+","
			}
		}
		var lastGoodsId = $(checkedBoxes[checkedBoxes.length-1]).val();
		if(lastGoodsId != '') {
			goodsIds += lastGoodsId 
		}
	} else {
		alert("请选择餐品！")
		return;
	}
	requestUpdateBookStatus(goodsIds,bookStatus);
}

function requestUpdateBookStatus(goodsIds,bookStatus) {
	var index = layer.confirm("是否确定更改状态？",function(){
		layer.close(index);
		$.ajax({
			url : path + "/order/updateTodayOrderBookStatus.do?goodsIds=" + goodsIds+"&bookStatus=" +bookStatus,
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