<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet"
	href="<c:url value="/static/styles/user/add.css"/>" />
<script src="<c:url value="/static/scripts/lib/sha1.js"/>">
	
</script>
</head>
<body>
	<%@include file="../common/header.jsp"%>

	<div id="container">
		<%@ include file="../common/sidebar.jsp"%>
		<div id="main">
			<div class="title">用户管理</div>
			<div class="type_list">
				<ul>
					<li class="current"><a
						href="<c:url value="/system/admin/user/list.html"/>"> 修改密码</a></li>

				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav">
				<ul>


				</ul>
			</div>
			<div class="content ">
				<div class="form_area">
					<form id="save_form"
						action="<c:url value="/system/admin/user/password.json"/>"
						method="post" class="form-horizontal">

						<fieldset class="form-group">
						<input type="hidden" name="id" value="${currentUser.id }" />
								<label for="password" class="col-sm-2 control-label">新密码</label>
								<div class="col-sm-10">
								<input type="password" name="password" class="form-control"
									id="password" value="" />
							</div>
						</fieldset>
						<fieldset class="form-group">
							<label for="rePassword" class="col-sm-2 control-label">确认新密码</label>
							<div class="col-sm-10">
								<input type="password" name="rePassword"
									class="col-sm-10 form-control" id="re_password" value="" />
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
			wechat.user.password.init();

		});
		$$namespace('wechat.user.password');
		wechat.user.password.data = {
			'validation' : {
				
				'password' : {
					type : 'string',
					min : 6,
					msg : '密码不能小于6个字'
				},
				'rePassword' : {
					type : 'condition',
					target : 'password',
					msg : '两次输入密码不一致'
				}
			}
		};
		wechat.user.password.init = function() {
			var roleIdArr = '${user.roleIds}'.split(',');
			$('#role_id_arr option').each(function() {
				if ($$contains(roleIdArr, $(this).attr('value'))) {
					$(this).attr('selected', 'selected');
				}
			});
		};
		wechat.user.password.preSubmit = function() {

			$('#password').val(hex_sha1($('#password').val()));
		};
		$('#save_btn')
				.click(
						function() {
							var result = $$validator(wechat.user.password.data.validation);
							if (result.errCode != '0') {
								$$alert({
									content : result.msg
								});
								return false;
							}
							wechat.user.password.preSubmit();
							$
									.post(
											$('#save_form').attr('action'),
											$('#save_form').serialize(),
											function(result) {
												if (result.errCode != '0') {
													$$alert({
														content : result.msg
													});
												} else {
													$$alert({
														content : '用户保存成功',
														callback : function() {
															window.location = '<c:url value="/system/admin/index.html"/>';
														}
													})
												}
											}, 'json');
							return false;
						});
	</script>
</body>
</html>