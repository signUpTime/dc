//document.write("<script language='javascript' src='public.js'></script>");
var isDealing=0;
function querySecondMatchOrder(){
	$("#secondMatchDiv").empty();
	$.ajax({
		type : "post",
		url : "../order/selectSecondMatchOrder.do",
		async : false,//设置为
		dataType : "json",
		success : function(data) {
			if(data.result==true){
				var dataTable="";
				var response=data.model;
				for(var i=0;i<response.length;i++){
					var isAudition="不需要试听";
					if(response[i].isAudition==1){
						isAudition="需要试听";
					}
				dataTable+=
					"<div class='data' id='"+response[i].requestId+"Div'>"+
					"<span class='order_name'>家长昵称："+response[i].userName+"</span>"+
					"科目年级："+response[i].grade+" "+response[i].course+"<br/>"+
					"上课地点："+response[i].address+"<br/>"+
					"上课时间："+response[i].startCourseTime+" "+response[i].timeBlock+"<br/>"+
					"家长期望课酬："+response[i].startPrice+"-"+response[i].endPrice+"元/小时<br/>"+
					"试听需求："+isAudition+"<br/>"+
					"<span class='order_button' id='a4'>"+
					"<input type='button' name='secondReceiveOrder' id='button' value='接单' class='order_accept secondReceiveOrder' onclick='secondReceiveOrder("+response[i].requestId+")'/>"+
					"</span>"+
					"</div>";
			}
				$("#secondMatchDiv").append(dataTable);
				if(response.length ==1 && response[0].status == 1){
					secondReceiveOrder(response[0].requestId);
				}
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function secondReceiveOrder(requestId){
	$.ajax({
		type : "post",
		url : "../order/applyForSecondMatch.do",
		dataType : "json",
		async : false,//设置为
		data: {	
			"requestId":requestId
		},
		success : function(data) {
			if(data.result==true){
				var response=data.model;
				var dataTable = "";
				dataTable+=
			    "<div id='a3'> <span class='phone'>家长手机："+response.userPhoneNum+"</span>"+
			    "<span><select id='isEffective' class='select'><option value='-1'>家长请求是否有效</option><option value='1'>有效</option><option value='0'>无效</option><option value='2'>其他</option></select></span>"+
			    "<span class='block'>"+
			    "<textarea name='' cols='' rows='' id='feedBack'></textarea>"+
			    "</span> <span class='order_button'>"+
			    "<input type='button' name='button' id='button' value='成功'  class='order_accept' onClick='commitApply("+requestId+","+response.applyLogId+",3)' />"+
			    "<input type='button' name='button' id='button' value='失败'  class='order_no' onClick='commitApply("+requestId+","+response.applyLogId+",2)' />"+
			    "</span>"+
			    "</div>";
				$(".secondReceiveOrder").remove();
				$("#"+requestId+"Div").append(dataTable);
				isDealing=1;
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

function commitApply(requestId,applyLogId,result){
	var isEffective = $("#isEffective").val();
	if(isEffective == -1){
		alert("请选择家长请求是否有效");
		return false;
	}
	var feedBack=$("#feedBack").val();
	if(result==2 && feedBack==""){
		alert("联系失败必须输入反馈内容");
		return false;
	}
	if(feedBack.length > 200 ){
		alert("反馈内容最多200个汉字");
		return false;
	}
	var request={"requestId":requestId,"id":applyLogId,"status":result,"feedback":feedBack,"isEffective":isEffective};
	$.ajax({
		type : "post",
		url : "../order/commitSecondMatch.do",
		async : false,//设置为
		dataType : "json",
		contentType:"application/json",  
		data:JSON.stringify(request),
		success : function(data) {
			if(data.result==true){
				querySecondMatchOrder();
				isDealing=0;
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
function refreshSecondMatchOrder(){
	if(isDealing == 0){
		querySecondMatchOrder();
	}
	setTimeout(function() {
		refreshSecondMatchOrder();
	}, 120000);
}