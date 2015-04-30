<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@include file="common/taglib.jsp"%>
<%@ include file="common/resources.jsp"%>
<link rel="stylesheet" href="<c:url value="/static/styles/login.css"/>" />
<script src="<c:url value="/static/scripts/lib/sha1.js"/>">
	
</script>

</head>
<body>
	<%@include file="./common/header.jsp"%>
	<div class="container" id="login_container">
		<div class="row">
			<div class="col-xs-offset-2 col-xs-5">
				<div class="logo">

					<img height="300x" width="450px" alt="" src="<c:url value="/static/images/login_logo.jpg"/>" />
				</div>
			</div>
			<div class=" col-xs-4">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span></span>用户登录
						</h3>
					</div>
					<div class="panel-body">
						<form id="login_form" action="<c:url value="/system/login.html" />"
							class="form-horizontal" method="post">

							<div class="form-group">
								<label for="userName" class="col-xs-3 control-label">
									用户名 </label>
								<div class="col-xs-9">
									<input name="userName" type="text" class="form-control"
										id="user_Name" placeholder="用户名" value="<shiro:principal/>" />
								</div>
							</div>

							<div class="form-group">
								<label for="password" class="col-xs-3 control-label"> 密码
								</label>
								<div class="col-xs-9">
									<input name="password" type="password" class="form-control"
										id="password" placeholder="密码" />
								</div>
							</div>
							<div
								class="alert alert-danger col-xs-offset-3 col-xs-9 ${msg==null||msg==''?'h':'' }">${msg}</div>
							<div class="form-group">
								<div class="col-xs-offset-5 col-xs-2">
									<button id="login_btn" type="submit" class="btn btn-success "
										value="登录">登录</button>

								</div>
							</div>

						</form>

					</div>

				</div>
			</div>
		</div>
	</div>


	<script>
		$$browserCheck();
		$('#login_form').submit(function() {
			$('#password').val(hex_sha1($('#password').val()));
		});
		/*
		$('#login_btn')
				.click(
						function() {
							$
									.post(
											$('#login_form').attr('action'),
											$('#login_form').serialize(),
											function(result, status) {
												if (result.errCode == '0') {
													$$alert({
														content : '登录成功'
													});
													window.location = '	${pageContext.request.contextPath}/admin/index.html';
												} else {

													$$alert({
														type : 'error',
														content : result.msg ? result.msg
																: result.data.errMsgList[0]
													});
												}
											}, 'json');
							return false;
						}); */
	</script>
</body>
</html>