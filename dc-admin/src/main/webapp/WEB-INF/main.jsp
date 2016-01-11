<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/WEB-INF/common/common.jsp"/>
<script>
var arrayObj=[];//聊天窗口
// MQMessage.COMMAND_PUSH=1;
// MQMessage.COMMAND_PUSH_MESSAGE=6;
$(document).ready(function(){
	var height=document.body.clientHeight-105;
	$("#content").height(height);
});
</script>
<body id="body">
<%-- 	<iframe id="main" name="main" src="<%=request.getContextPath()%>/page/index.do" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" ></iframe> --%>
		<iframe id="head" name="head" src="<%=request.getContextPath()%>/page/getHeader.do" width="100%" height="105" frameborder="0" scrolling="yes" noresize="noresize"></iframe>
		<iframe src="<%=request.getContextPath()%>/foods/foodsList.do" frameborder="0" width="100%" noresize="noresize" name="content" id="content" scrolling="yes"></iframe>
		<input id="path"  style="display:none;" value="<%=request.getContextPath()%>"/>

<script src="<%=request.getContextPath()%>/js/LL.js<%=System.getProperty("version")%>" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/pushService.js<%=System.getProperty("version")%>" type="text/javascript"></script>
</body>
</html>