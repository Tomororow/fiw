<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<html>
<head>
<title>设备列表</title>
</head>

<body>
	<div class="operate">
		<ul>
			<li class="add" onclick="addBaseDeviceInfo()">新增</li>
            <li class="del" onclick="delBaseDeviceInfo()">删除</li>
		</ul>
		<div style="padding-top:5px">
	        <span style="margin-left:10px;">机井编码：</span>
	        <input type="text" style="color:#adadad; width:120px;" value="${deviceCode==null ? '请输入机井编码' : deviceCode}" id="deviceCode" value="请输入机井编码" onfocus="if(this.value == '请输入机井编码') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井编码'" />
			
			<span style="margin-left:10px;">机井名称：</span>
	   		<input type="text" style="color:#adadad; width:130px;" value="${deviceName==null ? '请输入机井名称' : deviceName}" id="deviceName" value="请输入机井名称" onfocus="if(this.value == '请输入机井名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入机井名称'" />
		    
		    <input type="button" class="btn-search" value="查询" onclick="getDeviceInfoByCondition()">
		</div>
	</div>
	<div class="device" id="baseDeviceInfoDiv">
		<table cellspacing='0' cellpadding='3' class='list-table'>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"/></th>
                    <th>机井编码</th>
					<th>机井名称</th>
					<th>智能设备型号</th>
					<th>智能设备物联网卡号</th>
					<th>机井用途</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty baseDeviceInfoList}">
					<tr>
						<td colspan="6">没有相关数据</td>
					</tr>
				</c:if>
				<c:if test="${!empty baseDeviceInfoList}">
					<c:forEach var="item" items="${baseDeviceInfoList}" varStatus="vs">
				 		<tr class="${vs.index%2==0?'singular':'double'}">
							<td><input type="checkbox" id="areaId" value="${item.id}" /></td>
							<td>
								${empty item.deviceCode ? "--" : item.deviceCode}
							</td>
							<td>
								${empty item.deviceName ? "--" : item.deviceName}
							</td>
							<td>
								${empty item.deviceModel ? "--" : item.deviceModel}
							</td>
							<td>
								${empty item.simCard ? "--" : item.simCard}
							</td>
							<td>
								${empty item.wellUse ? "--" : item.wellUse}
							</td>
							<td width="30" align="center">
	                            <ul style="width:100px;">
	                                <li class="edit" title="编辑基础信息" onclick="editBaseDeviceInfo('${item.id}')"></li>
	                                <li class="picturEdit" title="编辑图片" onclick="editPicture('${item.id}')"></li>
	                                <li class="del" title="删除" onclick="delBaseDeviceInfo('${item.id}')"></li>
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
					changePage(n);
					return false;
				}
			});
		});
		
	</script>
</body>
</html>