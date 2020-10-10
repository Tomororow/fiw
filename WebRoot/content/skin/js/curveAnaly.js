
var caTreeObj;

$(function() {
	getGroupTree();
});

//获取行政树
function getGroupTree() {
	$(".tree").height($(".left").outerHeight()- 10);
	caTreeObj = ztreeFun($("#ztree"),"stAddvcdD/addvcdDInfo.do?isSearchDevice=true",treeOnClick,treeInitComplete);
}

function treeInitComplete(event, treeId, treeNode){
	
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var node = treeObj.getNodeByParam("isDevice", true);
	
	if(node != null){
		treeObj.selectNode(node);
		//默认点击第一个节点
	   treeObj.setting.callback.onClick(null, treeObj.setting.treeId, node);
//	   searchCurve();
	}else{
	   //获取节点
	   var nodes = treeObj.getNodes();
	   if (nodes.length>0) 
	   {
		   treeObj.selectNode(nodes[0]);
		   //默认点击第一个节点
		   treeObj.setting.callback.onClick(null, treeObj.setting.treeId, nodes[0]);
	   }
	}
}

function treeOnClick(treeNode) {
	
//	if(treeNode.isDevice){
//		$("#caDiv #factorType").show();
//		$("#caDiv #factorType_disable").hide();
//		$("#caDiv .operate input[type='button']").removeClass("disabled");
//	}else{
//		$("#caDiv #factorType_disable").show();
//		$("#caDiv #factorType").hide();
//		$("#caDiv .operate input[type='button']").addClass("disabled");
//	}
	searchCurve();
	
}

/**
 * 查询
 */
function searchCurve() {

	var factorType = $("#factorType").val();
	var params = {factorType:factorType};
	var searchType = $("#searchType").val();
	
	var selectdNode = caTreeObj.getSelectedNodes()[0];
	if(!selectdNode.isDevice){
		$.Popup.create({ title : "提示", content : "请选择树中的测站!" });
		return;
	}
	
	if(searchType==0){
		//时段
		var query_beginTime = $("#raw_query_beginTime").val();
		var query_endTime = $("#raw_query_endTime").val();

		if (query_beginTime != "" || query_endTime != "") {
			if (query_beginTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入开始时间!"
				});
				return;
			}
			if (query_endTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入结束时间!"
				});
				return;
			}
		}
		params['query_beginTime'] = query_beginTime;
		params['query_endTime'] = query_endTime;
		
	}else if(searchType==1){
		//时段
		var query_beginTime = $("#query_beginTime").val();
		var query_endTime = $("#query_endTime").val();

		if (query_beginTime != "" || query_endTime != "") {
			if (query_beginTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入开始时间!"
				});
				return;
			}
			if (query_endTime == "") {
				$.Popup.create({
					title : "提示",
					content : "请输入结束时间!"
				});
				return;
			}
		}
		params['query_beginTime'] = query_beginTime;
		params['query_endTime'] = query_endTime;
	}else{
		//日月年
		var query_Time = $(".query_Time:visible").val();
		if (query_Time == "") {
			$.Popup.create({
				title : "提示",
				content : "请输入查询时间"
			});
			return;
		}
		params['query_Time'] = query_Time;
	}
	var aryIds = getNodeIdsByTreeId("ztree");
	params['nodeIds'] = aryIds.toString();
	params['searchType'] = searchType;
	showMark();
	$.getJSON("curveAnaly/getChartsData.do", params, function(data) {
		hideMark();
		var xData=[];
        var dataList = data.dataList;
        if(searchType=='0'){
        	for (var i = 0; i < dataList.length; i++) {
        		var newDate=new Date(dataList[i].tm);
//     	   xData.push(newDate.getHours()+":"+newDate.getMinutes());
        		if(factorType=='water' && dataList[i].water!=undefined){
        			xData.push([newDate,dataList[i].water]);
        		}else if(factorType=='flowratepers' && dataList[i].flowratepers!=undefined){
        			xData.push([newDate,dataList[i].flowratepers]);
        		}else if(factorType=='flowrateperh' && dataList[i].flowrateperh!=undefined){
        			xData.push([newDate,dataList[i].flowrateperh]);
        		}else if(factorType=='voltage' && dataList[i].voltage!=undefined){
        			xData.push([newDate,dataList[i].voltage]);
        		}else if(factorType=='signalinten' && dataList[i].signalinten!=undefined){
        			xData.push([newDate,dataList[i].signalinten]);
        		}else if(factorType=='flowrateadd' && dataList[i].flowrateadd!=undefined){
        			xData.push([newDate,dataList[i].flowrateadd]);
        		}
        	}
        	showReport(xData,data.xAxi);
        }else{
        	for (var i = 0; i < dataList.length; i++) {
        		xData.push();
        	}
        	showReport(data.dataList,data.xAxi);
        }
		// loadFactorList();
	});
}

function showReport(xData,xAxi)
{
	if(xData.length==0){
		$("#eChartsMain").hide();
		$("#nodata").css("line-height",$("#nodata").css("height"));
		$("#nodata").show();
	}else{
		$("#eChartsMain").show();
		$("#nodata").hide();
		
		var searchType = $("#searchType").val();
		var selectdNode = caTreeObj.getSelectedNodes()[0];
		var name = selectdNode.name;
		require(
	            [
	                'echarts',
	                'echarts/theme/macarons',
	                'echarts/chart/line',
	                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	            ],
	            function (echarts) {
	                // 基于准备好的dom，初始化echarts图表
	            	var myChart = echarts.init(document.getElementById('eChartsMain'));

	            	var unit = $("#factorType").find("option:selected").attr("unit");
	            	var yAxisName = $("#factorType").find("option:selected").text() + (unit==undefined || unit=="" ?"":"(" + unit +")");
	            	
	            	var option = {
	            		    title : {
	            		        text : '【' + name +'】' + $("#factorType").find("option:selected").text() + $("#searchType").find("option:selected").text(),
	            		        subtext : ''
	            		    },
	            		    tooltip : {
	            		        trigger: 'item',//item|axis
	            		        formatter : function (params) {
	            		        	//console.log(params);
	            		        	var str =  "名称：" + name +'<br/>';
	            		        	if(searchType=='0'){
	            		        		var date = new Date(params.value[0]);
	            		        		str =  str + "时间：" + date.Format("yyyy-MM-dd HH:mm:ss") + '<br/>' +
			        		                   "量值：" + params.value[1];
	            		        	}else{
	            		        		
	            		        		str =  str + "时间：" + params[1] + '<br/>' +
		        		                   "量值：" + params.value;
	            		        	}
	            		            return str;
	            		        },  
	            		        axisPointer:{
	            		            show: true,
	            		            type : 'shadow',//line
	            		            lineStyle: {
	            		                type : 'dashed',
	            		                width : 1
	            		            }
	            		        }
	            		    },
//	            		    calculable:false,
	            		    toolbox: {
	            		        show : true,
	            		        feature : {
	            		        	dataZoom : {show: true},
	            		        	magicType: {
	                                    show : true,
	                                    type: ['line', 'bar']
	                                },
//	            		        	mark : {show: true},
//	            		            dataView : {show: true, readOnly: false},
	            		            restore : {show: true},
	            		            saveAsImage : {show: true}
	            		        },
	            		        padding : [5,20,5,5]
	            		    },
	            		    dataZoom: {
	            		        show: true,
	            		        start : 0,
	            		        end :25
	            		    },
	            		    legend : {
	            		        data : [$("#factorType").find("option:selected").text()]
	            		    },
	            		    grid: {
	            		    	//x2:180,
	            		        y2: 80
	            		    },
	            		    xAxis : [
	            		         xAxi
	            		    ],
	            		    yAxis : [
	            		        {
	            		        	name : yAxisName,
	            		            type : 'value',
	            		            //splitNumber:5,//y轴分割段数
	            		            min:0,
	            		            //scale : true//脱离0值比例，放大聚焦到最终_min，_max区间
	            		        }
	            		    ],
	            		    noDataLoadingOption:{
	            		    	text:' ',//暂无数据 ie8闪烁
	            		        textStyle:{
	            		            fontFamily:'微软雅黑',
	            		            fontSize:14
	            		        },
	            		    	effect:'bubble',
	            		    	effectOption: {
		                            effect: {
		                                n: 0
		                            }
	            		    	}
	            		    },
	            		    series : [
	            		        {
	            		        	showAllSymbol: true,
	            		        	//symbolSize:3,
	            		            name: $("#factorType").find("option:selected").text(),
	            		            type: 'line',
	            		            //smooth:true,
	            		            //barWidth:15,
	            		            itemStyle:{
	            		                normal:{color:'#40b5d6'/*,barBorderRadius: [5,5,0,0]*/},
			            		        emphasis: { /*barBorderRadius: [5,5,0,0]*/}
	            		            },
	            		            data: xData
	            		        }
	            		    ]
	            		};
	            	myChart.setOption(option);
	            }
	        );	
	}
	
	
}

function showArea(obj){
	$(obj).parent("div").nextAll("div").hide();
	$("#" + $(obj).find("option:selected").attr("area")).show();
}

Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));

    str = str.replace(/MM/, this.getMonth() > 8 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
    str = str.replace(/M/g, (this.getMonth() + 1));

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}
