<%--
  Created by IntelliJ IDEA.
  User: wangyangyang
  Date: 2017/6/13
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="qqtag" uri="/WEB-INF/tld/qq-tag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/common/common.jsp"/>

<body>

    <input id="path"  style="display:none;" value="<%=request.getContextPath()%>"/>
    <div style="margin-top: 15px;text-align: center">
        <p>请选择送餐地址：
            <qqtag:selectDestinationTag id="selectDestination" name="selectDestination" selectedValue="${user.destinationId}"></qqtag:selectDestinationTag>
        </p>
        <br/>
        <input type="button" id="saveAdd" value="确定" class="button" style="width: 60px;" onclick="addDestinationId()">
    </div>
<div id="queryResult"></div>
</body>

<script type="text/javascript">
    var path = $("#path").val();

    function addDestinationId(){
        var dataObj={};
        var destinationId = $("#selectDestination").val();
        dataObj.destinationId = destinationId;
        console.log(dataObj);
        $.ajax({
            url: path+"/user/addDestinationId.do?destinationId="+destinationId,
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.result==true){
                    parent.query(1);
                    parent.layer.closeAll();
                }else{
                    if(data.resultMsg){
                        layer.alert(data.resultMsg);
                    }
                }
            }
        });
    }

</script>

</html>

