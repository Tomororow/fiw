var scrollTimer;
var redSumInterval;
var inval = 0;//定义定时器的计数器
var saleRedLineEcharts;//农业、工业、生活、绿化等取水模块
var saleSumEcharts;//用水总量模块
var aPlanLineEcharts;//已配水量和已用水量模块
var stackDayEcharts;//用水量日折线图
var stackMonthEcharts;//用水量月折线图
var stackBarEcharts;//用水量柱状图
var mapEcharts;//地图Echarts
var bigData = new Vue({
	el:'#bigData',
	data:{
		dialogVisible:false,
		nowTime:'',//顶部时间
		week:'',//星期几
		/*title:'张掖市甘州区',//标题*/
		title:'额济纳旗',//标题
		clockTime:null,//时间定时器
		/*cityid:'620702',//张掖市的行政区ID*/
		cityid:'152923',//额济纳旗的行政区ID
		weather:'',//顶部右上角天气
		weatherImg:'',//天气的各种图片
		waterList:[],//水管区域集合
		waterRedList:'10000000',//取水红线
		querySumRedVal: '',//取水红线的数据
		querySumRedData:[{
		       waterSum:0,
		       waterFarming:0,
		       waterVirest:0,
		       waterLife:0,
		       waterIndustry:0,
		}],//取水红线集合
		planWaterSum:0,//配水总量
		planWaterList:[],//各水管所配水的集合
		useWaterAreaName:'',//用水量选择的数据
		useWaterArea:{},//用水量数据的数组
		useWaterLine:'',//用水量曲线
		useWaterLineList:{},//用水量数据的数组
		useWaterSign:0,//区分是否日用（0）水曲线还是月用水曲线（1）
		deviceList:[],//地下水计量设备的基本信息
		deviceListIndety:[],//工业机井计量设备的基本信息
		warnList:[],//预警集合
		warnVal:'',//预警设备
	    echartList:[],//Echarts集合
	    useList:[],//用水量集合
	    ruleForm:[],//取水红线的数组
	    /*圆圈数据*/
	    redSum:{
	        sumWater:'',//取水总量
	  	    waterTypeName:'',//圆圈的用水类型名称
	  	    waterTypetarget:'',//圆圈的用水类型的用水指标
	  	    waterTypeSum:0,//红线数据
	  	    waterTypeRate:0,//占比比率
	    },
		deviceDetail:"",//计量设施运行情况
		deviceType:'0'//0:地下水计量;1:工业机井计量
	},
	methods:{
		/**
		 * 查询方法（公共）
		 */
		handleQueryClick:function(sign){
			var _this = this;
			switch (sign) {
			case '001'://取水红线
				_this.hanPostQuerty("water_red_line",{waterName:_this.querySumRedVal},"querySumRedData","waterRedLine");//取水红线数据集合
				break;
			case '002'://用水量
				_this.hanPostQuerty("use_water",{waterName:_this.useWaterAreaName},"useWaterArea","useWaterArea");//用水量数据集合
				break;
			case '003'://用水量曲线图
				_this.hanPostQuerty("use_water_line",{waterName:_this.useWaterLine,type:_this.useWaterSign},"useWaterLineList","useWaterLine");//用水量曲线图
				break;
			case '004'://各个水管所的配水量
				_this.hanPostQuerty("plan_water","","planList","planWater");//各个水管所的配水量
				break;
			case '005'://计量设施运行情况
				_this.hanPostQuerty("device_List",{waterName:_this.deviceDetail},"deviceList","deviceList");///设备的基本信息
				break;
			case '006'://预警监测
				_this.hanPostQuerty("warn_list",{waterName:_this.warnVal},"warnList","warnList");///设备的基本信息
				break;
			}
		},
		
		/**
		 * 区分日用水还是月用水
		 * 0:日用水;1:月用水
		 */
		handleUseWater:function(sign){
			var _this = this;
			stackDayEcharts.clear();
			_this.useWaterSign = sign;
			_this.handleQueryClick("003");
		},
		/**
		 * 提交配置参数的form表单
		 */
		handleSubMit:function(sign){
			var _this = this;
			if(sign==0){//写入
				$.post("/big_Data/proper_Write.do",{addForm:JSON.stringify(_this.ruleForm)},function(data){
					
					_this.$message({
				          message: '参数设置成功...',
				          type: 'success'
				        });
					_this.dialogVisible = false;
					_this.handleSubMit(1);
				});
			}else if(sign==1){//读取
				$.post("/big_Data/proper_Read.do","",function(data){
					if(data!=""&&data!=null){
						_this.ruleForm = JSON.parse(data);
					}
				});
			}
		},
		/**
		 * post请求的封装方法
		 */
		hanPostQuerty:function(url,data,sign,type){
			var _this = this;
			$.post("/big_Data/"+url+".do",data,function(result){
				if(result!=""&&result!=null){
					_this[sign] = JSON.parse(result);
					switch (type) {
						case 'waterArea'://水管区域的集合数据
						_this.querySumRedVal =_this[sign][0].waterAreaName;//取水总量和红线比对值的选择
						_this.useWaterAreaName = _this[sign][0].waterAreaName;//用水量水管区域值的选择
						_this.useWaterLine = _this[sign][0].waterAreaName;//用水量水管区域值的选择
						_this.deviceDetail = _this[sign][0].waterAreaName;//计量设施运行情况
						_this.warnVal = _this[sign][0].waterAreaName;//预警
						_this.handleQueryClick('001');
						setTimeout(function(){_this.handleQueryClick('002');},300);
						setTimeout(function(){_this.handleQueryClick('003');},500);
						setTimeout(function(){_this.handleQueryClick('004');},700);
						setTimeout(function(){_this.handleQueryClick('005');},900);
						setTimeout(function(){_this.handleQueryClick('006');},1100);
						break;
					case 'waterRedLine'://取水总量和红线对比

						_this.redSum.waterTypeName = "农业用水";
                        _this.redSum.sumWater = _this.converSion(_this.querySumRedVal,"sumWater");
						_this.redSum.waterTypetarget = _this.converSion(_this.querySumRedVal,"waterFarming");/*_this.ruleForm[0].waterFarming;*/
						_this.redSum.waterTypeSum = _this.dataConver(bigData.querySumRedData['waterFarming'])+'m3',
						_this.redSum.waterTypeRate = bigData.querySumRedData['waterFarming']/(_this.redSum.waterTypetarget*10000);
						echartsMethods("saleRedLine",'saleRedLine');
						echartsMethods("saleSum",'saleSum');
						break;
					case 'planWater'://各个水管所的配水量
						_this.planWaterSum = _this[sign]["maxWater"];//总的配水量
						_this.planWaterList = _this[sign]["list"];//各个水管所的配水量集合
						break;
					case 'useWaterArea':
						_this.useList = [];
						var useDayNum = (Number(_this.useWaterArea.dayUseWater)/Number(_this.useWaterArea.sumMonthWater)*100);
						useDayNum = isNaN(useDayNum)?'0':useDayNum.toFixed(0);
						var useMonthNum = Number(_this.useWaterArea.sumMonthWater)/Number(_this.useWaterArea.sumYearWater)*100;
						useMonthNum = isNaN(useMonthNum)?'0':useMonthNum.toFixed(0);
						var useYearNum = Number(_this.useWaterArea.sumYearWater)/Number(_this.useWaterArea.sumWater)*100 ;
						useYearNum = isNaN(useYearNum)?'0':useYearNum.toFixed(0);
						_this.useList.push({waterAreaName:'总用水量：',useNum:_this.useWaterArea.sumWater==0?'0':'100%',num:_this.useWaterArea.sumWater},{waterAreaName:'日用水量：',useNum:useDayNum+"%",num:_this.useWaterArea.dayUseWater},{waterAreaName:'月用水量：',useNum:useMonthNum+"%",num:_this.useWaterArea.sumMonthWater},{waterAreaName:'年用水量：',useNum:useYearNum+"%",num:_this.useWaterArea.sumYearWater});
						echartsMethods("aPlanLine","aPlanLine");
						break;
					case 'useWaterLine':
						var vituy = _this.useWaterSign=="0"?"stackDayLine":"stackMonthLine";
						echartsMethods(vituy,vituy);
						echartsMethods("stackBar","stackBar");
						break;
					case 'deviceList':
						echartsMethods("mapEcharts","mapEcharts");
						break;
					case 'warnList':
						
						break;
					}
				}
			});
		},
		dataConver:function(viyut){
			var _this = this;
			var resukt = 0;
			if(viyut>10000){
				resukt = (viyut/10000).toFixed(2)+"W";
			}
			return resukt
		},

        /**
         *农业,工业，生活用水量转换
         */
        converSion:function(waterAreaName,type){
            var _this = this;
            var waterNum = 0;
            if(_this.waterList.length>0){

                for(var i=0;i<_this.waterList.length;i++){
                   if(_this.waterList[i].waterAreaName==waterAreaName){
                       waterNum = _this.waterList[i][type];
                    }
                }
            }
            return waterNum;
        },
		/**
		 * 计量设施运行情况
		 * （0：地下水；1：工业机井）
		 */
		handleDeType:function(sign){
			var _this = this;
			_this.deviceType = sign;
			if(sign=='1'){
				if(_this.deviceListIndety.length==0){
					$.post("/big_Data/device_List_Indety.do","",function(data){
						if(data!=null&&data!=''){
							debugger
							_this.deviceListIndety = JSON.parse(data);
						}
					});
				}
			}
		},
    },
	mounted:function(){
		var _this = this;
		_this.clockTime = setInterval("refreshClock()",1000);//开启时钟定时器
		var url = 'https://tianqiapi.com/api?version=v6&appid=57276933&appsecret=6963znIW&cityid='+_this.cityid;//和调用天气的API
		$.get(url,function(data){

			_this.weatherImg = data.wea_img;
			_this.weather = "\r\n"+data.wea+"\r\n"+data.win+"\r\n"+data.win_speed+"\r\n"+"气温\r\n"+data.tem+"°"+"\r\n空气质量\r\n"+data.air_level+"\r\nPM2.5:\r\n"+data.air_pm25;
		});
		_this.hanPostQuerty("water_List","","waterList","waterArea");//水管区域集合查询
	},
	created:function(){
		var _this = this;
		_this.handleSubMit(1);
		window.clearInterval(scrollTimer);
		window.clearInterval(redSumInterval);
	}
});

/**
 * 整个页面监听按键按下操作
 */
/*$(document).keydown(function(event){
    if(event.keyCode == 32){
    	bigData.dialogVisible = true;
    }
})*/

function echartsMethods(type,ids){
	colorItem();//调用该方法将color的对象数据拿出去
	switch (type) {
	case 'saleRedLine'://取水红线模块
		saleRedLineEcharts = echarts.init(document.getElementById(ids));
		var value = bigData.redSum.waterTypeRate;
		 var data = [value.toFixed(2), value.toFixed(2), value.toFixed(2),value.toFixed(2)];
		 
		 var option = {
				 title: {
					 top:'20%',
			         //text: (0.8 * 100).toFixed(0) + '{a|%}',
					 text:bigData.redSum.waterTypeSum,
			         textStyle: {
			             fontSize:25,
			             fontFamily: 'yjsz',
			             fontWeight: 'normal',
			             color: '#bcb8fb',
			             rich: {
			                 a: {
			                     fontSize: 28,
			                 }
			             }
			         },
			         x: 'center',
			         y: '35%'
			     },
			     graphic: [{
			         type: 'group',
			         left: 'center',
			         top: '50%',
			         children: [{
			             type: 'text',
			             z: 100,
			             left: '10',
			             top: 'middle',
			             style: {
			                 fill: '#aab2fa',
			                 text:Number(value*100).toFixed(2)+'%',
			                 font: '20px Microsoft YaHei'
			             }
			         }]
			     }],
			        series: [{
				         type: 'liquidFill',
				         radius: '85%',
				         center: ['50%', '50%'],
				         data: data,
				         outline: saleRedOutLine,
				         backgroundStyle:saleRedBack,
				         color: saleRedColor,
				         label: {
				             normal: {
				                 formatter: '',
				             }
				         }
				     }]
			    };
		 saleRedLineEcharts.setOption(option);
		break;
	case 'saleSum'://总用水量模块
		saleSumEcharts = echarts.init(document.getElementById(ids));
		var value = bigData.querySumRedData['waterSum']/(bigData.converSion(bigData.querySumRedVal,"sumWater")*10000);

		 var data = [value.toFixed(2), value.toFixed(2), value.toFixed(2),value.toFixed(2)];
		 var option = {
				 title: {
					 top:'20%',
			         //text: (0.8 * 100).toFixed(0) + '{a|%}',
					 text:bigData.dataConver(bigData.querySumRedData['waterSum'])+'m3',
			         textStyle: {
			             fontSize: 25,
			             fontFamily: 'yjsz',
			             fontWeight: 'normal',
			             color: '#bcb8fb',
			             rich: {
			                 a: {
			                     fontSize: 28,
			                 }
			             }
			         },
			         x: 'center',
			         y: '35%'
			     },
			     graphic: [{
			         type: 'group',
			         left: 'center',
			         top: '50%',
			         children: [{
			             type: 'text',
			             z: 100,
			             left: '10',
			             top: 'middle',
			             style: {
			                 fill: '#aab2fa',
			                 text: Number(value*100).toFixed(2)+'%',
			                 font: '20px Microsoft YaHei'
			             }
			         }]
			     }],
			        series: [{
				         type: 'liquidFill',
				         radius: '85%',
				         center: ['50%', '50%'],
				         //  shape: 'roundRect',
				         data: data,
				         outline: saleSumOutLine,
				         backgroundStyle:saleSumBack,
				         color: saleSumColor,
				         label: {
				             normal: {
				                 formatter: '',
				             }
				         }
				     },]
			    };
		 saleSumEcharts.setOption(option);
		break;
	case 'aPlanLine':
		aPlanLineEcharts = echarts.init(document.getElementById(ids));
		var xAisData = bigData.useWaterArea["xAisData"] ;
		var planData = bigData.useWaterArea["planData"];
		var useWaterData = bigData.useWaterArea["useWaterData"];
		option = {
			color:['#2dc178','#00f9f9'],
	        tooltip : {
	            trigger: 'axis',
	            axisPointer: {
	                type: 'cross',
	                //xy轴十字架指示数值背景区域色
	                label: {
	                    backgroundColor: '#6a7985'
	                }
	            }
	        },
	        textStyle: {
	        	 color: '#fff'
	        },
	        legend: {
	            data:['已配水量','已用水量'],
	            textStyle: {
	                color: '#8db0ef',
	            },
	            //图例标记与顶部标题的距离
	            top:7
	        },
	        //简单说grid是控制边距的
	        grid: {
	        	top:'20%',
	            left: '3%',
	            right: '4%',
	            bottom: '3%',
	            containLabel: true
	        },
	        xAxis : [{
	                type : 'category',
	                //y轴边框颜色
	                axisLine: {
	                    show: true,
	                    lineStyle: {
	                        color: '#0182ca',
	                    } 
	                },
	                data : xAisData,
	            }],
	        yAxis : [
	            {
	                type : 'value',
	                //y轴横向分割线颜色
	                splitLine: {
	                    show: true,
	                    lineStyle: {
	                       color: '#0c3668',
	                       type:'dashed'
	                    }
	                },
	                //y轴刻度线颜色
	                axisTick: {
	                    show: false,
	                    lineStyle: {
	                        color: '#00345c',
	                    } 
	                },
	                //y轴边框颜色
	                axisLine: {
	                    show: false,
	                    lineStyle: {
	                        color: '#00345c',
	                    } 
	                }
	            }
	        ],
	        series : [
	            {
	                name:'已配水量',
	                type:'line',
	                smooth: true, //是否平滑
	                symbol: 'none',
	                areaStyle: {
	                	  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                            offset: 1,
	                            
	                            color: 'rgb(0, 255, 128,1)'
	                        }, {
	                            offset: 0,
	                            color: 'rgb(177, 255,251,0.5)'
	                        }])
	                },
	                lineStyle: {
	                    show:false,
	                    color: '#02417700',
	                },
	                data:planData
	            },
	            {
	                name:'已用水量',
	                type:'line',
	                smooth: true, //是否平滑
	                symbol: 'none',
	                areaStyle: {
	                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                            offset: 1,
	                            color: 'rgba(0,249, 249,1)'
	                        }, {
	                            offset: 0,
	                            color: 'rgba(9, 0, 255,1)'
	                        }])
	                },
	                lineStyle: {
	                   color: '#02417700',
	                }, 
	                 data:useWaterData
	            },
	        ]
	    }
		aPlanLineEcharts.setOption(option);
		break;
	case 'stackDayLine':
		stackDayEcharts = echarts.init(document.getElementById(ids));
		var dayXsiaData = bigData.useWaterLineList.dayXsiaData;
		var dayYsiaData = bigData.useWaterLineList.dayYsiaData;
		var nowDay = bigData.useWaterLineList.nowDay;
		
		option = {
		     color:['#1d96fe','#ffda85',],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
	                type: 'cross',
	                //xy轴十字架指示数值背景区域色
	                label: {
	                    backgroundColor: '#6a7985'
	                }
	            }
		    },
		    legend: {
		        data: [nowDay],
		        textStyle: {
			                color: '#8db0ef',
			            },
			            //图例标记与顶部标题的距离
			            top:7,
			            right:40,
			            icon:'roundRect',
		    },
		    grid: {
		    	top:'20%',
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: [
		        {
		            type: 'category',
		           boundaryGap: false,
		           data: dayXsiaData,
		            axisLine: {
		            show: true,
		             lineStyle: { color: '#1fa1ff'} 
		            }
		        }
		    ],
		    yAxis: [
		        {
		        	min: 0,
		            // max: 140,
		            splitNumber: 4,
		            splitLine: {
			           show: false,
		            },
		              axisLine: {
		            show: true,
		            lineStyle: {
			            color: '#1fa1ff',
			        } 
		        },
		            type: 'value'
		        }
		    ],
		    series: [
		        {
		            name: nowDay,
		            type: 'line',
		            
		            smooth: true, //是否平滑
		            symbol: 'none',
		            lineStyle: {
		                normal: {
		                    color: "#1d96fe",
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                            offset: 0,
		                            color: 'rgba(3,42,145,0.3)'
		                        },
		                        {
		                            offset: 1,
		                            color: 'rgba(3,42,145,0)'
		                        }
		                    ], false),
		                    shadowColor: 'rgba(3,42,145, 0.9)',
		                    shadowBlur: 20
		                }
		            },
		            data: dayYsiaData
		        },
		    ]
		};
		stackDayEcharts.setOption(option);
		break;
	case 'stackMonthLine':
		stackMonthEcharts = echarts.init(document.getElementById(ids));
		var Legend = [bigData.useWaterLineList.beforeMonth,bigData.useWaterLineList.curMonth];  
		var XsiaData = bigData.useWaterLineList.monthXsiaData;
		var CurData = bigData.useWaterLineList.monthYsiaCurData;
		var BeforData = bigData.useWaterLineList.monthYsiaBeforeData;
		option = {
		     color:['#1d96fe','#ffda85',],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
	                type: 'cross',
	                //xy轴十字架指示数值背景区域色
	                label: {
	                    backgroundColor: '#6a7985'
	                }
	            }
		    },
		    legend: {
		        data: Legend,
		        textStyle: {
			                color: '#8db0ef',
			            },
			            //图例标记与顶部标题的距离
			            top:7,
			            right:20,
			            icon:'roundRect',
		    },
		    grid: {
		    	top:'20%',
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: [
		        {
		            type: 'category',
		           boundaryGap: false,
		           data: XsiaData,
		            axisLine: {
		            show: true,
		             lineStyle: { color: '#1fa1ff'} 
		            }
		        }
		    ],
		    yAxis: [
		        {
		        	min: 0,
		            // max: 140,
		            splitNumber: 4,
		            splitLine: {
			           show: false,
		            },
		              axisLine: {
		            show: true,
		            lineStyle: {
			            color: '#1fa1ff',
			        } 
		        },
		            type: 'value'
		        }
		    ],
		    series: [
		        {
		            name: Legend[0],
		            type: 'line',
		            smooth: true, //是否平滑
		            symbol: 'none',
		            lineStyle: {
		                normal: {
		                    color: "#ffda85",
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                            offset: 0,
		                            color: 'rgba(244,205,95,0.8)'
		                        },
		                        {
		                            offset: 1,
		                            color: 'rgba(244,205,95,0)'
		                        }
		                    ], false),
		                    shadowColor: 'rgba(244,205,95, 0.9)',
		                    shadowBlur: 20
		                }
		            },
		            data: BeforData
		        },
		        {
		            name: Legend[1],
		            type: 'line',
		            
		            smooth: true, //是否平滑
		            symbol: 'none',
		            lineStyle: {
		                normal: {
		                    color: "#1d96fe",
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                            offset: 0,
		                            color: 'rgba(3,42,145,0.3)'
		                        },
		                        {
		                            offset: 1,
		                            color: 'rgba(3,42,145,0)'
		                        }
		                    ], false),
		                    shadowColor: 'rgba(3,42,145, 0.9)',
		                    shadowBlur: 20
		                }
		            },
		            data: CurData
		        },
		    ]
		};
		stackMonthEcharts.setOption(option);
		break;
	case 'stackBar':
		stackBarEcharts = echarts.init(document.getElementById(ids));
		var Legend = [bigData.useWaterLineList.year-1+"年",bigData.useWaterLineList.year+"年"];  
		var XsiaData = bigData.useWaterLineList.CurXsiaData;
		var CurData = bigData.useWaterLineList.CurYsiaData;
		var BeforData = bigData.useWaterLineList.BeforeYsiaData;
		option = {
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
		            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data: Legend,
		         textStyle: {
			                color: '#8db0ef',
			            },
			            //图例标记与顶部标题的距离
			            bottom:5,
			            icon:'roundRect',
		    },
		    grid: {
		    	top:'12%',
		        left: '3%',
		        right: '4%',
		        bottom: '20%',
		        containLabel: true
		    },
		    xAxis: [
		        {
		            type: 'category',
		            axisLine: {
		            show: true,
		             lineStyle: { color: '#1fa1ff'} 
		            },
		            data: XsiaData
		        }
		    ],
		    yAxis: [
		        {
		        	min: 0,
		            // max: 140,
		            splitNumber: 4,
		            type: 'value',
		             splitLine: {
			           show: false,
		            },
		            axisLine: {
		            show: true,
		             lineStyle: { color: '#1fa1ff'} 
		            },
		            
		        }
		    ],
		    series: [
		        {
		            barGap: 0,
		            name: Legend[0],
		            type: 'bar',
		            barWidth:'15%',
		            itemStyle: {
		                color: new echarts.graphic.LinearGradient(
		                    0, 0, 0, 1,
		                    [{
		                            offset: 0,
		                            color: '#61dae2'
		                        },
		                        {
		                            offset: 1,
		                            color: '#0484de'
		                        },
		                    ]
		                ),
		                shadowColor: 'blue',
		            },
		            data: BeforData
		        },
		        {
		            barGap: 0,
		            name: Legend[1],
		            type: 'bar',
		            barWidth:'15%',
		            itemStyle: {
		                color: new echarts.graphic.LinearGradient(
		                    0, 0, 0, 1,
		                    [{
		                            offset: 0,
		                            color: '#fe971a'
	                        },
	                        {
	                            offset: 1,
	                            color: '#fe441b'
	                        },
		                    ]
		                ),
		                shadowColor: 'blue',
		            },
		            data: CurData
		        }
		    ]
		};
		stackBarEcharts.setOption(option);
		break;
	case 'mapEcharts':
		var uploadedDataURL = "/content/skin/bigData/json/ejnq.json";
		mapEcharts = echarts.init(document.getElementById(ids));
		var data = bigData.deviceList.bigList;
		$.getJSON(uploadedDataURL, function(geoJson) {
			
	    echarts.registerMap('hanzhou', geoJson);
	    mapEcharts.hideLoading();
	    option = {
	        //backgroundColor: '#020933',
	        title: {
	            top: 20,
	            subtext: '',
	            x: 'center',
	            textStyle: {
	                color: '#ccc'
	            }
	        },
	        tooltip : {
	            trigger: 'item',
	            formatter:function(params, ticket, callback){
	                console.log(params);
	                if(params.seriesType=="effectScatter") {
	                    return params.data.name+""+params.data.value[2];
	                }else if(params.seriesType=="lines"){
	                    return params.data.fromName+"->"+params.data.toName+"<br />"+params.data.value;
	                }else{
	                    return params.name;
	                }
	            }
	        },
	        geo: {
	           map: '甘州区',
	          aspectScale: 0.75, //长宽比
	          zoom: 1.1,
	          roam: false,
	          itemStyle: {
	            normal: {
	              areaColor: '#013C62',
	              shadowColor: '#182f68',
	              shadowOffsetX: 0,
	              shadowOffsetY: 25
	            },
	            emphasis: {
	              areaColor: '#2AB8FF',
	              borderWidth: 0,
	              color: 'green',
	              label: {
	                show: false
	              }
	            }
	          }
	        },
	        series: [ {
	            type: 'map',
	            roam: true,
	            label: {
	              normal: {
	                show: false,
	                textStyle: {
	                  color: '#fff'
	                }
	              },
	              emphasis: {
	                textStyle: {
	                  color: '#fff'
	                }
	              }
	            },

	            itemStyle: {
	              normal: {
	                borderColor: '#2ab8ff',
	                borderWidth: 1.5,
	                areaColor: '#12235c'
	              },
	              emphasis: {
	                areaColor: '#2AB8FF',
	                borderWidth: 0,
	                color: 'green'
	              }
	            },
	            zoom: 1.1,
	            roam: true,
	            map: '甘州区' //使用
	          },
	          {
	            type: 'effectScatter',
	            coordinateSystem: 'geo',
	            showEffectOn: 'render',
	            rippleEffect: {
	              period: 15,
	              scale: 4,
	              brushType: 'fill'
	            },
	            hoverAnimation: true,
	            itemStyle: {
	              normal: {
	                color: '#01ffff',
	                shadowBlur: 10,
	                shadowColor: '#333'
	              }
	            },
	            data: data
	          }

	        ]
	    };
	    mapEcharts.setOption(option);
	});
		/*var data = bigData.deviceList.bigList;
		       option = {
		           geo: {
		               map: '额济纳旗',
		               label: {
		                   emphasis: {
		                       show: false
		                   }
		               },
		               roam: true,//允许其放大缩小
		               itemStyle: {
		                   normal: {
		                       areaColor: '#4c60ff',
		                       borderColor: '#002097'
		                   },
		                   emphasis: {
		                       areaColor: '#293fff'
		                   }
		               }
		           },
		           series : [{
		                   name: '额济纳旗',
		                   type: 'effectScatter',
		                   coordinateSystem: 'geo',
		                   data: data,
		                   symbolSize:12,
		                   showEffectOn: 'render',
		                   rippleEffect: {
		                       brushType: 'stroke'
		                   },
		                   hoverAnimation: true,
		                   label: {
		                       normal: {
		                    	   color: '#19e8b5',
		                           formatter: '{b}',
		                           position: 'right',
		                           show: true
		                       }
		                   },
		                   itemStyle: {
		                       normal: {
		                           color:function(val) {
		                        	   
		                               return val.data.value[2]=='在线'?'#00ffff':'#00ffff';
		                           },
		                           shadowBlur: 10,
		                           shadowColor: '#19e8b5'
		                       }
		                   },
		                   zlevel: 1
		               }]
		       };*/
		       
		break;
	default:
		break;
	}
}
function randomValue() {
    return Math.round(Math.random()*1000);
}

var saleSumOutLine;
var saleSumBack;
var saleSumColor;
var saleRedOutLine;
var saleRedBack;
var saleRedColor;
function colorItem(){
	saleRedOutLine = {
            borderDistance:12, //边框距离
            itemStyle: {
                borderWidth: 5,
                borderColor:  {
	         type: 'linear',
	         x: 0,
	         y: 0,
	         x2: 0,
	         y2: 1,
	         colorStops: [{
	             offset: 1,
	             color: '#3ad49b80'
	         }, {
	             offset: 0.5,
	             color: '#29f9ddd1'
	         }, {
	             offset: 0,
	             color: '#00fbc1'
	         }],
	         globalCoord: false
                },
                shadowBlur:6,
                shadowColor: '#d4feff',
                
            }
		};
	saleRedBack = {
			color: {
				type: 'linear',
                x: 1,
                y: 0,
                x2: 0.5,
                y2: 1,
                colorStops: [{
                    offset: 1,
                    color: '#4491fd00'
                }, {
                    offset: 0.5,
                    color: '#4491fd40'
                }, {
                    offset: 0,
                    color: '#4491fd00'
                }],
                globalCoord: false
                },
        };
	saleRedColor = {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [{
                offset: 1,
                color: '#3a64d480'
            }, {
                offset: 0.5,
                color: '#1fdee166'
            }, {
                offset: 0,
                color: '#1fdee1cc'
            }],
            globalCoord: false
        };
	saleSumOutLine = {
            borderDistance:12, //边框距离
            itemStyle: {
                borderWidth: 5,
                borderColor:  {
	         type: 'linear',
	         x: 0,
	         y: 0,
	         x2: 0,
	         y2: 1,
	         colorStops: [{
	             offset: 1,
	             color: '#101da9f7'
	         }, {
	             offset: 0.5,
	             color: '#0092f9'
	         }, {
	             offset: 0,
	             color: '#177bf7'
	         }],
	         globalCoord: false
                },
                shadowBlur: 6,
                shadowColor: '#d4feff',
                
            }
		};
	saleSumBack = {
			color: {
				type: 'linear',
                x: 1,
                y: 0,
                x2: 0.5,
                y2: 1,
                colorStops: [{
                    offset: 1,
                    color: '#4491fd00'
                }, {
                    offset: 0.5,
                    color: '#4491fd40'
                }, {
                    offset: 0,
                    color: '#4491fd00'
                }],
                globalCoord: false
                },
        };
	
	saleSumColor = {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [{
                offset: 1,
                color: '#3a64d480'
            }, {
                offset: 0.5,
                color: '#1fdee166'
            }, {
                offset: 0,
                color: '#1fdee1cc'
            }],
            globalCoord: false
        };
	
}

/**
 * 初始化加载定时任务的时钟
 * @returns
 */
function refreshClock(){
	var myDay = new Date().getDay();
	var week = ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'];
	bigData.week = '\r\n'+week[myDay];
	bigData.nowTime = moment(new Date()).format('YYYY 年 MM 月 DD 日 HH:mm')+'\r\n';
	
}





