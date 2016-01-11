function queryPotentialStudent(){
	$("#potentialStudentDiv").empty();
	$.ajax({
		type : "post",
		url : "../order/selectFindTeacherByType.do",
		async : false,//设置为
		dataType : "json",
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
					"老师性别："+response[i].sex+"<br/>"+
					"来源："+response[i].requestType+"<br/>"+
					"<span class='order_button' id='a4'>"+
					"<input type='button' id='button' value='接单' class='order_accept' onclick='receiveRequest("+response[i].findTeacherRequestId+")'/>"+
					"</span>"+
					"</div>";
				}
				$("#potentialStudentDiv").append(dataTable);
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function receiveRequest(requestId){
	var isAccept = true;
	var dealResult = sendDealRequest(requestId,isAccept);
	$("#"+requestId+"FindTeacherDiv").remove();
	if(dealResult){
		alert("接单成功");
		$("#"+requestId+"FindTeacherDiv").remove();
	}
}

function rejectRequest(requestId){
	var isAccept = false;
	var dealResult = sendDealRequest(requestId,isAccept);
	if(dealResult){
		$("#"+requestId+"FindTeacherDiv").remove();
	}
}

function sendDealRequest(requestId,isAccept){
	var dealResult = false
	$.ajax({
		type : "post",
		url : "../order/dealFindTeacherRequest.do",
		dataType : "json",
		async : false,//设置为
		data: {	
			"requestId":requestId,
			"isAccept":isAccept
		},
		success : function(data) {
			if(data.result==true){
				dealResult = true ;
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
	return dealResult;
}
function refreshPotentialStudent(){
	queryPotentialStudent();
	setTimeout(function() {
		refreshPotentialStudent();
	}, 900000);
}