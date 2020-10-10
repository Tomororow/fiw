<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>远程充值列表</title>
	<link href="${ctx}/content/skin/adapters/bootstrap/bootstrap.css" rel="stylesheet" type="text/css" />
		<style>
			#seleCard {margin:10px 0 10px 10px}
			#seleCard span{font-size: 15px;}
			.pop-select{display:inline-block;height: 26px;background-color: #eef8fe;font-size:14px;}
			.com_btn{width: 105px;height: 30px;border-radius: 3px;margin-top: 10px;background-color: #33AA99;color: white;border-style: none;cursor: pointer;}
			.tableList{border:solid 1px #ccc;}
			.tableList tr td{height: 25px;width:125px;font-size:14px;border: solid 0.5px #ccc}
			.right_user{background: ''}
			.tableList tr td:nth-child(even){color:#6495ed;font-weight: 600 }
			#waterIpu{position:absolute;top:8px;padding-left:10px;height: 50%;width:90%;border: solid 1px #ccc;background: #eef8fe;color: green;font-size:20px;font-weight: 600}
			#waterIpu::-webkit-input-placeholder{color: #a8b5cc;font-size: 15px;}
			#showDiv{height:540px;position: relative;margin-top: -60px;background-color: #00000014;z-index:999;display:none;}
			#myAlert{display: none;}
			.popup-box{left:450px;}
		</style>
</head>
<body>
	<div id="showDiv" align="center">
		<%-- <img src="${ctx}/content/skin/css/images/loading.gif" style="margin-top:15%"/> --%>
		<div style="position: absolute;top: 45%; width: 500px;left: 235px;">
			<span style="color:#2fbda5" id="loadText">指令已下发，正在充值......</span>
			<div class="progress progress-striped active" >
				<div class="progress-bar progress-bar-success" role="progressbar"
		 			aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
				</div>
			</div>
		</div>
		<div id="myAlert" class="alert alert-success" style="position: absolute;top: 45%;left: 380px;color:#c7c7c7;background-color:#141513f2;border:#141513f2">
    		<a href="#" class="close" data-dismiss="alert" style="margin-left: 30px;">&times;</a>
    		<strong id="chargeText">成功！充值成功</strong>
		</div>
	</div>
	<div style="margin-top: 50px;left:15px; top:10px;height: 410px;z-index:0" class="right_user">
		<div id="seleCard" align="left">
			<span>选择当前充值卡号：</span>
			<select class="pop-select" id="query_cardCode" name="query_cardCode" onchange="handleChange('card',this);" style="width:200px;">
	  			<option value="">--请选择--</option>
	   			<c:forEach var="item" items="${cardList}">
	   				<option value="${item.cardCode}">${item.cardCode}</option>
	   			</c:forEach>
			</select>
			<span style="margin-left: 20px;">配水年份：</span>
			<select class="pop-select" id="query_year" name="query_year" onchange="handleChange('year',this);" style="width:120px;">
	  			<option value="">--请选择--</option>
	   			<c:forEach var="item" items="${yearList}">
	   				<option value="${item}">${item}</option>
	   			</c:forEach>
			</select>
			<span style="margin-left: 20px;">配水伦次：</span>
			<select class="pop-select" id="query_round" name="query_round" onchange="handleChange('round',this);">
	  			<option value="">--请选择--</option>
	   			<%-- <c:forEach var="item" items="${rptList}">
	   				<option value="${item}">${item}</option>
	   			</c:forEach> --%>
			</select>
		</div>
		<table class='tableList' cellpadding="0" cellspacing="0" width="750" height="360" border="1">
		  <tr>
		    <td>机井编码</td>
		    <td colspan="2">${deviceCode }</td>
		    <td>充值卡号</td>
		    <td colspan="2" name="cardCode"></td>
		  </tr>
		  <tr>
		    <td>网络状态</td>
		    <td colspan="2">
		    	<c:choose>
					<c:when test="${netState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>离线</c:when>
					<c:when test="${netState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>在线</c:when>
				</c:choose>
			</td>
		    <td>水泵状态</td>
		    <td colspan="2">
		    	<c:choose>
					<c:when test="${pumpState==0}"><img src='${ctx}/content/skin/css/images/icon/icon-exclamation.png'></img>关泵</c:when>
					<c:when test="${pumpState==1}"><img src='${ctx}/content/skin/css/images/icon/icon-accept.png'></img>开泵</c:when>
		   		</c:choose>
		    </td>
		  </tr>
		  <tr>
		    <td>配水年份</td>
		    <td name="year"></td>
		    <td>配水轮次</td>
		    <td name="round"></td>
		    <td>配水单价</td>
		    <td name="price"></td>
		  </tr>
		  <tr>
		    <td>总配水量</td>
		    <td name="sumWaterAmout">&nbsp;</td>
		    <td>已充值水量</td>
		    <td name="waterAmout">&nbsp;</td>
		    <td>可充值水量</td>
		    <td name="mayAmout" style="color: red;">&nbsp;</td>
		  </tr>
		  <tr>
		    <td>本次充值水量</td>
		    <td colspan="3" align="left" style="position: relative;">
		    	<input type="number" id="waterIpu" placeholder="请输入本次充值水量" onchange="handleChangeWater(this)" onkeyup="handleChangeWater(this)"/>
		    	<span style="color: #666666;position: absolute;right: 60px;top: 18px;">m³</span><br>
		    	<span style="color: #c3b38d;position: absolute;font-size: 12px;bottom: 2px;left: 10px;font-weight: 0"><span style="color: red;">*</span>请输入充值水量，只能输入大于0的正整数</span>
		   	</td>
		   	 <td>本次充值金额</td>
		     <td name="payMoney"></td>
		  </tr>
		</table>
	</div>
</body>
<script type="text/javascript">
	allList = '${allList}';
	if(allList!=''||allList!=null){
		allList = JSON.parse(allList);
		if(allList.length==0){
			alert("该井无配水计划，请先配水...");
			hideMark();
			$("#popup_0").remove();
		}
	}
</script>
</html>
