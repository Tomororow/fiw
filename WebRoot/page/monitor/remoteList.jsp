<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>远程监控列表</title>
	<style>
		.data-operate {
			background: url(${ctx}/content/skin/css/images/botton-list-operate.png) center center no-repeat;
		}
		
		/* 设备配置项 */
		.operate-batch {
			position: relative;
			width: 154px;
			z-index: 200;
			font-size: 12px;
			font-weight: normal;
			display: none;
		}
		
		.operate-batch span {
			float: left;
			display: block;
			width: 154px;
			height: 5px;
			background: url(${ctx}/content/skin/css/images/icon-arrow-white-up.png) no-repeat;
		}
		
		.operate-batch dl {
			float: left;
			margin: 0;
			background-color: #ededed;
			width: 152px;
			border-left: 1px solid #c5d9df;
			border-right: 1px solid #c5d9df;
			border-bottom: 1px solid #c5d9df;
		}
		
		.operate-batch dl dt {
			float: left;
			width: 124px;
			text-align: left;
			padding: 8px 0 8px 30px;
			border-bottom: 1px solid #e6e6e6;
			background: url(${ctx}/content/skin/css/images/icon-arrow-gray-left.jpg) left center no-repeat;
		}
		
		.operate-one {
			margin: 30px 0 0 -110px;
		}
		
		.operate-more {
			margin-left: -120px;
		}
		.operate-batch .littleModel {width: 100px;height: 50px;position: relative;right: 110px;top: 80px;display:none;}
		.operate-batch .littleModel dl dt{width:95px}
		.operate-batch dl dt:hover{background: #cacaca;}
	</style>
</head>

<body>
	<div class="device">
		<span class="siteNow" style="font-size:16px;">符合条件机井数量:&nbsp;${count}</span>
		<table cellspacing='0' cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th>机井编码</th>
					<th>机井名称</th>
					<th>业主名称</th>
					<th>安装地点</th>
					<th>设备版本</th>
					<th>网络状态</th>
					<th>水泵状态</th>
					<th>所属行政区</th>
					<th>所属水管区</th>
					<th width="30">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty baseDeviceInfoList}">
					<tr>
						<td colspan="10">没有相关数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty baseDeviceInfoList}">
					<c:forEach var="item" items="${baseDeviceInfoList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td>
								${empty item.deviceCode ? "--" : item.deviceCode}
							</td>
							<td>
								${empty item.deviceName ? "--" : item.deviceName}
							</td>
							<td>
								${empty item.ownerName ? "--" : item.ownerName}
							</td>
							<td>
								${empty item.installAddress ? "--" : item.installAddress}
							</td>
							<td>
								${empty item.deviceVersion ? "--" : item.deviceVersion}
							</td>
							<td>
								<c:if test="${item.deviceType==1}">
									<c:choose>
										<c:when test="${item.netStatek==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
										<c:when test="${item.netStatek==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
										<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:otherwise>
								   	</c:choose>
								</c:if>
							   	<c:if test="${item.deviceType==2}">
							   		<c:choose>
										<c:when test="${item.netStateq==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
										<c:when test="${item.netStateq==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
										<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:otherwise>
								   	</c:choose>
							   	</c:if>
							</td>
							<td>
							   <c:if test="${item.deviceType==1}">
									<c:choose>
										<c:when test="${item.pumpState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>关泵</c:when>
										<c:when test="${item.pumpState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>开泵</c:when>
										<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img>无数据</c:otherwise>
								   	</c:choose>
								</c:if>
							   	<c:if test="${item.deviceType==2}">
									<c:choose>
										<c:when test="${item.openState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>关泵</c:when>
										<c:when test="${item.openState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>开泵</c:when>
										<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img>无数据</c:otherwise>
								   	</c:choose>
								</c:if>
							</td>
							<td>${item.areaName}</td>
							<td>${item.waterAreaName}</td>
							<td>
								<ul style="width:60px; padding-left:15px;">
									<li class="data-operate" title="操作">
										<div class="operate-batch operate-one">
											<span></span>
											<dl style="width:160px;position: relative;">
												<dt onclick="setRemoteInfo('${item.deviceCode}', '${item.deviceName}')">控制器远程设置</dt>
												<dt onclick="remoteOpenUpdate('${item.deviceCode}','${item.deviceName}', 57)">远程升级</dt>
												<dt onclick="remoteOpenAndPump('${item.deviceCode}', 1)">远程重启</dt>
												<dt class="zeroHandle" >远程清零</dt>
												<dt onclick="remoteOpenAndPump('${item.deviceCode}', 4)">锁定用户卡</dt>
												<dt onclick="remoteOpenAndPump('${item.deviceCode}', 5)">解锁用户卡</dt>
												
												<%-- <dt onclick="remoteOpenAndPump('${item.deviceCode}', 6)">开启电表通讯异常跳闸</dt>
												<dt onclick="remoteOpenAndPump('${item.deviceCode}', 7)">关闭电表通讯异常跳闸</dt>
												<dt onclick="remoteOpenAndPump('${item.deviceCode}', 8)">开启水表通讯异常跳闸</dt>
												<dt onclick="remoteOpenAndPump('${item.deviceCode}', 9)">关闭水表通讯异常跳闸</dt> --%>
												
												<!-- 民乐工业园区  -->
												<%-- <dt onclick="remoteOpenAndPump('${item.deviceCode}', 10)">远程开启阀门</dt>
												<dt onclick="remoteOpenAndPump('${item.deviceCode}', 11)">远程关闭阀门</dt> --%>
											</dl>
											<div class="littleModel" >
												<dl style="position: absolute;width:124px;border:solid 1px #d0d7d8;">
													<dt onclick="remoteOpenAndPump('${item.deviceCode}', 2)">控制器清零</dt>
													<dt onclick="remoteOpenAndPump('${item.deviceCode}', 3)">水表表头清零</dt>
												</dl>
											</div>
										</div>
									</li>
								</ul>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="list-page">
			<div id="pagination"></div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
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
					changeRemotePage(n);
					return false;
				}
			});
		});
		
		$(".list-table tbody tr td ul li.data-operate").each(function () {
			$(this).click(function () {
	        	if ($(this).children("div.operate-batch").is(":hidden")) {
	        		// 先把同胞隐藏在显示
	                $(".list-table tbody tr td ul li.data-operate").children("div.operate-batch").hide();
	                $(this).children("div.operate-batch").show();
	                $(this).children("div.operate-batch").children("dl").children("dt.zeroHandle").bind("mouseover", function () {
	                	$(this).parent().parent().children("div.littleModel").css("display","block");
	                });
	            } else {
	            	$(this).children("div.operate-batch").hide();
	            }
			});
		    $(this).children("div.operate-batch").bind("mouseleave", function () {
		    	$(this).hide();
		    	$(this).children("div.littleModel").css("display","none");
		    });
		});
	</script>
</body>
</html>