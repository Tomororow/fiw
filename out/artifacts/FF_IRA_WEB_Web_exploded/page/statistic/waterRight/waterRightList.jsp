<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<div class="right_user" style="left:15px;top:50px">
	<center>
		<div>
			<img alt="" src="${ctx}/content/skin/css/images/no_authority.png">
			<h1>抱歉，暂未开放权限，请联系管理员</h1>
		</div>
	</center>
</div>
<script type="text/javascript">
	$(function() {
		var totalPage = ${pagingBean.pageNum};
		var totalRecords = ${pagingBean.totalItems};
		var pageNo = ${pagingBean.pageNo};
		if(!pageNo){
			pageNo = 1;
		}
		//生成分页
		pagination.generPageHtml({
			//填充的id
			pagerid : "pagination",
			//当前页
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			mode : 'click',
			click : function(n){
				//分页执行方法
				changeWaterRightPage(n);
				//this.selectPage(n);
				return false;
			}
		});
	});
</script>

