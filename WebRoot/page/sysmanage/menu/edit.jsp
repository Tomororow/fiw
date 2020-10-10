<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<form id="sysMenuForm">
   <input type="hidden" name="id" id="id" value="${sysMenu.id}">
   <input type="hidden" name="menucode" id="menucode" value="${sysMenu.menucode}">
   <input type="hidden" name="enabledstate" id="enabledstate" value="${sysMenu.enabledstate}">
   <input type="hidden" name="menulevel" id="menulevel" value="${sysMenu.menulevel}">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
	<c:if test="${sysMenu.pid!='0'}">
		 <tr>
	      	<td class="table-left"><label for="parentName">父菜单模块</label>： </td>
	          <td class="table-right">
	          	${parentName}
	          </td>
	      </tr>
    </c:if>  
       <tr>
      	<td class="table-left"><label for="menucode">菜单编码</label>： </td>
          <td class="table-right">
          	${sysMenu.menucode}
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="menuname">菜单名称</label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="menuname" name="menuname" value="${sysMenu.menuname}" maxlength="32">
          </td>
      </tr>
      
        <tr>
      	<td class="table-left"><label for="menuorder">菜单排序</label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="menuorder" name="menuorder" value="${sysMenu.menuorder}">
          </td>
      </tr>

       <tr>
      	<td class="table-left"><label for="menuurl">菜单链接</label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="menuurl" name="menuurl" value="${sysMenu.menuurl}" maxlength="128">
          </td>
      </tr>
       <tr>
      	<td class="table-left"><label for="menuicon">菜单图标</label>： </td>
          <td class="table-right">
          	<input class="pop-input-common" type="text" id="menuicon" name="menuicon" value="${sysMenu.menuicon}">
          </td>
      </tr>
      
       <tr>
      	<td class="table-left"><label for="menuremark">备注</label>： </td>
          <td class="table-right">
            <textarea rows="5" cols="21" class="pop-textarea"  id="menuremark" name="menuremark" maxlength="256">${sysMenu.menuremark }</textarea>
          </td>
      </tr>

	</table>
</form>
<script type="text/javascript">
	$(document).ready(function(){
	  //$('.combobox').combobox();
	});
</script>