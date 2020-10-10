<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<title>实时数据列表</title>
	<style>
		.data-operate { background: url(${ctx}/content/skin/css/images/botton-list-operate.png) center center no-repeat; }
		/* 设备配置项 */
		.operate-batch { position: relative; width: 154px; z-index: 200; font-size: 12px; font-weight: normal; display:none}
		.operate-batch span { float: left; display: block; width: 154px; height: 5px; background: url(${ctx}/content/skin/css/images/icon-arrow-white-up.png) no-repeat; }
		.operate-batch dl { float: left; margin: 0; background-color: #ededed; width: 152px; border-left: 1px solid #c5d9df; border-right: 1px solid #c5d9df; border-bottom: 1px solid #c5d9df; }
		.operate-batch dl dt { float: left; width: 124px; text-align: left; padding: 8px 0 8px 30px; border-bottom: 1px solid #e6e6e6; background: url(${ctx}/content/skin/css/images/icon-arrow-gray-left.jpg) left center no-repeat; }
		.operate-one { margin: 30px 0 0 -110px; }
		.operate-more { margin-left: -120px; }
		.active{top:78px;}
		.noactive{top:40px;} /*#e6eff5;*/
		#IndustInp tr td input{width: 100%;height: 100%;border: none;border-radius: 4px;padding-left: 5px;box-sizing: border-box;text-align:center;cursor: text!important;}
		#IndustInp tr:nth-child(odd) td input{background:#fff; }
		#IndustInp tr:nth-child(even) td input{background:#e6eff5; }
		#IndustInp tr td input:hover{cursor: text!important;}
	</style>
</head>

<body>
<div id="vitu">
	<div class="right_user"  v-if="vity.activeName=='first'" v-bind:class="[vity.isActive ? vity.active : vity.noactive]" style="left:0px;background: none;right: 0px;">
		<span class="siteNow" style="font-size:16px;">在线机井数量:&nbsp;${onlineCount}/${count}</span>
		<table cellspacing='0' cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th>机井编码</th>
					<th>机井名称</th>
					<th>采集时间</th>
					<th>最后通讯时间</th>
					<th>已用水量<br>(m³)</th>
					<th>瞬时流量<br>(m³/s)</th>
					<th>剩余水量<br>(m³)</th>
					<th>累计用水量<br>(m³)</th>
					<th>网络状态</th>
					<th>水泵状态</th>
					<th>A相电压<br>(V)</th>
					<th>B相电压<br>(V)</th>
					<th>C相电压<br>(V)</th>
					<th>A相电流<br>(A)</th>
					<th>B相电流<br>(A)</th>
					<th>C相电流<br>(A)</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty baseDeviceInfoList}">
					<tr>
						<td colspan="17">没有相关数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty baseDeviceInfoList}">
					<c:forEach var="item" items="${baseDeviceInfoList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td>${item.deviceCode}</td>
							<td>${item.deviceName}</td>
							<td>
								<c:if test="${item.deviceType==1}">
									<c:if test="${item.collectTimek==null}">--</c:if>
									<c:if test="${item.collectTimek!=null}">
										<fmt:formatDate value="${item.collectTimek}" pattern="yyyy-MM-dd HH:mm:ss" />
									</c:if>
								</c:if>
								<c:if test="${item.deviceType==2}">
									<c:if test="${item.collectTimeq==null}">--</c:if>
									<c:if test="${item.collectTimeq!=null}">
										<fmt:formatDate value="${item.collectTimeq}" pattern="yyyy-MM-dd HH:mm:ss" />
									</c:if>
								</c:if>
							</td>
							<td>
								<c:if test="${item.commTime==null}">--</c:if>
								<c:if test="${item.commTime!=null}">
									<fmt:formatDate value="${item.commTime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</c:if>
							</td>
							<td>
								<c:if test="${item.deviceType==1}">
									${empty item.usewater ? "--" : item.usewater}
								</c:if>
								<c:if test="${item.deviceType==2}">
									${empty item.useWt ? "--" : item.useWt}
								</c:if>
							</td>
							<td>
								<c:if test="${empty item.instantFlow}">
									--
								</c:if>
								<c:if test="${!empty item.instantFlow }">
									<fmt:formatNumber type="number" value="${item.instantFlow/1000}" pattern="0.00" maxFractionDigits="2"/>
								</c:if>
							</td>
							<td>
								<c:if test="${item.deviceType==1}">
									${empty item.remainWater ? "--" : item.remainWater}
								</c:if>
								<c:if test="${item.deviceType==2}">
									${empty item.leftWt ? "--" : item.leftWt}
								</c:if>
							</td>
							<td>
								<c:if test="${item.deviceType==1}">
									${empty item.totalFlow ? "0.00" : item.totalFlow}
								</c:if>
								<c:if test="${item.deviceType==2}">
									${empty item.curWtBase ? "0.00" : item.curWtBase}
								</c:if>
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
							<td>
								<fmt:formatNumber maxFractionDigits="0" value="${empty item.aPhaseVoltage ? '0.00' : item.aPhaseVoltage}"/>V
							</td>
							<td>
								<fmt:formatNumber maxFractionDigits="0" value="${empty item.bPhaseVoltage ? '0.00' : item.bPhaseVoltage}"/>V
							</td>
							<td>
								<fmt:formatNumber maxFractionDigits="0" value="${empty item.cPhaseVoltage ? '0.00' : item.cPhaseVoltage}"/>V
							</td>
							<td>
								<fmt:formatNumber maxFractionDigits="0" value="${empty item.aPhaseCurrent ? '0.00' : item.aPhaseCurrent}"/>A
							</td>
							<td>
								<fmt:formatNumber maxFractionDigits="0" value="${empty item.bPhaseCurrent ? '0.00' : item.bPhaseCurrent}"/>A
							</td>
							<td>
								<fmt:formatNumber maxFractionDigits="0" value="${empty item.cPhaseCurrent ? '0.00' : item.cPhaseCurrent}"/>A
							</td>
							<td>
							  <ul style="width:30px; padding-left:15px;">
	                             <li class="data-operate" title="操作">
	                             	<div class="operate-batch operate-one">
	                                      <dl>
	                                      	<c:if test="${item.deviceType==1}">
		                                      	<dt onclick="detailInfo('${item.id}','${item.deviceCode}')"><a>详细动态数据</a></dt>
		                                      	<dt onclick="historyData('${item.id}','0')"><a>时段报历史数据</a></dt>
		                                      	<dt onclick="historyData('${item.id}','1')"><a>开关泵历史数据</a></dt>
		                                      	<dt onclick="requestData('${item.deviceCode}','${item.id}','0')"><a>上下卡历史数据</a></dt>
		                                      	<dt onclick="requestData('${item.deviceCode}','${item.id}','1')"><a>遥测上下卡数据</a></dt>
		                                      	<dt onclick="unusualHstoryData('${item.deviceCode}','${item.id}','0')"><a>设备异常历史记录</a></dt>
		                                      	<dt onclick="unusualHstoryData('${item.deviceCode}','${item.id}','1')"><a>主控跳闸历史记录</a></dt>
		                                    </c:if>
		                                    <c:if test="${item.deviceType==2}">
		                                      	<dt onclick="historyData('${item.id}','0')"><a>历史数据</a></dt>
		                                    </c:if>
	                                      </dl>
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
		<input type="hidden" value="${pagingBean.pageNo}" id="pageNos" />
	</div>
	<!--================= 管道计量===================== -->
	<div class="right_user" v-if="vity.activeName=='second'" style="left:0px; top:108px;background: none;right: 0px;">
		<%--<span class="siteNow" style="font-size:16px;">在线机井数量:&nbsp;${onlineCount}/${count}</span>--%>
		<table cellspacing='0'  cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th>设备名称</th>
					<th>设备编码</th>
					<th>设备状态</th>
					<th>端口号</th>
					<th>瞬时流量</th>
					<th>瞬时流速</th>
					<th>流量百分比</th>
					<th>流体电导比</th>
					<th>正向累积值(m³)</th>
					<th>反向累积值(m³)</th>
					<th>上报时间</th>
					<th>历史记录</th>
				</tr>
			</thead>
			<tbody id="IndustInp">
				<c:if test="${empty IndustrialDeviceList}">
					<tr>
						<td colspan="12">没有相关数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty IndustrialDeviceList}">
					<c:forEach var="item" items="${IndustrialDeviceList}" varStatus="vs">
						<tr class="${vs.index%2==0?'singular':'double'}">
							<td>
								<input onchange="handleBlur('${vs.index}','${item.ids}');" id="IndusDeviceName${vs.index}"  value="${item.devicename==null?'--':item.devicename}"/>
							</td>
							<td>${item.devicecode==null?'--':item.devicecode}</td>
							<td>
								<c:choose>
									<c:when test="${item.backtwo==0||item.backtwo==null}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
									<c:when test="${item.backtwo==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
									<c:otherwise><img src='${ctx}/content/skin/css/images/icon/icon-upgrade.gif'></img></c:otherwise>
								</c:choose>
							</td>
							<td>${item.deviceport==null?'--':item.deviceport}</td>
							<td>${item.instantflow==null?'--':item.instantflow}</td>
							<td>${item.instantspeed==null?'--':item.instantspeed}</td>
							<td>${item.flowpercent==null?'--':item.flowpercent}</td>
							<td>${item.ratiopercent==null?'--':item.ratiopercent}</td>
							<td>${item.positivetotal==null?'--':item.positivetotal}</td>
							<td>${item.reversetotal==null?'--':item.reversetotal}</td>
							<td>
								<c:if test="${item.createtime!=null}">
									<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</c:if>
							</td>
							<td><el-button icon="el-icon-edit" type="text" onclick="IndustrialList(1,1,'${item.devicename}','${item.deviceport}')">历史数据</el-button></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="list-page">
			<div id="pagination"></div>
		</div>
		<input type="hidden" value="${pagingBean.pageNo}" id="pageNos" />
	</div>
	<script type="text/javascript">
	var vitu = new Vue({
		el:'#vitu',
		data:{
			
		},
		methods:{

		},
		created:function(){
			var_this = this;
		}
	});

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
				changeDataPage(n,0);
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
			} else {
			    $(this).children("div.operate-batch").hide();
			}
		});
		$(this).children("div.operate-batch").bind("mouseleave", function () {
		    $(this).hide();
		});
	});
	</script>
	</div>
</body>
</html>