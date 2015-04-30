<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet" href="<c:url value="/static/styles/user/list.css"/>" />
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
							用户管理</a></li>
					
				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav">
				<ul>
					<li>
						<a class="  btn mybtn btn-success"
							id="create_user" href="<c:url value="/system/admin/user/add.html"/>">创建用户</a></li>
					
				</ul>
			</div>
			<div class="content">
				<table id="user_list" class="table table-bordered table-hover">
				<thead><tr><th>序号</th><th>用户名</th><th>角色</th><th>操作</th> </tr> </thead>
				<tbody>
				<c:forEach var="item" items="${pageModel.page.list }" varStatus="sta">
				<tr class="${sta.index%2==0?'success':'' }">
				<td>${sta.index+1 }</td><td>${item.userName}</td><td>${item.description}</td><td><div class="opt ${item.id==currentUser.id?'hi':''}"><a href="<c:url value="/system/admin/user/edit.html?id=${item.id }"/>"><i class="edit" title="编辑"></i></a><a href="javascript:void(0);" class="" ><i class="del" user_id="${item.id}" title="删除"></i></a></div> </td>
				</tr>
				</c:forEach>
				</tbody>
				 </table>
				<div class="clear"></div>
				<div id="page"></div>
			</div>

		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>


	<script>
			$(document).ready(function(){
				$$pager({url:'<c:url value="/system/admin/user/list.html"/>',
					currentPageNum:${pageModel.pageForm.pageNum},
					totalSize:${pageModel.page.count},
					pageSize:${pageModel.pageForm.pageSize},
					parentDiv:$('#page')
				});
				
			});
			$$namespace('wechat.user');
	
	 $('#user_list .del').click(function(){
	 	var selId=$(this).attr('user_id');
	 	$$alertFloat({posRef:$(this),content:'您确定要删除吗？',callback:function(event){
					$.post('<c:url value="/system/admin/user/delete.json"/>',{'id':selId},function(result){
						
						if(result.errCode=='0'){
						$$alert({content:'删除成功',callback:function(){
							window.location='<c:url value="/system/admin/user/list.html"/>';
						
						}});
				//	 $(this).parents('li').hide();
						 
						}else{
						
						}
						
						event.data.alertFloat.hide();
					},'json').error(function(){event.data.alertFloat.hide();alert('error');});
				}});
	 });
			</script>
</body>
</html>