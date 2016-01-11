function queryNewDirectionalPush(){
	$("#newDirectionalPushDiv").empty();
	$.ajax({
		type : "post",
		url : "../order/selectNewDirectPush.do",
		async : false,//设置为
		dataType : "json",
		success : function(data) {
			if(data.result==true){
				var dataTable="";
				var response=data.model;
				for(var i=0;i<response.length;i++){
				dataTable+=
					"<div class='data' id='"+response[i].directPushId+"NewDirectPushDiv'>"+
					"<span class='order_name'>家长昵称："+response[i].studentName+"("+response[i].studentPhoneNum+")"+"</span><br/>"+
					"科目年级："+response[i].course+" "+response[i].grade+"<br/>"+
					"授课老师："+response[i].teacherName+"<br/>"+
					"家长请求时间："+new Date(response[i].requestTime).format("yyyy-MM-dd hh:mm")+"<br/>"+
					"<span class='order_button' id='a4'>"+
					"<input type='button' id='button' value='知道了' class='order_accept' onclick='closeNewDirectPush("+response[i].directPushId+")'/>"+
					"</span>"+
					"</div>";
				}
				$("#newDirectionalPushDiv").append(dataTable);
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function closeNewDirectPush(directPushId){
	$.ajax({
		type : "post",
		url : "../order/closeNewDirectPush.do",
		dataType : "json",
		async : false,//设置为
		data: {	
			"directPushId":directPushId
		},
		success : function(data) {
			if(data.result==true){
				$("#"+directPushId+"NewDirectPushDiv").remove();
			}else{
				alert(data.resultMsg);
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function refreshNewDirectionalPush(){
	queryNewDirectionalPush();
	setTimeout(function() {
		refreshNewDirectionalPush();
	}, 902000);
}