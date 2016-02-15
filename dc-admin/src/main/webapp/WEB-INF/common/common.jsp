<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>轻轻点餐</title>
	<link href="<%=request.getContextPath()%>/CSS/core.css<%=System.getProperty("version")%>" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/CSS/style.css<%=System.getProperty("version")%>" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/CSS/themes/jquery.ui.all.css<%=System.getProperty("version")%>" rel="stylesheet" type="text/css"/>
<!-- 	easyui -->
<%-- 	<link href="<%=request.getContextPath()%>/CSS/easyui/themes/default/easyui.css<%=System.getProperty("version")%>" rel="stylesheet" type="text/css"/> --%>
<%-- 	<link href="<%=request.getContextPath()%>/CSS/easyui/themes/icon.css<%=System.getProperty("version")%>" rel="stylesheet" type="text/css"/> --%>
<!-- 	日历 -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js<%=System.getProperty("version")%>" type="text/javascript" ></script>
	<script src="<%=request.getContextPath()%>/js/jquery.paginate.js<%=System.getProperty("version")%>" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js<%=System.getProperty("version")%>" type="text/javascript" ></script>
<!-- 	分页 -->
	<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js<%=System.getProperty("version")%>"></script>
	<script src="<%=request.getContextPath()%>/js/layer/layer.js<%=System.getProperty("version")%>"></script>
	<script src="<%=request.getContextPath()%>/js/layer/extend/layer.ext.js<%=System.getProperty("version")%>"></script>
	<script src="<%=request.getContextPath()%>/js/public.js<%=System.getProperty("version")%>" type="text/javascript" ></script>
	<script src="<%=request.getContextPath()%>/js/validator.js<%=System.getProperty("version")%>" type="text/javascript" ></script>
	<script src="<%=request.getContextPath()%>/js/ajaxfileupload.js<%=System.getProperty("version")%>" type="text/javascript"></script>
<!-- 	easyui -->
<%-- <script src="<%=request.getContextPath()%>/js/easyui/jquery.easyui.min.js<%=System.getProperty("version")%>" type="text/javascript" ></script> --%>
</head>
