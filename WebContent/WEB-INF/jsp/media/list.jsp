<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet" href="<c:url value="/static/styles/media/list.css"/>" />
</head>
<body>
	<%@include file="../common/header.jsp"%>

	<div id="container">
		<%@ include file="../common/sidebar.jsp"%>
		<div id="main">
			<div class="title">素材管理</div>
			<div class="type_list">
				<ul>
					<li><a href="<c:url value="/system/admin/message/list.html"/>">
							图文消息</a></li>
					<li
						<c:if test="${pageModel.pageForm.type==mediaType.image }"> class="current"</c:if>><a
						href="<c:url value="/system/admin/media/list.html?type=${mediaType.image}"/>">
							图片</a>
					</li>
					<li
						<c:if test="${pageModel.pageForm.type==mediaType.voice }"> class="current"</c:if>><a
						href="<c:url value="/system/admin/media/list.html?type=${mediaType.voice}"/>">
							语音</a>
					</li>
					<li
						<c:if test="${pageModel.pageForm.type==mediaType.video }"> class="current"</c:if>><a
						href="<c:url value="/system/admin/media/list.html?type=${mediaType.video}"/>">
							视频</a>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav">
				<ul>
					<li><input class="fix_upload form-control input-sm hi"
						name="media_file" id="media_file" type="file">
						<button class="fix_upload hi btn mybtn btn-success"
							id="upload_media">上传</button></li>
					<li class="waiting h"><img
						src="<c:url value='/static/images/loading.gif'/>" /></li>
					<li><span class="tips"> <c:choose>
								<c:when test="${pageModel.pageForm.type==mediaType.image}">大小: 不超过1M,    格式: jpg<!-- 大小: 不超过2M,    格式: bmp, png, jpeg,jpg, gif --></c:when>
								<c:when test="${pageModel.pageForm.type==mediaType.voice}">大小: 不超过2M,    格式: mp3,amr<!-- 大小: 不超过5M,    长度: 不超过60s,    格式: mp3, wma, wav, amr --></c:when>
								<c:when test="${pageModel.pageForm.type==mediaType.video}">大小: 不超过5M,    格式: mp4<!-- 大小: 不超过20M,   格式: rm, rmvb, wmv, avi, mpg, mpeg, mp4 --></c:when>
							</c:choose> </span></li>
				</ul>
			</div>
			<div class="content">
				<ul id="media_list">
					<c:forEach items="${pageModel.page.list}" var="media"
						varStatus="sta">
						<li media_id="${media.id }"><c:choose>
								<c:when test="${pageModel.pageForm.type==mediaType.image}">
									<img class="media" src="<c:url value="${media.path}"/>" />
								</c:when>
								<c:when test="${pageModel.pageForm.type==mediaType.voice}">
									<audio class="media" src="<c:url value="${media.path}"/>"
										controls> 您的浏览器不支持 audio 标签。
									</audio>
								</c:when>
								<c:when test="${pageModel.pageForm.type==mediaType.video}">
									<video src="<c:url value="${media.path}"/>" controls></video>
								</c:when>
							</c:choose>


							<div class="media_title">
								<c:out value="${media.name}" />
							</div>
							<div class="size">
								<fmt:formatNumber value="${media.size/1024 }" pattern=".#" />
								k

							</div>
							<div class="opt">
								<span><i class="download"></i> </span> <span><i
									class="edit"></i> </span> <span><i class="del"></i> </span>
								<div class="clear"></div>
							</div></li>
					</c:forEach>
					<li class="template h"><c:choose>
							<c:when test="${pageModel.pageForm.type==mediaType.image}">
								<img class="media" src="<c:url value="${media.path}"/>" />
							</c:when>
							<c:when test="${pageModel.pageForm.type==mediaType.voice}">
								<audio class="media" src="<c:url value="${media.path}"/>"
									controls> 您的浏览器不支持 audio 标签。
								</audio>
							</c:when>
							<c:when test="${pageModel.pageForm.type==mediaType.video}">
								<video src="<c:url value="${media.path}"/>" controls></video>
							</c:when>
						</c:choose>
						<div class="media_title"></div>
						<div class="size">0k</div>
						<div class="opt">
							<span><i class="download"></i> </span> <span><i
								class="edit"></i> </span> <span><i class="del"></i> </span>
							<div class="clear"></div>
						</div></li>


				</ul>
				<div class="clear"></div>
				<div id="page"></div>
			</div>

		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>


	<script>
			$(document).ready(function(){
				$$uploadDisplayFix();
				$$pager({url:'<c:url value="/system/admin/media/list.html?type=${pageModel.pageForm.type}"/>',
					currentPageNum:${pageModel.pageForm.pageNum},
					totalSize:${pageModel.page.count},
					pageSize:${pageModel.pageForm.pageSize},
					parentDiv:$('#page')
				});
				
			});
			$$namespace('wechat.media');
	$('#media_file').change( {
		url : '<c:url value="/system/ajax/file_upload.json?action=${pageModel.pageForm.type}"/>',
		
		action : '${pageModel.pageForm.type}',
		cancelDefault:'true',
		preFunc:function(){
			$('.mynav .waiting').show();
		},
		successFunc:function(result, status,extra){
			$('.mynav .waiting').hide();
			if(result.errCode==0){
				
			/*	var newLi=$('#media_list .template').clone();
				newLi.prependTo($('#media_list'));
				newLi.attr('class','');
				$('.media',newLi).attr('src','${pageContext.request.contextPath}'+result.url);
				$('.media_title',newLi).html(result.original);
				$('.size',newLi).html((result.size/1024).toFixed(1)+'k');
				newLi.show();*/
				window.location=$('#main .type_list .current a').attr('href');
			}
			else{
				$$alert({type:'error',content:result.msg});
			}
			
		}
	}, $$ajaxFileUpload);
	 $('#upload_media').click(function(){
	 
	 	$('#media_file').click();
	 	return false;
	 });
	 $('#media_list .download').click(function(){
	 	window.open('<c:url value="/system/admin/media/download.html"/>?id='+$(this).parents('li').attr('media_id'));
	 });
	  $('#media_list .edit').click(function(){
	    var selItem=$(this).parents('li');
	 	var selId=selItem.attr('media_id');
	 	$$alertFloat({posRef:$(this).parent(),content:'<span>编辑名称</span><span><input type="text" class="form-control" value="'+$('.media_title',selItem).text()+'"/></span>',callback:function(event){
					var newName=$('input',event.data.alertFloat).val();
					$.post('<c:url value="/system/admin/media/update_name.json"/>',{'id':selId,'name':newName,},function(result){
						
						if(result.errCode=='0'){
							$$alert({content:'名称编辑成功',});
							$('.media_title',selItem).text(newName);
						 
						}else{
							$$alert({content:result.msg,});					
						}
						event.data.alertFloat.hide();
					},'json').error(function(){event.data.alertFloat.hide();alert('error');});
				}});
	 });
	 $('#media_list .del').click(function(){
	 	var selId=$(this).parents('li').attr('media_id');
	 	$$alertFloat({posRef:$(this).parent(),content:'您确定要删除吗？',callback:function(event){
					$.post('<c:url value="/system/admin/media/delete.json"/>',{'id':selId},function(result){
						
						if(result.errCode=='0'){
						$$alert({content:'删除成功'});
						 $(this).parents('li').hide();
						 
						}else{
						
						}
						event.data.alertFloat.hide();
					},'json').error(function(){event.data.alertFloat.hide();alert('error');});
				}});
	 });
			</script>
</body>
</html>