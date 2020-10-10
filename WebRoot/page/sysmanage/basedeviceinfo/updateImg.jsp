<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<title>设备列表</title>
<style>
	#updateImg{width:500px;margin: 0 auto;}
	#photoBefore{border: solid 1px #ccc;width:50%;height:220px;float: left;}
	#photoAfter{border: solid 1px #ccc;width:49%;height:220px;float: left;}
	#photoBefore img,#photoAfter img{width:100%;height: 85%;}
	#photoBefore span,#photoAfter span{position: relative;top:8px;font-size: 14px;}
	#photoBefore input,#photoAfter input{position: relative;top:8px;margin-left: 15px;}
</style>
</head>
<body onLoad="javascript:document.MyForm.reset()">
	<form id="MyForm" action="">
		<div id="updateImg" align="center">
			<div id="photoBefore">
				<img id="BeforeImg" />
				<span>安装前照片</span>
				<input type="button" onclick="photoClick(1);" value="重新上传"/>
				<input type="file" onchange="handlechange(this)" id="photoBefores" style="display: none"/>
			</div>
			<div id="photoAfter">
				<img id="AfterImg" src='/pir/${baseInfo.photoAfter}'/>
				<span>安装后照片</span>
				<input type="button" onclick="photoClick(2);" value="重新上传"/>
				<input type="file" onchange="handlechange(this)" id="photoAfters" style="display: none"/>
			</div>
			<div></div>
			<div style="clear: both;"></div>
		</div>
	</form>
</body>
<script type="text/javascript">
	var sign;
	function photoClick(type){
		if(type==1){//安装前的照片
			sign = 'B';
			document.getElementById("photoBefores").click();
		}else{//安装后的照片
			sign = 'A';
			document.getElementById("photoAfters").click();
		}
	}
	var formData = new FormData();
	function handlechange(event){
		debugger
		var fileType = event.files[0].type;
		if(fileType=="image/jpeg"||fileType=="image/png"){
			 formData.append('uploadFile', event.files[0]);
			 formData.append('deviceAreaCode',${baseInfo.deviceAreaCode});
			 formData.append('sign',sign);
			 formData.append('id','${baseInfo.id}');
			 ajaxFile(formData);
		}else{
			alert("请选择正确格式的图片文件！");
		} 
	}
	function ajaxFile(formData){
		$.ajax({
            url:"/baseDeviceInfo/updateImgInfo.do?"+ new Date().getTime(),
            data:formData,
            type: 'POST',
            dataType:'json',
            processData:false,
            contentType:false,
            cache:false,
            beforeSend :function(xmlHttp){
                xmlHttp.setRequestHeader("If-Modified-Since","0");
                xmlHttp.setRequestHeader("Cache-Control","no-cache");
             },
            success:function(data){
            	debugger
            	var r=confirm(data+",只有刷新页面才会使图片生效，是否刷新？")
            	if(r){
            		window.self.location.reload();
            	}
            }
        });
	}
	$(function(){//src='/pir/${baseInfo.photoBefore}'
		$("#BeforeImg").attr('src','/pir/${baseInfo.photoBefore}');
		$("#AfterImg").attr('src','/pir/${baseInfo.photoAfter}');
	});
</script>
</html>


