<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>权限树</title>
<meta http-equiv="pragma" content="no-cache">
<style type="text/css">
	.treePermissionInfo {
		margin: 20px 0 20px 50px;
	}
	.treePermissionInfo .tab-content{
		color: #ffffff;
	}
	.tab-title{
		margin-left: -255px;
		margin-bottom: 10px;
	}
	.tab-content .tab-pane{
		max-height:420px;
		overflow:auto;
	}
	div.treePermissionInfo .nav-tabs a {color:#428bca}
	
</style>
	<link rel="stylesheet" href="${ctx}/content/skin/adapters/bootstrap/css/bootstrap/bootstrap-navTabs.css">
	<script type="text/javascript" src="${ctx}/content/skin/adapters/bootstrap/bootstrap.min.js"></script>
</head>

<body>
    <input type="hidden" id="roleId" name="roleId" value="${roleId}"></input>
	<div>
		<div class="treePermissionInfo">
			<input class="tab-title" type="checkbox" id="selectMenuAll" onclick="selectMenuAll()" >菜单配置
	    	<div class="tab-content">
	    		<div id="menuTreeSet" class="tab-pane active">
	    			<ul id="menuInfoTree" class="ztree"></ul>
	    		</div>
	    	</div>
		</div>
	    <div>
	    </div>
	</div>
	<script type="text/javascript">
		   $(function(){
			   $('#treeTab a').click(function(e) {
				    if($(e.target).attr('id')==undefined)
			        	e.preventDefault();//阻止a链接的跳转行为
			        $(this).tab('show');//显示当前选中的链接及关联的content
			        //initPage();
			    });
			   //菜单
			   menuTreeInit('${roleId}');
		   });
	</script>
</body>
</html>