/******************************用户功能脚本******************************/
$(function(){
	// 加载系统用户
	var params = {
		pageNo:null,
		query_username:null
	};
	loadUserList(params);
	
	// 加载app用户
	var params1 = {
		pageNo:null
	};
	loadAppUserList(params1);
});

/**
 * 条件查询
 */
function searchUserList(){
	var query_username = $("#query_username").val();
	if(query_username == "请输入用户名称"){
		query_username="";
	}
	var params = {
		pid:$("#pid").val(),
		currentLevel:$("#currentLevel").val(),
		enterId:$("#currentEnterpriseId").val(),			
		query_username:query_username
	};
	loadUserList(params);
}

/**
 * 重新加载用户列表
 * @param params
 * @param flag
 */
function loadUserList(params,flag){
	showMark();
	$.get("sysUser/list.do",params,function(data){
		$("#userRight").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

/**
 * 重新加载app用户列表
 * @param params
 * @param flag
 */
function loadAppUserList(params,flag){
	showMark();
	$.get("sysUser/appUserList.do",params,function(data){
		$("#appUserRight").html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
}

function userList(node){
	var enterId = $("#enterId").val();
	var params={pid:node.id,currentLevel:node.level,
		enterId:enterId			
	};
    loadUserList(params);
}

function userList2(node){
	var enterId = $("#enterId").val();
	var params={pid:node.id,currentLevel:node.level,
		enterId:enterId			
	};
	$.get("sysUser/list.do",params,function(data){
		$("#userRight").html(data);
	});
}

/**
 * 分页查询
 * @param page
 */
function changePage(page){
	var query_username = $("#query_username").val();
	if(query_username == "请输入用户名称"){
		query_username="";
	}
	var params = {
		pageNo:page,
		query_username:query_username
	};
	loadUserList(params);
}

/**
 * 新增 
 */
function addUser() {
	var contentMsg = {
		title: "新增用户",   
		url:"sysUser/addPage.do",
		width:"600",
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				saveUser(thisPop);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 编辑用户信息
 * @param thisPop
 */
function editUser(){
	var selectRow = $("#sysUserDiv tbody input[type='checkbox']:checked");
	if (selectRow.length != 1) {
		$.Popup.create({ title: "提示", content: "请选择一条用户数据"});
		return;
	}
	var id = $(selectRow[0]).val();
	var contentMsg = {
		title: "编辑用户",   
		url:"sysUser/editPage.do",
		paraData:{id: id},
		width:"500",
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				saveEditUser(thisPop);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 用户信息修改页面
 */
function editUserInfo(id) {
	localStorage.setItem("userId", id);
	var contentMsg = {
		title: "编辑用户",   
		url:"sysUser/editPage.do",
		paraData:{id: id},
		width:"500",
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				saveEditUser(thisPop);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 用户新增保存
 * @param thisPop
 */
function saveUser(thisPop){
	var url = "sysUser/logAddUser.do";
	var param = $("#sysUserForm").serialize();
	
	// 检查下拉框必选项是否选择
	var valForm = validUserForm();
	var ckWaterArea = checkWaterAreaID();
	var ckArea = checkAreaID();
	var ckRole = checkRoleID();
	var unCode = uniqueUserCode();
	var ins = $("#userType").find("input");
	var checkTy = false;
	for(var i=0;i<ins.length;i++){
		if(ins[i].checked==true){
			checkTy = true;
		}
	}
	if(checkTy == false){
		alert("请选择账号类型...");
	}
	// 判断过后  提交表单
	if(valForm && ckWaterArea && ckArea && ckRole && unCode && checkTy){
		fnAjaxRequest(
			url,
			param,
			"json",
			"POST",
			function(data){
				fnDSuccess(data,thisPop);
				if(data.success){
					var params = {};
					loadUserList(params,true);
				}
			}
		);
	}
}

/**
 * 用户修改保存
 * @param thisPop
 */
function saveEditUser(thisPop){
	var url = "sysUser/logEditUser.do";
	var param = $("#sysUserForm").serialize();
	
	// 检查下拉框必选项是否选择
	var valForm = validUserForm();
	var ckWaterArea = checkWaterAreaID();
	var ckArea = checkAreaID();
	var ckRole = checkRoleID();
	var ins = $("#userType").find("input");
	var checkTy = false;
	for(var i=0;i<ins.length;i++){
		if(ins[i].checked==true){
			checkTy = true;
		}
	}
	debugger
	if(checkTy == false){
		alert("请选择账号类型...");
	}
	// 判断过后  提交表单
	if(valForm && ckWaterArea && ckArea && ckRole && checkTy){
		fnAjaxRequest(
			url,
			param,
			"json",
			"POST",
			function(data){
				fnDSuccess(data,thisPop);
				if(data.success){
					var params = {};
					loadUserList(params,true);
				}
			}
		);
	}
}

/**
 * 删除用户(单个删除)
 * @param id
 */
function delUserInfo(id) {
	var confirmMsg = {
		title: "提示",
		content: "确定删除选中用户?",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest(
					"sysUser/deleteUser.do",
					{id : id},
					"json",
					"POST",
					function(data){
						fnDSuccess(data,thisPop);
						var params = {
							pageNo:null,
							query_username:null
						};
						loadUserList(params,true);
						loadAppUserList(params,true);
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
 * 删除用户(批量删除)
 */
function deleteUser(){
	var aId = [];
	var selectRow = $("#sysUserDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		aId.push(selectRow[i].value);
	}
	if (aId.length == 0) {
		$.Popup.create({ title: "提示", content: "请选择用户"});
		return false;
	}
	var confirmMsg = {
		title: "提示",
		content: "确定删除选中用户?",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest(
					"sysUser/logDelUser.do",
					{items: aId.toString()},
					"json",
					"POST",
					function(data){
						fnDSuccess(data,thisPop);
						var params = {
							pid:$("#pid").val(),
							currentLevel:$("#currentLevel").val(),
							enterId:$("#currentEnterpriseId").val()		
						};
						loadUserList(params,true);
						loadAppUserList(params,true);
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
 * 密码初始化
 */
function initPwdUser(){
	var aId = [];
	var selectRow = $("#sysUserDiv tbody input[type='checkbox']:checked");
	for (var i = 0; i < selectRow.length; i++) {
		aId.push(selectRow[i].value);
	}
	if (aId.length == 0) {
		$.Popup.create({ title: "提示", content: "请选择用户"});
		return;
	}
	var confirmMsg = {
		title: "提示",
		content: "确定初始化选中用户(用户密码将被初始化为123456)?",
		width:"400",
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				fnAjaxRequest(
					"sysUser/logInitPwdUser.do",
					{items: aId.toString()},
					"json",
					"POST",
					function(data){
						fnDSuccess(data,thisPop);
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
 * 移动到对应机构的页面
 */
function moveUser(){
	var enterId = $("#currentEnterpriseId").val();
	var deId = [];
    var selectRow = $("#sysUserDiv tbody input[type='checkbox']:checked");
    for (var i = 0; i < selectRow.length; i++) {
    	deId.push(selectRow[i].value);
    }
    if (deId.length == 0) {
    	$.Popup.create({ title: "提示", content: "请选择要移动的用户"});
        return;
    }else{
    	var contentMsg = {
            title: "移动用户到机构",
            url :"sysUser/movePage.do",
            paraData:{dids: deId.toString(),enterId:enterId},
            width:"400",
            requestMethod: 'ajax',
            tbar: [{
                text: "确定",
                clsName: "homebg popup-confirm",
                handler: function (thisPop) {
                	var treeObj = $.fn.zTree.getZTreeObj("ztreeMoveUser");
                  	var sNodes = treeObj.getSelectedNodes();
                  	var rt = sNodes[0];
                  	var organId = rt.id;
                  	if(rt.children==null){
                  	    //进行分组移动操作
						fnAjaxRequest(
							"sysUser/logSaveMoveUser.do",
							{ids:deId.toString(),organId:organId},
							"json",
							"POST",
							function(data){
								if(data.success){
									$.Popup.close(thisPop);
									var params = {
										pid:$("#pid").val(),
										currentLevel:$("#currentLevel").val(),
										enterId:$("#currentEnterpriseId").val()			
									};
									loadUserList(params);
								} 
							}
						);
                  	}else{
                  	   $.Popup.create({ title: "提示", content: "只能移动到子部门下！"});
           	  		   return false;
                    }
                }
            }]
        };
      $.Popup.create(contentMsg);
    }
}


/**
 * 配置用户角色
 * @param userId
 * @param userName
 * @param enterId 所属企业id
 */
function setRoleInfo(userId, userName, enterId){
	  var contentMsg = {
           title: "配置【"+userName+"】角色",   
           url:"sysUser/setRoleInfo.do",
           paraData:{userId:userId,userName:userName,enterId:enterId},
           width:"500",
           requestMethod: 'ajax',
           tbar: [{
               text: "确定",
               clsName: "homebg popup-confirm",
               handler: function (thisPop) {
            		var nodes = roleTreeObj.getCheckedNodes(true);
            		var roleIds = [];
            	    for(var i=0;i<nodes.length;i++){
            	    	var node = nodes[i];
            	    	if(!node.isParent && node.level!=0){
            	    		roleIds.push(node.id);
            	    	}
            	    }
            	    var params = {
            	    		userId: userId,
            	    		roleIds : roleIds.join(",")
            	    };
            	    showMark();
            		$.post("sysUser/logSaveUserRole.do",params,function(data){
            			fnDSuccess(data,thisPop);
            			hideMarkLoading();
            		},"json");
               }
           }]
       };
   $.Popup.create(contentMsg);
}

/**
 * 用户角色树初始化
 * @param userId
 * @param userName
 * @param enterId
 */
var roleTreeObj;
function roleTreeInit(userId, userName, enterId){
	var params = {
		userId: userId,
		userName: userName,
		enterId:enterId
	};
	$.getJSON(
		"sysUser/getEnterpriseRole.do",params,
		function(data){
			var setting = {
			 	data:{ 
			        simpleData : {  
			            enable : true 
			        }  
			    },  
				check: {
					enable: true,
					chkStyle: "radio"
				},
				callback : {
					onClick : roleTreeOnClick
				}
			}; 
			var zNodes = data;
			
			roleTreeObj = $.fn.zTree.init($("#roleInfoTree"), setting, zNodes);
			var nodes = roleTreeObj.getNodes(); 
			roleTreeObj.expandNode(nodes[0], true);
		});
}
/**
 * ztree节点点击
 * @param event
 * @param treeId
 * @param treeNode
 */
function roleTreeOnClick(event, treeId, treeNode) {
	roleTreeObj.checkNode(treeNode,null,true);
}

/**
 * 表单验证
 * @returns boolean
 */
function validUserForm(){
	return $("#sysUserForm").validate({
		rules: {
			userCode: {
	    		required: true,
	    		specialCharValidate: true
	    	},
	    	userName: {
	    		required:true,
	    		specialCharValidate: true
	    	},
	    	userPassword: {
                required: true,
                minlength: 5  
            },
            fullName:{
            	required: true
            },
            mobile:{
            	required: true
            }
		},
	    messages: {
	    	userCode: {
	    		required: "必填项",
	    		specialCharValidate: "不能包含特殊字符"
	    	},
	    	userName: {
	    		required:"必填项",
	    		specialCharValidate: "不能包含特殊字符"
	    	},
	    	userPassword: {
                required: "请输入密码",
                minlength: $.validator.format("密码不能小于{0}个字符")
            },
            fullName:{
            	required: "必填项"
            },
            mobile:{
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

/**
 * 获取水管区域子区域级联操作
 * 	1、通过最高一级选择，如果该选择的有子节点（子区域），则将在旁边重新生成一个子区域下拉框集合
 * 	2、当选择了--请选择水管区域--选项，则将该下拉框后面的子下拉框全部清除
 */
function getSubWaterAreaId(_this) {
	// 获取到对应下拉框同一级的id=num的标签内容
	var num = $(_this).next("#num").val();
	// 下拉框选择内容不为空，则开始关联到子节点信息
	if(""!=$(_this).val()) {
		// 获取到子节点下拉框同一级的id=num的标签内容
		var subNum = $(_this).next("#num").val();
		$("#subWaterArea_"+parseInt(subNum)).empty();
		$.post("baseDeviceInfo/getChildAreaInfo.do",{
			id : $(_this).val()
		},function(data){
			// 有子区域信息集合，则开始拼接子区域下拉框信息
			if(data!=null && data!=''){
				var htmltv = "<select class='pop-select' style='display: inline-block;width:74px;' name='waterAreaId' onchange='getSubWaterAreaId(this)'>";
				htmltv += "<option value=''>--请选择水管区域--</option>";
				for(var i=0;i<data.length;i++) {
					htmltv += "<option value='"+data[i].id+"'>"+data[i].waterAreaName+"</option>";
				}
				htmltv += "</select>";
				htmltv += "<input type='hidden' id='num' name='num' value='"+(parseInt(num)+1)+"'/>";
				htmltv += "<span id='subWaterArea_"+(parseInt(num)+1)+"'></span>";
				$("#subWaterArea_"+parseInt(num)).append(htmltv);
			} else { // 该下拉框下没有子区域信息，则给出提示
				alert("该水管区域再无子一级信息");
			}
		},"json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else { // 下拉框内容为空，则将下拉框后面的信息置为空
		var subNum = $(_this).next("#num").val();
		$("#subWaterArea_"+parseInt(subNum)).empty();
	}
}

/**
 * 获取行政区域子区域级联操作
 * 同上面水管区域操作
 * @param _this
 */
function getSubAreaId(_this) {
	var num = $(_this).next("#areaNum").val();
	if(""!=$(_this).val()) {
		var subNum = $(_this).next("#areaNum").val();
		$("#subArea_"+parseInt(subNum)).empty();
		$.post("baseDeviceInfo/getChildDeviceAreaInfo.do",{
			id : $(_this).val()
		},function(data){
			if(data!=null && data!=''){
				var htmltv = "<select class='pop-select' style='display: inline-block;width:74px;' name='areaId' onchange='getSubAreaId(this)'>";
				htmltv += "<option value=''>--请选择行政区域--</option>";
				for(var i=0;i<data.length;i++) {
					htmltv += "<option value='"+data[i].id+"'>"+data[i].areaName+"</option>";
				}
				htmltv += "</select>";
				htmltv += "<input type='hidden' id='areaNum' name='areaNum' value='"+(parseInt(num)+1)+"'/>";
				htmltv += "<span id='subArea_"+(parseInt(num)+1)+"'></span>";
				$("#subArea_"+parseInt(num)).append(htmltv);
			} else {
				alert("该行政区域再无子一级信息");
			}
		},"json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else {
		var subNum = $(_this).next("#areaNum").val();
		$("#subArea_"+parseInt(subNum)).empty();
	}
}

/**
 * @description: 根据ID获取用户编码判断增加还是编辑
 * @param {} id
 */
function addOrEdit(id){
	var url = "sysUser/findUserWaterByID.do";
	fnAjaxRequest(url, {id: id}, "json", "POST",
		function(data){
			// 判断用户售水表是否有数据  有则修改  无则添加
			if(data == "success"){
				addPremission(id);
			}else{
				editPremission(id);
			}
		}
	);
}

/**
 * 新增 售水权限窗口
 */
function addPremission(id){
	var contentMsg = {
		title: "开通售水权限",
		url:"sysUser/addUserWaterPrePage.do",
		paraData:{id: id},
		width:"500",
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				addAndEditBuyWaterPre(thisPop);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * 修改 售水权限窗口
 */
function editPremission(id){
	var contentMsg = {
		title: "编辑售水权限",
		url:"sysUser/editUserWaterPage.do",
		paraData:{id: id},
		width:"500",
		requestMethod: 'ajax',
		tbar: [{
			text: "确定",
			clsName: "homebg popup-confirm",
			handler: function (thisPop) {
				addAndEditBuyWaterPre(thisPop);
			}
		}]
	};
	$.Popup.create(contentMsg);
}

/**
 * @description: 增加或修改用户售水权限
 * @param {} thisPop
 */
function addAndEditBuyWaterPre(thisPop){
	// 根据用户编码获取用户售水对象
	var userCode = $("#UserCode").val();
	var param = $("#sysUserWaterForm").serialize();
	
	// 第一个ajax请求  根据用户编码判断  用户售水表是否有值
	fnAjaxRequest("sysUser/findUserWaterByUserCode.do", {userCode: userCode}, "json", "POST",
		function(data){
			// 判断用户售水表是否有数据  有则修改  无则添加   
			if(data == "success"){
				fnAjaxRequest("sysUser/saveUserWater.do", param, "json", "POST",
					function(data){
						fnDSuccess(data,thisPop);
						if(data.success){
							var params = {};
							loadUserList(params,true);
						}
					}
				);
			}else{
				fnAjaxRequest("sysUser/editUserWater.do", param, "json", "POST",
					function(data){
						fnDSuccess(data,thisPop);
						if(data.success){
							var params = {};
							loadUserList(params,true);
						}
					}
				);
			}
		}
	);
}

/**
 * 检查水管区域必选项
 */
function checkWaterAreaID(){
	var waterAreaID = $('#waterAreaId option:selected').text();
	var flag = true;
	if(waterAreaID == "--请选择水管区域--"){ 
       	$("#waterAreaId").next('span').remove();
		$("#waterAreaId").after("<span id='msgWaterAreaInfo'></span>");
    	$("#msgWaterAreaInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必选项</font>");  
        flag = false; 
    }else{
    	$("#waterAreaId").next('span').remove();
    }
    return flag;
}

/**roleId
 * 检查行政区域必选项
 */
function checkAreaID(){
	var areaID = $('#areaId option:selected').text();
	var flag = true;
	if(areaID == "--请选择行政区域--"){ 
       	$("#areaId").next('span').remove();
		$("#areaId").after("<span id='msgAreaInfo'></span>");
    	$("#msgAreaInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必选项</font>");  
        flag = false; 
    }else{
    	$("#areaId").next('span').remove();
    }
    return flag;
}

/**
 * 检查行政区域必选项
 */
function checkRoleID(){
	var roleID = $('#roleId option:selected').text();
	var flag = true;
	if(roleID == "--所属角色--"){ 
       	$("#roleId").next('span').remove();
		$("#roleId").after("<span id='msgRoleInfo'></span>");
    	$("#msgRoleInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>必选项</font>");  
        flag = false; 
    }else{
    	$("#roleId").next('span').remove();
    }
    return flag;
}

/**
 * 电话号码 格式检查
 * @return {Boolean}
 */
function checkPhone(){ 
    var mobile = document.getElementById('mobile').value;
    var flag = true;
    if(!(/^1[34578]\d{9}$/.test(mobile))){ 
       	$("#mobile").next('span').remove();
		$("#mobile").after("<span id='msgPhoneInfo'></span>");
    	$("#msgPhoneInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>手机号码格式有误</font>");  
        flag = false; 
    }else{
    	$("#mobile").next('span').remove();
		$("#mobile").after("<span id='msgPhoneInfo'></span>");
    	$("#msgPhoneInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以使用</font>");  
    }
    return flag;
}

/**
 * 用户编码 唯一性校验
 */
function uniqueUserCode() {
	var userCode = $("#userCode").val();
	var flag = true;
	if(null!=userCode && ""!=userCode) {
		$.post("sysUser/uniqueUserCode.do", {
			userCode : userCode
		}, function(data) {
			if (data=="success") {
				$("#userCode").next('span').remove();//移除span
				$("#userCode").after("<span id='msgCodeInfo'></span>");
				$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:green;line-height:32px;'>可以使用</font>");
			} else {
				$("#userCode").next('span').remove();//移除span
				$("#userCode").after("<span id='msgCodeInfo'></span>");
				$("#msgCodeInfo").html("&nbsp;&nbsp;<font style='color:red;line-height:32px;'>已存在</font>");
				flag = false;
			}
		}, "json");
		$.ajaxSetup({
			async : false
		});
	}
	return flag;
}

/**
 * app用户注册审核页面
 */
function appAudit(id){
	var param = {"id":id};
	// ajax判断该用户是否审核过
	fnAjaxRequest("sysUser/checkIsAudit.do", param, "json", "GET",
		function(data){
			// 若未审核  则审核
			if(data == "success"){
				var contentMsg = {
					title: "App用户审核",
					url:"sysUser/appAuditPage.do",
					paraData:{id: id},
					width:"700",
					requestMethod: 'ajax',
					tbar: [{
						text: "确定",
						clsName: "homebg popup-confirm",
						handler: function (thisPop) {
							saveAppAudit(thisPop);
						}
					}]
				};
				$.Popup.create(contentMsg);
			// 审核过  则不再让审核
			}else{
				$.Popup.create({title: "温馨提示", content: "该用户已审核！"});
			}
		}
	);
}

/**
 * 保存app用户审核状态
 * @param thisPop
 */
function saveAppAudit(thisPop){
	// 序列化form表单参数
	var param = $("#appUserAuditForm").serialize();
	
	fnAjaxRequest("sysUser/appAudit.do", param, "json", "POST",
		function(data){
			fnDSuccess(data,thisPop);
			if(data == "success"){
				var params = {};
				loadAppUserList(params, true);
				$.Popup.close(thisPop);
				$.Popup.create({title: "温馨提示", content: "审核成功"});
			}
		}
	);
}

/**
 * 获取水管区域子区域级联操作
 * 	1、通过最高一级选择，如果该选择的有子节点（子区域），则将在旁边重新生成一个子区域下拉框集合
 * 	2、当选择了--请选择水管区域--选项，则将该下拉框后面的子下拉框全部清除
 */
function getAuditWaterAreaId(_this) {
	// 获取到对应下拉框同一级的id=num的标签内容
	var num = $(_this).next("#num").val();
	// 下拉框选择内容不为空，则开始关联到子节点信息
	if(""!=$(_this).val()) {
		// 获取到子节点下拉框同一级的id=num的标签内容
		var subNum = $(_this).next("#num").val();
		$("#subWaterArea_"+parseInt(subNum)).empty();
		$.post("baseDeviceInfo/getChildAreaInfo.do",{
			id : $(_this).val()
		},function(data){
			// 有子区域信息集合，则开始拼接子区域下拉框信息
			if(data!=null && data!=''){
				var htmltv = "<select class='pop-select' style='display: inline-block;width:90px;' name='waterAreaId' onchange='getAuditWaterAreaId(this)'>";
				htmltv += "<option value=''>--请选择水管区域--</option>";
				for(var i=0;i<data.length;i++) {
					htmltv += "<option value='"+data[i].id+"'>"+data[i].waterAreaName+"</option>";
				}
				htmltv += "</select>";
				htmltv += "<input type='hidden' id='num' name='num' value='"+(parseInt(num)+1)+"'/>";
				htmltv += "<span id='subWaterArea_"+(parseInt(num)+1)+"'></span>";
				$("#subWaterArea_"+parseInt(num)).append(htmltv);
			} else { // 该下拉框下没有子区域信息，则给出提示
				alert("该水管区域再无子一级信息");
			}
		},"json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else { // 下拉框内容为空，则将下拉框后面的信息置为空
		var subNum = $(_this).next("#num").val();
		$("#subWaterArea_"+parseInt(subNum)).empty();
	}
}

/**
 * 获取行政区域子区域级联操作
 * 	同上面水管区域操作
 * @param _this
 */
function getAuditAreaId(_this) {
	var num = $(_this).next("#areaNum").val();
	if(""!=$(_this).val()) {
		var subNum = $(_this).next("#areaNum").val();
		$("#subArea_"+parseInt(subNum)).empty();
		$.post("baseDeviceInfo/getChildDeviceAreaInfo.do",{
			id : $(_this).val()
		},function(data){
			if(data!=null && data!=''){
				var htmltv = "<select class='pop-select' style='display: inline-block;width:90px;' name='areaId' onchange='getAuditAreaId(this)'>";
				htmltv += "<option value=''>--请选择行政区域--</option>";
				for(var i=0;i<data.length;i++) {
					htmltv += "<option value='"+data[i].id+"'>"+data[i].areaName+"</option>";
				}
				htmltv += "</select>";
				htmltv += "<input type='hidden' id='areaNum' name='areaNum' value='"+(parseInt(num)+1)+"'/>";
				htmltv += "<span id='subArea_"+(parseInt(num)+1)+"'></span>";
				$("#subArea_"+parseInt(num)).append(htmltv);
			} else {
				alert("该行政区域再无子一级信息");
			}
		},"json");
		// 恢复异步
		$.ajaxSetup({
			async: true
		});
	} else {
		var subNum = $(_this).next("#areaNum").val();
		$("#subArea_"+parseInt(subNum)).empty();
	}
}