<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*,org.jasig.cas.client.authentication.*;"%>
<html>
<jsp:include page="/WEB-INF/common/common.jsp"/>
<body id="body">
	<%
   	 	response.sendRedirect("http://127.0.0.1:8080/dc-admin/login/login.do");
	%>
</body>
</html>