var audio;
$(function(){
	//设置ajax全局不缓存
	$.ajaxSetup({
		cache: false
	});

	//点击行选中第一个checkbox
	$(".list-table tbody tr").live("click",function(event){
        //取点击的对象
        var obj = event ? event.target : event.srcElement;
		var tag = obj.tagName;
		var checkbox = $(this).find("input[type='checkbox']:first");
		if(checkbox != undefined){
			var checked = $(checkbox).attr("checked");
			if(tag == 'TD' || tag == 'TR') {
				checkbox.click();
	        };
		};
	});
	audio = document.getElementById('audio');
});

/**
 * 设置音频开始播放
 * @returns
 */
function playVoice(){
	audio.play();
}
/**
 * 设置音频停止播放
 * @returns
 */
function stopVoice(){
	audio.pause();
	audio.currentTime = 0; //如只暂停则不需要此行
}
/**
 * 跳闸类型遍历方法
 */
function tripsListEach(){
	
}

/**
 * AJAX请求  
 * @param {} sUrl
 * @param {} aoParams
 * @param {} dataType
 * @param {} type
 * @param {} fnCallback
 */
function fnAjaxRequest(sUrl, aoParams, dataType, type, fnCallback) {
    $.ajax({
        url: sUrl,
        data: aoParams,
        success: function (json) {
            if (json != null) {
                if (json.sError) {
                    console.log(json.sError);
                }
                fnCallback(json);
            }
        },
        dataType: dataType,
        cache: false,
        type: type,
        error: function (xhr, error, thrown) {
            if (error == "parsererror") {
                console.log("AjaxRequest warning: JSON data from " +
                    "server could not be parsed. This is caused by a JSON formatting error.");
            } else {
                console.log("request error");
            }
        }
    });
}

/**
 * 响应菜单，刷新内容
 * @param {} target 请求url
 * @param {} id 返回的html填充的div Id
 * @param {} params 请求所带的参数
 */
function showContent(target,id,params){
	if(params == undefined){
		params={};
	}
	showMark();
	$.ajax({
		url : target,
		data : params,
		success : function(data){
			$("#"+id).html(data);
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
 * 操作成功
 * @param {} data
 */
function fnDSuccess(data,thisPop,showConfirm) {
    if (data.success) {
    	$.Popup.close(thisPop);
    }
    if (showConfirm && data.msg) {
    	$.Popup.create({ 
    		title: "提示", 
    		content: data.msg, 
    		tbar: [{
	            text: "确定",
	            clsName: "homebg popup-confirm",
	            handler: function (thisPop) {
	            	$.Popup.close(thisPop);
	            }
    		}]
    	});
    }else if (data.msg) {
    	$.Popup.create({ title: "提示", content: data.msg });
    }
}

/**
 * 显示laoding
 */
function showMark(){
	 if (!$(".mask").hasClass("loading")) {
         $(".mask").addClass("loading");
     }
	 if ($(".mask").is(":hidden")) {
         $(".mask").show();
     }
}

/**
 * 隐藏laoding
 */
function hideMark(){
	if ($(".mask").hasClass("loading")) {
        $(".mask").removeClass("loading");
    }
	if ($(".mask").is(":visible")) {
    	$(".mask").hide();
    }
}

/**
 * 隐藏laoding(不关闭阴影遮罩)
 */
function hideMarkLoading(){
	if ($(".mask").hasClass("loading")) {
		$(".mask").removeClass("loading");
	}
}

/**
 * 表单验证不通过，设置错误样式及信息
 * @param errorMap
 * @param errorList
 */
function showErrors(errorMap, errorList){
	if (errorList && errorList.length > 0) {
		//console.log(errorList);
		$.each(errorList, function(index, obj) {
			var item = $(obj.element);
			// 给输入框添加出错样式
			item.addClass('input-validation-error');
			item.next('span').remove();//移除span
			item.after("<span class='field-validation-error' style='font-size:12px'>"+obj.message+"</span>");
		});
	} else {
		var item = $(this.currentElements);
		item.removeClass('input-validation-error');
		item.removeAttr("title");
	}
}

/**
 * 获取当前选中节点的所有子节点
 * @param treeId 树id,默认为ztree
 */
function getNodeIdsByTreeId(treeId){
	var defaultId = "ztree";
	if(treeId != undefined && treeId != "") defaultId = treeId;
	var treeObj = $.fn.zTree.getZTreeObj(defaultId);
	var selectdNode = treeObj.getSelectedNodes()[0];
	
	var ids = [];
	
	ids = getChildren(ids,selectdNode);
	if(typeof(selectdNode)!="undefined"){
		ids.push(selectdNode.id);
	}
	return ids;
}

/**
 * 递归获取树节点下的所有叶子节点
 * @param ids 存放叶子节点id数组
 * @param treeNode 节点
 * @returns
 */
function getChildren(ids,treeNode){
	if(treeNode!=undefined){
		if (treeNode.isParent){
			for(var obj in treeNode.children){
				var id = treeNode.children[obj].id;
				if(id!=undefined&&id!='undefined'){
					ids.push(id);
					getChildren(ids,treeNode.children[obj]);
				}
			}
		}
	}
	return ids;
}

/**
 * 判断str是否不为空
 * @returns {Boolean}
 */
function isNotEmpty(str){
	var flag = false;
	if(str!="" && str!=undefined){
		flag = true;
	}
	return flag;
}

/**
 * 判断浏览器是否为ie(ie11以下)
 * @returns {Boolean}
 */
function isIE() { 
    if (!!window.ActiveXObject || "ActiveXObject" in window)  
        return true;  
    else  
        return false;  
}