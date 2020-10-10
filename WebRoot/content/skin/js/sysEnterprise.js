/**********************************企业功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$("#checkAll").live("click",function(){
		var check = $(this).attr("checked");
		var ckList = $("#sysEnterpriseList tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	});
});

/**
 * 查询企业名称
 */
function searchEnterPrise(){
	var enterName = $("#enterName").val();
	if(enterName=='请输入企业名称'){
		enterName = "";
	}
	showMark();
	$.ajax({
		url : "sysEnterprise/index.do",
		data : {enterName:enterName},
		success : function(data){
			$("#twoContain").html(data);
			hideMark();
	    },
	    error: function (xhr, error, thrown) {
	    	  if (error == "parsererror") {
	                console.log("AjaxRequest warning: JSON data from " +
	                    "server could not be parsed. This is caused by a JSON formatting error.");
	            } else {
	                console.log("request error");
	            }
	    	  hideMark();
	      }
		});
}
/**
 * 添加企业信息
 */
function addEnterprise(){
	 var contentMsg = {
            title: "新增企业信息",   
            url:"sysEnterprise/addEditPage.do",
            width:"500",
            requestMethod: 'ajax',
            tbar: [{
                text: "确定",
                clsName: "homebg popup-confirm",
                handler: function (thisPop) {
                	enterAddOrEdit(thisPop);
                }
            }]
        };
	 $.Popup.create(contentMsg);
}
/**
 * 编辑企业信息
 */
function editEnterPrise(){
	var chk_value =[];   
	  $('input[name="chk"]:checked').each(function(){    
		   chk_value.push($(this).val()); 
	  }); 
	  
	  if(chk_value.length>1 || chk_value==""){
		  $.Popup.create({ title: "提示", content: '请选择一项你要修改的数据' });
		  return false;
	  }
	 var contentMsg = {
           title: "编辑企业信息",   
           url:"sysEnterprise/addEditPage.do",
           width:"500",
           paraData:{id:chk_value.toString()},
           requestMethod: 'ajax',
           tbar: [{
               text: "确定",
               clsName: "homebg popup-confirm",
               handler: function (thisPop) {
            	   enterAddOrEdit(thisPop);
               }
           }]
       };
	 $.Popup.create(contentMsg);
}
/**
 * addorEdit
 */
function enterAddOrEdit(thisPop){
	  var url = "sysEnterprise/addOrEditEnterPrise.do";
	  var type =$('input[name="enterprisetype"]:checked').val();
	  var expiredtime = $("#enterpriseexpiredtime").val();
	  if(type=="2" && expiredtime==""){
		    $.Popup.create({ title: "提示", content: '请填写企业过期时间' });
			return false;
	  }
	  if(type=="1"){
			expiredtime = "";
	  }
      var param = $("#sysEnterpriseForm").serialize();
      param = param+"&expiredtime="+expiredtime;
   	  if(validateForm()){
         	  fnAjaxRequest(
         			    url,
        				param,
        				"json",
        				"POST",
        				function(data){
         			    	fnDSuccess(data,thisPop);
          					if(data.success){
          						loadEnterList(null,true);
          					}else{
          						//关闭新增的弹出窗
          						$.Popup.close(thisPop);
          					}
        				}
        	   );
   	  }
}

/**
 * 从新加载
 */
function loadEnterList(params,flag){
	var url = "sysEnterprise/index.do";
	showMark();
	$.ajax({
		url : url,
		data : params,
		success :function(data){
			$("#twoContain").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		}
	});
	
}

/**
 * 配置企业所拥有的要素
 */
function factorConfig(id,name){
	 var contentMsg = {
			   title:'【'+name+'】下应用的要素配置',
	           url:"sysEnterprise/factorConfig.do",
	           width:"650",
	           height:"200",
	           paraData:{id:id},
	           requestMethod: 'ajax',
	           tbar: [{
	               text: "确定",
	               clsName: "homebg popup-confirm",
	               handler: function (thisPop) {
	            	   saveEnterFactor(thisPop);
	               }
	           }]
	       };
		 $.Popup.create(contentMsg);
}



/**
 * 配置企业可见菜单
 * @param enterId 所属企业id
 */
function setSeeMenuInfo(enterId){
	 var contentMsg = {
	           title: "配置可见菜单信息",   
	           url:"sysEnterprise/setSeeMenuInfo.do",
	           paraData:{enterId : enterId},
	           requestMethod: 'ajax',
	           tbar: [{
	               text: "确定",
	               clsName: "homebg popup-confirm",
	               handler: function (thisPop) {
	            	   saveSeeMenu(enterId,thisPop);
	               }
	           }]
	       };
		 $.Popup.create(contentMsg);
}
/**
 * 保存企业在站点下选择的要素
 */
function saveEnterFactor(thisPop){
	  var enterPriseId = $("#enterPriseId").val();//企业id
	  var deId = [];
	  
	//读取factorChk
	  var selectRow = $("#factoryList ul li input[type='checkbox']:checked");
	  for (var i = 0; i < selectRow.length; i++) {
	    	deId.push(selectRow[i].value);
	  }
	  var url = "sysEnterprise/saveEnterFactor.do";
	  fnAjaxRequest(
				url,
				{enterPriseId:enterPriseId,ids:deId.toString()},
				"json",
				"POST",
				function(data){
					fnDSuccess(data,thisPop);
				}
	   );
}
/**
 * 要素配置全选
 */
function factorAll(){
    var check = $("#factorAll").attr("checked");
	var ckList = $("#enterFactorList li input[type='checkbox']");
	if(check){
		ckList.attr("checked",true);
	}else{
		ckList.removeAttr("checked");
	}
}


/**
 * 菜单配置全选
 */
function selectAll(){
	var treeObj = $.fn.zTree.getZTreeObj("seeMenuInfoTree");
    if ($("#selectAll").attr("checked")) {  
         treeObj.checkAllNodes(true);
    } else {  
    	 treeObj.checkAllNodes(false);  
    }  
}
/**
 * 应用——菜单树初始化
 * @param roleId
 * @param roleName
 * @param enterId
 */
var seeMenuTreeObj;
function seeMenuTreeInit(enterId){
	$.getJSON(
			"sysEnterprise/getMenuTree.do",{enterId:enterId},
			function(data){
				var setting = {
					 	data:{ 
					        simpleData : {  
					            enable : true 
					        }  
					    },  
						check: {
							enable: true
						},
						callback : {
							onClick : enterMenuTreeOnClick
						}
				}; 
				var zNodes = data;
				
				seeMenuTreeObj = $.fn.zTree.init($("#seeMenuInfoTree"), setting, zNodes);
				//展开选中节点
				var parentNodes = seeMenuTreeObj.getNodesByParam("isParent",true);
				//console.log(parentNodes);
				for(var i=0;i<parentNodes.length;i++){
			    	var node = parentNodes[i];
			    	if(node.check_Child_State == 1 || node.check_Child_State==2){
			    		seeMenuTreeObj.expandNode(node, true);
			    	}
			    }
			});
	
}
/**
 * ztree节点点击
 * @param event
 * @param treeId
 * @param treeNode
 */
function enterMenuTreeOnClick(event, treeId, treeNode) {
	seeMenuTreeObj.checkNode(treeNode,null,true);
}
/**
 * 保存选中的可见菜单
 * @param enterId
 */
function saveSeeMenu(enterId,thisPop){
	   var treeObj=$.fn.zTree.getZTreeObj("seeMenuInfoTree");
       var nodes=treeObj.getCheckedNodes(true);
       var menuArray="";
       for(var i=0;i<nodes.length;i++){
    	  menuArray+=nodes[i].id + ",";
       }
	   $.post("sysEnterprise/saveSeeMenu.do",{menuArray:menuArray,enterId:enterId},function(data){
		    fnDSuccess(data,thisPop);
		    if(!data.success){
		    	//关闭弹出的菜单配置框
				$.Popup.close(thisPop);
		    }
	   },"json");
}

function validateForm(){ 
	return $("#sysEnterpriseForm").validate({
		rules:{
			enterprisecode : {
			    required: true,
			    codeExist: true,
			    specialCharValidate: true
			   },
			enterprisename : {
			    required: true,
			    specialCharValidate: true
			   },
			enterprisedevicenum :{
			    required: true,
			    digits: true
			   },
			enterprisesmsnum :"digits",
			enterprisetel:{
				 specialCharValidate: true
		    },
		    enterprisefax:{
				 specialCharValidate: true
		    }
		},
		messages:{
			enterprisecode : {
			    required: "必填项",
			    codeExist:"存在了",
			    specialCharValidate: "不能包含特殊字符"
              },
			enterprisename : {
			    required: "必填项",
			    specialCharValidate: "不能包含特殊字符"
			},
			enterprisedevicenum:{
			    required: "必填项",
			    digits:"整数"
            },
			enterprisesmsnum :"整数",
			enterprisetel:{
				 specialCharValidate: "不能包含特殊字符"
		    },
		    enterprisefax:{
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
 * ajax 验证企业编码
 */
$.validator.addMethod("codeExist",function(value,element){
    var code = value;
    var id = $("#id").val();
    var result= false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
	$.post("sysEnterprise/checkCodeExist.do",{
		code : code,
		id : id
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

/**
 * 企业类型动态显示时间
 */
function viewPriseExpired(val){
	if(val=="2"){
	   if(window.ActiveXObject)
		  $("#priseexpired").css("display","block");
       else
		  $("#priseexpired").css("display","table-row");
	}else{
		  $("#priseexpired").css("display","none");
	}
}
/**
 * 分页查询
 * @param page
 */
function changePage(page){
	var params = {
			pageNo:page
			
	};
	loadEnterList(params);
}
