<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript" charset="utf-8" src="${ctx}/content/skin/js/readSerialData.js"></script>
<!-- 远程参数设置页面 -->
<html>
<head>
	<style type="text/css">
		/* 远程参数设置tab样式 */
		.remoteTab {
			width: 966px;
			border: #cccccc solid 1px;
			border-top: #cccccc solid 1px;
			border-bottom: #cccccc solid 1px;
			margin-left: 20px;
			font-weight: bold;
		}
		
		.menu {
			height: 38px;
		}
		
		.menu li {
			float: left;
			width: 160px;
			height: 38px;
			text-align: center;
			line-height: 38px;
			cursor: pointer;
			border-right: #cccccc solid 1px;
			color: #666;
			font-size: 14px;
			overflow: hidden;
			background: #E1EAF0;
		}
		
		.menu li.off {
			background: #FFFFFF;
			color: #336699;
			font-weight: bold;
		}
		
		.menu li {
			border-bottom: #cccccc solid 1px;
		}
		
		.menudiv{
			height: 430px;
		}
		
		/* 水电设置样式 */
		.star-select-sd{
			width: 160px;
			height: 28px;
			border-radius: 3px;
		}
		
		.star-input-sd{
			width: 159px;
			height: 26px;
			border-radius: 3px;
			border: #cccccc solid 1px;
		}
		
		/* 输入接口设置样式 */
		.star-select-jk{
			width: 130px;
			height: 28px;
			border-radius: 3px;
		}
		
		.star-input-jk{
			width: 129px;
			height: 26px;
			border-radius: 3px;
			border: #cccccc solid 1px;
		}
		
		/* 设置按钮样式 */
		.sz_btn{
			cursor: pointer;
		}
		
		/* 参数提交按钮样式 */
		#com_btn{
			width: 105px;
			height: 30px;
			border-radius: 3px;
			margin-top: 10px;
			background-color: #33AA99;
			color: white;
			border-style: none;
			cursor: pointer;
		}
		#showDiv{height:590px;position: absolute;width:100%;margin-top: -60px;background-color: #00000014;z-index:999;display:none;}
		#marques{display: block;background: #1c81da73;color: #000;width: 30%;border-radius: 15px;margin-top: 20px;}
	</style>
</head>

<body>
	<div id="showDiv" align="center">
		<img src="${ctx}/content/skin/css/images/loading.gif" style="margin-top:15%"/>
		<span id="marques" @click="marquesClick">
			<marquee behavior="scroll" width="100%" vspace="1" onmouseover="this.stop();" onmouseout="this.start();">正在读取参数，请稍后......</marquee>
		</span>
	</div>
	<div style="width:100%; height:100%;">
		<div class="remoteTab" id="remoteTab">
			<div class="menu">
				<ul>
					<li id="one1" onclick="setTab('one', 1)">水电设置</li>
					<li id="one2" onclick="setTab('one', 2)">输入接口设置</li>
					<li id="one3" onclick="setTab('one', 3)">继电器设置</li>
					<li id="one4" onclick="setTab('one', 4)">计数器设置</li>
					<li id="one5" onclick="setTab('one', 5)">串口设置</li>
					<li id="one6" onclick="setTab('one', 6)">基础参数</li>
					<li id="one7" onclick="setTab('one', 7)">网络设置</li>
					<li id="one8" onclick="setTab('one', 8)">存储设置</li>
				</ul>
			</div>
			<div class="menudiv">
				<!-- 水电设置 start -->
				<div id="con_one_1">
					<table border="0" class="list-table" style="width:100%; margin-top:25px;">
						<tr>
							<td>水量通道1:</td>
							<td>
								<select class="star-select-sd" id="SLTD1" name="SLTD1">
									<option value="0">无</option>
									<option value="1">计数器0</option>
									<option value="2">计数器1</option>
									<option value="3">计数器2</option>
									<option value="4">计数器3</option>
									<option value="5">RS232-0</option>
									<option value="6">RS232-1</option>
									<option value="7" selected="selected">RS485-0</option>
									<option value="8">RS485-1</option>
									<option value="9">DI0</option>
									<option value="10">DI1</option>
									<option value="11">DI2</option>
									<option value="12">DI3</option>
								</select>
							</td>
							<td>电量通道:</td>
							<td>
								<select class="star-select-sd" id="DLTD" name="DLTD">
									<option value="0">无</option>
									<option value="1">计数器0</option>
									<option value="2">计数器1</option>
									<option value="3">计数器2</option>
									<option value="4">计数器3</option>
									<option value="5">RS232-0</option>
									<option value="6">RS232-1</option>
									<option value="7">RS485-0</option>
									<option value="8" selected="selected">RS485-1</option>
									<option value="9">DI0</option>
									<option value="10">DI1</option>
									<option value="11">DI2</option>
									<option value="12">DI3</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>备份通道:</td>
							<td>
								<select class="star-select-sd" id="BFTD" name="BFTD">
									<option value="0" selected="selected">无</option>
									<option value="1">计数器0</option>
									<option value="2">计数器1</option>
									<option value="3">计数器2</option>
									<option value="4">计数器3</option>
									<option value="5">RS232-0</option>
									<option value="6">RS232-1</option>
									<option value="7">RS485-0</option>
									<option value="8">RS485-1</option>
									<option value="9">DI0</option>
									<option value="10">DI1</option>
									<option value="11">DI2</option>
									<option value="12">DI3</option>
								</select>
							</td>
							<td>电量转水量系数:<br/>(Kg->0.1度)</td>
							<td><input type="text" id="DZSXS" name="DZSXS" class="star-input-sd" value="1500" onblur="formCheck()"></td>
						</tr>
						<tr>
							<td>无电监测时间(秒):</td>
							<td><input type="text" id="WDJCSJ" name="WDJCSJ" class="star-input-sd" value="300" onblur="formCheck()"></td>
							<td>电量系数:<br/>(此值要除以2000)</td>
							<td><input type="text" id="DLXS" name="DLXS" class="star-input-sd" value="2000" onblur="formCheck()"></td>
						</tr>
						<tr>
							<td>电压系数:<br/>(此值要除以1000)</td>
							<td><input type="text" id="DYXS" name="DYXS" class="star-input-sd" value="1000" onblur="formCheck()"></td>
							<td>时间转水量系数:<br/>(Kg->秒)</td>
							<td><input type="text" id="SJZSL" name="SJZSL" class="star-input-sd" value="1.1" onblur="formCheck()"></td>
						</tr>
						<tr>
							<td>剩余水量报警阀值(吨):</td>
							<td><input type="text" id="SYSLFZ" name="SYSLFZ" class="star-input-sd" value="300" onblur="formCheck()"></td>
							<td>无水监测时间(分钟):</td>
							<td><input type="text" id="WSJCSJ" name="WSJCSJ" class="star-input-sd" value="5" onblur="formCheck()"></td>
						</tr>
						<tr>
							<td>电表过滤电流(A):</td>
							<td><input type="text" id="DBGLDL" name="DBGLDL" class="star-input-sd" value="6" onblur="formCheck()"></td>
						</tr>
					</table>
				</div>
				<!-- 水电设置 end -->
				
				<!-- 输入接口设置 start -->
				<div id="con_one_2" style="display:none;">
					<table border="0" class="list-table" style="width:100%; margin-top:10px;">
						<!-- <h4 style="padding-top:10px; padding-left:5px; float:left;">DI接口配置</h4> -->
						<tr style="height:50px;">
							<td>DI0采集间隔<br>(10ms):</td>
							<td><input type="text" id="DI0CJJG" name="DI0CJJG" class="star-input-jk" value="0" onblur="formCheck()"></td>
							<td>存储间隔<br>(分):</td>
							<td><input type="text" id="DI0CCJG" name="DI0CCJG" class="star-input-jk" value="0" onblur="formCheck()"></td>
							<td>报警方式:</td>
							<td>
								<select class="star-select-jk" id="DI0BJFS" name="DI0BJFS">
									<option value="0">不报警</option>
									<option value="1">低电平报警</option>
									<option value="2">高电平报警</option>
									<option value="3">上升沿报警</option>
									<option value="4">下降沿报警</option>
									<option value="5">双沿报警</option>
								</select>
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-jk" id="DI0YT" name="DI0YT">
									<option value="0">预留</option>
									<option value="1">主控监测</option>
									<option value="2">电磁锁监测</option>
									<option value="3">水泵电流检测</option>
								</select>
							</td>
						</tr>
						<tr style="height:50px;">
							<td>DI1采集间隔<br>(10ms):</td>
							<td><input type="text" id="DI1CJJG" name="DI1CJJG" class="star-input-jk" value="0" onblur="formCheck()"></td>
							<td>存储间隔<br>(分):</td>
							<td><input type="text" id="DI1CCJG" name="DI1CCJG" class="star-input-jk" value="0" onblur="formCheck()"></td>
							<td>报警方式:</td>
							<td>
								<select class="star-select-jk" id="DI1BJFS" name="DI1BJFS">
									<option value="0">不报警</option>
									<option value="1">低电平报警</option>
									<option value="2">高电平报警</option>
									<option value="3">上升沿报警</option>
									<option value="4">下降沿报警</option>
									<option value="5">双沿报警</option>
								</select>
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-jk" id="DI1YT" name="DI1YT">
									<option value="0">预留</option>
									<option value="1" selected="selected">主控监测</option>
									<option value="2">电磁锁监测</option>
									<option value="3">水泵电流检测</option>
								</select>
							</td>
						</tr>
						<tr style="height:50px;">
							<td>DI2采集间隔<br>(10ms):</td>
							<td><input type="text" id="DI2CJJG" name="DI2CJJG" class="star-input-jk" value="0" onblur="formCheck()"></td>
							<td>存储间隔<br>(分):</td>
							<td><input type="text" id="DI2CCJG" name="DI2CCJG" class="star-input-jk" value="0" onblur="formCheck()"></td>
							<td>报警方式:</td>
							<td>
								<select class="star-select-jk" id="DI2BJFS" name="DI2BJFS">
									<option value="0">不报警</option>
									<option value="1">低电平报警</option>
									<option value="2">高电平报警</option>
									<option value="3">上升沿报警</option>
									<option value="4">下降沿报警</option>
									<option value="5">双沿报警</option>
								</select>
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-jk" id="DI2YT" name="DI2YT">
									<option value="0">预留</option>
									<option value="1">主控监测</option>
									<option value="2" selected="selected">电磁锁监测</option>
									<option value="3">水泵电流检测</option>
								</select>
							</td>
						</tr>
						<tr style="height:50px;">
							<td>DI3采集间隔<br>(10ms):</td>
							<td><input type="text" id="DI3CJJG" name="DI3CJJG" class="star-input-jk" value="0" onblur="formCheck()"></td>
							<td>存储间隔<br>(分):</td>
							<td><input type="text" id="DI3CCJG" name="DI3CCJG" class="star-input-jk" value="1" onblur="formCheck()"></td>
							<td>报警方式:</td>
							<td>
								<select class="star-select-jk" id="DI3BJFS" name="DI3BJFS">
									<option value="0">不报警</option>
									<option value="1">低电平报警</option>
									<option value="2">高电平报警</option>
									<option value="3">上升沿报警</option>
									<option value="4">下降沿报警</option>
									<option value="5">双沿报警</option>
								</select>
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-jk" id="DI3YT" name="DI3YT">
									<option value="0">预留</option>
									<option value="1">主控监测</option>
									<option value="2">电磁锁监测</option>
									<option value="3" selected="selected">水泵电流检测</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				<!-- 输入接口设置 end -->
				
				<!-- 继电器设置 start -->
				<div id="con_one_3" style="display:none;">
					<table border="0" class="list-table" style="width:100%; margin-top:25px;">
						<tr>
							<td>继电器0默认输出:</td>
							<td>
								<select class="star-select-sd" id="JDQ0MRSC" name="JDQ0MRSC">
									<option value="0">断开</option>
									<option value="1">吸合</option>
								</select>
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="JDQ0YT" name="JDQ0YT">
									<option value="0">预留</option>
									<option value="1" selected="selected">主控</option>
									<option value="2">电磁锁</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>继电器1默认输出:</td>
							<td>
								<select class="star-select-sd" id="JDQ1MRSC" name="JDQ1MRSC">
									<option value="0">断开</option>
									<option value="1">吸合</option>
								</select>
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="JDQ1YT" name="JDQ1YT">
									<option value="0">预留</option>
									<option value="1">主控</option>
									<option value="2" selected="selected">电磁锁</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>继电器2默认输出:</td>
							<td>
								<select class="star-select-sd" id="JDQ2MRSC" name="JDQ2MRSC">
									<option value="0">断开</option>
									<option value="1">吸合</option>
								</select>
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="JDQ2YT" name="JDQ2YT">
									<option value="0">预留</option>
									<option value="1">主控</option>
									<option value="2" selected="selected">电磁锁</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>继电器3默认输出:</td>
							<td>
								<select class="star-select-sd" id="JDQ3MRSC" name="JDQ3MRSC">
									<option value="0">断开</option>
									<option value="1">吸合</option>
								</select>
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="JDQ3YT" name="JDQ3YT">
									<option value="0">预留</option>
									<option value="1">主控</option>
									<option value="2" selected="selected">电磁锁</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>主控类型:</td>
							<td>
								<select class="star-select-sd" id="ZKLX" name="ZKLX">
									<option value="0">空开</option>
									<option value="1">接触器</option>
								</select>
							</td>
							<td>主控继电器:</td>
							<td>
								<select class="star-select-sd" id="ZKJDQ" name="ZKJDQ">
									<option value="1">常闭</option>
									<option value="0">常开</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>电磁锁开锁延迟:</td>
							<td>
								<input type="text" id="DCSKSYC" name="DCSKSYC" class="star-input-sd" value="5">
							</td>
							<td>开机主控保护:</td>
							<td>
								<select class="star-select-sd" id="KJZKBH" name="KJZKBH">
									<option value="1">开启</option>
									<option value="0">不开启</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				<!-- 继电器设置 end -->
				
				<!-- 计数器设置 start -->
				<div id="con_one_4" style="display:none;">
					<table border="0" class="list-table" style="width:100%; margin-top:25px;">
						<tr>
							<td>计数器0方式:</td>
							<td>
								<select class="star-select-sd" id="JSQ0FS" name="JSQ0FS">
									<option value="0">不计数</option>
									<option value="1">上升沿</option>
									<option value="2" selected="selected">下降沿</option>
									<option value="3">双沿</option>
								</select>
							</td>
							<td style="width:100px">系数:</td>
							<td>
								<input type="text" id="JSQ0XS" name="JSQ0XS" class="star-input-sd" value="1">
							</td>
						</tr>
						<tr>
							<td>存储时间(分钟):</td>
							<td>
								<input type="text" id="JSQ0CCSJ" name="JSQ0CCSJ" class="star-input-sd" value="5">
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="JSQ0YT" name="JSQ0YT">
									<option value="0">预留</option>
									<option value="1" selected="selected">水表</option>
									<option value="2">电表</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>计数器1方式:</td>
							<td>
								<select class="star-select-sd" id="JSQ1FS" name="JSQ1FS">
									<option value="0">不计数</option>
									<option value="1">上升沿</option>
									<option value="2" selected="selected">下降沿</option>
									<option value="3">双沿</option>
								</select>
							</td>
							<td>系数:</td>
							<td>
								<input type="text" id="JSQ1XS" name="JSQ1XS" class="star-input-sd" value="1">
							</td>
						</tr>
						<tr>
							<td>存储时间(分钟):</td>
							<td>
								<input type="text" id="JSQ1CCSJ" name="JSQ1CCSJ" class="star-input-sd" value="5">
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="JSQ1YT" name="JSQ1YT">
									<option value="0">预留</option>
									<option value="1" selected="selected">水表</option>
									<option value="2">电表</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>计数器2方式:</td>
							<td>
								<select class="star-select-sd" id="JSQ2FS" name="JSQ2FS">
									<option value="0">不计数</option>
									<option value="1">上升沿</option>
									<option value="2" selected="selected">下降沿</option>
									<option value="3">双沿</option>
								</select>
							</td>
							<td>系数:</td>
							<td>
								<input type="text" id="JSQ2XS" name="JSQ2XS" class="star-input-sd" value="1">
							</td>
						</tr>
						<tr>
							<td>存储时间(分钟):</td>
							<td>
								<input type="text" id="JSQ2CCSJ" name="JSQ2CCSJ" class="star-input-sd" value="5">
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="JSQ2YT" name="JSQ2YT">
									<option value="0">预留</option>
									<option value="1">水表</option>
									<option value="2" selected="selected">电表</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>计数器3方式:</td>
							<td>
								<select class="star-select-sd" id="JSQ3FS" name="JSQ3FS">
									<option value="0">不计数</option>
									<option value="1">上升沿</option>
									<option value="2" selected="selected">下降沿</option>
									<option value="3">双沿</option>
								</select>
							</td>
							<td>系数:</td>
							<td>
								<input type="text" id="JSQ3XS" name="JSQ3XS" class="star-input-sd" value="1">
							</td>
						</tr>
						<tr>
							<td>存储时间(分钟):</td>
							<td>
								<input type="text" id="JSQ3CCSJ" name="JSQ3CCSJ" class="star-input-sd" value="5">
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="JSQ3YT" name="JSQ3YT">
									<option value="0">预留</option>
									<option value="1">水表</option>
									<option value="2" selected="selected">电表</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				<!-- 计数器设置 end -->
				
				<!-- 串口设置 start -->
				<div id="con_one_5" style="display:none;">
					<!-- <h5 style="position:absolute; float:left; margin-top:8px;">RS232-0接口配置</h5><br> -->
					<table border="0" class="list-table" style="width:100%; margin-top:5px;">
						<tr>
							<td>波特率:</td>
							<td>
								<select class="star-select-sd" id="RS2320BTL" name="RS2320BTL">
									<option value="0">300</option>
									<option value="1">600</option>
									<option value="2">1200</option>
									<option value="3">2400</option>
									<option value="4">4800</option>
									<option value="5">9600</option>
									<option value="6">14400</option>
									<option value="7">19200</option>
									<option value="8">38400</option>
									<option value="9">56000</option>
									<option value="10">57600</option>
									<option value="11" selected="selected">115200</option>
								</select>
							</td>
							<td>数据位:</td>
							<td>
								<select class="star-select-sd" id="RS2320SJW" name="RS2320SJW">
									<option value="0">8N1</option>
									<option value="1">8E1</option>
									<option value="2">8O1</option>
									<option value="3">7O1</option>
									<option value="4">7E1</option>
									<option value="5">7N1</option>
								</select>
							</td>
							<td>采集间隔:</td>
							<td>
								<input type="text" id="RS2320CJJG" name="RS2320CJJG" class="star-input-sd" value="0">
							</td>
						</tr>
						<tr>
							<td>存储时间(分钟):</td>
							<td>
								<input type="text" id="RS2320CCSJ" name="RS2320CCSJ" class="star-input-sd" value="0">
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="RS2320YT" name="RS2320YT">
									<option value="0">预留</option>
									<option value="1">TDS100</option>
									<option value="2">T188</option>
									<option value="3">MK_301</option>
									<option value="4">SX0201</option>
									<option value="5">YG19</option>
									<option value="6">DJSB</option>
									<option value="7">DSH</option>
									<option value="8">HTYDDB</option>
									<option value="9">WHTGSB</option>
									<option value="10">RHXTSB</option>
								</select>
							</td>
						</tr>
					</table>
					
					<!-- <h5 style="float:left; margin-top:8px;">RS232-1接口配置</h5><br> -->
					<table border="0" class="list-table" style="width:100%; margin-top:5px;">
						<tr>
							<td>波特率:</td>
							<td>
								<select class="star-select-sd" id="RS2321BTL" name="RS2321BTL">
									<option value="0">300</option>
									<option value="1">600</option>
									<option value="2">1200</option>
									<option value="3">2400</option>
									<option value="4">4800</option>
									<option value="5">9600</option>
									<option value="6">14400</option>
									<option value="7">19200</option>
									<option value="8">38400</option>
									<option value="9">56000</option>
									<option value="10">57600</option>
									<option value="11" selected="selected">115200</option>
								</select>
							</td>
							<td>数据位:</td>
							<td>
								<select class="star-select-sd" id="RS2321SJW" name="RS2321SJW">
									<option value="0">8N1</option>
									<option value="1">8E1</option>
									<option value="2">8O1</option>
									<option value="3">7O1</option>
									<option value="4">7E1</option>
									<option value="5">7N1</option>
								</select>
							</td>
							<td>采集间隔:</td>
							<td>
								<input type="text" id="RS2321CJJG" name="RS2321CJJG" class="star-input-sd" value="0">
							</td>
						</tr>
						<tr>
							<td>存储时间(分钟):</td>
							<td>
								<input type="text" id="RS2321CCSJ" name="RS2321CCSJ" class="star-input-sd" value="0">
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="RS2321YT" name="RS2321YT">
									<option value="0">预留</option>
									<option value="1">TDS100</option>
									<option value="2">T188</option>
									<option value="3">MK_301</option>
									<option value="4">SX0201</option>
									<option value="5">YG19</option>
									<option value="6">DJSB</option>
									<option value="7">DSH</option>
									<option value="8">HTYDDB</option>
									<option value="9">WHTGSB</option>
									<option value="10">RHXTSB</option>
								</select>
							</td>
						</tr>
					</table>
					
					<!-- <h5 style="float:left; margin-top:8px;">RS485-0接口配置</h5><br> -->
					<table border="0" class="list-table" style="width:100%; margin-top:5px;">
						<tr>
							<td>波特率:</td>
							<td>
								<select class="star-select-sd" id="RS4850BTL" name="RS4850BTL">
									<option value="0">300</option>
									<option value="1">600</option>
									<option value="2">1200</option>
									<option value="3">2400</option>
									<option value="4">4800</option>
									<option value="5" selected="selected">9600</option>
									<option value="6">14400</option>
									<option value="7">19200</option>
									<option value="8">38400</option>
									<option value="9">56000</option>
									<option value="10">57600</option>
									<option value="11">115200</option>
								</select>
							</td>
							<td>数据位:</td>
							<td>
								<select class="star-select-sd" id="RS4850SJW" name="RS4850SJW">
									<option value="0">8N1</option>
									<option value="1">8E1</option>
									<option value="2">8O1</option>
									<option value="3">7O1</option>
									<option value="4">7E1</option>
									<option value="5">7N1</option>
								</select>
							</td>
							<td>采集间隔:</td>
							<td>
								<input type="text" id="RS4850CJJG" name="RS4850CJJG" class="star-input-sd" value="5">
							</td>
						</tr>
						<tr>
							<td>存储时间(分钟):</td>
							<td>
								<input type="text" id="RS4850CCSJ" name="RS4850CCSJ" class="star-input-sd" value="5">
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="RS4850YT" name="RS4850YT">
									<option value="0">预留</option>
									<option value="1">TDS100</option>
									<option value="2" selected="selected">T188</option>
									<option value="3">MK_301</option>
									<option value="4">SX0201</option>
									<option value="5">YG19</option>
									<option value="6">DJSB</option>
									<option value="7">DSH</option>
									<option value="8">HTYDDB</option>
									<option value="9">WHTGSB</option>
									<option value="10">RHXTSB</option>
								</select>
							</td>
						</tr>
					</table>
					
					<!-- <h5 style="float:left; margin-top:8px;">RS485-1接口配置</h5> -->
					<table border="0" class="list-table" style="width:100%; margin-top:5px;">
						<tr>
							<td>波特率:</td>
							<td>
								<select class="star-select-sd" id="RS4851BTL" name="RS4851BTL">
									<option value="0">300</option>
									<option value="1">600</option>
									<option value="2">1200</option>
									<option value="3">2400</option>
									<option value="4">4800</option>
									<option value="5" selected="selected">9600</option>
									<option value="6">14400</option>
									<option value="7">19200</option>
									<option value="8">38400</option>
									<option value="9">56000</option>
									<option value="10">57600</option>
									<option value="11">115200</option>
								</select>
							</td>
							<td>数据位:</td>
							<td>
								<select class="star-select-sd" id="RS4851SJW" name="RS4851SJW">
									<option value="0">8N1</option>
									<option value="1">8E1</option>
									<option value="2">8O1</option>
									<option value="3">7O1</option>
									<option value="4">7E1</option>
									<option value="5">7N1</option>
								</select>
							</td>
							<td>采集间隔:</td>
							<td>
								<input type="text" id="RS4851CJJG" name="RS4851CJJG" class="star-input-sd" value="5">
							</td>
						</tr>
						<tr>
							<td>存储时间(分钟):</td>
							<td>
								<input type="text" id="RS4851CCSJ" name="RS4851CCSJ" class="star-input-sd" value="5">
							</td>
							<td>用途:</td>
							<td>
								<select class="star-select-sd" id="RS4851YT" name="RS4851YT">
									<option value="0">预留</option>
									<option value="1">TDS100</option>
									<option value="2">T188</option>
									<option value="3">MK_301</option>
									<option value="4">SX0201</option>
									<option value="5" selected="selected">YG19</option>
									<option value="6">DJSB</option>
									<option value="7">DSH</option>
									<option value="8">HTYDDB</option>
									<option value="9">WHTGSB</option>
									<option value="10">RHXTSB</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				<!-- 串口设置 end -->
				<!-- 基础参数start -->
				<div id="con_one_6" style="display:none;">
					<table border="0" class="list-table" style="width:100%; margin-top:25px;">
						<tr>
							<td>设备ID(固定10位):</td>
							<td>
								<input type="text" id="SBID" name="SBID" value="0000000001" class="star-input-sd" >
							</td>
							<td>机井行政码(12位):</td>
							<td>
								<input type="text" id="JJXZM" name="JJXZM" value="000000000001"  class="star-input-sd" >
							</td>
						</tr>
						<tr>
							<td>电话号码(最多20位):</td>
							<td>
								<input type="text" id="DHHM" name="DHHM" value="13812345678"  class="star-input-sd" >
							</td>
							<td>机井水管码(11位):</td>
							<td>
								<input type="text" id="JJSGM" name="JJSGM" value="00000000001" class="star-input-sd" >
							</td>
						</tr>
					</table>
				</div>
				<!-- 基本设置end -->
				
				<!-- 网络设置 start -->
				<div id="con_one_7" style="display:none;">
					<!-- <h4 style="float:left; margin-top:8px;">服务器地址与端口</h4> -->
						<table border="0" class="list-table" style="width:100%; margin-top:5px;">
							<tr>
								<td>主中心地址:</td>
								<td>
									<input type="text" id="ZXDZ" name="ZXDZ" value="000.000.000.000" class="star-input-sd" >
								</td>
								<td>端口号:</td>
								<td>
									<input type="text" id="DKH" name="DKH" value="0000" class="star-input-sa" >
								</td>
							</tr>
							<tr>
								<td>备中心地址:</td>
								<td>
									<input type="text" id="BZXDZ" name="BZXDZ" value="000.000.000.000" class="star-input-sd" >
								</td>
								<td>端口号:</td>
								<td>
									<input type="text" id="BDKH" name="BDKH" value="0000" class="star-input-sa" >
								</td>
							</tr>
						</table>
					<!-- <h4 style="float:left; margin-top:8px;">域名解析DNS参数</h4> -->
						<table border="0" class="list-table" style="width:100%; margin-top:5px;">
							<tr>
								<td>主中心域名服务器地址</td>
								<td><input type="text" id="ZYM" name="ZYM" value=""  class="star-input-sd" ></td>
								<td>备中心域名服务器地址</td>
								<td><input type="text" id="BYM" name="BYM" value="" class="star-input-sd" ></td>
								<td>GPRS工作模式:</td>
								<td>
								<select class="star-input-sa" id="GPRSG" name="GPRSG">
									<option value="0">TCP</option>
									<option value="1">UDP</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>心跳时间(单位s)</td>
								<td><input type="text" id="XTSJ" name="XTSJ" value="" class="star-input-sd" ></td>
								<td>时段报(单位分钟)</td>
								<td><input type="text" id="SDB" name="SDB" value="" class="star-input-sd" ></td>
								<td>增量报(单位吨)</td>
								<td><input type="text" id="ZLB" name="ZLB" value="" class="star-input-sa" ></td>
							</tr>
						</table>
				</div>
				<!-- 网络设置end -->			
				<!-- 存储设置 start -->
				<div id="con_one_8" style="display:none;">
					<!-- <h5 style="float:left; margin-top:8px;">存储配置</h5> -->
					<table border="0" class="list-table" style="width:100%; margin-top:5px;">
						<tr>
							<td>计数器0存储长度:</td>
							<td>
								<input type="text" id="JSQ0CD" name="JSQ0CD"  value="0"  class="star-input-sd" >
							</td>
							<td>RS232-0存储长度:</td>
							<td>
								<input type="text" id="RS2320" name="RS2320" value="0" class="star-input-sd" >
							</td>
						</tr>
						<tr>
							<td>计数器1存储长度:</td>
							<td>
								<input type="text" id="JSQ1CD" name="JSQ1CD" value="0" class="star-input-sd" >
							</td>
							<td>RS232-1存储长度:</td>
							<td>
								<input type="text" id="RS2321" name="RS2321" value="0"  class="star-input-sd" >
							</td>
						</tr>
						<tr>
							<td>计数器2存储长度:</td>
							<td>
								<input type="text" id="JSQ2CD" name="JSQ2CD" value="0" class="star-input-sd" >
							</td>
							<td>RS485-0存储长度:</td>
							<td>
								<input type="text" id="RS4850" name="RS4850" value="0" class="star-input-sd" >
							</td>
						</tr>
						<tr>
							<td>计数器3存储长度:</td>
							<td>
								<input type="text" id="JSQ3CD" name="JSQ3CD" value="0" class="star-input-sd" >
							</td>
							<td>RS485-1存储长度:</td>
							<td>
								<input type="text" id="RS4851" name="RS4851" value="0" class="star-input-sd" >
							</td>
						</tr>
						<tr>
							<td>刷卡记录存储长度:</td>
							<td>
								<input type="text" id="SKJL" name="SKJL" value="0" class="star-input-sd" >
							</td>
							<td>擦除记录:</td>
							<td>
								<select class="star-input-sd" id="CCJU" name="CCJU">
									<option value="0">不擦除</option>
									<option value="1">擦除</option>
							</td>
						</tr>
					</table>
				</div>
				<!-- 存储设置end -->	
				<!-- 基础参数end -->
			</div>
		</div>
		<!-- 数据提交按钮 -->
		<input type="button" id="com_btn" name="com_btn" value="保存参数" onclick="commitRemoteInfo('${deviceCode}')">
		<span style="font-weight:600">|</span>
		<input type="button" id="com_btn" value="回读参数" onclick="readSerialData('${deviceCode}')">
	</div>
</body>
<script type="text/javascript">
	var name_0 = 'one';
	var cursel_0 = 1;
	var links_len;
	
	$(function(){
		var links = document.getElementById("remoteTab").getElementsByTagName('li');
		links_len = links.length;
		setTab(name_0, cursel_0);
		
		/* 一分钟定时 取消禁用按钮 */
		setTimeout(enableRemoteBtn(), 60000);
	});
	
	function setTab(name, cursel) {
		cursel_0 = cursel;
		for (var i = 1; i <= links_len; i++) {
			var menu = document.getElementById(name + i);
			var menudiv = document.getElementById("con_" + name + "_" + i);
			if (i == cursel) {
				menu.className = "off";
				menudiv.style.display = "block";
			} else {
				menu.className = "on";
				menudiv.style.display = "none";
			}
		}
	}
</script>
</html>