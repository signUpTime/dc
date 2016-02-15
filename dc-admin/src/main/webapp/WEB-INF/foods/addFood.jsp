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
				<td><input id="name" type="text" class="input_data input_edit" maxlength="64"/></td>
			</tr>
			<tr>
				<th>价钱</th>
				<td><input id="price" type="text" class="input_data input_edit"  /></td>
			</tr>
			<tr>
				<th>商家</th>
				<td>
					<qqtag:selectShopTag name="selectShop" id="selectShop" firstLabelDisplay="全部商家" firstLabelValue="-1"></qqtag:selectShopTag>
				</td>
			</tr>
			<tr>
				<th>图片</th>
				<td id="titleImg">
					<input name="pic" type="file" id="titlePic" class="input_updata" onchange="ValidateImgUrl(this,'titleImg')"/>
					<div  class='place_pic' id='imageArea' style="display:none">
						<img id="foodPic" src='' width='120' align='absmiddle' />
						<input id="picName" value='' type="hidden"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>主页地址</th>
				<td><input id="sourceUrl" type="text" class="input_data input_edit"  maxlength="1024"/></td>
			</tr>
			<tr>
				<th>简介</th>
				<td>
					<textarea id="description" rows="8" cols="80" ></textarea>
				</td>
			</tr>
		</table>
		<div style="margin-top: 15px;text-align: center">
				<input type="button" id="saveAdd" value="保存" class="button" style="width: 100px;" onclick="saveFood()"></input>
				<input type="button" id="cancelSaveAdd" value="取消" class="button_cancel" style="width: 100px;" onclick="cancelAdd()"></input>
		</div>
		<input id="path"  style="display:none;" value="<%=request.getContextPath()%>"/>
	</div>
</body>
<script type="text/javascript">
	var picLoc = "<%=request.getContextPath()%>/images/foods/";
	var path = $("#path").val();
	function cancelAdd() {
		parent.layer.closeAll();
	}
	
	//图片验证及提交
	function ValidateImgUrl(obj, str) {
		
		var url = GetObj(obj).value;
		var filename = url.substring(url.lastIndexOf(".") + 1).toLowerCase();
		if (filename != "jpg" && filename != "jpeg" && filename != "png") {
			alert("请确认上传文件格式为jpg或png");
			obj.outerHTML = obj.outerHTML;
			return false;
		}
		/* if (str == 'titleImg') {
			titleNum = titleNum + 1;
		} */
		if (url.length > 0) {
			var path = $("#path").val();
			$.ajaxFileUpload({
						url : path + "/common/uploadPicture.do",
						secureuri : false,
						fileElementId : obj.id,
						dataType : "json",
						success : function(data, status) {
							$("#foodPic").attr('src',picLoc+data.picName);	
							$("#picName").val(data.picName);
							$("#imageArea").show();
						},
						error : function(data, status, e) {
							/* if (str == 'titleImg') {
								titleNum = titleNum - 1;
							} */
							alert(e);
						}
					})
			return true;
		} else {
			return false;
		}
	}
	
	function saveFood() {
		var dataObj = {};
		var name = $("#name").val();
		if(name == '') {
			layer.alert("请输入餐名");
			return;
		}
		var price = $("#price").val();
		if(price == '') {
			layer.alert("请输入价钱");
			return;
		}
		var shopId = $("#selectShop").val();
		if(shopId == -1) {
			layer.alert("请选择商家");
			return;
		}
		var pic = $("#picName").val();
		if(pic == '') {
			layer.alert("请上传图片");
			return;
		}
		var sourceUrl = $("#sourceUrl").val();
		var description = $("#description").val();
		dataObj.name = name;
		dataObj.price = price;
		dataObj.shopId = shopId;
		dataObj.pic = pic;
		dataObj.sourceUrl = sourceUrl;
		dataObj.description = description;
		$.ajax({
			url: path+"/foods/addFood.do",
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
	
	function GetObj(id) {
		return "string" == typeof id ? document.getElementById(id) : id;
	}
	
	function toAddShop() {
		$.ajax({
			url: path+"/shop/toAddShop.do",
			type : "post",
			dataType : "html",
			success : function(data) {
				layer.open({
					type : 1, //page层
					skin : 'layui-layer-lan',
					area : [ '1200px', '600px' ],
					title : '新增商家',
					content : data
				});
			}
		});
	}
</script>

</html>