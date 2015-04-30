<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet" href="<c:url value="/static/styles/message/single.css"/>" />
<script
		src="<c:url value="/static/plugins/ueditor/ueditor.config.js"/> "></script>
<script 
		src="<c:url value="/static/plugins/ueditor/ueditor.all.js"/>  "></script>
</head>
<body>
	<%@include file="../common/header.jsp"%>

	<div id="container">
		<%@ include file="../common/sidebar.jsp"%>
		<div id="main">
			<div class="title">素材管理</div>
			<div class="type_list">
				<ul>
					
					<li class="current"><a
						href="<c:url value="/system/admin/message/list.html"/>"> 图文消息 </a></li>
					<li>
							<a href="<c:url value="/system/admin/media/list.html?type=${mediaType.image}"/>"> 图片</a>
						</li>
						<li>
							<a href="<c:url value="/system/admin/media/list.html?type=${mediaType.voice}"/>"> 语音</a>
						</li>
						<li>
							<a href="<c:url value="/system/admin/media/list.html?type=${mediaType.video}"/>"> 视频</a>
						</li>
				
				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav">
				<ul>
					<li><a href="#">图文消息</a></li>
					<li>/</li>
					<li><c:choose>
							<c:when test="${msg==null}">新建</c:when>
							<c:otherwise>编辑</c:otherwise>
						</c:choose>图文消息</li>
				</ul>
			</div>
			<div class="content">
				<div class="cont_left">
					<div class="title">
						<c:choose>
							<c:when test="${msg==null}">标题</c:when>
							<c:otherwise><c:out value="${msg.title}"></c:out></c:otherwise>
						</c:choose>
					</div>
					<div class="cover">
						<c:choose>
							<c:when test="${msg==null}">封面图片</c:when>
							<c:otherwise>
								<img src="<c:url value="${msg.cover }"/>" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="cont_right">
					<form id="save_form" action="<c:url value="/system/admin/message/save.json"/>"
						method="post">

						<fieldset>
							<label for="title"> 标题 </label> <input type="hidden" id="id"
								name="id" value="${msg.id}" /> 
								<input type="hidden" name="mediaId" id="media_id"
								value="${msg.mediaId}"/>
								<input type="hidden" id="type"
								name="type" value="1" /><input name="title" id="title"
								type="text" value="<c:out value="${msg.title}"/>" placeholder="" />
						</fieldset>
						<fieldset>
							<label for="author"> 作者 <span class="tips">(选填)</span> </label> <input
								name="author" id="author" type="text" value="<c:out value="${msg.author}"/>"
								placeholder="" />
						</fieldset>
						<fieldset id="image_file_field">
							<label for="image_file"> 封面 <span class="tips">(大图片建议尺寸：360像素
									* 200像素)</span> </label>
									<input type="hidden" name="thumbMediaId" id="thumb_media_id"
								value="${msg.thumbMediaId}"> 
									 <input type="hidden" name="cover" id="cover"
								value="${msg.cover}"> <input class="fix_upload form-control input-sm hi"
								name="image_file" id="image_file" type="file"  />
							<button class="fix_upload hi btn mybtn btn-success" id="upload_image" onload="alert('a');">
								上传</button>
							<span class="waiting h" id="imageWaiting"> <img
								src="<c:url value="/static/images/loading.gif"/>" /> </span>
							<div class="preview  <c:if test="${msg.cover==null}">h</c:if>"
								id="imagePreview">
								<img src="<c:url value="${msg.cover }"/>" width="360px" height="200px" />
							</div>
						</fieldset>

						<fieldset>
							<input type="checkbox" id="isCoverInContent"
								name="isCoverInContent" value="1"
								<c:if test="${msg==null||msg.isCoverInContent==1 }"> checked="checked"</c:if> />
							<span class="tips">封面图片显示在正文中</span>
						</fieldset>
						<fieldset>
							<div
								class="more <c:if test="${msg!=null&&msg.summary!=''}">h</c:if>"
								id="add_summary">添加摘要</div>
							<div class="<c:if test="${msg==null||msg.summary==''}">h</c:if>">
								<label for="summary"> 摘要 <span class="tips"></span> </label>
								<textarea name="summary" id="summary"><c:out value="${msg.summary}"/></textarea>
							</div>
						</fieldset>
						<fieldset><label for="content"> 正文 <span class="tips"></span> </label><textarea name="content" id="content"><c:out value="${msg.content}"/></textarea></fieldset>
						<fieldset>
							<div
								class="more <c:if test="${msg!=null&&msg.link!=''}">h</c:if>"
								id="add_link">添加原文链接</div>
							<div class="<c:if test="${msg==null||msg.link==''}">h</c:if>">
								<label for="link"> 原文链接 <span class="tips"></span> </label> <input
									name="link" id="link" type="text" value="<c:out value="${msg.link}"/>"
									placeholder="" />
							</div>
						</fieldset>
						<fieldset>
							<div
								class="more <c:if test="${msg!=null&&msg.accessUrl!='' }">h</c:if>"
								id="add_access_url">添加访问链接</div>
							<div class="<c:if test="${msg==null||msg.accessUrl=='' }">h</c:if>">
								<label for="accessUrl"> 访问链接 <span class="tips"></span> </label> <input
									name="accessUrl" id="access_url" type="text"
									value="<c:out value="${msg.accessUrl}"/>" placeholder="" />
							</div>
						</fieldset>
						<fieldset class="h">
							<textarea id="wechat_json" name="wechatJson"></textarea>
							<textarea id="subs" name="subs"></textarea>
						</fieldset>
					</form>
				</div>
				<div class="clear"></div>
				<div class="btnRow">
					<button class="post btn mybtn  btn-success" id="save_btn">保存</button>
					<button class="btn mybtn btn-info h">预览</button>
				</div>
			</div>
			<div class="clear"></div>

		</div>
	</div>

	<%@ include file="../common/footer.jsp"%>

	
	<script >

	window.UEDITOR_HOME_URL='${pageContext.request.contextPath}';
	    var editor = UE.getEditor('content',{
	
	imageUrlPrefix:'${pageContext.request.contextPath}', 
	config:'0',
    imageActionName: ''+global.data.config.image, 
	serverUrl:'<c:url value="/system/ajax/file_upload.json"/>',
    autoHeightEnabled: false,
    autoFloatEnabled: false,
	initialFrameWidth:500,
	initialFrameHeight:150,
	saveInterval: 60000
});

	</script>
	
	<script>
	
	$(document).ready(function(){
		$$uploadDisplayFix();
	});
		$$namespace('wechat.single');
		wechat.single.data = {
			'errors' : {
				'title' : '标题不能为空且不能超过64个字',
				'cover' : '必须插入一张图片',
				'content' : '正文不能为空且不能超过20000个字'
			},
			msgs:{'0':{}}
		};
		wechat.single.validator = function() {
			var result = {
				'errCode' : 0
			};;
		

				
				if (!$('#title').val()
						|| $('#title').val().length<1||$('#title').val().length>64) {
					result.errCode = 1;

					result.msg = wechat.single.data.errors.title;
					
				}
				else if (!$('#cover').val() || $('#cover').val() .length < 1) {
					result.errCode = 1;
					result.msg = wechat.single.data.errors.cover;
					
				}
				else if (!editor.getContent() 
						|| editor.getContent() .length<1||editor.getContent() .length>20000) {//此处不精确
					result.errCode = 1;
					result.msg = wechat.single.data.errors.content;
					
				}
			
		
			return result;
		};
		wechat.single.createWechatJson = function() {
			var nameMapping={'title':'title','author':'author', 'thumbMediaId': 'thumb_media_id','content':'content','summary':'digest','link':'content_source_url','isCoverInContent':'show_cover_pic','accessUrl':'url','cover':'picurl'};
		 	var msg=wechat.single.data.msgs['0'];
		 	var articleArr=[];
		 	var article={};
		 	article.order=0;
		 	for (var p in msg){
		 		if(nameMapping[p]){
		 			article[nameMapping[p]]=msg[p];
		 		}
		 	};
		 	articleArr.push(article);
		 
		 	var wechatMsgs={'articles':articleArr};
			
			return JSON.stringify(wechatMsgs);
		};
		wechat.single.preSubmit = function() {
			wechat.single.data.msgs['0'] = $$form2json($('#save_form'));
			$('#subs').val(JSON.stringify(wechat.single.data.msgs));
			$('#wechat_json').val(wechat.single.createWechatJson ());	
		};
		$('#image_file')
				.change(
						{
							url : '<c:url value="/system/ajax/file_upload.json?action='+global.data.config.image+'"/>',
							divSelector : '#image_file_field',
							action : '1',
							context:'${pageContext.request.contextPath}',
							successFunc : function(result, status, extra) {
								if (result.errCode == 0) {
									$('#main .cont_left .cover')
											.html(
													'<img src="${pageContext.request.contextPath}'+result.url+'" width="300px" height="160px"/>');
									$('#cover').val(result.url);
									$('#thumb_media_id').val(result.mediaId);

								} else {

									$$alert({
										type : 'error',
										content : result.msg
									});
								}
							}
						}, $$ajaxFileUpload);
		$('#upload_image').click(function() {
			$('#image_file').click();
			return false;
		});
		$('#save_btn')
				.click(
						function() {
							var result = wechat.single.validator();
							if (result.errCode != '0') {
								$$alert({
									type : 'error',
									content : result.msg
								});
								//不放在alert callback中，即时显示

								if ($('.cont_right').attr('seq') != result.seq) {

									$('.cont_left').find(
											'[seq=' + result.seq + '] .edit')
											.click();
								}
								return;
							}
							wechat.single.preSubmit();
							$
									.post(
											$('#save_form').attr('action'),
											$('#save_form').serialize(),
											function(result, status) {
												if (result.errCode != '0') {

													$$alert({
														type : 'error',
														content : result.data.errMsgList
																.join('<br/>')
													});
												} else {
													$('#id').val(result.data.messageForm.id);
													$$alert({
														content : '保存成功',
														callback : function(
																data) {

															window.location = '<c:url value="/system/admin/message/list.html"/>';
														}
													});
												}

											}, 'json');
						}

				);
		$('#title').bind('keyup', function() {
			$('#main .cont_left .title').text($(this).val());

		});
		$('#add_summary,#add_link,#add_access_url').click(function() {
			$(this).hide();
			$(this).siblings().show();
		});
	</script>
	<div class="js_confirm_dialog"></div>
</body>
</html>