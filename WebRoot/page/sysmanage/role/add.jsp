<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysRoleForm">
	<table class="pop-table">
		<tr>
			<td class="table-left"><span style="color:red">*</span><label for="roleCode">角色编码</label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="roleCode" name="roleCode" maxlength="32" onblur="checkRoleCodeExist()"></td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span><label for="roleName">角色名称</label>：</td>
			<td class="table-right"><input class="pop-input-common" type="text" id="roleName" name="roleName" maxlength="64"></td>
		</tr>
		<tr>
			<td class="table-left"><label for="remark">备注</label>：</td>
			<td class="table-right"><textarea  rows="5" cols="21" class="pop-textarea" id="remark" name="remark" rows="2" style="height:50px;" maxlength="256"></textarea></td>
		</tr>
	</table>
</form>