<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta http-equiv="x-ua-compatible" content="ie=9"/>
  <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
  <title>机井灌溉云平台大数据展示</title>
  <link rel="icon" type="image/x-icon" href="${ctx}/content/skin/css/images/jzlogo.ico" />
  <link href="${ctx}/content/skin/bigData/css/bigDataPage.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="${ctx}/content/skin/adapters/element-ui/theme-chalk/index.css">
  <script type="text/javascript" src="${ctx}/content/skin/adapters/Jquery/jquery-1.8.0.js"></script>
  <script type="text/javascript" src="${ctx}/content/skin/adapters/moment/moment.js"></script>
  <script type="text/javascript" src="${ctx}/content/skin/adapters/vue/vue.js"></script>
  <script src="${ctx}/content/skin/adapters/element-ui/index.js"></script>
  <script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/echarts.js"></script>
  <script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/echarts-liquidfill.js"></script>
  <script type="text/javascript" src="${ctx}/content/skin/adapters/echarts/china.js"></script>
</head>
<body>
	<div id="bigData">
		<!--=== header头部模块设计  ===-->
		<div class="header">
			<ul>
				<li>{{nowTime}}<i style="margin-left: 8px;">{{week}}</i></li>
				<li class="gradient-text">{{(title)+('地下水资源管理系统')}}</li>
				<li>
					<img id="weatherImg" v-if="weatherImg!=''" v-bind:src="('${ctx}/content/skin/bigData/images/')+(weatherImg)+('.png')"/>{{weather}}
				</li>
				<div style="clear: both"></div>
			</ul>
			<div class="enterSystem" onclick="window.location.href='login.do'">
				<span>进入应用>> </span><span id="go">GO</span>
			</div>
		</div>
		<!--=== content模块  ===-->
		<div class="content">
			<ul>
				<!-- 上边一部分 -->
				<div>
					<!-- 取水总量和红线对比 -->
					<li>
						<!-- 头部模块设置 -->
						<div style="display:flex;">
							<div class="topSign"><span>取水总量和红线对比</span></div>
							<div style="height:20px;margin-left: 100px;">
								<template>
								  <el-select v-model="querySumRedVal" id="elSelect" @change="handleQueryClick('001');"  placeholder="请选择">
								    <el-option v-for="item in waterList" :key="item.waterAreaName" :label="item.waterAreaName" :value="item.waterAreaName"></el-option>
								  </el-select>
								</template>
							</div>
							 <el-button id="query" @click="handleQueryClick('001');" icon="el-icon-search" circle></el-button>
						</div>
						<div id="querCotnet">
							<div><span>{{querySumRedVal}}</span></div>
							<div align="center">
								<table width="100%" height="100%">
									<tr>
										<td>
											<div id="saleRedLine" style="width: 100%;height:100%;"></div>
										</td>
										<td>
											<div id="saleSum" style="width: 100%;height:100%;"></div>
										</td>
									</tr>
									<tr>
										<td><span class="el-icon-star-on"></span>{{redSum.waterTypeName}} | <span style="color: #ffbc00">指标：</span>{{redSum.waterTypetarget}}(万m³)</td>
										<td><span class="el-icon-star-on"></span>取水总量 | <span style="color: #ffbc00">总指标：</span>{{redSum.sumWater}}(万m³)</td>
									</tr>
								</table>
							</div>
							<div id="queRump" style="overflow: auto">
								<table width="100%" border="0" style="height: 130%;table-layout:fixed">
									<%--<tr>
										<td><span ></span>工业用水</td>
										<td>
											<div id="parcessOne"></div>
                                            <div id="IndustryOne"><span>{{(querySumRedData['waterIndustry']/10000).toFixed(2)}}</span><span>(万m³)</span></div>
										</td>
									</tr>
									<tr>
										<td><span></span>生活用水</td>
										<td>
											<div id="parcessTwo"></div>
                                            <div id="LifeTwo"><span>{{(querySumRedData['waterLife']/10000).toFixed(2)}}</span><span>(万m³)</span></div>
										</td>
									</tr>--%>
                                    <tr>
                                        <td><span></span>工业用水</td>
                                        <td></td>
                                        <td>{{(querySumRedData['waterIndustry']/10000).toFixed(2)}}</td>
                                        <td width="10%" style="color:#00ffff;font-size: 12px; ">
                                            <span>(万m³)</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><span></span>生活用水</td>
                                        <td></td>
                                        <td>{{(querySumRedData['waterLife']/10000).toFixed(2)}}</td>
                                        <td width="10%" style="color:#00ffff;font-size: 12px; ">
                                            <span>(万m³)</span>
                                        </td>
                                    </tr>
									<tr>
										<td><span></span>绿化用水</td>
										<td></td>
                                        <td>{{(querySumRedData['waterVirest']/10000).toFixed(2)}}</td>
                                        <td width="10%" style="color:#00ffff;font-size: 12px; ">
                                            <span>(万m³)</span>
                                        </td>
									</tr>
                                    <tr>
                                        <td><span></span>绿化用水</td>
                                        <td></td>
                                        <td>{{(querySumRedData['waterFarming']/10000).toFixed(2)}}</td>
                                        <td width="10%" style="color:#00ffff;font-size: 12px; ">
                                            <span>(万m³)</span>
                                        </td>
                                    </tr>
									<%--<tr>
										<td><span></span>农业用水</td>
										<td>
											<div id="parcessFour"></div>
											<div id="FarmingFour"><span>{{(querySumRedData['waterFarming']/10000).toFixed(2)}}</span><span>(万m³)</span></div>
										</td>
									</tr>--%>
								</table>
							</div>
						</div>
					</li>
					<!-- 配水计划 -->
					<li>
						<table id="topCenter" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<!-- 头部模块设置 -->
									<div style="display:flex;padding-right: 20px;">
										<div class="topSign" style="width:40%"><span>配水计划</span></div>
										<!-- <div style="height:20px;margin-left: 80px;width: 150px;">
											<template>
											  <el-select v-model="queryVal" id="elSelect"  placeholder="请选择">
											    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
											  </el-select>
											</template>
										</div>
										 <el-button id="query" @click="handleQueryClick('001');" icon="el-icon-search" circle></el-button> -->
									</div>
									<div id="planContent">
										<ul>
											<li id="planCess" v-for="(plan,index) in planWaterList">
												<div><span>{{plan.waterAreaName}}</span></div>
												<div id="processPlan"><!--  -->
													<div align="right" v-bind:style="{width: (plan.waterSum/planWaterSum)*100+('%')}"></div>
													<span v-bind:style="{color:(plan.waterSum/planWaterSum==1?'#000':'#00ff14')}" style="font-size:12px;font-style:italic;position: absolute;top: 0;right: 5px;">{{plan.waterSum}}m³</span>
												</div>
											</li>
										</ul>
									</div>
								</td>
								<td>
									<!-- 头部模块设置 -->
									<div style="display:flex;padding-right: 20px;">
										<div class="topSign" style="width:30%"><span>用水量</span></div>
										<div style="height:20px;margin-left: 80px;width: 150px;">
											<template>
											  <el-select v-model="useWaterAreaName" @change="handleQueryClick('002');" id="elSelect"  placeholder="请选择">
											    <el-option v-for="item in waterList" :key="item.waterAreaName" :label="item.waterAreaName" :value="item.waterAreaName"></el-option>
											  </el-select>
											</template>
										</div>
										 <el-button id="query" @click="handleQueryClick('002');" icon="el-icon-search" circle></el-button>
									</div>
									<div id="useContent">
										<div id="useName">{{useWaterAreaName}}</div>
										<ul>
											<li id="useCess" v-for="(use,index) in useList">
												<div><span>{{use.waterAreaName}}</span></div>
												<div id="processUse">
													<div align="right" v-bind:style="{width: use.useNum}"><span style="color: #21a8bd">{{use.num}}m³</span></div>
												</div>
											</li>
										</ul>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<!-- 头部模块设置 -/* height: 13%; */-->
									<div style="display:flex;">
										<div class="topSign" style="width:30%"><span>已配水量和已用水量比对图</span></div>
										<!-- <div style="height:20px;margin-left: 100px;">
											<template>
											  <el-select v-model="queryVal" id="elSelect"  placeholder="请选择">
											    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
											  </el-select>
											</template>
										</div>
										 <el-button id="query" @click="handleQueryClick('001');" icon="el-icon-search" circle></el-button> -->
									</div>
									<div id="aPlanContent">
										<div id="aPlanLine" style="width: 100%;height:100%;"></div>
									</div>
								</td>
							</tr>
						</table>
					</li>
					<!-- 用水量曲线图 -->
					<li>
						<!-- 头部模块设置 -/* height: 13%; */-->
						<div style="display:flex;">
							<div class="topSign" style="width:20%"><span>用水量曲线图</span></div>
							<div style="height:20px;margin-left: 100px;">
								<template>
								  <el-select v-model="useWaterLine" id="elSelect" @change="handleQueryClick('003');"  placeholder="请选择">
									<el-option v-for="item in waterList" :key="item.waterAreaName" :label="item.waterAreaName" :value="item.waterAreaName"></el-option>
								  </el-select>
								</template>
							</div>
							 <el-button id="query" @click="handleQueryClick('003');" icon="el-icon-search" circle></el-button>
						</div>
						<div id="stackAreaName">
							<div align="right"><span>{{useWaterLine}}</span></div>
							<ul>
								<li v-bind:style="{color:useWaterSign=='0'?'#63d2d2':'#146fc7'}" style="font-family: '宋体';cursor: pointer;" @click="handleUseWater(0)">日取水量曲线图</li>
								<li style="margin:-15px 10px 0 10px"><img src="${ctx}/content/skin/bigData/images/verline.png"/></li>
								<li v-bind:style="{color:useWaterSign=='1'?'#63d2d2':'#146fc7'}" style="font-family: '宋体';cursor: pointer;" @click="handleUseWater(1)">月取水量曲线图</li>
							</ul>
							<div id="stackContent">
								<div>
									<div id="stackDayLine" v-if="useWaterSign=='0'" style="width: 100%;height:100%;"></div>
									<div id="stackMonthLine" v-if="useWaterSign=='1'" style="width: 100%;height:100%;"></div>
								</div>
								<div>
									<div id="stackBar" style="width: 100%;height:100%;"></div>
								</div>
							</div>
						</div>
					</li>
					<div class="clear"></div>
				</div>
				<!-- 下边一部分 -->
				<div>
					<!-- 计量设施的运行情况 -->
					<li>
						<!-- 头部模块设置 -/* height: 13%; */-->
						<div style="display:flex;">
							<div class="topSign" style="width:30%"><span>计量设施运行情况</span></div>
							<div style="height:20px;margin-left: 100px;">
								<template>
									<el-select v-model="deviceDetail" id="elSelect" @change="handleQueryClick('005');"  placeholder="请选择">
										<el-option v-for="item in waterList" :key="item.waterAreaName" :label="item.waterAreaName" :value="item.waterAreaName"></el-option>
									</el-select>
								</template>
							</div>
							<el-button id="query" @click="handleQueryClick('005');" icon="el-icon-search" circle></el-button>
						</div>
						<div id="deviceSign">
                            <div>
                                <table>
                                    <tr>
                                        <td :style="{color:deviceType=='0'?'rgb(101, 210, 210)':'#009de6'}" @click="handleDeType(0)">地下水计量</td>
                                        <td :style="{color:deviceType=='0'?'#009de6':'rgb(101, 210, 210)'}" @click="handleDeType(1)">工业机井计量</td>
                                    </tr>
                                </table>
                            </div>
							<div style="height: 8%" v-if="deviceType=='0'">
								<table>
									<thead>
										<tr>
											<td>设备名称</td><td>设备状态</td><td>开关泵状态</td><td>已用水量(m³)</td><td>通讯时间</td>
										</tr>
									</thead>
								</table>
							</div>
							<div id="deviceList" v-if="deviceType=='0'">
								<table>
									<tbody>
										<tr v-for="(tab,index) in deviceList.deviceList">
											<td>{{tab.deviceName}}</td>
											<td v-bind:style="{color:tab.netStatek=='0'?'red':'#00ff00'}">{{tab.netStatek=='1'?'在线':'离线'}}</td>
											<td v-bind:style="{color:tab.pumpState=='0'?'#ffbe05':'#fff'}">{{tab.pumpState=='1'?'开泵':'关泵'}}</td>
											<td>{{tab.totalwater}}</td><td>{{tab.commTimee}}</td>
										</tr>
									</tbody>
								</table>
							</div>
                            <div style="height: 8%" v-if="deviceType=='1'">
                                <table>
                                    <thead>
                                    <tr>
                                        <td>设备名称</td><td>设备状态</td><td>瞬时流量(m³)</td><td>正向累积值</td><td>上报时间</td>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                            <div id="deviceList" v-if="deviceType=='1'">
                                <table>
                                    <tbody>
                                    <tr v-for="(tab,index) in deviceListIndety">
                                        <td>{{tab.devicename}}</td>
                                        <td v-bind:style="{color:tab.backtwo=='1'?'#00ff00':'red'}">{{tab.backtwo=='1'?'在线':'离线'}}</td>
                                        <td>{{tab.instantflow==null?'--':Number(tab.instantflow)}}</td>
                                        <td>{{tab.positivetotal==null?'--':Number(tab.positivetotal)}}</td>
										<td>{{tab.upTime==null?'--':tab.upTime}}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
						</div>
					</li>
					<!-- 地图部分 -->
					<li>
						<!-- 头部模块设置 -/* height: 13%; */-->
						<div style="display:flex;">
							<div class="topSign" style="width:20%">
								<span>站点分布</span>
							</div>
						</div>
						<div id="mapShow" style="position: relative;">
							<!-- <div id="mapSign">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>在线</td><td><div class="mapSdty"></div></td>
									</tr>
									<tr>
										<td>离线</td><td><div class="mapSdty"></div></td>
									</tr>
								</table>
							</div> -->
							<div id="mapEcharts" style="width: 100%;height:100%;"></div>
						</div>
					</li>
					<!-- 预警模块 -->
					<li>
						<!-- 头部模块设置 -/* height: 13%; */-->
						<div style="display:flex;">
							<div class="topRedSign" style="width:20%">
								<span>预警监测</span>
								<img src="${ctx}/content/skin/bigData/images/warning.png"/>
							</div>
							<div style="height:20px;margin-left: 100px;">
								<template>
									<el-select v-model="warnVal" id="elSelect" @change="handleQueryClick('006');"  placeholder="请选择">
										<el-option v-for="item in waterList" :key="item.waterAreaName" :label="item.waterAreaName" :value="item.waterAreaName"></el-option>
									</el-select>
								</template>
							</div>
							<el-button id="query" @click="handleQueryClick('006');" icon="el-icon-search" circle></el-button>
						</div>
						<div id="warnSign">

							<div style="height: 8%">
								<table>
									<thead>
										<tr>
											<td>设备名称</td><td>负责人电话</td><td>告警类型</td><td>告警状态</td><td>告警时间</td>
										</tr>
									</thead>
								</table>
							</div>
							<div id="warnTable" style="height: 92%;overflow-y:scroll">
								<table>
									<tbody>
										<tr v-for="(tab,index) in warnList">
											<td>{{tab.deviceName}}</td><td>{{tab.ownerTelphone==null||tab.ownerTelphone==''?'--':tab.ownerTelphone}}</td><td>{{tab.warnType}}</td><td>{{tab.warnState=='1'?'已处理':'未处理'}}</td><td>{{tab.warnTimee}}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</li>
					<div class="clear"></div>
				</div>
			</ul>
		</div>
		<!-- 监听按键按下弹出对话框修改相关参数 -->
		<el-dialog title="配置相关参数" :visible.sync="dialogVisible" :close-on-click-modal="false"  width="50%">
	  			<el-form :model="ruleForm" :inline="true" label-width="120px" size="medium" class="demo-form-inline">
				  <el-form-item label="总指标" prop="sumWater" required>
				    <el-input v-model="ruleForm.sumWater"><template slot="append">万m³</template></el-input>
				  </el-form-item>
				  <el-form-item label="农业用水指标" prop="waterFarming" required>
				    <el-input v-model="ruleForm.waterFarming"><template slot="append">万m³</template></el-input>
				  </el-form-item>
				  <el-form-item label="工业用水指标" prop="waterIndustry" required>
				    <el-input v-model="ruleForm.waterIndustry"><template slot="append">万m³</template></el-input>
				  </el-form-item>
				  <el-form-item label="生活用水指标" prop="waterLife" required>
				    <el-input v-model="ruleForm.waterLife"><template slot="append">万m³</template></el-input>
				  </el-form-item>
				  <el-form-item label="绿化用水指标" prop="waterVirest" required>
				    <el-input v-model="ruleForm.waterVirest"><template slot="append">万m³</template></el-input>
				  </el-form-item>
				</el-form>
				 <span style="color:#b17200 ">注：请务必填写相关配置参数，以便大数据页面统计数据的准确性！</span>
			  <span slot="footer" class="dialog-footer">
		    	<el-button @click="dialogVisible = false">取 消</el-button>
		    	<el-button type="primary" @click="handleSubMit(0)">提交参数</el-button>
			  </span>
		</el-dialog>
	</div>
</body>
<script type="text/javascript" src="${ctx}/content/skin/bigData/js/ganzhou.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/bigData/js/bigDataPage.js"></script>
<script type="text/javascript" src="${ctx}/content/skin/bigData/js/bigDataElse.js"></script>

</html>




