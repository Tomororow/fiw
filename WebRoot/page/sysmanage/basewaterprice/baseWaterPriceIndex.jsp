<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<title>参数信息管理</title>
	<link href="${ctx}/content/skin/adapters/bootstrap/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
	<link href="${ctx}/content/skin/adapters/bootstrap/css/bootstrap/font-awesome.min.css" rel="stylesheet" />
	<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/adapters/bootstrap/bootstrap.1.8.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/baseWaterPrice.js"></script>
</head>

<style type="text/css">
	body {
		color: #000;
		font-size: 12px;
		font-family: "Helvetica Neue", Helvetica, STheiti, 微软雅黑, 宋体, Arial, Tahoma, sans-serif, serif;
	}
	
	/*左侧菜单*/
	.sidebar-menu {
		border-right: 1px solid #c4c8cb;
	}
	
	/*一级菜单*/
	.menu-first {
		height: 45px;
		line-height: 45px;
		background-color: #029F89;
		border-top: 1px solid #efefef;
		border-bottom: 1px solid #e1e1e1;
		padding: 0;
		font-size: 14px;
		font-weight: normal;
		text-align: center;
		color: black;
	}
	
	/*一级菜单鼠标划过状态*/
	.menu-first:hover {
		text-decoration: none;
		background-color: #F2F2F2;
		border-top: 1px solid #b7b7b7;
		border-bottom: 1px solid #acacac;
	}
	
	/*二级菜单*/
	.menu-second li a {
		background-color: #AAD8CC;
		height: 31px;
		line-height: 31px;
		border-top: 1px solid #efefef;
		border-bottom: 1px solid #efefef;
		font-size: 12px;
		text-align: center;
	}
	
	/*二级菜单鼠标划过样式*/
	.menu-second li a:hover {
		text-decoration: none;
		background-color: #66c3ec;
		border-top: 1px solid #83ceed;
		border-bottom: 1px solid #83ceed;
		border-right: 3px solid #f8881c;
		border-left: 3px solid #66c3ec;
	}
	
	/*二级菜单选中状态*/
	.menu-second-selected {
		background-color: #66c3ec;
		height: 31px;
		line-height: 31px;
		border-top: 1px solid #83ceed;
		border-bottom: 1px solid #83ceed;
		border-right: 3px solid #f8881c;
		border-left: 3px solid #66c3ec;
		text-align: center;
	}
	
	/*覆盖bootstrap的样式*/
	.nav-list,.nav-list li a {
		padding: 0px;
		margin: 0px;
		color: black;
		cursor: pointer; 
	}
</style>
<body>
	<div class="left">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span2">
						<div class="sidebar-menu" style="width:237px;">
							<a href="#userMeun" class="nav-header menu-first collapse" data-toggle="collapse">参数信息管理</a>
							<ul id="userMeun" class="nav nav-list menu-second in collapse">
								<li><a onclick="loadInfo(this)"
									data-src="/parameterManage/baseWaterPrice.do">基本水费费率</a></li>
								<li><a onclick="loadInfo(this)"
									data-src="/parameterManage/measureTypePrice.do">计量水费费率</a></li>
								<li><a onclick="loadInfo(this)"
									data-src="/parameterManage/maintainPrice.do">末级渠系(维修养护)费率</a></li>
								<li><a onclick="loadInfo(this)"
									data-src="/parameterManage/waterSourcePrice.do">水资源费费率</a></li>
								<li><a onclick="loadInfo(this)"
									data-src="/parameterManage/threeVoltage.do">三相电压预警参数</a></li>
								<li><a onclick="loadInfo(this)"
									data-src="/parameterManage/powerInfo.do">功率参数设置</a></li>
								<li><a onclick="loadInfo(this)"
									data-src="/parameterManage/pumpingWell.do">灌溉期预警参数设置</a></li>
								<li><a onclick="loadInfo(this)"
									data-src="/parameterManage/warnErrorPage.do?sign=0">设备异常类型设置</a></li>
								<c:if test="${login_user.roleName=='系统管理员'}">
									<li ><a onclick="loadInfo(this)"
											data-src="/parameterManage/waterIndicator.do">用水指标设置</a></li>
								</c:if>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="leftnav">
		<a href="javascript:void(0)">
			<img src="${ctx}/content/skin/css/images/botton-closeLeft.gif" />
		</a>
	</div>
	<div class="right_user"></div>
	<center>
		<div class="right_user" id="baseWaterPriceContent" style="overflow-y:hidden;"></div>
	</center>
</body>
<script type="text/javascript">
	$(function() {
		$(".leftnav").click(function() {
			if ($(".left").css("display") == "none") {
				$(".leftnav").css("left", "237px");
				$(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeLeft.gif");
				$(".right_user").css("left", "275px");
			} else {
				$(".leftnav").css("left", "0px");
				$(".leftnav img").attr("src", "${ctx}/content/skin/css/images/botton-closeRight.gif");
				$(".right_user").css("left", "20px");
			}
			$(".left").toggle();
			$(".left-bottom").toggle();
		});
		
		$('#organTree').find("li").each(function() {
			$("#organTree li:first").click().addClass("clickColor");
		});
		
		$('#organTree').find("li").click(function() {
			var m = $(this), s = m.siblings();
			m.addClass("clickColor");
			s.removeClass("clickColor");
		});

		$("#userMeun li a").click(function() {
			var m = $(this), s = m.siblings();
			m.addClass("sell");
			s.removeClass("sell");
		});
		
		//默认载入第一个菜单
		if (${fn:length(menuList)>1 }) {
			$("#userMeun li a").eq(1).click();
		} else {
			$("#userMeun li a:first").click();
		}
	});
</script>
</html>