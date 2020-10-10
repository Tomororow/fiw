var financial = new Vue({
	el:'#financial',
	data:{
		financList:[],
		createtime:'',
		deviceName:'',
		dialogVisible:false,
		month:'',
		activeName:'1',
		quarterName:'',
		datePickerMonth:'',
		datePickerYear:'',
		pagesize:10,
		currentPage:1,
		deviceName:'',
		treeNode:{},
		//级联菜单
		selectedOptions2:'',
		//开始时间
		startTime:'',
		//结束时间
		endTime:'',
		distYear:'',
		distRound:'',
	},
	methods:{
		//查询按钮
		baseWater:function(type){
			var _this = this;
			debugger
			_this.financList = [];
			_this.startTime = $("#startTime").val();
			_this.endTime = $("#endTime").val();
				$.post("financial_manage/device_name.do",{"waterAreaCode": _this.treeNode.deviceCode,"deviceName":_this.deviceName,"startTime":_this.startTime,"endTime":_this.endTime,"distYear":_this.distYear,"distRound":_this.distRound},function(data){
					if(data!=null||data!=""){
						_this.financList = JSON.parse(data);
					}else{
						_this.$message({
							message:"警告，未查询到该机井的相关信息",
							type:"warning"
						});
					}
				});
		},
		dateFormat:function(time) {
		    var date=new Date(time);
		    var year=date.getFullYear();
		    var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
		    var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
		    var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
		    var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
		    var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
		    // 拼接
		    return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		},
		//级联菜单单击事件
		handleChange:function(){
			
		},
		//分页方法
		handleCurrentChange:function(currentPage){
			var _this = this;
			_this.currentPage = currentPage;
		},
		//分页方法
		handleSizeChange:function(size){
			var _this = this;
			_this.pagesize= size;
		},
		//报表统计按钮点击事件
		dialogVis:function(){
			var _this = this;
			_this.activeName = '1';
			_this.treeNode.deviceCode=null;
			_this.datePickerMonth=null
			_this.datePickerYear=null
			_this.quarterName=null
			$("#fnTop .el-dialog--small").css("width","auto").css("top","19%").css("min-width","240px");
			_this.dialogVisible = true;
			$.post("distance_charge/test_tree.do","",function(data){
				debugger
				var trees = JSON.parse(data);
				var ztree = $.fn.zTree.init($("#ztreeTwo"), setting,JSON.parse(data));
			});
		},
		//报表导出
		/*tueExport:function(type){
			var _this = this;
			var dataSum = "";
			ft = false;
			switch (type) {
			case "1":
				if(_this.treeNode.deviceCode==""||_this.treeNode.deviceCode==null){
					_this.$message({
						message:"警告，请选择水管区域或其他方式导出...",
						type:"warning"
					});
				}else{
					ft = true;
					dataSum = _this.treeNode.deviceCode;
				}
				break;
			case "2":
				if(_this.datePickerMonth==""||_this.datePickerMonth==null){
					_this.$message({
						message:"警告，请选择月份或其他方式导出...",
						type:"warning"
					});
				}else{
					ft = true;
					dataSum = moment(_this.datePickerMonth).format('YYYY-MM');
				}
				break;
			case "3":
				if(_this.datePickerYear==""||_this.datePickerYear==null){
					_this.$message({
						message:"警告，请选择年份或其他方式导出...",
						type:"warning"
					});
				}else{
					ft = true;
					dataSum = moment(_this.datePickerYear).format('YYYY');
				}
				break;
			default:
				if(_this.quarterName==""||_this.quarterName==null||_this.quarterName=="0"){
					_this.$message({
						message:"警告，请选择季度或其他方式导出...",
						type:"warning"
					});
				}else{
					ft = true;
					dataSum = _this.quarterName;
				}
				break;
			}
			if(ft){
				window.location.href = "financial_manage/repExport.do?dataSum="+dataSum+"&type="+type;
				_this.dialogVisible = false;
			}
		},
	}*/tueExport:function(type){
		var _this = this;
		var dataSum = "";
			window.location.href = "financial_manage/repExport.do?waterAreaCode="+ _this.treeNode.deviceCode+"&type="+type+"&deviceName="+_this.deviceName+"&startTime="+_this.startTime+"&endTime="+_this.endTime+"&distYear="+_this.distYear+"&distRound="+_this.distRound;
			_this.dialogVisible = false;
		
	},
}
	,
	created:function(){
		var _this = this;
		debugger
		//初始化页面查询树结构
		$.post("distance_charge/test_tree.do","",function(data){
			var seer = $.fn.zTree.init($("#ztree"), setting,JSON.parse(data));
			var treeObj = $.fn.zTree.getZTreeObj("ztree");
			var nodeList = treeObj.getNodes(); //展开第一个根节点
			for(var i = 0; i < nodeList.length; i++) { //设置节点展开第二级节点
				treeObj.expandNode(nodeList[i], true, false, true);
				var nodespan = nodeList[i].children;
				for(var j = 0; j < nodespan.length; j++) { //设置节点展开第三级节点
					treeObj.expandNode(nodespan[j], true, false, true);
				}
			}
			//初始化页面数据
			$.post("financial_manage/select_all.do",{"code":JSON.parse(data)[0].deviceCode,"type":"1"},function(data){
				debugger
				_this.financList = JSON.parse(data);
			});
		});
		
	},
});
//树节点回调函数
var setting = {callback: {onClick: zTreeOnClick}};
//回调方法
function zTreeOnClick(event, treeId, treeNode){
	debugger
	financial.financList = [];
	financial.treeNode = treeNode;
	$.post("financial_manage/select_all.do",{"code":treeNode.deviceCode,"type":"1"},function(data){
		financial.financList = JSON.parse(data);
	});
}
