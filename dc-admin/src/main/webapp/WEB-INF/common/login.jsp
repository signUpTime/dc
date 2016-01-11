<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>轻轻点餐</title>
<link href="<%=request.getContextPath()%>/CSS/core.css" rel="stylesheet" type="text/css" />
</head>

<body class="body_login">
<div class="header">
  <%-- <div class="body"><img src="<%=request.getContextPath()%>/images/logo.png" width="175" height="35" /></div> --%>
</div>
<div class="content">
  <div class="body">
    <div class="login" id="login" >
    <form>
      <table width="90%" border="0" cellspacing="0" cellpadding="0" style="margin-left:18px;">
        <tr>
          <td><h1 class="login_h1">登录名<span id="usernameworning" style="display: none;">注：请输入用户名</span></h1>
            <input name="username" type="text" class="input_login" id="username"/></td>
        </tr>
        <tr>
          <td><h1 class="login_h1">登录密码<span id="passwordworning" style="display: none;">注：请输入登录密码</span></h1>
            <input name="password" type="password" class="input_login" id="password"/></td>
        </tr>
        <%-- <tr>
          <td>
            <h1 class="login_h1">验证码<span id="randworning" style="display: none;">注：请输入图片中字符，大小写忽略</span></h1>
            <p>
              <input name="textfield" type="text" class="input_code" id="authCode"/>
              <img id="authCodeImg" src="<%=request.getContextPath()%>/login/getAuthCode.do" height="50" align="absmiddle"/>&nbsp;<a id="changeAuthCode" href="#">换一张 </a></p>
          </td>
        </tr> --%>
        <tr>
          <td height="40" class="font">
            <input name="checkbox" type="checkbox" id="checkbox" checked="checked" />记住我
         </td>
        </tr>
        <tr>
          <td><input type="button" name="button" id="loginSubmit" value="登录"  class="input_button" onclick="login()"/>
            <font class="font"><a href="#">忘记密码？</a></font></td>
        </tr>
      </table>
      </form>
    </div>
    <div class="validate" style="display: none;" id="validate">
    <form class="login">
      <table width="90%" border="0" cellspacing="0" cellpadding="0" style="margin-left:18px;">
        <tr>
          <td height="80" class="title">为了您的安全 请输入验证码</td>
        </tr>
        <tr>
          <td class="font1">手机尾号 <span id="tailNumber"></span>
            <input type="button" name="button2" value="发送验证码"  class="code" id="sendCode" onclick="time()"/>
            <input name="textfield2" type="text" class="input_s" id="checkCodeTime" style="display:none;" value="1425978974239" />
          </td>
        </tr>
        <tr>
          <td>
            <h1>验证码<span id="messageCodeErrorMsg" style="display: none;">注：您输入的验证码有误，请重新输入</span></h1>
            <p><input name="textfield" type="text" class="input_login" id="messageCode"/></p>
          </td>
        </tr>
        <tr>
          <td height="20" class="font">&nbsp;</td>
        </tr>
        <tr>
          <td align="center"><input type="button" name="button" id="button" value="确定"  class="input_button" onclick="login()"/></td>
        </tr>
      </table>
       </form>
    </div>
  </div>
</div>
<div class="footer">
  <div class="body">联系我们 400-208-1788</div>
</div>
<input id="path"  style="display:none;" value="<%=request.getContextPath()%>"/>
<script src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/public.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.autocomplete.js"></script>
<script>
var wait=60;
window.alert = function(msg) {
	layer.alert(msg);
}
//对登录表单添加回车事件 ，回车就登陆  添加onkeydown事件
$(".login").keydown(function(e) {
	var ke = e.keyCode;
		if (ke == 13) {
			login();
		};
});
//登录验证
function login() {
	$("#usernameworning").hide();
	$("#passwordworning").hide();
	var username = $("#username");
	var password = $("#password");
	var request={"userName":$.trim(username.val()),"password":$.trim(password.val())};
	$.ajax({
			type : "post",
			url : path.val() + "/login/login.do",
			async : false,//设置为同步请求
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(request),
			success : function(data) {
				if (data.result == true) {
					javascript: location.href = path.val() + "/page/main.do";
				}
				if (data.resultMsg == "false") {
					//清空密码
					password.val("");
					alert("用户名或密码错误");
				}
				if (data.resultMsg == "newAddress") {
					$("#login").hide();
					$("#tailNumber").text(
							$.trim(username.val()).substr(
									$.trim(username.val()).length - 4));
					$("#validate").show();
				}
				if (data.resultMsg == "errorCheckCode") {
					$("#messageCodeErrorMsg").show();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络异常，请稍后重试");
			}
		});
}
</script>

</body>
</html>
