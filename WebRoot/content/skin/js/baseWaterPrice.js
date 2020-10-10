/**********************************区域功能脚本***************************************************/
/**
 * 初始化页面
 */
$(function(){
	$(".leftTree").height($(".left").outerHeight()- 10);
});

function getSelectStyleInfo(_this) {
	$(_this).parent().parent().find("a").removeAttr("style");
	$(_this).css("backgroundColor","#66c3ec");
	$(_this).css("height","31px");
	$(_this).css("line-height","31px");
	$(_this).css("border-top","1px solid #83ceed");
	$(_this).css("border-bottom","1px solid #83ceed");
	$(_this).css("border-right","3px solid #f8881c");
	$(_this).css("border-left","3px solid #66c3ec");
	$(_this).css("text-align","center");
}

/**
 * 点击区域，加载右侧参数设置列表
 * @param params
 * @param flag 是否保留阴影遮罩
 */
function loadInfo(_this){
	var url = $(_this).data('src');
	var flag = false;
	showMark();
	$.get(url,function(data){
		$("#baseWaterPriceContent").empty().html(data);
		if(flag==undefined || !flag){
			hideMark();
		}else{
			hideMarkLoading();
		}
	});
	getSelectStyleInfo(_this);
}

/**
 * 保存/编辑公用方法
 */
function addAndEditDevice(){
	 var createTimeStr = $("#createTime").val();
	 var editTimeStr =  $("#editTime").val();
	 var param = $("#baseWaterPriceForm").serialize();
	 var uid = $("#waterId").val();
	 param = param+"&createTimeStr="+createTimeStr+"&editTimeStr="+editTimeStr+"&id="+uid;
	 var showConfirm = true;
	 if(validateBDIForm()){
		 $.ajax({
			 	type:"POST",
			 	url:"/waterPrive/saveParam.do",
				data:param,
				dataType:"json",
				success:function(data){
				 fnDSuccess(data,showConfirm);
   				 if(data.success){
   					loadBaseWaterPrice(dom);
   				 }else{
   					 $.Popup.close();
   				 }
			 }
		 });
	}
}

/**
 * 属性校验
 * @returns
 */
function validateBDIForm(){ 
	return $("#baseWaterPriceForm").validate({
		rules:{
			standardPrice: {
				required: true,
			    maxlength: 16,
				number: true
			},
			exceedPrice: {
				required: true,
			    maxlength: 16,
				number: true
			},
			useWaterType : {
				required: true,
				specialCharValidate: true,
				maxlength: 64
			}
		},
		messages:{
			standardPrice: {
				number: "必须为数字",
				maxlength:"必须为数字"
			},
			exceedPrice: {
				number: "必须为数字",
				maxlength:"必须为数字"
			},
			useWaterType : {
            	required: "必填项",
				specialCharValidate: "不能包含特殊字符",
				maxlength:"不超过32个字"
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