var audio;
var header = new Vue({
	el:'#header',
	data:{
		warnCount:0,
	},
	methods:{
		/*预警按钮的点击方法*/
		warnChange:function(){
			var _this = this;
			debugger
			if(_this.warnCount==0){
				confirmMsg("暂无任何预警信息");
			}else{
				confirmMsgWarn("有"+_this.warnCount+"条预警信息，请尽快处理......");
			}
		},
		vityWarnMethods:function(){
			var _this = this;
			debugger
			$.post("warning_Record/warn_count.do",{"state":0},function(data){
				debugger
				if(data!=0){
					_this.warnCount = Number(data);
					playVoice();
				}else{
					_this.warnCount = 0;
					stopVoice();
				}
			});
		},
		/*与前端保持Socket保持长连接（只要有预警就及时响应前端页面）*/
		WebSocket:function(){
			var _this = this;
			debugger
			var websocket = null;
		      //判断当前浏览器是否支持WebSocket
		      if ('WebSocket' in window) {
		    //建立连接，这里的/websocket ，是ManagerServlet中开头注解中的那个值
		          websocket = new WebSocket("ws://61.178.178.92:2004/ws");
		      }
		      else {
		    	  console.log('当前浏览器 Not support websocket');
		          //alert('当前浏览器 Not support websocket')
		      }
		      //连接发生错误的回调方法
		      websocket.onerror = function () {
		    	  console.log("WebSocket连接发生错误...",0);
		      };
		      //连接成功建立的回调方法
		      websocket.onopen = function () {
		    	  console.log("WebSocket连接成功");
		          //setMessageInnerHTML("WebSocket连接成功");
		      }
		      //接收到消息的回调方法
		      websocket.onmessage = function (event) {
		          setMessageInnerHTML(event.data,1);
		          console.log("====来了  ==== "+event.data);
		      }
		      //连接关闭的回调方法
		      websocket.onclose = function () {
		          setMessageInnerHTML("WebSocket连接关闭",2);
		         
		      }
		      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
		      window.onbeforeunload = function () {
		          closeWebSocket();
		      }
		      //将消息显示在网页上
		      function setMessageInnerHTML(innerHTML,type) {
		    	  if(type==1){//后端返回的数据
		    		  _this.vityMethods();
		    	  }else if(type==0){
		    		  _this.$message.warning(innerHTML);
		    	  }else if(type==2){
		    		  console.log(innerHTML);
		    	  }
		      }
		      //关闭WebSocket连接
		      function closeWebSocket() {
		          websocket.close();
		          setMessageInnerHTML("WebSocket连接关闭",2);
		      }
		},
	},
	created:function(){
		var _this = this;
		debugger
		//_this.WebSocket();//页面与后端实时响应
		_this.vityWarnMethods();
		 window.vityWarnMethods = _this.vityWarnMethods;
	}
});

function confirmMsg(str){
	var confirmMsg = {
			title : "提示",
			content : "<center>"+str+"</center>",
			tbar : [{
				// 点击取消，则关闭弹出框，并关闭背景加载信息
				text : "取消",
				clsName : "homebg popup-confirm",
				handler : function(thisObj) {
					$.Popup.close(thisObj);
					hideMark();
				}
			}]
		};
	$.Popup.create(confirmMsg);
}
function confirmMsgWarn(str){
	var confirmMsg = {
			title : "预警提示",
			width:400,
			content : "<center>"+str+"</center>",
			tbar : [ {
				text : "取消警报",
				width:'100px',
				clsName : "cancelWarnBtn",
				handler : function(thisPop) {
					stopVoice();
					// 点击确定后，关闭弹出框
					$.Popup.close(thisPop);
					// 关闭背景加载信息
					hideMark();
				}
			}, {
				// 点击取消，则关闭弹出框，并关闭背景加载信息
				text : "跳转至故障详情页面",
				clsName : "confirmWarnBtn",
				handler : function(thisObj) {
					skipAge();
					$.Popup.close(thisObj);
					hideMark();
				}
			}]
		};
	$.Popup.create(confirmMsg);
}
/*
 * 跳转至故障详情页面
 */
function skipAge(){
	// 如果点击确定，则将页面跳转到告警查询页面，这里onclick*=必须用history，这样才能让头部菜单指向告警查询
	var m = $(".top .top_content li a[onclick*=history]").parent();
	debugger
	// 设置被选中的菜单，并将class=sell，只有class=sell的菜单，才能显示被选中状态
	if (m != undefined && m.length > 0) {
		var s = m.siblings();
		m.find("a").addClass("sell");
		s.find("a").removeClass("sell");
	}
	// 二级菜单跳转到异常机井智能分析菜单上
	var tag = "device";
	showContent("history/index.do?menuId=3&stcd=" + null + "&tag=" + tag, 'contain');
}



