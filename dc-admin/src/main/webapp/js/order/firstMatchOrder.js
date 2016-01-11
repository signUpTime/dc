//document.write("<script language='javascript' src='LL.js'></script>");
//document.write("<script language='javascript' src='public.js'></script>");
function queryFirstMatchOrder(){
	$("#firstMatchDiv").empty();
	$.ajax({
		type : "post",
		url : "../order/selectMatchOrder.do",
		async : false,//设置为
		dataType : "json",
		success : function(data) {
			if(data.result==true){
				var dataTable="";
				var response=data.model;
				for(var i=0;i<response.length;i++){
					if(response[i].matchType==1){
						var isAudition="不需要试听";
						if(response[i].isAudition==1){
							isAudition="需要试听";
						}
						dataTable+="<div class='data' id='"+response[i].requestId+"Table'>";
						if(response[i].hasAcked || response[i].requestStatus != 10){
							dataTable +="<span class='order_time' id='"+response[i].requestId+"NoTimer'>来源智能匹配</span>"
						}else{
							dataTable += "<input class='timeInput' style='display: none;' onclick='timer("+response[i].requestId+","+response[i].leftTime+ ")'>"+
				            "<span class='order_time' id='"+response[i].requestId+"Timer'>来源智能匹配：倒计时 00:00:"+response[i].leftTime+"</span>";
						}
						
						dataTable +=
						            "<span class='order_name'>家长昵称："+response[i].userName+"</span>"+
						            "科目年级："+response[i].grade+" "+response[i].course+"<br/>"+
						            "上课地点："+response[i].address+"<br/>"+
						            "上课时间："+response[i].startCourseTime+" "+response[i].timeBlock+"<br/>"+
						            "试听需求："+isAudition+"<br/>";
						if(response[i].hasAcked || response[i].requestStatus != 10){
							dataTable += 
						         "订单状态：" + response[i].requestStatusStr+
								"<div class='order_button'><input type='button' name='button' id='"+response[i].requestId+"closeRequest' value='关闭'  class='order_no'  onclick='closeRequest("+ "\"" + response[i].requestId + "\",\"" + response[i].assistantId +  "\",\"" + response[i].userId + "\"" + ")'/></div>";
						}else{
							dataTable += "<div class='order_button'><input type='button' name='button' id='"+response[i].requestId+"ReceiveOrder' value='接单'  class='order_accept' onclick='receiveOrder("+response[i].requestId+")'/><input onclick='notAccept("+response[i].requestId+")'  type='button' name='button' id='button' value='暂不接'  class='order_no'/></div>";
						}
						            
						dataTable += "</div>";
					}else{
						var times=response[i].times;
						var timeMsg="";
						for(var j=0;j<times.length;j++){
							timeMsg+=times[j].date+" "+times[j].startCourseTime+"至"+times[j].endCourseTime+"  ";
						}
						var gender="";
						if(response[i].teacherInfo.gender==0){
							gender="<img src='../images/icon_woman.png' style='vertical-align:middle;'/>";
						}else{
							gender="<img src='../images/icon_man.png' style='vertical-align:middle;'/>";
						}
						dataTable+=
						      	"<div class='data' id='"+response[i].orderId+"Table'>"+
						            "<span class='order_time'>来源手机号码查询</span>"+
						            "<span class='order_name'>家长昵称："+response[i].userName+"("+response[i].userPhoneNum+")</span>"+
						            "<span class='order_name'>老师昵称："+response[i].teacherInfo.nick+"("+response[i].teacherInfo.name+")</span>"+
						            "科目年级："+response[i].grade+" "+response[i].course+"<br/>"+
						            "上课地点："+response[i].address+"<br/>"+
						            "上课时间："+timeMsg+"<br/>"+
						            "总计课时："+response[i].totalTime+"小时<br/>"+
						            "课程金额：¥"+response[i].price+"<br/>";
							if(response[i].requestStatus==70){
								dataTable+=
									"<div class='order_button'><input type='button' name='button' id='button' value='同意'  class='order_accept' onclick='commitOrder("+response[i].orderId+")'/><input onclick='rejectOrder("+response[i].orderId+")' type='button' name='button' id='button' value='拒绝' class='order_no'/></div>"+
						        "</div>";
							}else{
								dataTable+= 
									"订单状态:"+response[i].requestStatusStr+"<br/>"+
									" <div class='order_button'><input type='button' name='button' id='button' value='关闭' class='order_no' onclick='closeOrder("+ "\"" + response[i].orderId + "\",\"" + response[i].assistantId +  "\",\"" + response[i].userId + "\"" + ")'/></div>"+
						        "</div>";
							}
					}
				}
				$("#firstMatchDiv").append(dataTable);
				$(".timeInput").trigger("click");
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function receiveOrder(requestId){
	$.ajax({
		type : "post",
		url : "../order/selectMatchTeacher.do",
		async : false,//设置为
		dataType : "json",
		data: {	
			"requestId":requestId
		},
		success : function(data) {
			if(data==null){
				alert("查询失败");
			}else if(data.result==false){
				$("#"+requestId+"").append(data.resultMsg);
			}else{
				var dataDiv="";
				dataDiv+=
				"<div class='order_teacher_list'><div class='data'>";
				var response=data.model;
				for(var i=0;i<response.length;i++){
					dataDiv+=
		        		"<input name='"+requestId+"Teacher' type='checkbox' value='"+response[i].id+"' /> <font class='order_teacher'>"+response[i].nick+"</font>好评率："+((isNaN(response[i].praiseNum/response[i].teachNum*100))?"--":xround((response[i].praiseNum/response[i].teachNum*100),2)+"%")+" 距上课地点:"+response[i].distance+"KM<br/>";
				}
				dataDiv+=
					 "<div class='buttom'>"+
			        	"<span class='block'><input name='' type='checkbox' value='0' onclick='checkAll(this,"+requestId+")' /> 全选  <input type='button' name='button' id='button' value='帮选中老师接单'  class='order_accept' onclick='commitReceiveOrder("+requestId+")'/></span>"+ 
			        "</div></div></div>";
				layer.open({
				    type: 1, //page层
				    skin: 'layui-layer-lan',
				    area: ['800px', '400px'],
				    title: '老师详情',
				    content: dataDiv
				}); 
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}
//暂不接
function notAccept(requestId){
	$.ajax({
		type : "post",
		url : "../order/notAccept.do",
		async : false,//设置为
		dataType : "json",
		data:{
			"requestId":requestId
		},
		success : function(data) {
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
	var tableId=requestId+"Table";
	$("#"+tableId+"").remove();
}

//关闭智能匹配页面
function closeRequest(requestId, assistantId, userId){
	var request = {"assistantId":assistantId, "matchType":1, "requestId":requestId, "orderId":0, "userId":userId}
	
	$.ajax({
		type : "post",
		url : "../order/taClose.do",
		async : false,//设置为
		dataType : "json",
		contentType:"application/json",
		data:JSON.stringify(request),
		success : function(data) {
			if(data.result==true){
				$("#"+requestId+"Table").remove();
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


//关闭手机号查询页面
function closeOrder(orderId, assistantId, userId){
	var request = {"assistantId":assistantId, "matchType":2, "requestId":0, "orderId":orderId, "userId":userId}
	$.ajax({
		type : "post",
		url : "../order/taClose.do",
		async : false,//设置为
		dataType : "json",
		contentType:"application/json",
		data:JSON.stringify(request),
		success : function(data) {
			if(data.result==true){
				$("#"+orderId+"Table").remove();
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

function checkAll(obj,checkboxName){
	checkboxName=checkboxName+"Teacher";
	var isChecked=$(obj).val();
	if(isChecked==0){
		$("input[name='"+checkboxName+"']").prop("checked",true);
		$(obj).attr("value",1);
	}else if(isChecked==1){
		$("input[name='"+checkboxName+"']").prop("checked",false);
		$(obj).attr("value",0);
	}
}
function commitReceiveOrder(requestId){
	var checkboxName=requestId+"Teacher";
	var teacherIds=[];
	$("input[name='"+checkboxName+"']:checked").each(function(){
		teacherIds.push($(this).val());
        });
	if(teacherIds.length==0){
		alert("请选择接单老师");
		return false;
	}
	var request={"teacherIds":teacherIds,"requestId":requestId};
	$.ajax({
		type : "post",
		url : "../order/commitReceiveOrder.do",
		async : false,//设置为
		dataType : "json",
		contentType:"application/json",  
		data:JSON.stringify(request),
		success : function(data) {
			if(data==null){
				alert("接单失败");
			}else if(data.result==false){
				alert(data.resultMsg);
			}else{
//				var response=data.model;
//				var toUser=response.userId;
//				var message='{"t":"2","q":"'+requestId+'","n":"'+teacherIds.length+'"}';
//				toUser="s"+pad(toUser, 15);
//				send(toUser,message);
				alert("接单成功");
				layer.closeAll();
				queryFirstMatchOrder();
				$("#"+requestId+"Timer").css("display","none");
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}
//同意
function commitOrder(orderId){
	$.ajax({
		type : "post",
		url : "../order/changeStatus.do",
		async : false,//设置为
		dataType : "json",
		data:{
			"orderId":orderId,
			"status":1
		},
		success : function(data) {
			if(data==null){
				alert("确定订单失败");
			}else if(data.result==false){
				alert(data.resultMsg);
				$("#"+orderId+"Table").remove();
			}else{
//				var response=data.model;
//				var toUser=response.userId;
//				var message='{"t":"8","tt":"提示","tv":"您有新的订单,请进入“找老师”界面，及时去支付."}';
//				toUser="s"+pad(toUser, 15);
//				send(toUser,message);
				alert("确认成功");
//				$("#"+orderId+"Table").remove();
				queryFirstMatchOrder();
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}
function rejectOrder(orderId){
	$.ajax({
		type : "post",
		url : "../order/changeStatus.do",
		async : false,//设置为
		dataType : "json",
		data:{
			"orderId":orderId,
			"status":8
		},
		success : function(data) {
			if(data==null){
				alert("操作失败");
			}else if(data.result==false){
				alert(data.resultMsg);
//				$("#"+orderId+"Table").remove();
				queryFirstMatchOrder();
			}else{
				alert("拒绝成功");
//				$("#"+orderId+"Table").remove();
				queryFirstMatchOrder();
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}
function timer(id,second)  {  
	var timeSpan = $("#"+id+"Timer");
	if (second <= 0) {
		$("#"+id+"Table").remove();
	} else {
		var min=(second-second%60)/60;
		if(min<10){
			min="0"+min;
		}
		var s=second%60;
		if(s<10){
			s="0"+s;
		}
		var timeContent ="来源智能匹配：倒计时 00:"+min+":"+s+"";
		timeSpan.html(timeContent);
		second--;
		setTimeout(function() {
			timer(id,second,status);
		}, 1000);
	}		
}
function send(user,value) { 
 LL.Message.send(user,value); 
}