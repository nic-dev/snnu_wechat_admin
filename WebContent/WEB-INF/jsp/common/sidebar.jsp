<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sidebar">

	<ul class="menu">
		<li class="title">
			<i class="icon"><img src="<c:url value="/static/images/menu_function.png"/>" /> </i> 功能
		</li>
		<li  >
			<a href="<c:url  value="/system/admin/broadcast/index.html"/>">群发</a><span class="info"></span>
		</li>
		<li  >
			<a href="<c:url  value="/system/admin/reply/index.html"/>">自动回复</a><span class="info"></span>
		</li>
		<li  >
			<a href="<c:url value="/system/admin/custom_menu/list.html"/>">自定义菜单</a><span class="info"></span>
		</li>
	</ul>
	<ul class="menu">
		<li class="title">
			<i class="icon"><img src="<c:url value="/static/images/menu_management.png"/>" /> </i>管理
		</li>
		<li  >
			<a href="javascript:void(0)">用户管理</a><span class="info">即将上线</span>
		</li>
		<li  >
			<a href="javascript:void(0)">消息管理</a><span class="info">即将上线</span>
		</li>
		<li  >
			<a href="<c:url value="/system/admin/message/list.html"/>">素材管理</a>
		</li>
		<li class="h" >
			<a href="<c:url value="/system/admin/simul_message/list.html"/>">同步素材管理</a>
		</li>
	</ul>
	<ul class="menu">
		<li class="title">
			<i class="icon"><img src="<c:url value="/static/images/menu_developer.png"/>" /> </i>系统
		</li>
		<shiro:hasAnyRoles name="sys_admin"> 
		<li  >
			<a href="<c:url value="/system/admin/sys_config/list.html"/>">系统配置</a>
		</li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="sys_admin,admin"> 
		<li  >
			<a href="<c:url value="/system/admin/user/list.html"/>">用户管理</a>
		</li>
		</shiro:hasAnyRoles>
		<li  >
			<a href="<c:url value="/system/admin/user/password.html"/>">修改密码</a>
		</li>
		
	</ul>
	
</div>
<script>
$(document).ready(function(){
	$('li a[href*="'+window.location.pathname+'"]').parent().addClass('active');
});
</script>