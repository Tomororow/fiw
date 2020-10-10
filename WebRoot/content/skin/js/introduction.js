/**********************************系统简介脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	loadRoleList();
});

/**
 * 重新加载
 */
function loadRoleList(){
	var flag = true;
	var url = "/information/introduction.do";
	showMark();
	$.ajax({
		url : url,
		success :function(data){
			$("#introduction").html(data);
			if(flag==undefined || !flag){
				hideMark();
			}else{
				hideMarkLoading();
			}
		}
	});
}

/**
 * 保存/编辑公用方法
 */
var detEditor;
function kedit(){
	detEditor = KindEditor.create('textarea[name="content"]', {
    	cssPath : "/content/skin/adapters/kindeditor/plugins/code/prettify.css",
        allowFileManager : true,
        allowUpload : true,
        uploadJson : "../../page/information/upload_json.jsp",
        fileManagerJson : "../../page/information/file_manager_json.jsp",
        afterBlur: function(){this.sync();}
    });
}

function saveHtml(){
	var infoValue = detEditor.html();	
	 var showConfirm = true;
	 $.ajax({
		 	type:"POST",
		 	url:"/information/saveHtml.do",
			data:{
				"infoValue":infoValue
			},
			dataType:"json",
			success:function(data){
			 fnDSuccess(data,showConfirm);
			 if(data.success){
				 loadRoleList();
			 }else{
				 $.Popup.close();
			 }
		 }
	 });
}
