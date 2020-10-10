<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>异常参数设置</title>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/paramPumpingWell.js"></script>
</head>
<body>
	<div id="paramPumpingWellDiv">
		<div class="operate">
			<ul >
				<li class="add" onclick="addPumpingWellInfo()">新增</li>
				<li class="del" onclick="delPumpingWell()">删除</li>
				<li style="margin-right: 0;padding-left: 0;">
					<div style="height:35px;margin-top: -9px;">
						<span style="margin-left:10px;">所属水管区域：</span>
				   		<select class="pop-select" id="waterAreaId" name="waterAreaId" style="height:25px; width:120px;margin-top: 5px;padding: 0;">
				   			<option value="">--水管区域--</option>
				   			<c:forEach var="item" items="${sysWaterAreaList}">
				   				<option value="${item.id}">${item.waterAreaName}</option>
				   			</c:forEach>
				   		</select>
					</div>
				</li>
				<li style="margin-right: 0;margin-left:12px;padding-left: 0;">
					<div style="height:35px;margin-top: -9px;">
						<span>机井名称：</span><input type="text" id="deviceName" placeholder="请输入机井名称"  style="height:15px; width:120px;margin-top: 5px;" />
					</div>
				</li>
				<li style="margin-right: 0;margin-left:12px;padding-left: 0;">
					<div style="height:35px;margin-top: -9px;">
						<span>机井编码：</span><input type="text" id="deviceCode" placeholder="请输入机井编码"  style="height:15px; width:120px;margin-top: 5px;" />
					</div>
				</li>
				<li style="margin-right: 0;padding-left: 0;">	
					<div style="height:35px;margin-top: -9px;">
						<span style="margin-left:10px;">用水类型：</span>
			 	        <select class="pop-selectt" id="deviceWellUse" name="deviceWellUse" style="height:25px; width:120px;margin-top: 5px;padding: 0;">
				   			<option value="">--请选择--</option>
				   			<c:forEach var="item" items="${wellList}">
				   				<option value="${item.wellUse}">${item.wellUse}</option>
				   			</c:forEach>
				   		</select>
					</div>
				</li>
				<li style="margin-right: 0;padding-left: 0;">
					<div style="height:35px;margin-top: -9px;">
						<span style="margin-left:10px;">起始时间：</span> 
						<input  style="height:15px; width:137px; font-size:12px; margin-top: 5px;" id="query_beginTime" class="Wdate" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"
							onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'query_endTime\') ||\'%y-%M-%d %H:%m:%s\' }'})" />
					</div>
				</li>
				<li style="margin-right: 0;padding-left: 0;">
					<div style="height:35px;margin-top: -9px;">
						<span style="margin-left:10px;">&nbsp;结束时间：</span> 
						<input style="height:15px; width:137px;font-size:12px;margin-top: 5px;" id="query_endTime" class="Wdate" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd HH:mm:ss" />"  type="text"
						onfocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					</div>
				</li>
				<li style="margin-right: 0;padding-left: 0;">
					<div style="height:35px;margin-top: -9px;">
						<input type="button"  style=" width:62px;margin-top: 5px;" class="btn-search" value="查询" onclick="queryInfoparam()">
					</div>
					
				</li>
			</ul>
		</div>
		<div class="device">
			<table cellspacing="1" cellpadding="3" class='list-table'>
				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"/></th>
						<th>机井名称</th>
						<th>机井编码</th>
						<th>用水类型</th>
	              		<th>所属水管区域</th>
						<th>灌溉期起始时间</th>
						<th>灌溉期结束时间</th>
						<!-- <th>剩余水量最低值(m³)</th> -->
						<th>充值后水量无变化周期设置</th>
						<th>机井不在线天数(天)</th>
						<!-- <th>用水量最低值(m³)</th> -->
						<th>高峰期用数量无变化周期设置</th>
						<th width="80">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty paramPowerList}">
						<tr>
							<td colspan="11">没有相关数据</td>
						</tr>
					</c:if>
					<c:if test="${!empty paramPowerList}">
						<c:forEach var="item"  items="${paramPowerList}" varStatus="vs">
					 		<tr class="${vs.index%2==0?'singular':'double'}">
								<td><input type="checkbox" id="threeVoltageId" value="${item.id}" /></td>
								<td>${item.deviceName}</td>
								<td>${item.devicecode}</td>
								<td>${item.deviceWellUse}</td>
								<td>${item.waterAreaName}</td>
								<td>
									<fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<%-- <td style="color: #f5b015;font-weight: 600;font-size: 14px;">${item.backone}</td> --%>
								<td style="color: #f5b015;font-weight: 600;font-size: 14px;">${item.backone==0?'未设置':item.backone}</td>
								<td style="color: #f5b015;font-weight: 600;font-size: 14px;">${item.onnetstate}</td>
								<%-- <td style="color: #f5b015;font-weight: 600;font-size: 14px;">${item.waterlow}</td> --%>
								<td style="color: #f5b015;font-weight: 600;font-size: 14px;">${item.waterlow==0?'未设置':item.waterlow}</td>
								<td width="80">
			                 		<ul style="width:80px;">
			                 			<li class="edit" title="修改" onclick="editPumpingWellById('${item.id}')"></li>
			                        	<li class="del" title="删除" onclick="delPumpingWellById('${item.id}')"></li>
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
					changePowerPage(n);
					//this.selectPage(n);
					return false;
				}
			});
		});
	</script>
</body>
</html>