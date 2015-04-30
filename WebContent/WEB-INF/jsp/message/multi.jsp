<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet"
	href="<c:url value="/static/styles/message/multi.css"/>" />
<script
	src="<c:url value="/static/plugins/ueditor/ueditor.config.js"/> "></script>
<script src="<c:url value="/static/plugins/ueditor/ueditor.all.js"/>  "></script>
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
						href="<c:url value="/system/admin/message/list.html"/>"> 图文消息
					</a>
					</li>
					<li><a
						href="<c:url value="/system/admin/media/list.html?type=${mediaType.image}"/>">
							图片</a></li>
					<li><a
						href="<c:url value="/system/admin/media/list.html?type=${mediaType.voice}"/>">
							语音</a></li>
					<li><a
						href="<c:url value="/system/admin/media/list.html?type=${mediaType.video}"/>">
							视频</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav">
				<ul>
					<li><a href="#">图文消息</a>
					</li>
					<li>/</li>
					<li><c:choose>
							<c:when test="${msg==null}">新建</c:when>
							<c:otherwise>编辑</c:otherwise>
						</c:choose>图文消息</li>
				</ul>
			</div>
			<div class="content">
				<div class="cont_left">
					<div class="parent" seq="0">
						<div class="title">
							<c:choose>
								<c:when test="${msg==null}">标题</c:when>
								<c:otherwise>
									<c:out value="${msg.title}" />
								</c:otherwise>
							</c:choose>
						</div>
						<div class="cover">
							<c:choose>
								<c:when test="${msg==null}">封面图片</c:when>
								<c:otherwise>
									<img src="<c:url value="${msg.cover }"/>" width="300px"
										height="160px" />
								</c:otherwise>
							</c:choose>
						</div>
						<div class="h">
							<div class="opt vertical_wrap">
								<div class="vertical_content">
									<i class="edit"></i><i class="del hi"></i>
								</div>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="subs_area h">

						<div class="sub" seq="1">
							<div class="title">标题</div>
							<div class="cover">缩略图</div>
							<div class="h">
								<div class="opt vertical_wrap">
									<div class="vertical_content">
										<i class="edit"></i><i class="del"></i>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="add_sub">+</div>

				</div>
				<div class="cont_right_height_control"></div>
				<div class="cont_right" seq="0">
					<i class="myarrow myarrow_out_fix"></i> <i
						class="myarrow myarrow_in_fix"></i>
					<form id="save_form"
						action="<c:url value="/system/admin/message/save.json"/>"
						method="post">

						<fieldset>
							<label for="title"> 标题 </label> <input type="hidden" id="id"
								name="id" value="${msg.id}" /> 
								<input type="hidden" name="mediaId" id="media_id"
								value="${msg.mediaId}"/>
								<input type="hidden" id="type"
								name="type" value="2" /> <input name="title" id="title"
								type="text" value="<c:out value="${msg.title}"/>" />
						</fieldset>
						<fieldset>
							<label for="author"> 作者 <span class="tips">(选填)</span> </label> <input
								name="author" id="author" type="text"
								value="<c:out value="${msg.author}"/>" placeholder="" />
						</fieldset>
						<fieldset id="image_file_field">
							<label for="imageFile"> 封面 <span class="tips">(大图片建议尺寸：360像素
									* 200像素)</span> </label> <input type="hidden" name="thumbMediaId" id="thumb_media_id"
								value="${msg.thumbMediaId}"> <input type="hidden"
								name="cover" id="cover" value="${msg.cover}"> <input
								class="fix_upload form-control input-sm hi" name="imageFile"
								id="imageFile" type="file" />
							<button class="fix_upload hi btn mybtn btn-success"
								id="uploadImage">上传</button>

							<span class="waiting h" id="imageWaiting"> <img
								src="<c:url value="/static/images/loading.gif"/>" /> </span>
							<div class="preview <c:if test="${msg==null }">h</c:if>"
								id="imagePreview">
								<img src="<c:url value="${msg.cover }"/>" width="360px"
									height="200px" />
							</div>
						</fieldset>

						<fieldset>
							<input type="checkbox" id="isCoverInContent"
								name="isCoverInContent" value="1"
								<c:if test="${msg==null||msg.isCoverInContent==1 }">checked="checked"</c:if> />
							<span class="tips">封面图片显示在正文中</span>
						</fieldset>
						<fieldset class="h">
							<!-- 多图文没有摘要 -->
							<div
								class="more <c:if test="${msg!=null&&msg.summary!='' }">h</c:if> "
								id="add_summary">添加摘要</div>
							<div class="<c:if test="${msg==null||msg.summary=='' }">h</c:if>">
								<label for="summary"> 摘要 <span class="tips"></span> </label>
								<textarea name="summary" id="summary"><c:out value="${msg.summary}" /></textarea>
							</div>
						</fieldset>
						<fieldset>
							<label for="content"> 正文 <span class="tips"></span> </label>
							<textarea name="content" id="content"><c:out value="${msg.content}" /></textarea>


						</fieldset>
						<fieldset>
							<div
								class="more <c:if test="${msg!=null&&msg.link!='' }">h</c:if>"
								id="add_link">添加原文链接</div>
							<div class="<c:if test="${msg==null||msg.link=='' }">h</c:if>">
								<label for="link"> 原文链接 <span class="tips"></span> </label> <input
									name="link" id="link" type="text"
									value="<c:out value="${msg.link}"/>" placeholder="" />
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
							
							<!-- 主图文的subs属性保存时动态生产，放在textarea会出现subs[0]的subs属性不必要的嵌套  -->
							<div id="subsDiv">
								<c:out value="${msg.subs}" />
							</div>
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
	<script>
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
		$$namespace('wechat.multi');
		$(document).ready(function() {
			$$uploadDisplayFix();
			wechat.multi.initData();
			
			wechat.multi.initSubsDiv();
		});
		wechat.multi.data = {
			'msgs' : {
				'0' : {},
				'1' : {}
			},
			'errors' : {
				'title' : '标题不能为空且不能超过64个字',
				'cover' : '必须插入一张图片',
				'content' : '正文不能为空且不能超过20000个字'
			},
			'subModel':	'<div class="sub"><div class="title">标题</div><div class="cover">缩略图</div><div class="h"><div class="opt vertical_wrap"><div class="vertical_content"><i class="edit"></i><i class="del"></i></div></div></div></div>',
			'curSeq' : 0,
			'isNew' : ${msg == null},
		};
		wechat.multi.initData = function() {
			if(wechat.multi.data.isNew){
				return;
			}
			var subsJson = $('#subsDiv').text();
			//alert(subsJson);

			var subs =null; 
			try{
				subs=$.parseJSON(subsJson);
				wechat.multi.data.msgs = subs;
			}catch(err){
				
				$('#sys_error').html(err.toString());
			}
		};
		wechat.multi.initSubsDiv = function() {
			var subsArea=$('.subs_area')[0];
			subsArea=$(subsArea);
			if (!wechat.multi.data.isNew) {
				var subs=[];
				for(var subIndex in wechat.multi.data.msgs){
					if(subIndex!='0'){	
						
						if(!$.isEmptyObject(wechat.multi.data.msgs[subIndex])){
							var newSub=$(wechat.multi.data.subModel);
							subClickProc(newSub);
							
							newSub.hover(subMouseoverFunc, subMouseoutFunc);
			
							newSub.attr('seq',subIndex);//也可重新从1开始递增计数
							$('.title',newSub).text(wechat.multi.data.msgs[subIndex].title);
							$('.cover',newSub).html('<img width="100%" height="100%" src="${pageContext.request.contextPath}'+wechat.multi.data.msgs[subIndex].cover+'"/>');
							subs.push(newSub);
						}
						
					}
				}
				if(subs.length>0){
					subsArea.html('');
					for(var subIdx in subs){
						subs[subIdx].appendTo(subsArea);
					}
				}
			}
			
			subsArea.show();
		};
		wechat.multi.saveCurrent = function() {
			var saveForm = $('#save_form');
			var contLeft = $('.cont_left')[0];

			var current = $(contLeft).find(
					'[seq=' + wechat.multi.data.curSeq + ']')[0];

			wechat.multi.data.msgs[wechat.multi.data.curSeq] = $$form2json($('#save_form'));

		};
		wechat.multi.fixedResetForm = function() {
			$('#imagePreview').hide();
			$('#type').val('2');
			$('#isCoverInContent').attr('checked', 'checked');
			editor.setContent('');
		};
		wechat.multi.fixedFillForm = function() {
			
			if ($.isEmptyObject(wechat.multi.data.msgs[wechat.multi.data.curSeq])) {
				return;
			}
			var imagePath = wechat.multi.data.msgs[wechat.multi.data.curSeq].cover;

			if (imagePath) {
				$('#imagePreview img').attr('${pageContext.request.contextPath}'+imagePath);
				$('#imagePreview').show();
			}

			editor
					.setContent(wechat.multi.data.msgs[wechat.multi.data.curSeq].content);
		};
		/*
			//TODO 编写一个简易的json校验框架
			规则用json格式定义，如valiJson{'title':{'type':'regex','express':'\w{1,20}'},'age':{'type':'number','min':8,'max':100},'birthday':{'type':'date'}}
			校验时调用validator(valiObj,valiJson);//valiObj为待校验对象
		 */
		wechat.multi.validator = function() {
			var result = {};
			var msgs = wechat.multi.data.msgs;

			for ( var seq in msgs) {

				result.errCode = 1;
				result.seq = seq;
				if (!msgs[seq].title
						|| msgs[seq].title.length<1||msgs[seq].title.length>64) {

					result.msg = wechat.multi.data.errors.title;
					return result;
				}
				if (!msgs[seq].cover || msgs[seq].cover.length < 1) {

					result.msg = wechat.multi.data.errors.cover;
					return result;
				}
				if (!msgs[seq].content
						|| msgs[seq].content.length<1||msgs[seq].content.length>20000) {//此处不精确

					result.msg = wechat.multi.data.errors.content;
					return result;
				}
			}
			result = {
				'errCode' : 0
			};
			return result;
		};
		wechat.multi.createWechatJson = function() {
		var nameMapping={'title':'title','author':'author', 'thumbMediaId': 'thumb_media_id','content':'content','summary':'digest','link':'content_source_url','isCoverInContent':'show_cover_pic','accessUrl':'url','cover':'picurl'};
		 var msgs=wechat.multi.data.msgs;
		 var articleArr=[];
		 for(var p in msgs){
		 	var article={};
		 	article.order=p;
		 	for (var p1 in msgs[p]){
		 		
		 		if(nameMapping[p1]&&msgs[p][p1]){
		 			article[nameMapping[p1]]=msgs[p][p1];
		 		}
		 	};
		 	articleArr.push(article);
		 };
		 var wechatMsgs={'articles':articleArr};
		 
		return JSON.stringify(wechatMsgs);
		
		};
		wechat.multi.preSubmit = function() {
		$('#wechat_json').val(wechat.multi.createWechatJson ());
			$('#subs').val(JSON.stringify(wechat.multi.data.msgs));
		};
		var subClickProc = function(src) {
			$('.edit', src)
					.click(
							function() {
								var seq = src.attr('seq');
								wechat.multi.saveCurrent();

								$$resetForm($('#save_form'));
								wechat.multi.fixedResetForm();
								wechat.multi.data.curSeq = seq;
								$('.cont_right').attr('seq', seq);

								$$fillForm(wechat.multi.data.msgs[seq],
										$('#save_form'));

								wechat.multi.fixedFillForm();

								$('.cont_right_height_control')
										.css(
												{
													'height' : (src.position().top - $(
															'.cont_left')
															.position().top)
															+ 'px'
												});
							});
			$('.del', src).click(function() {
				if($('.cont_left .sub').length<=1){
					$$alert({type:'error',content:'无法删除，多条图文至少需要2条消息'});
					return;
				}
				var selSub=$(this).parents('.sub');
				var selSeq=selSub.attr('seq');
				
				delete wechat.multi.data.msgs[selSeq];
				selSub.remove();
				$('.cont_left .parent .edit').click();
		
			});
		};
		var subMouseoverFunc = function(event) {
			var src = $(event.currentTarget);

			$('.opt', src).parent().show();

		};
		var subMouseoutFunc = function(event) {
			var src = $(event.currentTarget);
			$('.opt', src).parent().hide();

		};
		subClickProc($('.parent'));
		subClickProc($('.sub'));
		$('.parent,.sub').hover(subMouseoverFunc, subMouseoutFunc);
		$('.add_sub')
				.click(
						function() {
							if ($('.sub').length >= 7) {
								$$alert({
									type : 'error',
									content : '多图文列表长度不能超过8个'
								});
								return;
							}

							var newSub = $(wechat.multi.data.subModel);

							newSub.appendTo($('.subs_area'));
							newSub.attr('seq', 1 + parseInt(newSub.prev().attr(
									'seq')));
							subClickProc(newSub);
							newSub.hover(subMouseoverFunc, subMouseoutFunc);

							wechat.multi.data.msgs[newSub.attr('seq')] = {};
						});

		$('#imageFile')
				.change(
						{
							url : '<c:url value="/system/ajax/file_upload.json?action='+global.data.config.image+'"/>',
							divSelector : '#image_file_field',
							action : '1',
							context:'${pageContext.request.contextPath}',
							successFunc : function(result, status, extra) {
								if (result.errCode == 0) {
									$(
											'.cont_left  [seq='
													+ $('.cont_right').attr(
															'seq') + '] .cover')
											.html(
													'<img src="${pageContext.request.contextPath}'
															+ result.url
															+ '" width="100%" height="100%"/>');
									$('#cover').val(result.url);
									$('#thumb_media_id').val(result.mediaId);
								}
							}
						}, $$ajaxFileUpload);
		$('#uploadImage').click(function() {
			$('#imageFile').click();
			return false;
		});
		$('#save_btn').click(
				function() {
					wechat.multi.saveCurrent();
					var result = wechat.multi.validator();
					if (result.errCode != '0') {
						$$alert({
							type : 'error',
							content : result.msg
						});
						//不放在alert callback中，即时显示

						if ($('.cont_right').attr('seq') != result.seq) {

							$('.cont_left').find(
									'[seq=' + result.seq + '] .edit').click();
						}
						return;
					}
					$('.cont_left .parent .edit').click();
					wechat.multi.preSubmit();

					$.post($('#save_form').attr('action'), $(
							'#save_form').serialize(), function(result) {
						if (result.errCode == '0') {
							$('#id').val(result.data.messageForm.id);
							$$alert({
								content : '保存成功'
							});
									window.location = '<c:url value="/system/admin/message/list.html" />';

						} else {
							$$alert({
								type : 'error',
								content : result.data.errMsgList.join('<br/>')
							});
						}
					}, 'json');
				}

		);
		$('#title').bind(
				'keyup',
				function() {
					$(
							'.cont_left [seq=' + wechat.multi.data.curSeq
									+ '] .title').text($(this).val());

				});
		$('#add_summary,#add_link,#add_access_url').click(function() {
			$(this).hide();
			$(this).siblings().show();
		});
	</script>


</body>
</html>