<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysUserForm">
	<table class="pop-table" style="width:600px;">
		<tr>
        	<td class="table-left"><span style="color:red">*</span><label for="waterAreaId">所属水管区域</label>：</td>
			<td class="table-right" colspan="2">
				<select class="pop-select" id="waterAreaId" style="display:inline-block; width:78px;" name="waterAreaId" onchange="getSubWaterAreaId(this)">
					 <option value=''>--请选择水管区域--</option>
					 <c:forEach var="item"  items="${sysWaterAreaList}">
	                 	<option value="${item.id}">${item.waterAreaName}</option>
	                 </c:forEach>
	            </select>
				<input type="hidden" id="num" name="num" value="0"/>
	            <span id="subWaterArea_0"></span>
			</td>
        </tr>
        <tr>
        	<td class="table-left"><span style="color:red">*</span><label for="areaId">所属行政区域</label>：</td>
			<td class="table-right" colspan="2">
				<select class="pop-select" id="areaId" style="display:inline-block; width:78px;" name="areaId" onchange="getSubAreaId(this)">
					<option value=''>--请选择行政区域--</option>
					<c:forEach var="item"  items="${sysAreaList}">
	                	<option value="${item.id}">${item.areaName}</option>
	                </c:forEach> 
				</select>
				<input type="hidden" id="areaNum" name="areaNum" value="0"/>
				<span id="subArea_0"></span>
			</td>
        </tr>
        <!-- 平台操作方式  按照水管区域还是行政区域走 -->
		<tr>
        	<td class="table-left"><span style="color:red">*</span><label for="areaId">操作方式</label>：</td>
			<td class="table-right" colspan="2">
				<select class="pop-select" id="areaWay" style="display:inline-block; width:150px;" name="areaWay">
					<option value='0' selected="selected">水管区域方式</option>
					<option value='1'>行政区域方式</option>
				</select>
			</td>
        </tr>     
        <tr>
        	<td class="table-left"><span style="color:red">*</span><label for="areaId">账号类型</label>：</td>
			<td class="table-right" id="userType" colspan="2">
				<!-- <select class="pop-select" id="userTypeJuris" multiple="multiple" size="1" style="display:inline-block; width:150px;" name="userTypeJuris">
					<option value='0' selected="selected">地下水计量</option>
					<option value='1'>管道计量</option>
				</select> -->
				<input type="checkbox" id="userTypeJuris" name="userTypeJuris" value="0" style="float: none;"/>地下水计量
				<input type="checkbox" id="userTypeJuris" name="userTypeJuris" value="1" style="float: none;"/>管道计量
			</td>
        </tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span><label for="areaId">账号权限</label>：</td>
			<td class="table-right" id="userType" colspan="2">
				<input type="checkbox" id="Role" name="Role" value="000100" style="float: none;"/>是否开启充值
				<input type="checkbox" id="IsCardUpdate" name="IsCardUpdate" value="1" style="float: none;"/>是否升级卡
			</td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span><label for="userCode">用户编码</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="userCode"  name="userCode" maxlength="32" onblur="uniqueUserCode()"/>
				<span id="msgCodeInfo"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span><label for="userName">登陆名称</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="userName"  name="userName" maxlength="64"/>
			</td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span><label for="userPassword">登陆密码</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="userPassword" name="userPassword" maxlength="32">
			</td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span><label for="roleId">所属角色</label>：</td>
			<td class="table-right">
				<select class="pop-select" id="roleId" name="roleId">
					<option value=''>--所属角色--</option>
					<c:forEach var="item" items="${sysRoleList}">
	                	<option value="${item.id}">${item.roleName}</option>
	                </c:forEach> 
				</select>
			</td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span><label for="fullName">用户姓名</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="fullName" name="fullName" maxlength="64">
			</td>
		</tr>
		<tr>	
			<td class="table-left"><span style="color:red">*</span><label for="mobile">手机号</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="mobile" name="mobile" maxlength="16" onblur="checkPhone()">
			</td>
		</tr>
		<tr>	
			<td class="table-left"><label for="email">E-mail</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="email" name="email" maxlength="32">
			</td>
		</tr>
		<tr>	
			<td class="table-left"><label for="address">地址</label>：</td>
			<td class="table-right">
				<input class="pop-input-common" type="text" id="address" name="address" maxlength="32">
			</td>
		</tr>
		<tr>	
			<td class="table-left"><label for="remark">备注</label>：</td>
			<td class="table-right">
				<textarea rows="5" cols="30" class="pop-textarea" id="remark" name="remark" maxlength="256"></textarea>
			</td>
		</tr>
	</table>
</form>