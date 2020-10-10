<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>新增异常类型</title>
		<style>
			.personList{border: solid 1px #ccc;width:90%;margin: 0 auto;}
			.personList th{background: #c4e3ff;height: 30px;vertical-align: middle;border: solid 1px #ccc;font-size:15px}
			.personList td{height: 25px;border: solid 1px #ccc;line-height: 25px;font-size:15px;}
			.com_btn{width: 105px!important;height: 30px;border-radius: 3px;margin-top: 10px;background-color: #33AA99;color: white;border-style: none;cursor: pointer;}
			#selectPerson{font-weight: 600;font-size:16px;}
			input[type="checkbox"] {
			    width: 100%;
			    height: 30px;
			    display: inline-block;
			    text-align: center;
			    vertical-align: middle;
			    margin-right: 10px;
			    position: relative;
			}
			input[type="checkbox"]::before:hover{background: #9cc0da;cursor: pointer;}
			input[type="checkbox"]::before {
			    content: "";
			    position: absolute;
			    top: 3px;
			    left: -4px;
			    background: #fff;
			    width: 20px;
			    height: 20px;
			    border: 1px solid #d9d9d9;
			}
			input[type="checkbox"]:checked::before {
			    content: "\2713";
			    background-color: #409eff;
			    position: absolute;
			    top: 3px;
			    left: -4px;
			    width: 20px;
			    height: 20px;
			    color: #fff;
			    font-weight: bold;
			}
		</style>
</head>
<body>
	<div>
		<table cellspacing="1" cellpadding="3" class="pop-table" style="float: none;">
			<tr>
				<td class="table-left"><label for="abnormaltype"><span style="color:red">*</span>异常类型：</label></td>
				<td class="table-right">
					<input id="abnormaltype" name="abnormaltype" class="abnormaltype" type="text"/>
				</td>
				<td class="table-left"><label for="abnormalcode"><span style="color:red">*</span>异常编码：</label></td>
				<td class="table-right">
					<input type="text" id="abnormalcode" name="abnormalcode"/>
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="abnormaldetail"><span style="color:red">*</span>异常详情：</label></td>
				<td class="table-right">
					<textarea  type="text" rows="3" cols="20" id="abnormaldetail" name="abnormaldetail"></textarea>
				</td>
				<td class="table-left"><label for="abnormaldetail"><span style="color:red">*</span>短信预警：</label></td>
				<td class="table-right">
					<input type="checkbox" onchange="handleMessChange(this)"/>
					<span id="isMess" style="position: relative;top: 8px;left: 20px;font-size: 14px;"></span>
				</td>
			</tr>
			<tr>
				<td class="table-left"><label for="abnormaldetail"><span style="color:red">*</span>关联人员：</label></td>
				<td class="table-right"></td>
				<td class="table-right"></td>
				<td class="table-right">已选择：<span id="selectPerson">0</span></td>
			</tr>
		</table>
	</div>
	<div style="height: 360px;overflow: auto;">
		<table class="personList">
			<tr>
				<th>选择</th>
				<th>人员姓名</th>
				<th>电话</th>
				<th>职务</th>
			</tr>
				<c:if test="${!empty sysInfoList}">
					<c:forEach var="item" items="${sysInfoList}" varStatus="vs">
				 		<tr class="${vs.index%2==0?'singular':'double'}">
							<td id="chryu" style="cursor: pointer;"><input type="checkbox" value="${item.id}" onchange="handleChange(this,${item.id})"/></td>
							<td>${item.serviceMan}</td>
							<td>${item.phone}</td>
							<td>${item.duty}</td>
						</tr>
					</c:forEach>
				</c:if>
				
			</tr>
		</table>
	</div>
</body>
</html>