document.write("<script language='javascript' src='public.js'></script>");
var isSetCourseTime=0;
var adjustTime=0;
var teacheTimeMap=new Map();
var addDivMap=new Map();
function toSetCourseTime(teacherId,courseId){
	if(isSetCourseTime==0){
		setCourseTime(teacherId,'',courseId);
	}else{
		$("#detail").hide();
		$("#calenderHeader").show();
		$("#calenderDiv").show();
	}
}
//根据老师查询日历
function setCourseTime(teacherId,date,courseId){
	isSetCourseTime=1;
	$("#tableContent").find("tr").remove();
	var studentId=$("#userId").val();
	 var now = new Date();
	 var yesterday = new Date(now-1000 * 60 * 60 * 24);
	if(date==null ||date==''){
		date=new Date().format("yyyy-MM");
	}
	$.ajax({
		type : "post",
		url : "../course/getTeacherCalender.do",
		dataType : "json",
		data: {
			"teacherId" : teacherId,
			"studentId" : studentId,
			"courseId"  : courseId,
			"month" : date
			},
		success : function(data) {
			if(data.result==false){
				alert(data.resultMsg);
			}else{
				$("#detail").hide();
				response=data.model;
				var dataTable="";
				dataTable+=
				"<tr>"+
			      "<th>周一</th>"+
			      "<th>周二</th>"+
			      "<th>周三</th>"+
			      "<th>周四</th>"+
			      "<th>周五</th>"+
			      "<th>周六</th>"+
			      "<th>周日</th>"+
			    "</tr>";
				for(var i=0;i<response.length;i++){
					var timeArray=new Array();
					var date=response[i].date;
					var dateTime=response[i].dateTime;
					var day=response[i].day;
					var details=response[i].dayDetails;
					var studentDetails=response[i].studentDetails;
					var beforOrAfter=1;
			    	if(dateTime>yesterday){
			    		beforOrAfter=2;
			    	}
					if(i%7==0){
						dataTable+="<tr>";
					}
					dataTable+=
				      "<td>"+
				      "<div class='day' id='"+date+"'>"+
				      "<h"+beforOrAfter+">"+day+"</h"+beforOrAfter+">";
						if(beforOrAfter==2){
							dataTable+=
							"<div class='add'><a  href='#' onclick='selectCourseTime(this)' val='"+date+"'><img src='../images/icon_add.png' width='30' height='30' align='absmiddle' />添加授课时间</a></div>";
						}
						for(var j=0;j<details.length;j++){
							if(details[j].status==2){
								dataTable+=
									"<div class='font"+beforOrAfter+"'><font class='tpye'>"+changeStartBlockToTime(details[j].startTimeBlock)+"至"+changeEndBlockToTime(details[j].endTimeBlock)+"</font><span class='tpye'>"+details[j].statusRemark+"</span></div>";
							}else if(details[j].status==1){
								dataTable+=
									"<div class='font"+beforOrAfter+"'><font class='tpye_course'>"+changeStartBlockToTime(details[j].startTimeBlock)+"至"+changeEndBlockToTime(details[j].endTimeBlock)+"</font><span class='tpye_course'>"+details[j].statusRemark+"</span><input style='display:none;' class='"+date+"block' value='"+details[j].startTimeBlock+","+details[j].endTimeBlock+"'/></div>";
							}
						}
						for(var j=0;j<studentDetails.length;j++){
							dataTable+=
								"<div class='font"+beforOrAfter+"'><font class='tpye_student'>"+changeStartBlockToTime(studentDetails[j].startTimeBlock)+"至"+changeEndBlockToTime(studentDetails[j].endTimeBlock)+"</font><span class='tpye_student'>"+studentDetails[j].statusRemark+"</span><input style='display:none;' class='"+date+"block' value='"+studentDetails[j].startTimeBlock+","+studentDetails[j].endTimeBlock+"'/></div>";
						}
						teacheTimeMap.put(date,timeArray);
					dataTable+=
				      "</div>"+
				      "</td>";
					
					if(i%7==6){
						dataTable+="</tr>";
					}
				}
				$("#tableContent").append(dataTable);
				addDivMap.each(function addCourseDiv(addDivMapkey,addDivMapvalue,addDivMapindex){
					if (typeof(addDivMapvalue) != "undefined"){
						addDivMapvalue.each(function addCourseDiv(divkey,divvalue,divindex){
							$("#"+addDivMapkey+"").append(divvalue);
							});
						}
					});
				$("#calenderHeader").show();
				$("#calenderDiv").show();
			}
		},
		error : function(
				XMLHttpRequest,
				textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}
function selectCourseTime(obj){
	var date=$(obj).attr("val");
	$("#selectCourseDate").attr("value",date);
	$("#A1").show();
}
function adjustCourse(obj,block){
	if(addDivMap.size() > 0 ){
		alert("已选择上课时间，请删除后重新选择");
	}else{
		var startBlock=parseInt($("#startBlock").val());
		var endBlock=parseInt($("#endBlock").val());
		if(block!=(endBlock-startBlock)){
			alert("调课课时请与原课时保持一致。");
			return false;
		}
		var flag=addCourse(obj);
		if(flag==false){
			return false;
		}
		$('input[name="courseTime"]').prop("checked",false);
		$("#newTime").prop("checked",true);
	}
}
function addCourse(obj,from){
	var date=$("#selectCourseDate").val();
	var startBlock=parseInt($("#startBlock").val());
	var endBlock=parseInt($("#endBlock").val());
	if(startBlock>endBlock){
		alert("开始时间必须小于结束时间,请重新选择");
		return false;
	}
	if('continueOrder' == from  && (endBlock - startBlock < 3)){
		alert("课程时间必须大于两小时,请重新选择");
		return false;
	}
	if(date==new Date().format("yyyy-MM-dd")){
		var now=new Date().format("hh:mm").split(":");
		var hour=parseInt(now[0]);
		var minute=parseInt(now[1]);
		var nowBlock=(hour-8)*2;
		if(minute>=30){
			nowBlock=nowBlock+1;
		}
		if((nowBlock+2)>startBlock){
			alert("课程不能调至当前时间一小时以内或过去时间段");
			return false;
		}
	}
	var times=teacheTimeMap.get(date);
	var isFalse=0;
	$("."+date+"block").each(function(index){
		var block=$(this).val().split(",");
		var existStart=parseInt(Number(block[0]));
		var existEnd=parseInt(Number(block[1]));
		if((startBlock>=existStart && (startBlock-1)<existEnd) || (endBlock>(existStart-1) && endBlock<=existEnd) || ((existStart-1)>=startBlock && (existStart-1)<endBlock) || (existEnd>(startBlock-1) && existEnd<=endBlock)){
			isFalse=1;
			return false;
		}
    });
	if(isFalse==1){
		alert("时间冲突,请重新选择");
		return false;
	}
	var addMessage="<font class='"+date+startBlock+" courseTime'>"+date+" "+changeStartBlockToTime(startBlock)+"至"+changeEndBlockToTime(endBlock)+"<input id='newTime' type='checkbox' checked='checked' style='display: none;' name='courseTime' value='"+date+","+startBlock+","+endBlock+"'><br /></font>";
	var addDiv="<div class='"+date+startBlock+" font3 courseTime' ><font>"+changeStartBlockToTime(startBlock)+"至"+changeEndBlockToTime(endBlock)+"</font>安排上课<a href='#' onclick='deleteCourse(this)' class='"+date+"' val='"+date+startBlock+"'><img src='../images/icon_delete.png' width='20' height='20' border='0' align='absmiddle' title='删除此时间段' /></a><input style='display:none;' class='"+date+"block' value='"+startBlock+","+endBlock+"'/></div>";
	$("#courseTimeTd").append(addMessage);
	$("#"+date+"").append(addDiv);
	var addDivs=addDivMap.get(date);
	if (typeof(addDivs) == "undefined")
	{
		addDivs=new Map();
		addDivs.put(date+startBlock,addDiv);
	} else{
		addDivs.put(date+startBlock,addDiv);
	}
	addDivMap.put(date,addDivs);
	$("#A1").hide();
}
function deleteCourse(obj){
	var deleteId=$(obj).attr("val");
	var date=$(obj).attr("class");
	var addDivs=addDivMap.get(date);
	$("."+deleteId+"").remove();
	addDivs.remove(deleteId);
	if(addDivs.size()==0){
		addDivMap.remove(date);
	}
}

function closeDetail(){
	$("#A1").hide();
}

function backContinuePage(){
	$(".courseTime").remove();
	$("#newOrderCourseNum").html(0);
	addDivMap=new Map();
	$("#detail").show();
	$("#calenderHeader").hide();
	$("#calenderDiv").hide();
	closeDetail();
}
function commitCourseTime(){
	$("#newOrderCourseNum").html($("#courseTimeTd").children(".courseTime").length);
	$("#detail").show();
	$("#calenderHeader").hide();
	$("#calenderDiv").hide();
}

function changeStartBlockToTime(block){
	if(block%2==0){
		return 8+block/2+":00"
	}else{
		return 8+(block-1)/2+":30";
	}
}
function changeEndBlockToTime(block){
	if(block%2==0){
		return 8+block/2+":30"
	}else{
		return 8+(block+1)/2+":00";
	}
}
