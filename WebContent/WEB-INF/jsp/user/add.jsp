<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet"
	href="<c:url value="/static/styles/user/add.css"/>" />
	<script src="<c:url value="/static/scripts/lib/sha1.js"/>">  </script>
</head>
<body>
	<%@include file="../common/header.jsp"%>

	<div id="container">
		<%@ include file="../common/sidebar.jsp"%>
		<div id="main">
			<div class="title">用户管理</div>
			<div class="type_list">
				<ul>
					<li class="current"><a href="<c:url value="/system/admin/user/list.html"/>">
							用户管理</a>
					</li>

				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav">
				<ul>


				</ul>
			</div>
			<div class="content ">
				<div class="form_area">
					<form id="save_form" action="<c:url value="/system/admin/user/add.json"/>" method="post" class="form-horizontal">
						<fieldset class="form-group ">

							<input type="hidden" name="id" id="id" value="${user.id}" /> <label
								for="userName" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" name="userName" class="form-control"
									id="user_name" value="${user.userName }" />
							</div>
						</fieldset>
						<fieldset class="form-group">
							<label for="password" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control"
									id="password" value="" />
							</div>
						</fieldset>
						<fieldset class="form-group">
							<label for="rePassword" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-10">
								<input type="password" name="rePassword"
									class="col-sm-10 form-control" id="re_password" value="" />
							</div>
						</fieldset>
						<fieldset class="form-group">
							<label for="roleIdArr" class="col-sm-2 control-label">选择角色</label>
							<div class="col-sm-10">
							
								<select multiple="multiple" class="form-control" name="roleIdArr" id="role_id_arr">
									<c:forEach var="item" items="${roleList}" varStatus="sta">
										<option value="${item.id }">${item.description}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="description" id="description" value="">
							</div>
						</fieldset>
						<div class="form-group">
							<div class="col-sm-offset-5 col-sm-7">
								<button class="post btn mybtn btn-success" id="save_btn">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>


	<script>
		$(document).ready(function() {
			wechat.user.add.init();

		});
		$$namespace('wechat.user.add');
		wechat.user.add.data={
		'validation' : {
				'userName' :{ type:'string',min:2,max:20, msg: '用户名不能为空且不能超过20个字'},
				'password' :{ type:'string',min:6,msg: '密码不能小于6个字'},
				'rePassword' :{type:'condition',target:'password', msg: '两次输入密码不一致'},
				'roleIdArr':{type:'notNull',msg:'请至少选择一个角色'}
			}
		};
		wechat.user.add.init=function(){
			var roleIdArr='${user.roleIds}'.split(',');
			$('#role_id_arr option').each(function(){
			if($$contains(roleIdArr,$(this).attr('value'))){
				$(this).attr('selected','selected');
			}
			});
		};
		wechat.user.add.preSubmit=function(){
		
			$('#password').val(hex_sha1($('#password').val()));
			var descriptionArr=[];
			$('#role_id_arr option').each(function(){
				if($(this).attr('selected')=='selected'){
				descriptionArr.push($(this).text());
				}
			});
			$('#description').val(descriptionArr.join(','));
		};
		$('#save_btn').click(function(){
			var result=$$validator(wechat.user.add.data.validation);
			if(result.errCode!='0'){
				$$alert({content:result.msg});
				return false;
			}
			wechat.user.add.preSubmit();
			$.post($('#save_form').attr('action'),$('#save_form').serialize(),function(result){
				if(result.errCode!='0'){
					$$alert({content:result.msg});
				}
				else{
					$('#id').val(result.data.userForm.id);
					$$alert({content:'用户保存成功',callback:function(){
						window.location='<c:url value="/system/admin/user/list.html"/>';
					}});
				}
			},'json');
			return false;
		});
		
	</script>
</body>
</html>