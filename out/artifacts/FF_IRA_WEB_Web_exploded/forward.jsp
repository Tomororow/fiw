<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>机井灌溉云平台大数据展示</title>
		<meta http-equiv="pragma" content="no-cache">
	</head>
	<body>
	</body>
		<%-- <jsp:forward page="index.do"></jsp:forward> --%>
		<%-- <jsp:forward page="bigDataPage.do"></jsp:forward> --%>
		<script type="text/javascript">
			window.onload=function(){
		        // 初始化内容 
		        var count = localStorage.getItem("webCount");
		        if(count==null){
		        	window.location.href="bigDataPage.do";
		        	localStorage.setItem("webCount","ready");
		        }else{
		        	window.location.href="index.do";
		        }
		    };
		</script>
</html>
