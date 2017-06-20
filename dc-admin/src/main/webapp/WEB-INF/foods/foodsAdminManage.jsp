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
            	<font>当前位置：</font>点餐-餐品管理-餐品列表
            	<input type="button" class="button" value="新增" onclick="toAddFood()"/>
            	<input type="button" class="button" value="删除" onclick="deleteFoods()"/>
            	<input type="button" class="button" value="上架" onclick="enableFood()"/>
            	<input type="button" class="button" value="下架" onclick="disableFood()"/>
            </td>
            <td nowrap="nowrap" class="right">
				送餐地址:
				<qqtag:selectDestinationTag id="selectDestination" name="selectDestination" firstLabelDisplay="不限" firstLabelValue="0" selectedValue="${user.destinationId}"></qqtag:selectDestinationTag>
            	商家:
            	<input type="text" id="shopName" class="input_data"/>
            	餐名：
            	<input type="text" id="goodsName" class="input_data" />
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
	request.foodsName = $("#goodsName").val();
	request.destinationId = $("#selectDestination").val();
	$.ajax({
		url : path + "/foods/queryAdminFoodsList.do",
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

function toAddFood() {
	var url = path + "/foods/toAddFoodInfo.do";
	alertDealPage(url);
}

function deleteFoods(){
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
	requestDeleteFoods(goodsIds);
}

function requestDeleteFoods(goodsIds) {
	var index = layer.confirm("是否确定删除？",function(){
		layer.close(index);
		$.ajax({
			url : path + "/foods/deleteFoods.do?goodsIds=" + goodsIds,
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

function enableFood(){
	var checkedBoxes = $("input[type='checkbox']:checked");
	var goodsIds = "";
	if(checkedBoxes.length > 0) {
		for(var i=0;i<checkedBoxes.length-1;i++) {
			var goodsId = $(checkedBoxes[i]).val();
			if(goodsId !='') {
				goodsIds += goodsId+",";
			}
		}
		var lastGoodsId = $(checkedBoxes[checkedBoxes.length-1]).val();
		if(lastGoodsId != '') {
			goodsIds += lastGoodsId ;
		}
	} else {
		alert("请选择餐品！")
		return;
	}
	$.ajax({
		url : path + "/foods/enableFoods.do?goodsIds=" + goodsIds,
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
}

function disableFood(){
	var checkedBoxes = $("input[type='checkbox']:checked");
	var goodsIds = "";
	if(checkedBoxes.length > 0) {
		for(var i=0;i<checkedBoxes.length-1;i++) {
			var goodsId = $(checkedBoxes[i]).val();
			if(goodsId !='') {
				goodsIds += goodsId+",";
			}
		}
		var lastGoodsId = $(checkedBoxes[checkedBoxes.length-1]).val();
		if(lastGoodsId != '') {
			goodsIds += lastGoodsId ;
		}
	} else {
		alert("请选择餐品！")
		return;
	}
	$.ajax({
		url : path + "/foods/disableFoods.do?goodsIds=" + goodsIds,
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
}

</script>
</html>