<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<form id="changePwdUserForm">
	<input type="hidden" id="change_userId" name="change_userId" value="${userId}">
	<table border="0" cellspacing="1" cellpadding="3" class="pop-table">
       	<tr>
      		<td class="table-left"><label for="oldUserpwd">旧密码</label>： </td>
          	<td class="table-right">
          		<input class="pop-input-common" type="password" id="oldUserpwd" name="oldUserpwd">
          	</td>
      	</tr>
       	<tr>
      		<td class="table-left"><label for="userpwd">新密码</label>： </td>
          	<td class="table-right">
          		<input class="pop-input-common" type="password" id="userPwd" name="userPwd">
          	</td>
      	</tr>
       	<tr>
      		<td class="table-left"><label for="confirm_userpwd">确认密码</label>： </td>
          	<td class="table-right">
          		<input class="pop-input-common" type="password" id="confirm_userpwd" name="confirm_userpwd">
          	</td>
      	</tr>
	</table>
</form>
<script type="text/javascript">
	/**
	 * 表单验证
	 * @returns boolean
	 */
	function validForm(){
		return $("#changePwdUserForm").validate({
			rules: {
					oldUserpwd :{
						required: true,
						checkUserPwd:true
					},
			    	userpwd: {
	                    required: true,
	                    minlength: 6,
	                    maxlength: 16  
	                },
	                confirm_userpwd: {
	                    required: true,
	                    minlength: 6,
	                    maxlength: 16,
	                    equalTo: "#userPwd" //自带判断当前文本框值与指定ID为userpwd的文本框的值是否相同
	                }
				},
		    messages: {
			    	oldUserpwd :{
						required: "请输入旧密码",
						checkUserPwd:"旧密码不正确"
					},
			    	userpwd: {
	                    required: "请输入新密码",
	                    minlength: "密码不能小于6个字符",
	                    maxlength: "密码不能大于16个字符"
	                },
	                confirm_userpwd: {
	                    required: "请输入确认密码",
	                    minlength: "确认密码不能小于6个字符",
	                    maxlength: "确认密码不能大于16个字符",
	                    equalTo: "两次输入密码不一致"
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
	 * 保存提交
	 */
	function userFnChangePwdSubmit(thisPop){
		if(validForm()){
			var userId = $("#change_userId").val();
			var userPwd = $("#userPwd").val();
			var oldUserpwd = $("#oldUserpwd").val();
			var url = "sysUser/changePwdSave.do";
			showMark();
			fnAjaxRequest(
					url,
					{
						userId :userId,
						userPwd : userPwd,
						oldUserpwd : oldUserpwd
					},
					"json",
					"POST",
					function (data) {
						fnDSuccess(data,thisPop);
			});
		}
	}
	
	/***
	 * ajax 验证名称
	 */
	$.validator.addMethod("checkUserPwd",function(value,element){
	    var userPwd = value;
	    var userId = $("#change_userId").val();
	    var rusult = false;
	    // 设置同步
	    $.ajaxSetup({
	        async: false
	    });
	    
		$.post("sysUser/checkUserPwd.do",{
			userId : userId,
			userPwd : userPwd
		},function(data){
			if(data.success){
				rusult = false;
	    	}else{
	    		rusult = true;
	    	} 
		},"json");
		  // 恢复异步
	    $.ajaxSetup({
	        async: true
	    });
		return rusult;
	},"密码错误!");
</script>