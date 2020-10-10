<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统简介</title>
</head>
<style>
	#but{
		background-color: #00BCD4;
	    color: white;
	    font-size: 13px;
	    border-radius: 1px;
	    padding: 5px 20px;
	    text-align: center;
	    margin-left: 62%;
	    box-shadow: 0 0 0 1px #fdda55,0 0 0 2px #00BCD4;
	}
</style>
<body>
	<form name="example" method="post" action="introduction.jsp" style="margin-bottom: 20px;">
	<!--文章编辑器使用插件KindEditor,使用kindeditor.js,zh_CN.js，及必须在前js同目录下themes/default的文件defualt.css及defualt.png-->
		<textarea id="editor_id" name="content" style="width:700px;height:200px;visibility:hidden;">${sysInfo.infoValue}</textarea>
		
	</form>
	<span id="but" name="getHtml" onclick="saveHtml()">提交</span>
	<script type="text/javascript">
		//文章编辑器使用插件KindEditor
		kedit();
	</script>
</body>
</html>