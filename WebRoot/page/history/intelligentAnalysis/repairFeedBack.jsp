<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<style>
	#lis li {float:left;list-style:none;display:inline-block}
	#lis {margin-left:0px;}
</style>
<form id="repairFeedBackForm">
	<input type="hidden" value="${id}" id="intelligentAnalysisId" name="intelligentAnalysisId" />
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table" style="width:100%;">
		<tr>
			<td class="table-left"><span style="color:red">*</span>处理结果：</td>
			<td class="table-right">
				<ul id="lis">
					<li>
						<span><input type="radio" value="0" id="dealResult" name="dealResult" checked="checked"/>已解决</span>
					</li>
					<li>
						<span><input type="radio" value="1" id="dealResult" name="dealResult" />未解决</span>
					</li>
					<li>
						<span><input type="radio" value="2" id="dealResult" name="dealResult" />解决中</span>
					</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span>处理方式描述：</td>
			<td class="table-right">
				<textarea rows="5" cols="40" style="resize:none;" id="dealRemark" name="dealRemark"></textarea>
			</td>
		</tr>
		<tr>
			<td class="table-left">预计报修所用时长：</td>
			<td class="table-right">
				&nbsp;&nbsp;${intelligentDeal.handleTime}&nbsp;小时
			</td>
		</tr>
		<tr>
			<td class="table-left">实际报修所用时长：</td>
			<td class="table-right">
				&nbsp;&nbsp;${overTimes}&nbsp;小时
			</td>
		</tr>
		<tr>
			<td class="table-left">超时所用时间：</td>
			<td class="table-right">
				&nbsp;&nbsp;${overUseTime}&nbsp;小时
			</td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span>是否超时：</td>
			<td class="table-right">
				<ul id="lis">
					<c:if test="${overUseTime<=0}">
					<li>
						<span><input type="radio" value="1" id="isOverTime" name="isOverTime" checked="checked"/>否</span>
					</li>
					<li>
						<span><input type="radio" value="0" id="isOverTime" name="isOverTime" />是</span>
					</li>
					</c:if>
					<c:if test="${overUseTime>0}">
					<li>
						<span><input type="radio" value="1" id="isOverTime" name="isOverTime"/>否</span>
					</li>
					<li>
						<span><input type="radio" value="0" id="isOverTime" name="isOverTime" checked="checked"/>是</span>
					</li>
					</c:if>
					<span style="color:red">（根据是否超时自动选择，也可视情况更改）</span>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="table-left"><span style="color:red">*</span>超时描述：</td>
			<td class="table-right">
				<textarea rows="5" cols="40" style="resize:none;" id="overTimeRemark" name="overTimeRemark"></textarea>
			</td>
		</tr>
	</table>
</form>