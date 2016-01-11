<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/common/common.jsp" />
<body margin="0">
<div class="header">
 <%--  <div class="left"><img src="<%=request.getContextPath()%>/images/logo_s.png" width="143" height="30" /></div> --%>
  <div class="right">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="user">
      <tr>
        <td align="right"><span id="nick">${user.name}</span>，<span id="timeBolck"></span>好<br>
          <span id="nowTime"></span>&nbsp;<!-- [<a href="#" onClick="parent.location='../login/logout.do'" class="a2">退出</a>] --></td>
        <td width="50" align="right"><img id="icon" src="${iconSrc}" width="35"/></td>
      </tr>
    </table>
  </div>
</div>
<div class="mainnav">
  <ul class="ban">
    <li>
    	<a href="<%=request.getContextPath()%>/foods/toFoodsManage.do" target="content" id="set" name="group" val="../images/nav_setup" imgId="setup"><img name="groupImg" id="setup" val="<%=request.getContextPath()%>/images/nav_setupA.png" src="<%=request.getContextPath()%>/images/nav_setupA.png" width="28" height="28" border="0" /><br />
                            点餐
        </a>
    </li>
    <li style="width:5px">&nbsp;</li>
  </ul>
</div>
<input id="path1"  style="display:none;" value="<%=request.getContextPath()%>"/>

<script type="text/javascript"> 
var authority=[];
<c:forEach var="item" items="${sessionScope.privMap}">
	<c:if test="${item.key == 1}">
	authority.push(1);
	</c:if>
	<c:if test="${item.key == 2}">
	authority.push(2);
	</c:if>
	<c:if test="${item.key == 3}">
	authority.push(3);
	</c:if>
</c:forEach>
	var nowTime = ${nowDate};
	function currentNow(){ 
		var d=new Date(nowTime),str=''; 
		str +=d.getFullYear()+'年'; //获取当前年份 
		str +=d.getMonth()+1+'月'; //获取当前月份（0——11） 
		str +=d.getDate()+'日  '; 
		str +=(d.getHours() < 10)?"0"+d.getHours()+':':d.getHours()+':'; 
		str +=(d.getMinutes() < 10)?"0"+d.getMinutes()+':':d.getMinutes()+':'; 
		str +=(d.getSeconds() < 10)?"0"+d.getSeconds():d.getSeconds(); 
		$("#nowTime").text(str);
	} 
	function current(){ 
		nowTime = nowTime + 1000;
		var d=new Date(nowTime),str=''; 
		str +=d.getFullYear()+'年'; //获取当前年份 
		str +=d.getMonth()+1+'月'; //获取当前月份（0——11） 
		str +=d.getDate()+'日  '; 
		str +=(d.getHours() < 10)?"0"+d.getHours()+':':d.getHours()+':'; 
		str +=(d.getMinutes() < 10)?"0"+d.getMinutes()+':':d.getMinutes()+':'; 
		str +=(d.getSeconds() < 10)?"0"+d.getSeconds():d.getSeconds(); 
	// 	alert(d.getHours());
		if(d.getHours()<=12){
			$("#timeBolck").text("上午");
		}else{
			$("#timeBolck").text("下午");
		}
		return str; 
	} 
	$(function(){
		currentNow();
		setInterval(function(){$("#nowTime").text(current)},1000); 
	});
	$("a[name='group']").click(
			function() {
				$("a[name='group']").attr('id','unCurrent');
				$(this).attr('id','current');
				var currentImg=$(this).attr("val")+"B.png";
				$("img[name='groupImg']").each(function(){
					var img=$(this).attr("val");
					$(this).attr('src',img);
				});
				var imgId=$("#current").attr("imgId");
				$("#"+imgId+"").attr('src',currentImg);
			});
	function toOrder(){
		window.parent.content.location.href="<%=request.getContextPath()%>/order/toOrder.do";
		$("a[name='group']").attr('id','unCurrent');
		$("img[name='groupImg']").each(function(){
			var img=$(this).attr("val");
			$(this).attr('src',img);
		});
		$(".order").attr('id','current');
		$("#order").attr('src','../images/nav_orderB.png');
	}
	function toMessage(){
		window.parent.content.location.href="<%=request.getContextPath()%>/message/toMessage.do";
		$("a[name='group']").attr('id','unCurrent');
		$(".message").attr('id','current');
		$("img[name='groupImg']").each(function(){
			var img=$(this).attr("val");
			$(this).attr('src',img);
		});
		$("#news").attr('src','../images/nav_newsB.png');
	}
	
</script>

</body>
</html>