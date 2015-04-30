<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="./common/taglib.jsp"%>
<%@include file="./common/resources.jsp"%>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/static/styles/unauthorized.css"/>">
</head>
<body>
	<%@include file="./common/header.jsp"%>
	<div id="container" class="vertical_wrap">

		<div class="content vertical_content">
			<div class="image"></div>
			您没有权限访问该页面
		</div>
	</div>
	<%@include file="./common/footer.jsp"%>
</body>