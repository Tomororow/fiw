/**********************************菜单功能脚本***************************************************/

/**
 * 初始化页面
 */
$(function(){
	$("#checkAll").live("click",function(){
		var check = $(this).attr("checked");
		var ckList = $("#sysMenuList tbody input[type='checkbox']");
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
	loadMenuList(params);
}

/**
 * 从新加载
 */
function loadMenuList(params,flag){
	var url = "sysMenu/index.do";
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
 * 子菜单管理
 */
function menuManager(menuId,menuName,menuCode){
/*	if(menuCode=="2"){
		$.Popup.create({ title: "提示", content: ""+menuName+"没有子菜单管理"});
		return false;
	}else{*/
		var contentMsg = {
		           title: "子菜单管理",   
		           url:"sysMenu/menuPage.do",
		           paraData:{id:menuId},
		           width:"500",
		           requestMethod: 'ajax',
		       };
	    $.Popup.create(contentMsg);
	/*}*/
}

/**
 * 查询父菜单名称
 */
function searchMenu(){
	var menuName = $("#menuName").val();
	if(menuName=='请输入菜单名称'){
		menuName = "";
	}
	showMark();
	$.ajax({
		url : "sysMenu/index.do",
		data : {menuName:menuName},
		success : function(data){
			$("#twoContain").html(data);
			hideMark();
	}});
}

/**
 * 新增子菜单
 */
function addChildMenu(menuId){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
  	var sNodes = treeObj.getSelectedNodes();
  	var name = sNodes[0].name;
  	var id = sNodes[0].id;
  	var level = sNodes[0].level;
  	var pid = sNodes[0].pId;
  	if(pid!=null){
  		$.Popup.create({ title: "提示", content: '最多只能添加二级的菜单!' });
    	return false;
  	}
	var contentMsg = {
       title: "新增菜单信息",   
       url:"sysMenu/addPage.do",
       width:"500",
       paraData:{id:id,level:level,menuName:name},
       requestMethod: 'ajax',
       tbar: [{
           text: "确定",
           clsName: "homebg popup-confirm",
           handler: function (thisPop) {
    		  var param = $("#sysMenuForm").serialize();
    	   	  if(validMenuForm()){
	         	  fnAjaxRequest(
					    "sysMenu/addMenu.do",
						param,
						"json",
						"POST",
						function(data){
					    	fnDSuccess(data,thisPop);
					    	if(data.success){
					    		ztreeFun($("#menuTree"),"sysMenu/getChildMenuList.do?menuId="+menuId,null);
							} 
						}
	 			    );
	    	   	  }
           }
       }]
	};
    $.Popup.create(contentMsg);
}
/**
 * 子菜单修改
 */
function editChildMenu(menuId){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
  	var sNodes = treeObj.getSelectedNodes();
  	var level = sNodes[0].level;
  	var id = sNodes[0].id;
  	if(level==0){
  		$.Popup.create({ title: "提示", content: '父菜单无法修改' });
  		return false;
  	}else{
  		 var parentName = sNodes[0].getParentNode().name;
  		 editMethod(id,parentName,0,menuId);
  	}
}
/**
 * 删除子菜单
 */
function delChildMenu(menuId){
	var treeObj = $.fn.zTree.getZTreeObj("menuTree");
  	var sNodes = treeObj.getSelectedNodes();
  	var name = sNodes[0].name;
  	var id = sNodes[0].id;
  	var level = sNodes[0].level;
    if(level==0){
    	$.Popup.create({ title: "提示", content: '父菜单无法删除!' });
    	return false;
    }else{
    	var rt = sNodes[0];
      	if(rt.children==null){
      		var confirmMsg = {
                    title: "提示",
                    content: "确定删除选中["+name+"]菜单?",
                    tbar: [{
                        text: "确定",
                        clsName: "homebg popup-confirm",
                        handler: function (thisPop) {
                        	 fnAjaxRequest(
         	         			    "sysMenu/delMenu.do",
         	        				{id:id},
         	        				"json",
         	        				"POST",
         	        				function(data){
         	        					fnDSuccess(data,thisPop);
        	         			    	if(data.success){
        	         			    		ztreeFun($("#menuTree"),"sysMenu/getChildMenuList.do?menuId="+menuId,null);
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
      	}else{
      		$.Popup.create({ title: "提示", content: '您选择的菜单存在子模块，不能删除!' });
	  		return false;
  	    }
    }
}

/**
 * 编辑公用方法
 * @param id
 * @param parentName
 * @param load
 * @param menuId
 */

function editMethod(id,parentName,load,menuId){
	  var contentMsg = {
           title: "编辑菜单信息",   
           url:"sysMenu/editPage.do",
           width:"500",
           paraData:{id:id,parentName:parentName},
           requestMethod: 'ajax',
           tbar: [{
               text: "确定",
               clsName: "homebg popup-confirm",
               handler: function (thisPop) {
        		  var param = $("#sysMenuForm").serialize();
        	   	  if(validMenuForm()){
					  fnAjaxRequest("sysMenu/editMenu.do", param, "json", "POST",
							function(data) {
								fnDSuccess(data, thisPop);
								if (data.success) {
									if (load == 1) {
										loadMenuList(null, true);
									} else {
										ztreeFun($("#menuTree"),
												"sysMenu/getChildMenuList.do?menuId="
														+ menuId, null);
									}
								}
							}
					  );
        	   	  }
               }
           }]
       };
    $.Popup.create(contentMsg);
}

/**
 * 表单验证
 */
function validMenuForm(){ 
	return $("#sysMenuForm").validate({
		rules:{
			menucode : {
			    required: true,
			    menuCodeExist: true
			},
			menuname : {
			    required: true,
			    specialCharValidate: true
			},
			menuorder : {
			    required: true,
			    digits: true
			},
			menuurl : {
			    required: true
			}
		},
		messages:{
			menucode : {
			    required: "必填项",
			    menuCodeExist:"存在了"
            },
			menuname : {
			    required: "必填项",
			    specialCharValidate:"不能包含特殊字符"
            },
			menuorder :{
			    required: "必填项",
			    digits:"整数"
            },
		    menuurl : {
			    required: "必填项"
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
 * ajax 验证菜单编码是否重复
 */
$.validator.addMethod("menuCodeExist",function(value,element){
    var menuCode = value;
	var id = $("#id").val();
    var result= false;
    // 设置同步
    $.ajaxSetup({
        async: false
    });
	$.post("sysMenu/checkMenuCodeExist.do",{
		menuCode : menuCode,
		id:id
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
},"菜单编码，已经存在了!");