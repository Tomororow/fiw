<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>远程监控列表</title>
	<style type="text/css">
		#addUpdate div{display: flex;margin: 20px;}
		#addUpdate div span{font-size: 14px;}
		#addUpdate div input{color: #000;height: 18px;}
		#updateFile{display:inline-block; width:250px;height: 25px;margin-left: 15px;}
	</style>
</head>
<body>
	<div id="addUpdate">
		<div>
			<span style="margin-left:10px;"><span style="color:red;">*</span>选择升级文件：</span>
		    <select class="pop-select" id="updateFile" name="updateFile">
	   			<option value="${updateFile!=''?updateFile:''}">${updateFile!=''?updateFile:'--请选择--'}</option>
	   			<c:forEach var="item" items="${updateFileList}">
	   				<option value="${item}">${item}</option>
	   			</c:forEach>
	   		</select>
   		</div>
   		<div>
   			<span style="margin-left:10px;"><span style="color:red;">*</span>升级服务器地址：</span>
   	    	<input type="text" id="updateUrl" value="${updateUrl!=''?updateUrl:''}"  placeHolder="${updateUrl!=''?updateUrl:'请输入服务器地址'}"/>
   	   	 	<span style="margin-left:10px;"><span style="color:red;">*</span>端口号：</span>
   	    	<input type="text" id="updatePort" value="${updatePort!=''?updatePort:''}" placeHolder="${updatePort!=''?updatePort:'请输入端口号'}"/>
   	    </div>
   	    <div>
   	    	<span style="margin-left:10px;">&nbsp;&nbsp;Web Server-Ip：</span>
   	    	<input type="text" id="webServerIp" disabled value="${webServerIp!=''?webServerIp:''}" placeHolder="${webServerIp!=''?webServerIp:'请输入Web Server的ip'}"/>
   	    	<span style="margin-left:10px;">&nbsp;&nbsp;端口号：</span>
   	    	<input type="text" id="webServerPort" disabled value="${webServerPort!=''?webServerPort:''}" placeHolder="${webServerPort!=''?webServerPort:'请输入Web Server的端口号'}"/>
		</div>
	</div>
</body>
</html>
	