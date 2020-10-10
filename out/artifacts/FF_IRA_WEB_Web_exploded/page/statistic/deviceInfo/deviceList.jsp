<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<style type="text/css">
	#tinybox{position:absolute; display:none; padding:10px; background:#ffffff url(../image/preload.gif) no-repeat 50% 50%; border:10px solid #e3e3e3; z-index:2000;}
	#tinymask{position:absolute; display:none; top:0; left:0; height:100%; width:100%; background:#000000; z-index:1500;}
	#tinycontent{background:#ffffff; font-size:1.1em;}
</style>
<!--统计分析  机井基本信息查询  -->
<!-- 引用弹出框的js -->
<script type="text/javascript" src="${ctx}/content/skin/js/tinybox.js"></script>

<div class="right_user" style="left:15px;top:50px">
	<table cellspacing='0' cellpadding='3' class='list-table'>
		<thead>
			<tr>
				<th>机井编码</th>
				<th>机井名称</th>
				<th>所属行政区域</th>
				<th>所属水管区域</th>
				<th>业主姓名</th>
				<th>业主电话</th>
				<th>地亩数</th>
				<th>水井用途</th>
				<th>设备型号</th>
				<th>安装前照片</th>
				<th>安装后照片</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty deviceInfoList}">
				<tr>
					<td colspan="10">没有相关数据</td>
				</tr>
			</c:if>
			<c:if test="${!empty deviceInfoList}">
				<c:forEach var="item" items="${deviceInfoList}" varStatus="vs">
					<tr class="${vs.index%2==0?'singular':'double'}">
						<td>${item.deviceCode == null || item.deviceCode == '' ? '--' : item.deviceCode}</td>
						<td>${item.deviceName == null || item.deviceName == '' ? '--' : item.deviceName}</td>
						<td>${item.areaName == null || item.areaName == '' ? '--' : item.areaName}</td>
						<td>${item.waterAreaName == null || item.waterAreaName == '' ? '--' : item.waterAreaName}</td>
						<td>${item.ownerName == null || item.ownerName == '' ? '--' : item.ownerName}</td>
						<td>${item.ownerTelphone == null || item.ownerTelphone == '' ? '--' : item.ownerTelphone}</td>
						<td>${item.sJArea == null || item.sJArea == '' ? '--' : item.sJArea}</td>
						<td>${item.wellUse == null || item.wellUse == '' ? '--' : item.wellUse}</td>
						<td>${item.deviceModel == null || item.deviceModel == '' ? '--' : item.deviceModel}</td>
						<td><a class="ml20" style="cursor: pointer;" onclick="ShowPhotoInfo('${item.photoBefore}')">预览</a></td>
						<td><a class="ml20" style="cursor: pointer;" onclick="ShowPhotoInfo('${item.photoAfter}')">预览</a></td>
						<td>
							<ul style="width:20px; padding-left:20px;">
	                            <li class="report" title="详情" onclick="detailBaseDeviceInfo('${item.id}')"></li>
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
				changeDevicePage(n);
				return false;
			}
		});
	});
	
	//弹出机井拍摄前/后的图片
	function ShowPhotoInfo(photoInfo) {
		// 获取到图片的地址
		var content2 = "<img width='640' height='466' src='/pir/"+photoInfo+"' />";
		// 将图片的信息以弹出框形式展现出来
		if(null==photoInfo || ""==photoInfo) {
			
		}
		TINY.box.show(content2,0,0,0,1);
	}
</script>