<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="common/taglib.jsp"%>
<%@ include file="common/resources.jsp"%>
<link rel="stylesheet" href="<c:url value="/static/styles/index.css"/>" />
</head>
<body>
	<%@ include file="common/header.jsp"%>
	<div id="container">
		<%@ include file="common/sidebar.jsp"%>
		<div id="main">
			<div class="title h"></div>
			<div class="type_list h"></div>
			<div class="mynav h">
				<ul>
				</ul>

			</div>
			<div class="content">
				<div class="notice">
					<ul>
						<li><a href="javascript:void(0);">新消息:0</a></li>
						<li><a href="javascript:void(0);">新增人数:0</a></li>
						<li><a href="javascript:void(0);">总用户数:0</a></li>
					</ul>
				</div>
				<div class="list">
					<ul>
						<li class="title"><a href="javascript:void(0);">系统公告</a></li>
						<li><a href="javascript:void(0);">欢迎使用微信管理后台</a><span
							class="date">2014-08-26</span></li>

					</ul>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>

	<%@ include file="common/footer.jsp"%>
	<script>
		
	</script>
</body>
</html>