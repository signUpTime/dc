function queryInitiativeRequest(){
	$("#initialtiveRequestDiv").empty();
	$.ajax({
		type : "post",
		url : "../order/selectFindTeacherByType.do",
		async : false,//设置为
		dataType : "json",
		data:{
			type : 2
		},
		success : function(data) {
			if(data.result==true){
				var dataTable="";
				var response=data.model;
				for(var i=0;i<response.length;i++){
				dataTable+=
					"<div class='data' id='"+response[i].findTeacherRequestId+"FindTeacherDiv'>"+
					"<span class='order_name'>家长昵称："+response[i].studentName+"</span><br/>"+
					"科目年级："+response[i].course+" "+response[i].grade+"<br/>"+
					"上课地点："+response[i].address+"<br/>"+
					"老师性别："+response[i].sex+"<br/>"+
					"家长请求时间："+new Date(response[i].createTime).format("yyyy-MM-dd hh:mm")+"<br/>"+
					"<span class='order_button' id='a4'>"+
					"<input type='button' id='button' value='接单' class='order_accept' onclick='receiveRequest("+response[i].findTeacherRequestId+")'/>"+
					"<input type='button' id='button' value='不接单' class='order_accept' onclick='rejectRequest("+response[i].findTeacherRequestId+")'/>"+
					"</span>"+
					"</div>";
				}
				$("#initialtiveRequestDiv").append(dataTable);
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}
function refreshInitiativeRequest(){
	queryInitiativeRequest();
	setTimeout(function() {
		refreshInitiativeRequest();
	}, 901000);
}