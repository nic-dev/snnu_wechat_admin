<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="header">
	<a id="hd_left" href="<c:url value="/"/>"></a>
	<shiro:authenticated>  
	<div class="user">
		<img src="<c:url value="/static/images/admin.jpg"/>" id="user_head" />
		<div class="prev">
			<a href="#">订阅号</a>
		</div>
		<div class="info">
			<span class="name">${currentUser.userName}</span><a class="opt"
				href="<c:url value="/system/logout.html"/>">退出</a>
		</div>
	</div>
	</shiro:authenticated>
</div>