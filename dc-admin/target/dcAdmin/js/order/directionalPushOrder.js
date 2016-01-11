//document.write("<script language='javascript' src='public.js'></script>");
function queryDirectionalPush(){
	$("#directionalPushDiv").empty();
	$.ajax({
		type : "post",
		url : "../order/selectDirectionalPush.do",
		async : false,//设置为
		dataType : "json",
		success : function(data) {
			if(data.result==true){
				var dataTable="";
				var response=data.model;
				for(var i=0;i<response.length;i++){
					dataTable+=
						"<div class='data' id='"+response[i].requestId+"PushTable'>"+
				            "<span class='order_name'>家长昵称："+response[i].userName+"("+response[i].userPhoneNum+")</span>"+
				            "<span class='order_name'>老师昵称："+response[i].teacherInfo.nick+"("+response[i].teacherInfo.name+")</span>"+
				            "科目年级："+response[i].grade+" "+response[i].course+"<br/>"+
				            "上课地点："+response[i].address+"<br/>"+
				            "上课时间："+response[i].startCourseTime+" "+response[i].timeBlock+"<br/>"+
				            "老师课酬：学生上门("+response[i].priceOfTeacherHome+"元/小时)老师上门("+response[i].priceOfStudentHome+"元/小时)第三方场地("+response[i].priceOfThridPlace+"元/小时)<br/>"+
				            "家长期望课酬："+response[i].startPrice+"-"+response[i].endPrice+"元/小时<br/>"+
				            "家长请求时间："+new Date(response[i].createDate).format('yyyy-MM-dd hh:mm')+"<br/>"+
				            "<div class='order_button'><input type='button' name='button' id='button' value='关闭'  class='order_accept' onclick='closePushOrder("+response[i].requestId+")'/></div>"+
						"</div>";
				}
				$("#directionalPushDiv").append(dataTable);
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function closePushOrder(requestId){
	$.ajax({
		type : "post",
		url : "../order/closePushOrder.do",
		async : false,//设置为
		dataType : "json",
		data:{
			"requestId":requestId
		},
		success : function(data) {
			var tableId=requestId+"PushTable";
			$("#"+tableId+"").remove();
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});

}
