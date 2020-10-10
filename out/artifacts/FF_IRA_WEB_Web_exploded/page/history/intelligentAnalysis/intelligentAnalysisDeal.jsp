<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
	li {
		float : left;
		list-style: none;
	}
</style>
<form id="intelligentDealForm" >
	<input type="hidden" value="${intelligentAnalysis.id}" id="intelligentAnalysisId" name="intelligentAnalysisId" /> 
	<div style="width:100%;">
			<table border="0" cellspacing="1" cellpadding="3" class="pop-table" style="width:100%;">
			<tr>
				<td class="table-left">解决方式：</td>
				<td class="table-right">
					<ul>
						<li>
							<input type="radio" id="dealType" name="dealType" value="0" checked="checked" onclick="dealTypeInfos()"/>电话指导处理
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</li>
						<li>
							<input type="radio" id="dealType" name="dealType" value="1" onclick="dealTypeInfos()"/>前往现场处理
						</li>
					</ul>
				</td>
			</tr>
			<tr class="dealPersonInfo">
				<td class="table-left"><span style="color:red">*</span>维修技术员：</td>
				<td class="table-right">
					<select class="pop-select" id="serviceManId" name="serviceManId" style="display:inline-block; width:236px; border-radius:6px;" onchange="getServiceManPhone()">
						<option value="">--请选择技术员--</option>
						<c:forEach var="items" items="${serviceManList}">
							<option value="${items.id}">${items.serviceMan}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr class="dealPersonInfo">
				<td class="table-left"><span style="color:red">*</span>技术员电话：</td>
				<td class="table-right">
					<input class="pop-input-common" id="phone" name="phone" type="text" readonly="readonly" style="width:229px; border-radius:6px;"/>
				</td>
			</tr>
			
			<tr class="dealPersonInfo" style="display: none;">
				<td class="table-left"><span style="color:red">*</span>报修时间：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="fixTime" name="fixTime" readonly="readonly" value="${nowDate}" style="width:229px; border-radius:6px;"/>
				</td>
			</tr>
			
			<tr class="dealPersonInfo" style="display: none;">
				<td class="table-left"><span style="color:red">*</span>预计用时(小时)：</td>
				<td class="table-right">
					<input class="pop-input-common" type="text" id="handleTime" name="handleTime" style="width:229px; border-radius:6px;"/>
				</td>
			</tr>

			<tr >
				<td class="table-left">异常信息描述：</td>
				<td class="table-right">
					<textarea class="pop-input-common" rows="3" cols="30" id="dealExceptionRemark" name="dealExceptionRemark" style="resize:none; border-radius:6px; font-family: Microsoft YaHei;">${intelligentAnalysis.intelligentException==null?'':intelligentAnalysis.intelligentException}</textarea>
				</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		$(function(){
			dealTypeInfos();
           	getDealRemarkInfo();
		});
	</script>
</form>