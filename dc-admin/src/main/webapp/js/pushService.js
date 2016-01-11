function analyMessage(message){
	var json=LL.json(message);
	var type =parseInt(json.t);
	switch(type){
	case 1:
		//智能匹配
		var requestId=parseInt(json.q);
		intelligentMatch(requestId);
		break;
	case 6:
		chat(json);
		break;
	case 28:
		refreshResponseOrder();
		break;
	case 101:
		refreshResponseOrder();
		break;
	case 102:
		refreshResponseOrder();
		break;
	case 111: 
		//退课申请
		var additionalMsg="<br/>查看“退课审核”";
		showSystemMsg(json,additionalMsg);
		break;
	case 120: 
		//智能匹配成功
		var additionalMsg="<br/>查看“历史订单”";
		showSystemMsg(json,additionalMsg);
		break;
	}
}
/**
 * 智能匹配
 * @param requestId
 */
function intelligentMatch(requestId){
	$.ajax({
		type : "post",
		url :"../order/checkRequest.do",
		dataType : "json",
		data:{
			"requestId":requestId
		},
		success : function(data) {
			if(data.result==true){
				addResponseOrderDiv(requestId);
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}
/**
 * 智能匹配订单弹框
 * @param requestId
 */
function addResponseOrderDiv(requestId){
	try {
		content.window.contentpage.window.queryFirstMatchOrder();
	} catch (e) {
		if(requestId !=0){
			var date=new Date();
			var dateTime=date.format("yyyy-MM-dd hh:mm:ss");
			var div="";
			div+=
				"<div class='message' id='"+requestId+"'>"+
				"<span class='title'>"+
				"<span class='message_close' onclick=closeDiv("+requestId+")><img src='../images/close.png' /></span>"+
				"<span>响应订单消息</span>    </span>"+
				"<p class='message_cont'>系统提示：有订单待响应！<br/>"+"<a href='#' onclick='toResponseOrder("+requestId+")'>点击查看</a>"+"<br/> "+dateTime+"</p>"+
				"</div>"; 
			$("#body").append(div);
		}
	}
}
function refreshResponseOrder(){
	try {
		content.window.contentpage.window.queryFirstMatchOrder();
	} catch (e) {
	}
}
function refreshResponseOrderaa(){
	try {
		content.window.contentpage.window.queryFirstMatchOrder();
	} catch (e) {
	}
}
function chat(json){
	var send=escape(json.s);
	var sendType=parseInt(json.st);
	var receive=escape(json.r);
	var receiveType=escape(json.rt);
	var requestId=escape(json.id);
	var message=escape(json.c);
	message=unescape(message);
	var time=escape(json.pt);
	time=unescape(time);
	var windowId;
	var studentId;
	var teacherId;
	if(sendType==0){
		studentId=send;
		teacherId=receive;
		windowId=send+receive;
	}else if(sendType==1){
		studentId=receive;
		teacherId=send;
		windowId=receive+send;
	}
	var win=arrayObj[windowId];
	if(win==null){
		openNew(requestId,windowId,studentId,teacherId);
	}else{
		addMessage(requestId,windowId,studentId,teacherId,sendType,message,time);
	}
}
/**
 * 跳转至响应订单
 * @param requestId
 */
function toResponseOrder(requestId){
	 $("#head").contents().find("#toOrder").click();
	 $("#"+requestId+"").remove();
}
/**
 * 系统消息
 * @param json
 * @param additionalMsg
 */
function showSystemMsg(json,additionalMsg){
	var title=escape(json.tt);
	title=unescape(title);
	var content=escape(json.tv);
	content=unescape(content);
	content+=additionalMsg;
	addSystemDiv(parseInt(json.t),title,content);
}
 
 /**
  * 系统消息弹框
 * @param requestId
 * @param title
 * @param content
 */
function addSystemDiv(requestId,title,content){
	 var date=new Date();
	 var dateTime=date.format("yyyy-MM-dd hh:mm:ss");
	 var div="";
	 div+=
		"<div class='message' id='"+requestId+"'>"+
		"<span class='title'>"+
	    	"<span class='message_close' onclick=closeDiv("+requestId+")><img src='../images/close.png' /></span>"+
	    	"<span>"+title+"</span>    </span>"+
	    "<p class='message_cont'>"+content+"<br/>"+dateTime+"</p>"+
	"</div>"; 
	$("#body").append(div);
 }
 
 /**
  * 关闭弹框
 * @param requestId
 */
function closeDiv(requestId){
	 $("#"+requestId+"").remove();
 }

 /**
  * 开启新聊天窗口
 * @param requestId
 * @param windowId
 * @param studentId
 * @param teacherId
 */
function openNew(requestId,windowId,studentId,teacherId){
	 var url="../message/toChat.do?studentId="+studentId+"&teacherId="+teacherId+"&requestId="+requestId;
	 var newWin=window.open(url,windowId,'height=595,width=500,top=20,left=20,toolbar=no,menubar=no,scrollbars=no,alwaysRaised=yes,resizable=no,location=no,status=no');
	 arrayObj[windowId]=newWin;
	 newWin.focus();
 }
 /**
  * 聊天窗口显示新消息
 * @param requestId
 * @param windowId
 * @param studentId
 * @param teacherId
 * @param sendType
 * @param message
 * @param time
 */
function addMessage(requestId,windowId,studentId,teacherId,sendType,message,time){
	 try {
		 var win=arrayObj[windowId];
		 win.appendMessage(message,sendType,time);
		 win.focus();
	} catch (e) {
		openNew(requestId,windowId,studentId,teacherId)
	}
 }