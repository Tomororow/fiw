<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		debugger
		var userId = localStorage.getItem("userId");
		var vity = $("#userType").find("input");
		$.post("monitor/login_user.do",{"userId":userId},function(data){
			debugger
			if(data.search("0") != -1){//地下水
				vity[0].checked = true;
			}else{
				vity[0].checked = false;
			}
			if(data.search("1") != -1){//管道
				vity[1].checked = true;
			}else{
				vity[1].checked = false;
			}
			localStorage.clear();
		});
		//帐号权限
		var vityr = $("#roleType").find("input");
		var userdata=${rolelist}
		// for(var i=0;i<userdata.length;i++){
		// 	debugger
		// 	if(userdata[i]==00010){
		// 		vityr[1].checked = true;
		// 	}else{
		// 		vityr[1].checked = false;
		// 	}
		//
		// }
		debugger
		if(userdata[0]==000100){
			vityr[0].checked = true;
		}else{
			vityr[0].checked = false;
		}
		if(userdata[1]==1){
			vityr[1].checked = true;
		}else{
			vityr[1].checked = false;
		}
	});
</script>
<form id="sysUserForm">
	<input type="hidden" id="id" name="id" value="${model.id}" />
	<input type="hidden" id="areaId" name="areaId" value="${sysAreaList.get(0).getId()}" />
	<input type="hidden" id="waterAreaId" name="waterAreaId" value="${sysWaterAreaList.get(0).getId()}" />
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table" style="width:700px;">
		<tr>
        	<td class="table-left"><label for="waterAreaId">所属水管区域</label>：</td>
			<td class="table-right" colspan="3">
				<c:set var="startIndex" value="${fn:length(sysWaterAreaList)-1 }"></c:set>
				<c:forEach var="item" items="${sysWaterAreaList}" varStatus="s">
					<c:if test="${s.index%2==0 && s.index!=0}">
						<c:if test="${!s.last}">
							<span>${sysWaterAreaList[startIndex-s.index].waterAreaName}--</span>
						</c:if>
						<c:if test="${s.last}">
							<span>${sysWaterAreaList[startIndex-s.index].waterAreaName}</span>
						</c:if>
					</c:if>
				 	<c:if test="${s.index%2!=0 || s.index==0}">
				 		<c:if test="${!s.last}">
				 			<span>${sysWaterAreaList[startIndex-s.index].waterAreaName}--</span>
				 		</c:if>
						<c:if test="${s.last}">
							<span>${sysWaterAreaList[startIndex-s.index].waterAreaName}</span>
						</c:if>
					</c:if>
              	</c:forEach>
			</td>
        </tr>
        <tr>
        	<td class="table-left"><label for="areaId">所属行政区域</label>：</td>
			<td class="table-right" colspan="3">
				<c:set var="startIndex" value="${fn:length(sysAreaList)-1 }"></c:set>
				<c:forEach var="item"  items="${sysAreaList}" varStatus="s">
					<c:if test="${s.index%2==0 && s.index!=0}">
						<c:if test="${!s.last}">
							<span>${sysAreaList[startIndex-s.index].areaName}--</span>
						</c:if>
						<c:if test="${s.last}">
							<span>${sysAreaList[startIndex-s.index].areaName}</span>
						</c:if>
					</c:if>
					<c:if test="${s.index%2!=0 || s.index==0}">
				 		<c:if test="${!s.last}">
				 			<span>${sysAreaList[startIndex-s.index].areaName}--</span>
				 		</c:if>
						<c:if test="${s.last}">
							<span>${sysAreaList[startIndex-s.index].areaName}</span>
						</c:if>
					</c:if>
               	</c:forEach> 
			</td>
        </tr>
        <tr>
        	<td class="table-left"><label for="areaWay">操作方式</label>：</td>
			<td class="table-right">
				<select class="pop-select" id="areaWay" name="areaWay">
					<c:if test="${model.areaWay==0}">
                		<option value="0">水管区域方式</option>
					</c:if>
					<c:if test="${model.areaWay==1}">
                		<option value="1">行政区域方式</option>
					</c:if>
					<option value="0">水管区域方式</option>
					<option value="1">行政区域方式</option>
				</select>
				<span style="color:red">*</span>
			</td>
        </tr>
        <tr>
        	<td class="table-left"><label for="userTypeJuris">账号类型</label>：</td>
			<td class="table-right" id="userType" colspan="2" style="display: flex;padding-top:15px;">
				<div>
					<input type="checkbox"  id="userTypeJuris" name="userTypeJuris" value="0" style="float: none;"/>地下水计量
				</div>
				<div>
					<input type="checkbox"  id="userTypeJuris" name="userTypeJuris" value="1" style="float: none;"/>管道计量
				</div>
			</td> 
        </tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span><label for="role">账号权限</label>：</td>
			<td class="table-right" id="roleType" colspan="2">

				<input type="checkbox" id="Role" name="Role" value="000100" style="float: none;"/>是否开启充值
				<input type="checkbox" id="IsCardUpdate" name="IsCardUpdate" value="1" style="float: none;"/>是否升级卡
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="userCode">用户编码</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="userCode"  name="userCode" value="${model.userCode}" maxlength="32"/>
				<span style="color:red">*</span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="userName">登陆名称</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="userName" name="userName" value="${model.userName}"  maxlength="64"/>
				<span style="color:red">*</span>
			</td>
		</tr>
		<%-- <tr>
			<td class="table-left"><label for="userPassword">登陆密码</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="userPassword" value="${model.userPasswordming}" name="userPassword" maxlength="32">
				<span style="color:red">*</span>
			</td>
		</tr> --%>
		<tr>
			<td class="table-left"><label for="roleId">所属角色</label>：</td>
			<td class="table-right">
				<select class="pop-select" id="roleId" name="roleId">
					<option value=''>--所属角色--</option>
					<c:forEach var="item" items="${sysRoleList}">
						<c:if test="${item.id==model.roleId}">
	                		<option selected="selected" value="${item.id}">${item.roleName}</option>
						</c:if>
						<c:if test="${item.id!=model.roleId}">
	                		<option value="${item.id}">${item.roleName}</option>
						</c:if>
	                </c:forEach> 
				</select>
				<span style="color:red">*</span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><label for="fullName">用户姓名</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" value="${model.fullName}" id="fullName" name="fullName" maxlength="64">
			</td>
		</tr>
		<tr>	
			<td class="table-left"><label for="mobile">手机号</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" value="${model.mobile}" id="mobile" name="mobile" maxlength="16">
			</td>
		</tr>
		<tr>	
			<td class="table-left"><label for="email">E-mail</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" value="${model.email}" id="email" name="email" maxlength="32">
			</td>
		</tr>
		<tr>	
			<td class="table-left"><label for="address">地址</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" value="${model.address}" id="address" name="address" maxlength="32">
			</td>
		</tr>
		<tr>	
			<td class="table-left"><label for="remark">备注</label>：</td>
			<td class="table-right">
				<textarea rows="5" cols="30" class="pop-textarea" id="remark" name="remark" maxlength="256">${model.remark}</textarea>
			</td>
		</tr>
	</table>
</form>
