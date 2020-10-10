/**********************************设备功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$(".leftTree").height($(".left").outerHeight() - 10);
	$("#checkAll").live("click",function(){
		var check = $(this).attr("checked");
		var ckList = $("#stBprpBDiv tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	});
});

/**
 * 点击区域，加载右侧设备列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadStbprpList(params, flag){
	  showMark();
	$.get("stStbprpB/list.do",params,function(data){
		$("#deviceContent").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

/**
 * 查询测站编码
 */
function searchStcd(){
	showMark();
	var dstcd = $("#dstcd").val();
	if(dstcd=='请输入机井编码'){
		dstcd = "";
	}
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	
	$.ajax({
		url : "stStbprpB/list.do",
		data : {id:id,stcd:dstcd},
		success : function(data){
			$("#deviceContent").html(data);
			hideMark();
	    },
	    error: function (xhr, error, thrown) {
	    	hideMark();
   	    }
	});
}

/**
 * 分页查询
 * @param page
 */
function changePage(page){
	showMark();
	var dstcd = $("#dstcd").val();
	if(dstcd=='请输入机井编码'){
		dstcd = "";
	}
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
  	
	$.ajax({
		url : "stStbprpB/list.do",
		data : {id:id,stcd:dstcd,pageNo:page},
		success : function(data){
			$("#deviceContent").html(data);
			hideMark();
	    },
	    error: function (xhr, error, thrown) {
	    	hideMark();
   	    }
	});
}


/**
 * 新增设备
 */
function addStBprpB(){
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var name = "";
  	var id = "";
  	if(sNodes==""){
  		 $.Popup.create({ title: "提示", content: "请选择行政区域"});
  		 return false;
  	}else{
  		id = sNodes[0].id;
  		name = sNodes[0].name;
  	}
  	var rt = sNodes[0];
  	if(rt.children==null){
    	//先判断企业允许添加最多的设备数量是否超过
  	  	fnAjaxRequest(
  	            "stStbprpB/stbprpBCountResult.do",
  	            {},
  	            "json",
  	            "POST",
  	            function (data) {
  	                if(data.success==true){
  	            		var contentMsg = {
  	            		   id:"addStBprpBID",
  	           	           title: "新增设备-所属分组【"+name+"】",   
  	           	           url:"stStbprpB/addPage.do",
  	           	           width:"500",
  	           	           paraData:{addvcdId:id},
  	           	           requestMethod: 'ajax',
  	           	           tbar: [{
  	           	               text: "确定",
  	           	               clsName: "homebg popup-confirm",
  	           	               handler: function (thisPop) {
  	           	            	   addAndEditStBprpB(thisPop,"stStbprpB/logAddStbprpB.do");
  	           	               }
  	           	           }]
  	           	       };
  	                   $.Popup.create(contentMsg);
  	                }else{
  	                	$.Popup.create({ title: "提示", content: "该企业下最多允许添加"+data.msg+"个设备"});
  	            	  	return false;
  	                }
  	           });
  	}else{
  		$.Popup.create({ title: "提示", content: "只能在最子级下添加设备"});
	  	return false;
  	}
}
/**
 * 编辑设备
 */
function editStBprpB(stcd){
	var contentMsg = {
			   id:"editStBprpBID",//为了调用共同的方法
	           title: "修改设备",   
	           url:"stStbprpB/editPage.do",
	           width:"930",
	           paraData:{id:stcd},
	           requestMethod: 'ajax',
	           tbar: [{
	               text: "确定",
	               clsName: "homebg popup-confirm",
	               handler: function (thisPop) {
	            	   addAndEditStBprpB(thisPop,"stStbprpB/logEditStbprpB.do");
	               }
	           }]
	       };
   $.Popup.create(contentMsg);
}
/**
 * 保存/编辑公用方法
 */
function addAndEditStBprpB(thisPop,url){
	 var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	 var sNodes = treeObj.getSelectedNodes();
  	 var addvcd = sNodes[0].id;
	 var param = $("#stStbprpBForm").serialize();
	 if(validateStBprpBForm()){
		var lgtd = $("#devicelng").val();
		var lttd = $("#devicelat").val();
		if(lgtd == "" && lttd == ""){
			 $.Popup.create({ title: "提示", content: "安装地点，请先进行定位"});
	   		 return false;
		}
		param = param+"&lgtd="+lgtd+"&lttd="+lttd;
		  fnAjaxRequest(
   			    url,
  				param,
  				"json",
  				"POST",
  				function(data){
   			    	    fnDSuccess(data,thisPop);
	   				    if(data.success){
		   					var params = {
					    			id:addvcd
							};
		   					loadStbprpList(params, true);
    				    }else{
    				    	$.Popup.close(thisPop);
    				    }
  				}
  	   );
	}
}

function delStBprpB(stcd){
	var stId = [];
    if (stcd) {
    	stId.push(stcd);
    }else{
    	var selectRow = $("#stBprpBDiv tbody input[type='checkbox']:checked");
	    for (var i = 0; i < selectRow.length; i++) {
	    	stId.push(selectRow[i].value);
	    }
    }
    if (stId.length == 0) {
    	$.Popup.create({ title: "提示", content: "请选择设备"});
  	  	return false;
    }
  	var treeObj = $.fn.zTree.getZTreeObj("ztree");
  	var sNodes = treeObj.getSelectedNodes();
  	var id = sNodes[0].id;
	var confirmMsg = {
            title: "提示",
            content: "确定要删除选中的设备?",
            tbar: [{
                text: "确定",
                clsName: "homebg popup-confirm",
                handler: function (thisPop) {
                	var confirmMsg2 = {
                            title: "提示",
                            width:"500",
                            content: "该操作会删除设备相关的所有数据，删除后无法复原，请三思！",
                            tbar: [{
                                text: "确定",
                                clsName: "homebg popup-confirm",
                                handler: function (thisPop2) {
	                               	 fnAjaxRequest(
	               	         			    "stStbprpB/logDelStbprpB.do",
	               	        				{ids:stId.toString()},
	               	        				"json",
	               	        				"POST",
	               	        				function(data){
	               	        					fnDSuccess(data,thisPop);
	              	         			    	if(data.success){
	              	         			    	  	var params = {
	              	         			    				id:id
	              	         			    		};
	              	         			    	  	loadStbprpList(params, true);
	              	          					}else{
	              	          						$.Popup.close(thisPop);
	              	          					}
	               	        				}
	               	        	     );
                                }
                            }, {
                                text: "取消",
                                clsName: "homebg popup-cancel",
                                handler: function (thisObj2) {
                                    $.Popup.close(thisObj2);
                                    $.Popup.close(thisPop);
                                    
                                }
                            }]
                        };
                       $.Popup.create(confirmMsg2);
                }
            }, {
                text: "取消",
                clsName: "homebg popup-cancel",
                handler: function (thisObj) {
                    $.Popup.close(thisObj);
                }
            }]
        };
            $.Popup.create(confirmMsg);
}
/**
 * 移动设备
 */
function moveStBprpB(){
	var deId = [];
	var selectRow = $("#stBprpBDiv tbody input[type='checkbox']:checked");
    for (var i = 0; i < selectRow.length; i++) {
    	deId.push(selectRow[i].value);
    }
    if (deId.length == 0) {
    	$.Popup.create({ title: "提示", content: "请选择要移动的设备"});
  	  	return false;
    }else{
    	var contentMsg = {
 	           title: "移动设备区域",   
 	           width:"350",
 	           url:"stStbprpB/movePage.do",
 	           paraData:{dids: deId.toString()},
 	           requestMethod: 'ajax',
 	           tbar: [{
 	               text: "确定",
 	               clsName: "homebg popup-confirm",
 	               handler: function (thisPop) {
 	            	  moveAddvcdDSubmit(thisPop);
 	               }
 	           }]
 	       };
        $.Popup.create(contentMsg);
    }
}
/**
 * 保存移动设备操作
 */
function moveAddvcdDSubmit(thisPop){
	var dids  = $("#dids").val();
	var treeObj = $.fn.zTree.getZTreeObj("ztreeMove");
  	var sNodes = treeObj.getSelectedNodes();
  	var rt = sNodes[0];
  	var id = sNodes[0].id;
	if(rt.children==null){
		//进行分组移动操作
	      fnAjaxRequest(
	    		"stStbprpB/logMoveStbprpB.do",
				{ids:dids,addvcdId:id},
				"json",
				"POST",
				function(data){
					fnDSuccess(data,thisPop);
					if(data.success){
						var treeAreaObj = $.fn.zTree.getZTreeObj("ztree");
					  	var sAreaNodes = treeAreaObj.getSelectedNodes();
					  	var addvcd = sAreaNodes[0].id;
 			    	  	var params = {
 			    				id:addvcd
 			    		};
 			    	  	loadStbprpList(params, true);
  					}
				});
	}else{
		$.Popup.create({ title: "提示", content: "只能移动到子分组下！"});
  	  	return false;
	}
}



/**
 * 设备新增时定位
 */
function getLocation(addvcdDId){
	if(addvcdDId == undefined){
		addvcdDId == "";
	}
	var contentMsg = {
	           title: "设备定位",   
	           url:"stStbprpB/mapDialog.do",
	           width:"900",
	           paraData:{addvcdDId: addvcdDId},
	           requestMethod: 'ajax'
	       };
    var videoObj = $.Popup.create(contentMsg);
	videoObj.popObj.find("div.popup-content").css({"padding":"0"});
	videoObj.popObj.find("div.popup-control").css({"padding-bottom":"0"});

}


/**
 * 属性校验
 * @returns
 */
function validateStBprpBForm(){ 
	return $("#stStbprpBForm").validate({
		rules:{
			 stcd : {
			    required: true,
			    isTenNum: true,
			    stcdExist: true
			   },
			   stnm : {
				    required: true,
				    specialCharValidate: true,
				    maxlength:10
			   },
			   pwd :{
				    required: true,
				    digits:true,
				    range: [1, 65535]
			   },
				   
			   center : {
					required: true,
				    number: true,
				    digits:true,
				    range: [1, 255] 
			   },
			   tel : {
					required: true,
					isMobile: true,
					deviceTelExist:true
			   },
			   stlc : {
				    required: true,
				    specialCharValidate: true
				   },
			   comments:{
				   specialCharValidate: true
			   }
		},
		messages:{
			  stcd : {
				    required: "必填项",
				    isTenNum: "10位数字",
				    stcdExist:"存在了"
              },
              stnm : {
				    required: "必填项",
				    specialCharValidate: "不能包含特殊字符",
				    maxlength:"不超过10个字"
			   },
              pwd : {
	  				required: "必填项",
	  				digits:"1~65535整数",
	  				range:"1~65535整数"
              },
              center : {
  				required: "必填项",
  				number: "1~255整数",
  				digits:"1~255整数",
  				range:"1~255整数"
              },
              tel : {
  				required: "必填项",
  				isMobile: "手机号码",
  				deviceTelExist:"存在了"
               },	
              stlc : {
				    required: "必填项",
				    specialCharValidate: "不能包含特殊字符"
			   },
			   comments:{
				   specialCharValidate: "不能包含特殊字符"
			   }
            
		},
		showErrors:showErrors,
		onkeyup: function( element, event ) {
			if ( event.which === 9 && this.elementValue( element ) === "" ) {
				return;
			} else if ( element.name in this.submitted || element === this.lastElement ) {
				this.element( element );
				$(element).next('span').remove();//移除span
			}
		}
	}).form();
}

/***
 * ajax 验证测站编码
 */
$.validator.addMethod("stcdExist",function(value,element){
    var stcd = value;
    var result= false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
	$.post("stStbprpB/checkStcdExist.do",{
		stcd : stcd
	},function(data){
		if(data.success){
			result = false;
    	}else{
    		result = true;
    	} 
	},"json");
	  // 恢复异步
    $.ajaxSetup({
        async: true
    });
	return result;
},"企业编码，已经存在了!");

/***
 * ajax 验证通信识别码
 */
$.validator.addMethod("deviceTelExist",function(value,element){
    var deviceTel = value;
    var stcd = $("#stcd").val();
    var data3= false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
	$.post("stStbprpB/checkDeviceTelExist.do",{
		deviceTel : deviceTel,
		stcd : stcd
	},function(data){
		if(data.success){
    		data3 = false;
    	}else{
    		data3 = true;
    	} 
	},"json");
	  // 恢复异步
    $.ajaxSetup({
        async: true
    });
	//data3 false 已经存在了
	return data3;
},"通信识别号，已经存在了!");