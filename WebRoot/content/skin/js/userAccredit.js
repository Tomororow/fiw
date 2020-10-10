/*********************** 用户授权js脚本 ***********************/
 
/**
 * dom文档载入加载
 */
$(function(){
	$("#Port").val("");
	$("#Phone").val("");
	$("#Code").val("");
	
	$.ajax({
		url: '/sysUser/getValidateCode.do',
		data: {phone:"18089485943"},
		async:true,
		success: function(data){
			alert(data);
		}
	});
});

/**
 * 授权电话号码 格式校验
 * @returns {Boolean}
 */
function validatePhone(){
    var Phone = document.getElementById('Phone').value;
    var flag = true;
    if(!(/^1[34578]\d{9}$/.test(Phone))){
       	$("#Phone").next('span').remove();
		$("#Phone").after("<span id='msgInfo'></span>");
    	$("#msgInfo").html("<font style='color:red;'>手机号码格式有误</font>");
        flag = false;
    }else{
    	$("#Phone").next('span').remove();
		$("#Phone").after("<span id='msgInfo'></span>");
    	$("#msgInfo").html("<font style='color:green;'>手机号码可以使用</font>");
    }
    return flag;
}

/**
 * 获取验证码ajax调用
 */
function getValidateCode(){
	// 获取申请授权电话
	var Phone = $("#Phone").val();
	alert(validatePhone());
	
	if('' == Phone || null == Phone || undefined == Phone){
		$("#Phone").next('span').remove();
		$("#Phone").after("<span id='msgInfo'></span>");
    	$("#msgInfo").html("<font style='color:red;'>请输入手机号码</font>");
        return false;
	}else if(validatePhone()){
		alert("进入");
		$.get({
			url: "sysUser/getValidateCode.do",
			data: {phone:Phone},
			success: function(data){
				alert(data);
			}
		});
		
		/*fnAjaxRequest(
			"/sysUser/getValidateCode.do",
  			Phone,
			"JSON",
			"GET",
			function(data){
		    	fnDSuccess(data);
		    	if(data.success){
		    		alert(data);
				} 
			}
  		);*/
		
	}
};