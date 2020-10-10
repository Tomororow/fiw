/*页面初始化方法*/
var count = 0;
var personList = "";
$(function(){
	
});


/**
 * 新增异常类型
 */
function addWarnInfo(){
	count = 0;personList = "";
	var contentMsg = {
			title : "新增异常类型",   
			url : "parameterManage/warnErrorPage.do?sign=1",
			width : "700",
			height : "500",
			requestMethod: 'ajax',
			tbar: [{
				text: "确定提交",
				clsName: "com_btn",
				handler: function (thisPop) {
					loadWarnData(thisPop,'add','');
					//closeDetailInfo(thisPop, "monitor/detailInfo.do");
				}
			}]
		};
		$.Popup.create(contentMsg);
}
function loadPlanList(){
	var url = "parameterManage/warnErrorPage.do";
	$.ajax({
		url : url,
		data : {sign:0},
		success :function(data){
		}
	});
}

/**
 * 新增编辑的公共方法
 */
function loadWarnData(thisPop,sign,id){
	var abnormaltype = $("#abnormaltype").val();
	var abnormaldetail = $("#abnormaldetail").val();
	var abnormalcode = $("#abnormalcode").val();
	if(abnormaltype==""||abnormaltype==null){
		alert("异常类型不能为空...");
		return;
	}
	if(abnormalcode==""||abnormalcode==null){
		alert("异常类型编码不能为空...");
		return;
	}
	if(abnormaldetail==""||abnormaldetail==null){
		alert("异常详情不能为空...");
		return;
	}
	if(personList==""){
		alert("请选择关联联系人...");
		return;
	}
	
	debugger
	var param = {isMess:isMess,id:id,abnormaltype:abnormaltype,abnormaldetail:abnormaldetail,abnormalcode:abnormalcode,personList:personList}
	if(sign=='add'){
		$.post("parameterManage/addEditWarnType.do",param,function(data){
			debugger
			if(data=="添加成功"){
				alert("添加成功");
				$.Popup.close(thisPop);
				loadWarnInfo();//重新加载
			}
		});
	}else if(sign=='edit'){
		$.post("parameterManage/EditWarnType.do",param,function(data){
			debugger
			if(data=="修改成功"){
				alert("修改成功");
				$.Popup.close(thisPop);
				loadWarnInfo();//重新加载
			}
		});
	}
}

/**
 * 删除异常类型
 */
function delWarnInfo(id,code){
	$.post("parameterManage/delWarnType.do",{id:id,code:code},function(data){
		if(data=="成功"){
			alert("删除成功");
			loadWarnInfo();//重新加载
		}
	});
}

function loadWarnInfo(){
	$.post("parameterManage/warnErrorPage.do?sign=0","",function(data){
		debugger
		$("#baseWaterPriceContent").empty().html(data);
	});
}

/**
 * 修改异常类型
 */
function editWarnInfo(id,code){
	count = 0;personList = "";
	var contentMsg = {
			title : "新增异常类型",   
			url : "parameterManage/warnErrorPage.do?id="+id+"&sign=2"+"&code="+code,
			width : "700",
			height : "500",
			requestMethod: 'ajax',
			tbar: [{
				text: "确定提交",
				clsName: "com_btn",
				handler: function (thisPop) {
					loadWarnData(thisPop,'edit',id);
					//closeDetailInfo(thisPop, "monitor/detailInfo.do");
				}
			}]
		};
		$.Popup.create(contentMsg);
}
/**
 * 短信预警的开关
 */
var isMess = 0;
function handleMessChange(sign){
	if(sign.checked){
		$("#isMess").text('开启');
		isMess = 1;
	}else{
		$("#isMess").text('关闭');
		isMess = 0;
	}
	
}
/**
 * 分页方法
 */
function changePage(page){
	debugger
	$.post("parameterManage/warnErrorPage.do?sign=0&pageNo="+page,"",function(data){
		$("#baseWaterPriceContent").empty().html(data);
	});
}
/**
 * 选中取消方法
 * @param btn
 * @param sign
 */
function handleChange(btn,sign){
	debugger
	count = 0;
	personList = "";
	var confCount = $('[id=chryu]');//conf为元素id值
	for(var i=0;i<confCount.length;i++){
		var check = confCount[i].children['0'].checked;
		if(check){
			count+=1;
			personList+=confCount[i].children['0'].value+",";
		}
	}
	$("#selectPerson").text(count);
}


