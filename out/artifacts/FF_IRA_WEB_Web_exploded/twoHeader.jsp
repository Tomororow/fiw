<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 二级菜单 渲染遍历 -->
<div class="top">
	<div class="top_nav">
		<div style="width:100%; float:left; padding-left:330px; padding-top:4px;">
			<ul id="two_nav">
			 <c:forEach items="${sysMenuList}" var="item" varStatus="vs">
				<li onclick="showContent('${item.menuUrl}','twoContain')"> 
					<span style="width: 120px">
						${item.menuName}
					</span>
				</li>
				<li>
					<c:if test="${!vs.last}">
						|
					</c:if>
				</li>
			 </c:forEach>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function () {
	    $("#two_nav li").click(function () {
	        var m = $(this), s = m.siblings();
	        m.addClass("sell");
	        s.removeClass("sell");
	    }); 
	});
</script>