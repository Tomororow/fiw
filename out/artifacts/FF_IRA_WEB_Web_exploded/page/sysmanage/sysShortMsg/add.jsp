<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<form id="sysShortMsgForm">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table" style="width:100%;">
		<tr>
			<td class="table-left"><span style="color:red">*</span>短信编码：</td>
			<td class="table-right">
				<input type="text" id="MsgCode" style="width:142px;" name="MsgCode" maxlength="32" onblur="uniqueMsgCode()"/>
				<span id="msgCodeInfo"></span>
			</td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span>短信内容：</td>
			<td class="table-right">
				<textarea rows="8" cols="60" class="pop-textarea" id="MsgContent" name="MsgContent" maxlength="300"></textarea>
			</td>
		</tr>
	</table>
</form>
