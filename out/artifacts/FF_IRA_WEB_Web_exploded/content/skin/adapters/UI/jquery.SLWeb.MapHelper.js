/**
 * 描述：MapHelper地图加载插件
 * 调用方式：var mapObj = $.Map.Init();
 */
(function ($, window, document) {
    $.extend({
        Map: {
            /**
			 * 用途：初始化地图 
			 * 输入：id图层Id  
			 * 返回：mapObj实例化后地图对象 
			 */
            Init: function () {
                //初始化地图
                var mapObj = new AMap.Map("map", {
                	resizeEnable: true
                });
                
                $(".amap-logo,.amap-copyright").hide();
                return mapObj;
            },
            /**
			 * 用途：设置地图显示坐标集合
			 * 输入：map实例化后地图对象
			 * 返回：无
			 */
            SetMarker: function (map, coordinates) {
                fn = {
                	_click: function (map, marker, coordinates) {
                    }
                };
                
                if(coordinates != undefined){
                	for (var i = 0; i < coordinates.length; i++) {
                		if(coordinates[i].longitude != undefined && coordinates[i].latitude != undefined){
                			var netState = 0;
                			if(coordinates[i].deviceType==1) {
                				netState = coordinates[i].netStatek;
                			} else {
                				netState = coordinates[i].netStateq;
                			}
                			var marker = new AMap.Marker({
                				//自定义图标
                				icon: new AMap.Icon({
                					//图标大小
                					size: new AMap.Size(30, 38),
                					//图标地址
                					image: "content/skin/css/images/map/map-DEV-" + netState + ".png"
                				}),
                				//在地图上添加点
                			 	position: new AMap.LngLat(coordinates[i].longitude, coordinates[i].latitude),
                			});
                			marker.setMap(map);
                			$.Map.MapPointClick(map, marker, coordinates[i]);
                		}
                	}
                	map.setFitView();//地图自适应
                	if(map.getZoom()>9) map.setZoom(11);	
                }
            },
            
            MapPointClick: function (map, marker, coordinates) {
                //监听鼠标点击事件
                AMap.event.addListener(marker, 'click', function () {
                 	var info = $("<div><div>");
                    info.attr("class", "mapInfo");
                    var formatedTime = "";
                    if(coordinates.deviceType==1) {
                    	if (coordinates.collectTimek != null) {
                        	var collectTime = new Date(coordinates.collectTimek);
                            var month = collectTime.getMonth() + 1;
                            formatedTime = collectTime.getFullYear()+"-"+month+"-"+collectTime.getDate()+" "+collectTime.getHours()+":"+collectTime.getMinutes()+":"+collectTime.getSeconds();	
                        }
                    } else {
                    	if (coordinates.collectTimeq != null) {
                        	var collectTime = new Date(coordinates.collectTimeq);
                            var month = collectTime.getMonth() + 1;
                            formatedTime = collectTime.getFullYear()+"-"+month+"-"+collectTime.getDate()+" "+collectTime.getHours()+":"+collectTime.getMinutes()+":"+collectTime.getSeconds();	
                        }
                    }
                    
                    var infoHtml = "<div class=\"map-title map-skin\">";
                    infoHtml += "<h1>"+coordinates.deviceName+'详细信息'+"</h1>";
                    infoHtml += "<a class=\"map-close\"></a>";
                    infoHtml += "</div>";
                    infoHtml += "<div class=\"map-content\">";
                    infoHtml += "<div class=\"content\" style=\"width:504px\">";
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">机井名称</div>";
                    infoHtml += "<div class=\"content_info1\">"+coordinates.deviceName+"</div>";
                    
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">采集时间</div>";
                    if(coordinates.deviceType==1) {
                    	infoHtml += "<div class=\"content_info1\">"+(coordinates.collectTimek==null?'无数据':''+formatedTime+'')+"</div>";
                    } else {
                    	infoHtml += "<div class=\"content_info1\">"+(coordinates.collectTimeq==null?'无数据':''+formatedTime+'')+"</div>";
                    }
                    
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">年累计用水量</div>";
                    if(coordinates.deviceType==1) {
                    	infoHtml += "<div class=\"content_info1\">"+(coordinates.yearUseWater==null?'无数据':''+coordinates.yearUseWater+''+"m³")+"</div>";
                    } else {
                    	infoHtml += "<div class=\"content_info1\">"+(coordinates.yearUseWater==null?'无数据':''+coordinates.yearUseWater+'')+"</div>";
                    }
                    
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">剩余水量</div>";
                    if(coordinates.deviceType==1) {
                    	infoHtml += "<div class=\"content_info1\">"+(coordinates.remainWater==null?'无数据':''+coordinates.remainWater+''+"m³")+"</div>";
                    } else {
                    	infoHtml += "<div class=\"content_info1\">"+(coordinates.leftWt==null?'无数据':''+coordinates.leftWt+'')+"</div>";
                    }
                    
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">水泵状态</div>";
                    if(coordinates.deviceType==1) {
                    	if(coordinates.pumpState==0){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-exclamation.png'></img>关泵</div>";
                        }else if(coordinates.pumpState==1){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-accept.png'></img>开泵</div>";
                        }else{
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-upgrade.gif'></img>无数据</div>";
                        }
                    } else {
                    	if(coordinates.openState==0){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-exclamation.png'></img>关泵</div>";
                        }else if(coordinates.openState==1){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-accept.png'></img>开泵</div>";
                        }else{
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-upgrade.gif'></img>无数据</div>";
                        }
                    }
                    
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">网络状态</div>";
                    if(coordinates.deviceType==1) {
                    	if(coordinates.netStatek==0){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-exclamation.png'></img>离线</div>";
                        }else if(coordinates.netStatek==1){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-accept.png'></img>在线</div>";
                        }
                    } else {
                    	if(coordinates.netStateq==0){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-exclamation.png'></img>离线</div>";
                        }else if(coordinates.netStateq==1){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-accept.png'></img>在线</div>";
                        }
                    }
                    
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">设备版本</div>";
                    infoHtml += "<div class=\"content_info1\">"+(coordinates.deviceVersion==''?'无数据':''+coordinates.deviceVersion+'')+"</div>";
                    
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">上下卡状态</div>";
                    if(coordinates.deviceType==1) {
                    	if(coordinates.cardState==1){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-accept.png'>上卡</div>";
                        }else{
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-exclamation.png'>下卡</div>";
                        }
                    } else {
                    	if(coordinates.cardState==1){
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-accept.png'>上卡</div>";
                        }else{
                        	infoHtml += "<div class=\"content_info1\"><img src='content/skin/css/images/icon/icon-exclamation.png'>下卡</div>";
                        }
                    }
                    
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">安装前照片</div>";
                    infoHtml += "<div class=\"content_info1\"><a style='color:#317EF3; cursor:pointer;' class='ml20' onclick='ShowPhotoInfo(\""+coordinates.photoBefore+"\")'>预览</a></div>";
                    
                    infoHtml += "<div class=\"content_title1\" style=\"width:90px\">安装后照片</div>";
                    infoHtml += "<div class=\"content_info1\"><a style='color:#317EF3; cursor:pointer;' class='ml20' onclick='ShowPhotoInfo(\""+coordinates.photoAfter+"\")'>预览</a></div>";
                    infoHtml += "</div>";
                    infoHtml += "</div>";
                    infoHtml += "<div class=\"map-bottom\"></div>";
                    info.html(infoHtml);
                    
                    //绑定地图关闭事件
                    info.find("a.map-close").click(function () {
                        map.clearInfoWindow();
                    });
                    
                    var infoWindow = new AMap.InfoWindow({
                        isCustom: true,  //使用自定义窗体
                        content: info[0],
                        offset: new AMap.Pixel(18, -20)
                    });
                    infoWindow.open(map, marker.getPosition());
                });
            },
            
            /**
             * 用途：设置地图类型切换 
			 * 输入：map地图对象 layers地图类型 
			 * 返回：无 
             */
            SetLayers: function (map, layers) {
                if (layers == "satellite") {
                    map.setDefaultLayer(new AMap.TileLayer.Satellite({ zIndex: 6 }), new AMap.TileLayer.RoadNet({ zIndex: 11 }));
                } else {
                    map.setDefaultLayer(new AMap.TileLayer({ tileUrl: "http://mt{1,2,3,0}.google.cn/vt/lyrs=m@142&hl=zh-CN&gl=cn&x=[x]&y=[y]&z=[z]&s=Galil" }));
                }
            },
        }
    });
})(jQuery);

//弹出机井拍摄前/后的图片
function ShowPhotoInfo(photoInfo) {
	// 获取到图片的地址
	var content2 = "<img width='640' height='466' src='/pir/"+photoInfo+"' />";
	// 将图片的信息以弹出框形式展现出来
	TINY.box.show(content2,0,0,0,1);
};