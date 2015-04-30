<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet" href="<c:url value="/static/styles/broadcast/index.css"/>" />
</head>
<body>
	<%@include file="../common/header.jsp"%>

	<div id="container">
		<%@ include file="../common/sidebar.jsp"%>
		<div id="main">
			<div class="title">群发功能</div>
			<div class="type_list">
				<ul>
					<li class="current"><a href="<c:url value="/system/admin/broadcast/index.html"/>">
							新建群发消息</a></li>
					<li ><a href="<c:url value="/system/admin/broadcast/list.html"/>">
							已发送</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav">
				<ul>
					<li></li>
				</ul>
			</div>
				<div class="tips">
					为保障用户体验，微信公众平台严禁恶意营销以及诱导分享朋友圈，严禁发布色情低俗、暴力血腥、政治谣言等各类违反法律法规及相关政策规定的信息。一旦发现，我们将严厉打击和处理。
				</div>
				<div class="content">
					<div id="condition">
						<fieldset>
							<label for"objType">群发对象</label>
							<select id="objType" name="objType" class="form-control fix"><option>全部</option></select>
							<select id="groupType" name="groupType" class="form-control fix hi"><option>请选择</option></select>
						</fieldset>
						<fieldset class="hi">
							<label for"sex">性别</label>
							<select id="sex" name="sex" class="form-control fix"><option>请选择</option></select>
							
						</fieldset>
						<fieldset class="hi">
							<label for"region">群发地区</label>
							<select id="region" name="region" class="form-control fix"><option>请选择</option></select>
						</fieldset>
					</div>
					<div id="content_area">
							<div class="msg_opt h">
								<div class="panel">
									<div class="top"><i class="text" selected="selected"></i><i class="image"></i><i class="voice"></i><i class="video"></i><i class="news"></i> </div>
										<div class="middle"></div>
									<div class="bottom"><i class="emotion"></i><span class="tips">您还可以输入<span class="remaining"></span>字</span></div>
								</div>
								<div class="opt h"><button class="btn mybtn btn-success save_btn">保存</button><button class="btn mybtn btn-info" onclick="$('.msg_opt').hide();$('.opt_tips').show();">返回</button></div> 
								
							</div>
						
					</div>
					<div id="opt_panel" >
						<button id="broadcast_btn" class="btn mybtn btn-success">群发</button>
						<div class="tips">你今天还能群发<span id="left_count"></span>条消息
							<div  class="myarrow myarrow_out"></div>
							<div  class="myarrow myarrow_in"></div>
							</div>
					</div>
					
				</div>

		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>


	<script>
			$(document).ready(function(){
				wechat.broadcast.index.allowBroadcast();
				
				
			});
			$$namespace('wechat.broadcast.index');
			wechat.broadcast.index.data={
			wechatJson:{}
			};
			wechat.broadcast.index.allowBroadcast=function(){
					$.post('<c:url value="/system/admin/broadcast/allow.json"/>',{},function(result){
					if(result.errCode!='0'){
						$('#left_count').text('0');
				//		$$alert({type:'error',content:result.msg});
				wechat.broadcast.index.initBindFunc();
						$('.msg_opt .top i.text').click();
					}
					else{
						$('#left_count').text('1');
						wechat.broadcast.index.initBindFunc();
						$('.msg_opt .top i.text').click();
					//	$('.msg_opt').show();
					}
					$('.msg_opt').show();
				},'json');	
			};
			wechat.broadcast.index.initBindFunc=function(){
				$('.msg_opt .top i').click(function(event,init){
					$('.msg_opt .bottom .tips').hide();
					//$('.msg_opt .middle').html('');
					var type=$(this).attr('class');
					var src=$(this);
				
					if(type=='text'){
						var newText=$('<textArea class="text"></textArea>');
						$$WechatTextInputCheck({selEle:newText,container:$('.msg_opt')});
						if(init=='init'){
							
							
						}
						
						$('.msg_opt .middle').html(newText);
						$(src).siblings().removeAttr('selected');
						$(src).attr('selected','selected');
					}else if(type=='news'){
						if(init=='init'){
						
						}else{
							$$floater({title:'选择素材',width:'666px', content:'<iframe id="item_sel_iframe" style="border:0 none;width:100%;height:100%;" src="<c:url value="/system/admin/message/list_embedded.html"/>"></iframe>',callback:function(event){
								var selectedMsgs=$('#item_sel_iframe').contents().find('.content .li_col > div.sel_item');
								if(selectedMsgs.size()!=1){
									$$alert({type:'error',content:'尚未选择素材'});				
								}
								else{
									event.data.floater.hide();
									$('.msg_opt .middle').html(selectedMsgs.clone());
									$(src).siblings().removeAttr('selected');
									$(src).attr('selected','selected');
								}
							}});
						}
					}else /*if(type=='image'||type=='voice'||type=='video')*/{
						if(init=='init'){
							
						}else{
							$$floater({title:'选择素材',width:'643px', content:'<iframe id="item_sel_iframe" style="border:0 none;width:100%;height:100%;" src="<c:url value="/system/admin/media/list_embedded.html?type='+global.data.config[type]+'"/>"></iframe>',callback:function(event){
								var selectedMedias=$('#item_sel_iframe').contents().find('input.checked').parent();
								if(selectedMedias.size()!=1){
									$$alert({type:'error',content:'尚未选择素材'});				
								}
								else{
									event.data.floater.hide();
									$('.msg_opt .middle').html(selectedMedias.children('.media').clone());
									$(src).siblings().removeAttr('selected');
									$(src).attr('selected','selected');
								}
							}});
						}
					}
				});
			
				$('#broadcast_btn').click(function(){
					
					var msgType=$($('.msg_opt .top i[selected]')[0]).attr('class');
					var content,extra;
					if(msgType=='text'){
						content=$('.msg_opt .middle .text').val();
						if(content.length==0||content.length>global.data.MaxTextInputLength){
							$$alert	({type:'error',content:'文本长度应在0到'+global.data.MaxTextInputLength+'字之间'});
							return;
						}
						extra=content;
					}
					else if(msgType=='news'){
						content=$('.middle .message').attr('msg_wechat_id');
						if(!content){
							return;
						}
						extra=$('.middle .message .wechat_json_data').html();
						
					}
					else /*if(msgType=='image'||msgType=='voice'||msgType=='video')*/{
						content=$('.msg_opt .middle .media').attr('media_wechat_id');
						if(!content){
							return;
						}
						extra=$$removeContext($('.middle .media').attr('src'));
					}
					wechat.broadcast.index.createWechatJson(msgType,content,extra);
				});
			};
			wechat.broadcast.index.createWechatJson=function(msgType,content,extra){
				function fixMsgType(msgType){
					if(msgType=='news'){
						return 'mp'+msgType;
					}
					return msgType;
				}
				
				//wechat.broadcast.index.data.byGroup=;
				var fixedMsgType = fixMsgType(msgType);
				var byGroup = {
					filter : {
						group_id : '0'
					},
					msgtype : fixedMsgType
				};
			var desc;
			byGroup[fixedMsgType] = {};
				if(msgType=='text'){
					byGroup[fixedMsgType].content=content;
					
				}else{
					byGroup[fixedMsgType].media_id=content;
				}
				var wechatJson=JSON.stringify(byGroup);
		//		alert(wechatJson);
		//		alert('extra='+extra);
				$.post('<c:url value="/system/admin/broadcast/broadcast_by_group.json"/>',{'msgType':global.data.config[msgType],'wechatJson':wechatJson,'description':extra},function(result){
					if(result.errCode!='0'){
					 $$alert({type:'error',content:result.msg});
					}
					else{
						$$alert({content:'消息已发出'});
					//	$('#broadcast_btn').unbind();
					}
				},'json');	
		/*		$.post('<c:url value="/system/admin/group/list.json"/>',{},function(result){
					alert(result.errCode);
				},'json');*/
		/*	// openId:oRjZ9jspdnVf27APIarUJHwxC3_A   oRjZ9jr1KrkGTDa-HGB1kZOpTh2Q
				$.post('<c:url value="/system/admin/group/get_user_group_id.json"/>',{'wechatJson':'{"openid":"oRjZ9jspdnVf27APIarUJHwxC3_A"}'},function(result){
					alert(result.errCode);
				},'json');	
			*/
		/*		var byOpenId={touser:['oRjZ9jspdnVf27APIarUJHwxC3_A','oRjZ9jr1KrkGTDa-HGB1kZOpTh2Q'],msgtype:msgType};
				
				byOpenId[msgType]={};
				if(msgType=='text'){
					byOpenId[msgType].content=content;
				}else{
					byOpenId[msgType].media_id=content;
				}
				var wechatJson=JSON.stringify(byOpenId);
				alert(wechatJson);
				$.post('<c:url value="/system/admin/broadcast/broadcast_by_open_id.json"/>',{'wechatJson':wechatJson},function(result){
					alert(result.errCode);
				},'json');	*/
			};
			
	</script>
</body>
</html>