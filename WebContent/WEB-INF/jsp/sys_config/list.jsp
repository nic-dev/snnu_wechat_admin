<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet"
	href="<c:url value="/static/styles/sys_config/list.css"/>" />
</head>
<body>
	<%@include file="../common/header.jsp"%>

	<div id="container">
		<%@ include file="../common/sidebar.jsp"%>
		<div id="main">
			<div class="title">系统配置</div>
			<div class="type_list">
				<ul>
					<li class="current"><a
						href="<c:url value="/system/admin/sys_config/list.html"/>">
							系统配置</a>
					</li>

				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav">
				<ul>
					<li><a class="  btn mybtn btn-success h" id="create_sys_config"
						href="<c:url value="/system/admin/sys_config/add.html"/>">创建配置</a>
					</li>

				</ul>
			</div>
			<div class="content">
				<table id="sys_config_list" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>配置项</th>
							<th>值</th>
							<th>描述</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list }" varStatus="sta">
							<tr class="${sta.index%2==0?'success':'' }">
								<td>${sta.index+1 }</td>
								<td>${item.name}</td>
								<td class="value">${item.value}</td>
								<td>${item.description}</td>
								<td><div class="opt" ><a href="javascript:void(0);"><i class="edit" title="编辑" sys_config_id="${item.id}"></i></a></div></td>
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
		
			$$namespace('wechat.sys_config');
	
	 $('#sys_config_list .edit').click(function(){
	 	var parentTrEle=$($(this).parents('tr')[0]);
	 	var selId=$(this).attr('sys_config_id');
	 	$$alertFloat({posRef:$(this),content:'<span>请输入新值<span><input type="text" class="form-control" name="" id="newValue" />',callback:function(event){
					var newValue=$('#newValue').val();
					if(newValue.trim()==''){
						$$alert({type:'error',content:'输入值不能为空'});
						return;
					}
					
					$.post('<c:url value="/system/admin/sys_config/update_sys_config_value.json"/>',{'id':selId,'value':newValue},function(result){
						
						if(result.errCode=='0'){
						$('.value',parentTrEle).text(newValue);
						$$alert({content:'编辑成功',callback:function(){
							
						}});
				
						 
						}else{
						
						}
						
						event.data.alertFloat.hide();
					},'json').error(function(){event.data.alertFloat.hide();alert('error');});
				}});
	 });
			</script>
</body>
</html>