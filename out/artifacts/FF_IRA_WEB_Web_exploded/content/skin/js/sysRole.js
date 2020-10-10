/**********************************角色功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$("#checkAll").live("click",function(){
		var check = $(this).attr("checked");
		var ckList = $("#sysRoleList tbody input[type='checkbox']");
		if(check){
			ckList.attr("checked",true);
		}else{
			ckList.removeAttr("checked");
		}
	});
});

/**
 * 分页查询
 * @param page
 */
function changePage(page){
	var params = {
		pageNo:page
	};
	loadRoleList(params);
}

/**
 * 菜单配置全选
 */
function selectMenuAll(){
	var treeObj = $.fn.zTree.getZTreeObj("menuInfoTree");
    if ($("#selectMenuAll").attr("checked")) {  
         treeObj.checkAllNodes(true);
    } else {  
    	 treeObj.checkAllNodes(false);  
    }  
}

/**
 * 重新加载
 */
function loadRoleList(params,flag){
	var url = "sysRole/index.do";
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
 * 查询角色名称
 */
function searchSysRole(){
	var roleName = $("#roleName").val();
	if(roleName=='请输入角色名称'){
		roleName = "";
	}
	$.ajax({
		url : "sysRole/index.do",
		data : {searchRoleName:roleName},
		success : function(data){
			$("#twoContain").html(data);
		}});
}

/**
 * 新增角色
 */
function addSysRole(){
	var contentMsg = {
		title: "新增角色",   
		url:"sysRole/addPage.do",
		width:"500",
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				saveRole(thisPop);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 编辑角色
 */
function editSysRole(){
	var selectRow = $("#sysRoleList tbody input[type='checkbox']:checked");
	if (selectRow.length != 1) {
		$.Popup.create({ title: "提示", content: "请选择一条角色数据"});
		return;
	}
	var id = $(selectRow[0]).val();
	var contentMsg = {
		title: "修改角色",   
		url:"sysRole/editPage.do",
		width:"500",
		paraData:{id:id},
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				saveEditRole(thisPop);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 删除角色
 */
function delSysRole(){
	var delIds = [];
	var selectRow = $("#sysRoleList tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		delIds.push(selectRow[i].value);
	}
	if (delIds.length == 0) {
		$.Popup.create({ title: "提示", content: "请选择角色"});
		return;
	}
	var confirmMsg = {
		title: "提示",
		content: "确定要删除选中的角色?",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest(
					"sysRole/checkUserRoleBy.do",
					{items: delIds.toString()},
					"json",
					"POST",
					function(data){
						if(data.exist){
							var confirmMsg1 = {
								title: "提示",
								content: "选中角色中存在关联用户,是否删除?",
								tbar: [{
									text: "确定",
									clsName: "homebg popup-confirm",
									handler: function (thisPop) {
										fnAjaxRequest(
											"sysRole/delSysRole.do",
											{items: delIds.toString()},
											"json",
											"POST",
											function(data){
												fnDSuccess(data,thisPop);
												if(data.success){
													loadRoleList(null,true);
												} 
											}
										);
									}
								}, {
									text: "取消",
									clsName: "homebg popup-cancel",
									handler: function (thisObj) {
										$.Popup.close(thisObj);
									}
								}]
							};
							$.Popup.create(confirmMsg1);
						}else{
							fnAjaxRequest(
								"sysRole/delSysRole.do",
								{items: delIds.toString()},
								"json",
								"POST",
								function (data) {
									fnDSuccess(data,thisPop);
									if(data.success){
										loadRoleList(null,true);
									}
							});
						}
					}
				);
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
 * 新增权限保存
 * @param thisPop
 */
function saveRole(thisPop){
	var url = "sysRole/addSysRole.do";
	var param = $("#sysRoleForm").serialize();
	
	// 角色编码  唯一校验
	var valForm = validRoleForm();
	var existRoleCode = checkRoleCodeExist();
	
	// 判断过后  提交表单
	if(valForm && existRoleCode){
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadRoleList(null, true);
			}
		});
	}
}

/**
 * 修改权限保存
 * @param thisPop
 */
function saveEditRole(thisPop){
	var url = "sysRole/editSysRole.do";
	var param = $("#sysRoleForm").serialize();
	
	// 表单非空验证
	var valForm = validRoleForm();
	
	// 判断过后  提交表单
	if(valForm){
		fnAjaxRequest(url, param, "json", "POST", function(data) {
			fnDSuccess(data, thisPop);
			if (data.success) {
				loadRoleList(null, true);
			}
		});
	}
}

/**
 * 配置角色权限
 * 
 * @param roleId
 * @param roleName
 */
function setPermissionInfo(roleId){
	var contentMsg = {
			title: "编辑权限信息",
            url :"sysRole/setPermissionInfo.do",
            paraData:{roleId: roleId},
            width:"400",
            requestMethod: 'ajax',
            tbar: [{
                text: "确定",
                clsName: "homebg popup-confirm",
                handler: function (thisPop) {
                	rolePermissionFnAddOrEditSubmit(thisPop);
                }
            }]
        };
      $.Popup.create(contentMsg);
}

/**
 * 菜单Tree配置
 */
var menuTreeObj;
function menuTreeInit(roleId){
	var params = {
	    roleId: roleId
	};
	$.getJSON(
			"sysRole/getMenuTree.do",params,
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
							onClick : menuTreeOnClick
						}
				}; 
				var zNodes = data;
				menuTreeObj = $.fn.zTree.init($("#menuInfoTree"), setting, zNodes);
				//展开选中节点
				var parentNodes = menuTreeObj.getNodesByParam("isParent",true);
				for(var i=0;i<parentNodes.length;i++){
			    	var node = parentNodes[i];
			    	if(node.check_Child_State == 1 || node.check_Child_State==2){
			    		menuTreeObj.expandNode(node, true);
			    	}
			    }
			});
	
}

function rolePermissionFnAddOrEditSubmit(thisPop){
	var roleId = $("#roleId").val();
	/** 菜单树处理  */
	var menuIds = {};
	//获取选中的顶级菜单节点
	var topSelectMenuNodes = menuTreeObj.getNodesByFilter(getTopNodes);
	//循环选中顶级菜单节点，获取其下选中的菜单
	for(var i=0;i<topSelectMenuNodes.length;i++){
		var menuIdArray = [];
		var parentNode = topSelectMenuNodes[i];
		menuIdArray.push(parentNode.id);
		var selectChildNodes = menuTreeObj.getNodesByParam("checked", true, parentNode);
		
		for (var j=0; j < selectChildNodes.length; j++) {
			var childNode = selectChildNodes[j];
			menuIdArray.push(childNode.id);
		}
		menuIds[parentNode.id] = menuIdArray;
	}
	
    var params = {
		roleId: roleId,
		menuIds : JSON.stringify(menuIds)
    };
	$.post("sysRole/saveRoleMenuPermission.do",params,function(data){
		fnDSuccess(data,thisPop);
	},"json");
}

/**
 * ztree节点点击
 * @param event
 * @param treeId
 * @param treeNode
 */
function menuTreeOnClick(event, treeId, treeNode) {
	menuTreeObj.checkNode(treeNode,null,true);
}

/**
 * 自定义过滤方法——获取所有选中的最顶级父类
 * @param node
 * @returns {Boolean}
 */
function getTopNodes(node) {
    return ((node.checked == true || node.check_Child_State == 1 || node.check_Child_State==2)&&(node.level==0));
}

/**
 * 判断节点是否选中或存在被选中的子节点
 * @param node
 * @returns {Boolean}
 */
function isCheckedOrHasCheckedChild(node){
	return (node.checked == true || node.check_Child_State == 1 || node.check_Child_State==2);
}

/**
 * 获取子内容
 * @param nTreeObj
 * @param node
 */
function getChilds(nTreeObj, node){
	var childNodes = nTreeObj.getNodesByParam("pId",node.id);
	var array = [];
	
	for(var i=0;i<childNodes.length;i++){
		var cNode = childNodes[i];
		if(isCheckedOrHasCheckedChild(cNode)){
			var tmp = {};
			var mod = getChilds(nTreeObj,cNode);
			tmp[cNode.id]=mod;
			array.push(tmp);
		}
	}
	return array;
}

/**
 * 表单验证
 * @returns boolean
 */
function validRoleForm(){
	return $("#sysRoleForm").validate({
        rules: {
        	roleCode: {
        		required: true,
        		specialCharValidate: true
        	},
        	roleName:  {
        		required: true,
        		specialCharValidate: true
        	}
  		},
        messages: {
        	roleCode: {
        		required: "必填项",
        		specialCharValidate: "不能包含特殊字符"
        	},
        	roleName:  {
        		required: "必填项",
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

/**
 * 角色编码 唯一性校验
 */
function checkRoleCodeExist() {
	var roleCode = $("#roleCode").val();
	var flag = true;
	if(null!=roleCode && ""!=roleCode) {
		$.post("sysRole/checkRoleCodeExist.do", {
			roleCode : roleCode
		}, function(data) {
			if (data=="success") {
				$("#roleCode").next('span').remove();//移除span
				$("#roleCode").after("<span id='msgRoleInfo'></span>");
				$("#msgRoleInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以使用</font>");
			} else {
				$("#roleCode").next('span').remove();//移除span
				$("#roleCode").after("<span id='msgRoleInfo'></span>");
				$("#msgRoleInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
				flag = false;
			}
		}, "json");
		$.ajaxSetup({
			async : false
		});
	} else {
		$("#msgRoleInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必填项</font>");
		flag = false;
	}
	return flag;
}