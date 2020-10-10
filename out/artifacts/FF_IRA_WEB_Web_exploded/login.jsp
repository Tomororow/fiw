﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <meta charset="utf-8">
      <meta name="renderer" content="webkit"/>
      <meta name="force-rendering" content="webkit"/>
      <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <title> 机井灌溉云平台登录</title>
    <link href="${ctx}/content/skin/css/core/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery.enplaceholder.js"></script>
    <style>
       body { margin: 0; padding: 0; overflow: auto; font-size: 12px; color: #383939; font-family:微软雅黑; background:url(${ctx}/content/skin/css/images/login/bj.jpg) no-repeat bottom center #9fcbc4; -webkit-text-size-adjust: none; height: 100%; overflow: hidden; }
      .msg_error{width:354px; height:25px; float:left;padding-left:38px;}
    </style>
  </head>
  
  <body>
	<div class="login">
		<div class="login_title"><img src="${ctx}/content/skin/css/images/login/logo.png" width="85" height="52" />机井灌溉云平台</div>
			<div class="login_content">
				<div class="msg_error"><label id="pwderror" style="display:none;color:red;float:left;"></label></div>
				<ul>
				<li><input id="username" name="username" type="text" placeholder="请输入用户名" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" class="user" border="0" /></li>
				<li><input id="password" name="password" type="text"  placeholder="请输入密码" onfocus="if(this.value==defaultValue) {this.value='';this.type='password';style.color='#000'}" onblur="if(!value) {value=defaultValue; this.type='text';}" class="password" />
				</li>
				<li><input id="login_button" name="" type="button" class="login_button" value="登 录" onclick="login()"/></li>
				</ul>
			</div>
		</div>
	<div class="bottom">张掖金志信息技术有限公司（&copy;版权所有)&nbsp;&nbsp;&nbsp;&nbsp;建议浏览器使用IE9+、Google Chrome、FireFox，获得更好用户体验</div>
  </body>
  <script type="text/javascript">
	$(function() {  
  		//回车登录
        $("#username").keyup(function(event){
          if(event.keyCode == 13){  
            login();  
          }
        });
        $("#password").keyup(function(event){
          if(event.keyCode == 13){  
            login();  
          }
        });
      //通过插入元素模拟placeholder
      $('#username, #password').placeholder({isUseSpan:true});
      $('#username').focus();
    }); 
  	function login() {
		$("#login_button").removeAttr("onclick");
		$("#login_button").val("正 在 登 录...");
		document.getElementById( "pwderror" ).style.display = "none";
		var turl;
		var tdata;
		var username = $("#username").val();
		if(username=='请输入用户'){
			username = "";
		}
		var password = $("#password").val();
		if(password=='长度为6~16位字符'){
			password = "";
		}
		if(username=="" || password==""){
			$("#pwderror").html("用户名和密码不能为空！");
			document.getElementById( "pwderror" ).style.display = "inline";
			$("#login_button").attr("onclick","login()");
			$("#login_button").val("立即登录");
			return;
		}
		turl = '<%=basePath%>/userLogin.do';
		
		tdata = {
			userName : username,
			passWord : password
		};
		$.ajax({
			url : turl,
			type : 'POST',
			data : tdata,
			dataType : 'json',
			cache : false,
			success : function(data) {
				debugger
				if (!data.success) {
					$("#pwderror").html(data.msg);
					document.getElementById( "pwderror" ).style.display = "inline";
					$("#login_button").attr("onclick","login()");
					$("#login_button").val("立即登录");
					return;
				} else {
					if(data.isSimplePwd){
						$("#pwderror").html("该密码过于简单,强烈建议修改密码!");
						document.getElementById( "pwderror" ).style.display = "inline";
						setTimeout("reload()",1000);
					}else{
						reload();
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				$("#login_button").attr("onclick","login()");
				$("#login_button").val("立即登录");
			}
		});
	}
    function reload(){
		window.location.href = 'index.do';
	}
  </script>
</html>
