<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp" %>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="x-ua-compatible" content="ie=9"/>
	<title>机井灌溉云平台授权</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/content/skin/css/core/core.css"/>
    <script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery-1.8.0.min.js"></script>
    <%-- <script type="text/javascript" src="${ctx}/content/skin/js/userAccredit.js"></script> --%>
	<style>
    	/* 授权页面样式 */
	    body {overflow:auto; text-align:center; color:#383939; font-family:Microsoft YaHei; background:url(content/skin/css/images/login/bj.jpg) no-repeat bottom center #9fcbc4; -webkit-text-size-adjust:none; height:100%; overflow:hidden; }
	    .msg_info{ width:280px; height:23px; margin-left:33%; line-height:23px; }
    	.msgInfo{ margin-left:-170px; }
    	.accredit{ width:600px; height:auto; position:absolute; left:31%; top:15%;}
	    .accredit_title{ width:600px; height:83px; float:left; background:url(content/skin/css/images/login/accredit_title.png) no-repeat; line-height:83px; color:#fff; font-size:22px;}
    	.accredit_content{ width:601px; height:400px; float:left; background:url(content/skin/css/images/login/accredit_bottom.png) no-repeat; }
    	.accredit_content ul li{ width:602px; height:52px; float:left; font-size:15px; font-weight:bolder;}
    	.code_button{ width:145px; height:37px; border-radius:6px; outline:none; background:url(content/skin/css/images/login/code_button.png) no-repeat; color:#fff; cursor:pointer; font-size:16px;}
		.accredit_button{ width:390px; height:38px; border-radius:6px; position:absolute; left:18%; bottom:8%; outline:none; background:url(content/skin/css/images/login/accredit_button.png) no-repeat bottom; color:#fff; cursor:pointer; font-size:20px;}
    	
    	.input_text{ width:300px; height:38px; border:1px solid #dbdbdb; border-radius:6px; padding-left:10px; margin-left:12px; }
    	.code_text{ width:150px; height:38px; border:1px solid #dbdbdb; border-radius:6px; padding-left:10px; margin-left:12px; }
    	.accredit_textarea{ width:300px; height:70px; border:1px solid #dbdbdb; border-radius:6px; padding-left:10px; margin-left:12px; }
    </style>
    <script type="text/javascript">
	    /**
	     * dom文档载入加载
	     */
	    $(function(){
	    	$("#Port").val("");
	    	$("#Phone").val("");
	    	$("#Code").val("");
	    });
	
	    /**
	     * 授权电话号码 格式校验
	     * @returns {Boolean}
	     */
	    function validatePhone(){
	        var Phone = document.getElementById('Phone').value;
	        var flag = true;
	        if(!(/^1[34578]\d{9}$/.test(Phone))){
	           	$("#Phone").next('span').remove();
	    		$("#Phone").after("<span id='msgInfo'></span>");
	        	$("#msgInfo").html("<font style='color:red;'>手机号码格式有误</font>");
	            flag = false;
	        }else{
	        	$("#Phone").next('span').remove();
	    		$("#Phone").after("<span id='msgInfo'></span>");
	        	$("#msgInfo").html("<font style='color:green;'>手机号码可以使用</font>");
	        }
	        return flag;
	    }
	
	    /**
	     * 获取验证码ajax调用
	     */
	    function getValidateCode(){
	    	// 获取申请授权电话
	    	var Phone = $("#Phone").val();
	    	var curl;
	    	alert(validatePhone());
	    	
	    	curl = '<%=basePath%>sysUser/getValidateCode.do';
	    	alert("请求地址：" + curl);
	    	if('' == Phone || null == Phone || undefined == Phone){
	    		$("#Phone").next('span').remove();
	    		$("#Phone").after("<span id='msgInfo'></span>");
	        	$("#msgInfo").html("<font style='color:red;'>请输入手机号码</font>");
	            return false;
	    	}else if(validatePhone()){
	    		alert("进入");
	    		$.ajax({
	    			url: curl,
	    			data: {phone:Phone},
	//    			type: "GET",
	    			success: function(data){
	    				alert(data);
	    			}
	    		});
	    	}
	    };    
    </script>
</head>
<body>
	<div class="accredit">
		<div class="accredit_title"><img src="${ctx}/content/skin/css/images/login/logo.png" width="80" height="47" />机井灌溉云平台授权</div>
			<div class="accredit_content" >
				<div class="msg_info">
					<span id="msgInfo" class="msgInfo"></span>
				</div>
				<ul>
					<li><label>外网IP地址:</label><input id="IP" name="IP" type="text" readonly="readonly" class="input_text" value="${internetIP}"/></li>
					<li><label>映射IP端口:</label><input id="Port" name="Port" type="text" class="input_text" maxlength="5" /></li>
					<li><label>授权人电话:</label><input id="Phone" name="Phone" type="text" class="input_text" onblur="validatePhone()"/></li>
					<li>
						<label>输入验证码:</label><input id="Code" name="Code" type="text" class="code_text" maxlength="4"/>
						<input id="code_button" type="button" class="code_button" value="获取验证码" onclick="getValidateCode()"/>
					</li>
					<li>
						<label>&nbsp;授权备注&nbsp;:</label>
						<textarea class="accredit_textarea" id="AccreditRemark" name="AccreditRemark" ></textarea>
					</li>
					<li><input id="accredit_button" type="button" class="accredit_button" value="申&nbsp;请&nbsp;授&nbsp;权" /></li>
				</ul>
			</div>
		</div>
	<div class="bottom">张掖金志信息技术有限公司（&copy;版权所有)&nbsp;&nbsp;&nbsp;&nbsp;建议浏览器使用IE9+、Google Chrome、FireFox，获得更好用户体验</div>
</body>
</html>