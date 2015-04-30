<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet"
	href="<c:url value="/static/styles/reply/index.css"/>" />
</head>
<body>
	<%@include file="../common/header.jsp"%>

	<div id="container">
		<%@ include file="../common/sidebar.jsp"%>
		<div id="main">
			<div class="title">自动回复</div>
			<div class="type_list">
				<ul>
					<li></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav">
				<ul>
					<!-- <li><a class="btn mybtn btn-default btn-info" href="<c:url value="/system/admin/reply/index.html?type=subscribe"/>">被添加自动回复</a> <a class="btn mybtn btn-default" href="<c:url value="/system/admin/reply/index.html?type=auto"/>">消息自动回复</a> <a class="btn mybtn btn-default" href="<c:url value="/system/admin/reply/index.html?type=keyword"/>">关键词自动回复</a> </li> -->
					<li><button class=" btn mybtn btn-default btn-info"
							reply-type="subscribe">被添加自动回复</button>
						<button class=" btn mybtn btn-default" reply-type="auto">消息自动回复</button>
						<button class=" btn mybtn btn-default" reply-type="keyword">关键词自动回复</button>
					</li>
				</ul>
			</div>
			<div class="tips h"></div>
			<div class="content">

				<div id="content_area">
					
					<div class="msg_opt">
						<div class="panel">
							<div class="top">
								<i class="text" selected="selected"></i><i class="image"></i><i
									class="voice"></i><i class="video"></i><i class="news"></i>
							</div>
							<div class="middle"></div>
							<div class="bottom">
								<i class="emotion"></i><span class="tips">您还可以输入<span class="remaining"></span>字</span>
							</div>
						</div>
						<div class="opt ">
							<button class="post btn mybtn btn-success save_btn">保存</button>
							<button class="btn mybtn btn-info">清除内容</button>
						</div>

					</div>
					<div class="keyword_opt h">
						<div class="add_opt"><button class="btn mybtn btn-success" id="rule_add_btn">添加规则</button></div> 
						<div class="vpanel h">
							<div class=vtitle><span>新规则</span><i class="fold"></i> </div>
							<div class="vbody">
								<div class="vsub_area reply_rule"><span class="dot ">规则名</span><input type="text" name="ruleName"  class="vinput"/><span class="tips ">规则名最多60个字</span></div>
								<div class="vsub_area"><span class="dot ">关键词</span><span class="floatR"><button class="vbutton vbutton-link keyword_add_btn" >添加关键字</button></span></div>
								<div class="vsub_area reply_type h"><span class="dot ">回复</span><span class="floatR"><input type="checkbox" class="vcheckbox" name="ruleAll" /> <span class="tips floatR">回复全部</span></span></div>
								<div class="vsub_area msg_opt_type"><i class="text"></i><i class="image"></i> <i class="voice"></i> <i class="video"></i> <i class="news"></i>  <div class="clear"></div> </div>
								<div class="vsub_area msg_sel h" ><div class="msg_content"></div><div class="h"></div><span class="floatR"><i class="edit h"></i><i class="del"></i></span><div class="clear"></div></div>
							</div>
							<div class="vbottom"><span class="floatL h">文字(<span>0</span>)、 图片(<span>0</span>)、 语音(<span>0</span>)、 视频(<span>0</span>)、 图文(<span>0</span>)</span><span class="floatR"><button class="post vbutton vbutton-save keyword_save_btn">保存</button><button class="vbutton vbutton-del keyword_del_btn">删除</button> </span>
							<div class="clear"></div>
							 </div>
						</div>
				
					
					</div>
				</div>
				<div class="waiting h"></div>

			</div>
			
		</div>
		<div class="clear"><div>
	</div>
	<%@ include file="../common/footer.jsp"%>


	<script>
		$(document).ready(function() {

			wechat.reply.index.initBindFunc();
			$('.mynav button[reply-type="subscribe"]').click();
			
		});
		$$namespace('wechat.reply.index');
		wechat.reply.index.data = {
			replyType:{'subscribe':'1','auto':'2','keyword':'3'},
			wechatJson : {},
			currentType:'subscribe',
			currentId:null,
			eleModel:{keyword:'<div class="vsub_area keyword_input"><span class="nodot keyword"></span><span class="floatR"><button class="vbutton vbutton-link floatL">完全匹配</button><i class="edit h"></i><i class="del"></i></span><div class="clear"></div></div>',
					  msg:'<div class="vsub_area msg_sel"><div class="msg_content"></div><div class="h">haha u find me</div><span class="floatR"><i class="edit"></i><i class="del"></i></span><div class="clear"></div></div>'
			}
			
		};

		wechat.reply.index.initBindFunc = function() {
			$('.mynav button').click(
					function() {
					
						wechat.reply.index.data.currentType = $(this).attr('reply-type');
						
						$(this).siblings().removeClass('btn-info');
						$(this).addClass('btn-info');
						$(this).blur();
						$('#content_area > div').hide();
						$('.waiting').show();
						$.post(
								'<c:url value="/system/admin/reply/list.json?type='
										+ wechat.reply.index.data.replyType[wechat.reply.index.data.currentType ] + '"/>', {}, function(result) {
									
									if (result.errCode == '0') {
										if(wechat.reply.index.data.currentType=='keyword')
										{
										
											var replyList=result.data.replyList;
											if(replyList){
												
												var keywordInputModel='<div class="vsub_area keyword_input"><span class="nodot keyword"></span><span class="floatR"><button class="vbutton vbutton-link floatL">完全匹配</button><i class="edit h"></i><i class="del"></i></span><div class="clear"></div></div>';
												var keywordArea=$('.keyword_opt')[0];
												var panelModel=$($('.vpanel',$(keywordArea))[0]);
												panelModel.siblings('.vpanel').remove();
												for(var p in replyList){
													var newPanel=$(panelModel).clone();
													$('.vtitle span',newPanel).html('规则<strong>'+(1+parseInt(p))+'</strong>：'+replyList[p].ruleName);
													$(newPanel).attr('reply_id',replyList[p].id);
													$('.reply_rule input',newPanel).val(replyList[p].ruleName);
													var keywordArr=replyList[p].keyword.split('##');
													for (var p1 in  keywordArr){
														var keywordInputEle= $(keywordInputModel).clone();
														$('.keyword',keywordInputEle).text(keywordArr[p1]);
														$('.reply_type',newPanel).before(keywordInputEle);
														
													}
													var content=null;
													if(replyList[p].msgType==global.data.config.text){
														content=replyList[p].description;
													}
													else if(replyList[p].msgType==global.data.config.news){
														content=$(global.data.eleModel[global.data.mediaTypeOrder[replyList[p].msgType]].main);
														var msg=JSON.parse(replyList[p].description).articles[0];
														$('.title',content).text(msg.title);
														$('.cover img',content).attr('src','${pageContext.request.contextPath}'+msg.picurl);
														
													}else{
													//	alert(replyList[p].msgType);
													//	alert(global.data.mediaTypeOrder[replyList[p].msgType]);
														content=$(global.data.eleModel[global.data.mediaTypeOrder[replyList[p].msgType]]);
														
														content.attr('src','${pageContext.request.contextPath}'+replyList[p].description.split('##')[1]);
													}
													
													$('.msg_sel .msg_content',newPanel).html(content);
													$('.msg_sel .msg_content',newPanel).attr('msg_type',global.data.mediaTypeOrder[replyList[p].msgType]);
													$('.msg_sel .msg_content',newPanel).next().html(replyList[p].description);
													$$registerPanelFoldBtn(newPanel);
													
													$('.msg_opt_type .'+global.data.mediaTypeOrder[replyList[p].msgType],newPanel).attr('selected','selected');
													$('.msg_sel',newPanel).show();
													wechat.reply.index.initKeywordBind(newPanel);
													$$foldPanel(newPanel);
													
													newPanel.show();
													wechat.reply.index.initKeywordInputOptBind(newPanel);
													wechat.reply.index.initKeywordMsgOptBind(newPanel);
													
													$(keywordArea).append(newPanel);
													
												}
											}
											$('.waiting').hide();
											$('#content_area .keyword_opt').show();
										}else{
											var reply=result.data.reply;
											
											if(reply==null){
												wechat.reply.index.data.currentId=null;
												$('#content_area .msg_opt .top .text').click();
											}
											else{
												wechat.reply.index.data.currentId=reply.id;
												$('#content_area .msg_opt .top i:eq('+(reply.msgType-1)+')').trigger('click',['init',reply.description]);
												
												
											}
											$('.waiting').hide();
											$('#content_area .msg_opt').show();
										}
										
									}
								}, 'json');
					});
			$('.msg_opt .top i')
					.click(
							function(event, init,description) {
								$('.msg_opt .bottom .tips').hide();
						//		$('.msg_opt .middle').html('');
								var src=$(this);
								var type = $(this).attr('class');
							
								if (type == 'text') {
									var newText = $('<textArea class="text"></textArea>');
									$$WechatTextInputCheck({selEle:newText,container:$('.msg_opt')});
									
									if (init == 'init') {
										$('.msg_opt .middle').html('');
										newText.val(description);
									
									}
									
									$('.msg_opt .middle').html(newText);
									$(src).siblings().removeAttr('selected');
									$(src).attr('selected', 'selected');
									
								} else if (type == 'news') {
									if (init == 'init') {
										$('.msg_opt .middle').html('');
										var articles=JSON.parse(description).articles;
										var newEle=$(global.data.eleModel['news'].main);
										$('.detail .title',newEle).text(articles[0].title);
										$('.detail .create_time',newEle).text("");
										$('.detail img',newEle).attr('src','${pageContext.request.contextPath}'+articles[0].picurl);
										$('.wechat_json_data',newEle).html(description);
										if(articles.length>1){
											
											for(var i=1;i<articles.length;i++){
												var newEleSub=$(global.data.eleModel['news'].sub);
												$('.title',newEleSub).text(articles[i].title);
												
												$('img',newEleSub).attr('src','${pageContext.request.contextPath}'+articles[i].picurl);
												newEle.append(newEleSub);
											}
										}
										$('.msg_opt .middle').append(newEle);
										$(src).siblings().removeAttr('selected');
										$(src).attr('selected', 'selected');
									} else {
										$$floater({
											title : '选择素材',
											width : '666px',
											content : '<iframe id="item_sel_iframe" style="border:0 none;width:100%;height:100%;" src="<c:url value="/system/admin/message/list_embedded.html"/>"></iframe>',
											callback : function(event) {
												var selectedMsgs = $(
														'#item_sel_iframe')
														.contents()
														.find(
																'.content .li_col > div.sel_item');
												if (selectedMsgs.size() != 1) {
													$$alert({
														type : 'error',
														content : '尚未选择素材'
													});
												} else {
													event.data.floater.hide();
													$('.msg_opt .middle').html(
															selectedMsgs
																	.clone());
														$(src).siblings().removeAttr('selected');
														
														$(src).attr('selected', 'selected');
												}
											}
										});
									}
								} else /*if(type=='image'||type=='voice'||type=='video')*/{
									if (init == 'init') {
											$('.msg_opt .middle').html('');
											var newEle=$(global.data.eleModel[type]);
											newEle.attr('src','${pageContext.request.contextPath}'+description.split('##')[1]);
											$('.msg_opt .middle').append(newEle);
											$(src).siblings().removeAttr('selected');
											$(src).attr('selected', 'selected');
									} else {
										$$floater({
											title : '选择素材',
											width : '643px',
											content : '<iframe id="item_sel_iframe" style="border:0 none;width:100%;height:100%;" src="<c:url value="/system/admin/media/list_embedded.html?type='
													+ global.data.config[type]
													+ '"/>"></iframe>',
											callback : function(event) {
												var selectedMedias = $(
														'#item_sel_iframe')
														.contents()
														.find(
																'input.checked')
														.parent();
												if (selectedMedias.size() != 1) {
													$$alert({
														type : 'error',
														content : '尚未选择素材'
													});
												} else {
													event.data.floater.hide();
													$('.msg_opt .middle')
															.html(
																	selectedMedias
																			.children(
																					'.media')
																			.clone());
													$(src).siblings().removeAttr('selected');
													$(src).attr('selected', 'selected');
												}
											}
										});
									}
								}
							});

			$('.save_btn')
					.click(
							function() {
								var msgId=0;
								var msgType = $(
										$('.msg_opt .top i[selected]')[0])
										.attr('class');
								var content, extra;
								if (msgType == 'text') {
									content = $('.msg_opt .middle .text').val();
									if(content.length==0||content.length>global.data.MaxTextInputLength){
										$$alert	({type:'error',content:'文本长度应在0到'+global.data.MaxTextInputLength+'字之间'});
										return;
									}
									extra = content;
								} else if (msgType == 'news') {
									msgId=$('.middle .message').attr(
											'msg_id');
									content = $('.middle .message').attr(
											'msg_wechat_id');
									if (!content) {
										return;
									}
									extra = $(
											'.middle .message .wechat_json_data')
											.html();

								} else /*if(msgType=='image'||msgType=='voice'||msgType=='video')*/{
									msgId=$('.msg_opt .middle .media')
											.attr('media_id');
									content = $('.msg_opt .middle .media')
											.attr('media_wechat_id');
									if (!content) {
										return;
									}
									extra =content+'##'+ $$removeContext($('.middle .media')
											.attr('src'));
								}
								$.post('<c:url value="/system/admin/reply/save.json"/>',{'id':wechat.reply.index.data.currentId,name:wechat.reply.index.data.currentType+'_'+msgId,'type':wechat.reply.index.data.replyType[wechat.reply.index.data.currentType],'msgType':global.data.config[msgType],'wechatJson':'','description':extra},function(result){
									if(result.errCode!='0'){
									 $$alert({type:'error',content:result.msg});
									}
									else{
										$$alert({content:'保存成功'});
									//	$('#save_btn').unbind();
									}
								},'json');	
										
							});
							$('#rule_add_btn').toggle(function(){$(this).parent().next().show();},function(){$(this).parent().next().hide();});
							
							wechat.reply.index.initKeywordBind();
			
		};
		wechat.reply.index.initKeywordInputOptBind=function(parent){
			var target;
			if(parent.hasClass('keyword_input')){
				target=$('.del',parent);
			}
			else{
				target=$('.keyword_input .del',parent);
			}
			target.unbind();
			target.click(function(){
				$($(this).parents('.vsub_area')[0]).remove();
			});
		};
		wechat.reply.index.initKeywordMsgOptBind=function(parent){
			var target;
			if(parent.hasClass('msg_sel')){
				target=$('.del',parent);
			}
			else{
				target=$('.msg_sel .del',parent);
			}
			target.unbind();
			target.click(function(){
			//	$($(this).parents('.vsub_area')[0]).remove();
			$($(this).parents('.vsub_area')[0]).hide();
			});
		
		};
		wechat.reply.index.initKeywordBind=function(parent){
		
		$('.keyword_add_btn',parent).click(function(event){
				var src=$(this);
				var keywordBody=$(src).parents('.vbody')[0];
				if($('.keyword_input',$(keywordBody)).size()>=5){
					$$alert({type:'error',content:'一条规则中关键字不能超过5项'});
					return;
				}
				
				$$floater({title:'填写文本',
									type:'text_input',
									padding:'30px',
									textLimit:30,
									callback:function(event){
										var val=$('textarea',event.data.floater).val().trim();
										if(val.length==0){
											$$alert({type:'error',content:'关键字不能为空'});
										}
										
										else{
											
											var keywordsExists=false;
											var desc;
											$('.keyword_opt .keyword_input .keyword').each(function(){
												if($(this).text().trim()==val){
													
													keywordsExists=true;
													desc=$('input',$($(this).parent().siblings()[0])).val();
												}
											});
											if(keywordsExists==true){
												$$alert({type:'error',content:'该关键词已存在于规则'+desc});
												return;
											}
											var keywordEle=$(wechat.reply.index.data.eleModel.keyword);
											$('.keyword',keywordEle).text(val);
											
											
											$('.reply_type',keywordBody).before(keywordEle);
											wechat.reply.index.initKeywordInputOptBind(keywordEle);
											event.data.floater.hide();
										}
									}
				});
		
			});
			$('.msg_opt_type i',parent)
					.click(
							function(event, init,description) {
								var src=$(this);
								var type = $(src).attr('class');
								if (type == 'text') {
									var newText = $('<textArea class="text"></textArea>');
									//$$WechatTextInputCheck({selEle:newText,container:$('.msg_opt')});
									$$floater({title:'填写文本',
									type:'text_input',
									padding:'30px',
									textLimit:300,
									callback:function(event){
										var val=$('textarea',event.data.floater).val().trim();
										if(val.length==0){
											$$alert({type:'error',content:'内容不能为空'});
											
										}
										else{
										//	var keywordBody=$(src).parents('.vbody')[0];
											
										//	var msgEle=$(wechat.reply.index.data.eleModel.msg);
											
										//	$('.msg_content',msgEle).text(val);
										//	$(keywordBody).append(msgEle);
											var keywordBody=$(src).parents('.vbody')[0];
											$('.msg_content',keywordBody).text(val);
											$('.msg_content',keywordBody).next().text(val);
											$('.msg_content',keywordBody).attr('msg_type',type); 
											$('.msg_sel',keywordBody).show();
											wechat.reply.index.initKeywordMsgOptBind($('.msg_sel',keywordBody));
											event.data.floater.hide();
											
											$(src).siblings().removeAttr('selected');
											$(src).attr('selected', 'selected');
										}
									}
									
									});
		
									$('.msg_opt .middle').html(newText);
								} else if (type == 'news') {
									 
										$$floater({
											title : '选择素材',
											width : '666px',
										
											content : '<iframe id="item_sel_iframe" style="border:0 none;width:100%;height:100%;" src="<c:url value="/system/admin/message/list_embedded.html"/>"></iframe>',
											callback : function(event) {
												var selectedMsgs = $(
														'#item_sel_iframe')
														.contents()
														.find(
																'.content .li_col > div.sel_item ');
												if (selectedMsgs.size() != 1) {
													$$alert({
														type : 'error',
														content : '尚未选择素材'
													});
												} else {
											//		var msgEle=$(wechat.reply.index.data.eleModel.msg);
											//		$('.edit',msgEle).hide();
											//		var keywordBody=$(src).parents('.vbody')[0];
											//		$('.msg_content',msgEle).html($('.detail',selectedMsgs)
											//						.clone());
											//		$(keywordBody).append(msgEle);
													var keywordBody=$(src).parents('.vbody')[0];
													var selectedMsgsDetail=$('.detail',selectedMsgs).clone();
													$('.msg_content',keywordBody).html(selectedMsgsDetail);
												
													$('.msg_content',keywordBody).attr('msg_type',type); 
													var msg={};
													msg.title=$('.title',selectedMsgsDetail).text().trim();
													msg.cover=$$removeContext($('.cover img',selectedMsgsDetail).attr('src'));
												//	$('.msg_content',keywordBody).next().text(JSON.stringify(msg)+'@@'+selectedMsgs.attr('msg_id'));
												
													$('.msg_content',keywordBody).next().text($('.wechat_json_data',selectedMsgs).html());
													wechat.reply.index.initKeywordMsgOptBind($('.msg_sel',keywordBody));
													$('.msg_sel',keywordBody).show();
													event.data.floater.hide();
													$(src).siblings().removeAttr('selected');
													$(src).attr('selected', 'selected');
												}
											}
										});
									
								} else /*if(type=='image'||type=='voice'||type=='video')*/{
									
										$$floater({
											title : '选择素材',
											width : '643px',
											content : '<iframe id="item_sel_iframe" style="border:0 none;width:100%;height:100%;" src="<c:url value="/system/admin/media/list_embedded.html?type='
													+ global.data.config[type]
													+ '"/>"></iframe>',
											callback : function(event) {
												var selectedMedias = $(
														'#item_sel_iframe')
														.contents()
														.find(
																'input.checked')
														.parent();
												if (selectedMedias.size() != 1) {
													$$alert({
														type : 'error',
														content : '尚未选择素材'
													});
												} else {
									//				var msgEle=$(wechat.reply.index.data.eleModel.msg);
									//				$('.edit',msgEle).hide();
									//				var keywordBody=$(src).parents('.vbody')[0];
									//				$('.msg_content',msgEle).html(selectedMedias
									//										.children(
									//												'.media')
									//										.clone());
									//				$(keywordBody).append(msgEle);
													var keywordBody=$(src).parents('.vbody')[0];
													var selectedMedia=selectedMedias.children('.media');
													$('.msg_content',keywordBody).html(selectedMedia.clone());
													$('.msg_content',keywordBody).next().text(selectedMedia.attr('media_wechat_id')+'##'+$$removeContext(selectedMedia.attr('src')));
													$('.msg_content',keywordBody).attr('msg_type',type); 
													wechat.reply.index.initKeywordMsgOptBind($('.msg_sel',keywordBody));
													
													$('.msg_sel',keywordBody).show();
													event.data.floater.hide();
													$(src).siblings().removeAttr('selected');
													$(src).attr('selected', 'selected');
												}
											}
										});
									
								}
							});
					
				$('.keyword_save_btn',parent).click(function(){
					var keywordPanel=null;
					if(parent){
						
						keywordPanel=parent;
					}else{
						keywordPanel=$(this).parents('.vpanel')[0];
					}
					var oriReplyId=$(keywordPanel).attr('reply_id');
					
					var params={'id':oriReplyId,'type':wechat.reply.index.data.replyType[wechat.reply.index.data.currentType],'wechatJson':''};
					var result=wechat.reply.index.keywordSubmitValidation(keywordPanel,params);
					if(result.errCode!='0'){
						$$alert({type:'error',content:result.msg});
					}
					else{
							$.post('<c:url value="/system/admin/reply/save.json"/>',params,function(result){
									if(result.errCode!='0'){
									 $$alert({type:'error',content:result.msg});
									}
									else{
										$$alert({content:'保存成功'});
										if(oriReplyId!=result.data.replyForm.id){
											var newKeywordPanel=$(keywordPanel).clone();
											wechat.reply.index.keywordClearFunc();
											$('.vtitle span',$(newKeywordPanel)).html('规则<strong>'+$('.keyword_opt .vpanel').size()+'</strong>：'+result.data.replyForm.name);
											$(newKeywordPanel).attr('reply_id',result.data.replyForm.id);
											$$foldPanel(newKeywordPanel);
											$('.keyword_opt').append(newKeywordPanel);
										}
									//	$('#save_btn').unbind();
									}
								},'json');
					}
				});
				$('.keyword_del_btn',parent).click(function(){
					var src=$(this);
					$$alertFloat({posRef:$(this),content:'确定要删除吗？',callback:function(event){
						event.data.alertFloat.hide();
						var keywordArea=$($('.keyword_opt')[0]);
						var keywordPanel=$($(src).parents('.vpanel')[0]);
						var replyId=keywordPanel.attr('reply_id');
						if(replyId){
							$.post('<c:url value="/system/admin/reply/delete.json" />',{id:replyId},function(result){
								if(result.errCode=='0'){
									$$alert({content:'删除成功'});
									keywordPanel.remove();
									$('.vpanel',keywordArea).each(function(index){
										if(index>0){
										$('strong',$(this)).text(index);
										}
									});
								}else{
									$$alert({type:'error',content:result.msg});
								}
							
							},'json');
						}else{
							wechat.reply.index.keywordClearFunc();
							$$alert({content:'删除成功'});
							
						}
					}});
				});
				
			
		};
		wechat.reply.index.keywordClearFunc=function(){
			var vpanel=$('.keyword_opt .vpanel')[0];
			$(vpanel).removeAttr('reply_id');
			$('.reply_rule input',vpanel).val('');
			$('.keyword_input',vpanel).remove();
			$('.msg_content',vpanel).html('');
		};
		wechat.reply.index.keywordSubmitValidation=function(keywordPanel,params){
			var result={errCode:'0'};
			
			var ruleName=$('input[name="ruleName"]',keywordPanel).val();
			if(ruleName.length==0){
				result.errCode='1';
				result.msg='规则名称不能为空';
				return result;
			}
			
			var keywordEles=$('.keyword_input .keyword',keywordPanel);
			if(keywordEles.size()==0){
				result.errCode='1';
				result.msg='关键字不能为空';
				return result;
			}
			var msgEles=$('.msg_sel .msg_content',keywordPanel);
			if(msgEles.html().trim().length==0){
				result.errCode='1';
				result.msg='回复内容不能为空';
				return result;
			}
			var keywordArr=[];
			keywordEles.each(function(){
				keywordArr.push($(this).text());
			});
			var msgTypeArr=[];//目前reply表 msgType字段为int类型,由于只限制1条回复内容，故不存在类型转换错误问题
			var msgContentArr=[];
			var msgIdArr=[];
			msgEles.each(function(){
				msgContentArr.push($(this).next().text());
				msgTypeArr.push(global.data.config[$(this).attr('msg_type')]);
			});
			params.name='keyword_'+msgIdArr.join('##');
			params.ruleName=ruleName;
			params.keyword=keywordArr.join('##');
			params.description=msgContentArr.join('##');//当前只有一条后台暂不用解析
			params.msgType=msgTypeArr.join('##');//当前只有一条，暂不存在问题
			
			return result;
		};
	</script>
</body>
</html>