<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>
<link rel="stylesheet"
	href="<c:url value="/static/styles/custom_menu/list.css"/>" />
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div id="container">
		<%@ include file="../common/sidebar.jsp"%>
		<div id="main">
			<div class="title">自定义菜单</div>
			<div class="type_list"></div>
			<div class="mynav">
				<ul>

					<li><span class="tips">可创建最多3个一级菜单，每个一级菜单下可创建最多5个二级菜单</span></li>
				</ul>

			</div>
			<div class="content">
				<div class="cust_menu_box">
					<div id="cust_menu_list">
						<div class="header">
							<ul>
								<li custom_menu_id="0">菜单管理<span class="sort_opt h" ><button class="vbutton vbutton-save sort_save">确定</button> <button class="vbutton vbutton-del sort_cancel">取消</button></span> <span class="opt"><i class="add"></i><i class="sort"></i> </span></li>
							</ul>
						</div>
						<ul class="first">
						<!--  
							<li custom_menu_id="1"><span class="title">乐活</span><span
								class="opt"> <i class="add"></i><i class="edit"></i><i
									class="del"></i> </span>
								<ul class="second">
									<li custom_menu_id="11"><span class="title">校园生活</span><span
										class="opt"><i class="add unvisible"></i><i
											class="edit"></i><i class="del"></i> </span></li>
									<li><span class="title">校园生活</span><span class="opt"><i
											class="add unvisible"></i><i class="edit"></i><i class="del"></i>
									</span></li>
									<li><span class="title">校园生活</span><span class="opt"><i
											class="add unvisible"></i><i class="edit"></i><i class="del"></i>
									</span></li>
								</ul></li>
							<li><span>在你身边</span><span class="opt"> <i
									class="add"></i><i class="edit"></i><i class="del"></i> </span>
								<ul class="second">
									<li><span class="title">校园生活</span><span class="opt"><i
											class="add unvisible"></i><i class="edit"></i><i class="del"></i>
									</span></li>
									<li><span class="title">校园生活</span><span class="opt"><i
											class="add unvisible"></i><i class="edit"></i><i class="del"></i>
									</span></li>
									<li><span class="title">校园生活</span><span class="opt"><i
											class="add unvisible"></i><i class="edit"></i><i class="del"></i>
									</span></li>
								</ul></li>
							-->
						</ul>

					</div>
					<div class="cust_menu_opt">
						<div class="header">设置动作</div>
						<div class="cont_area">
							<div class="tips">你可以先添加一个菜单，然后开始为其设置响应动作</div>
							<div class="forbidden_tips h">已有子菜单，无法完成设置动作</div>
							<div class="opt_tips h"><span>请选择订阅者点击菜单后，公众号做出的相应动作</span>
								<div class="opt">
								<ul><li onclick="$('.opt_tips').hide();$('.msg_opt .top i.text').click();$('.msg_opt').show();"><i class="msg"></i><span >发送信息</span></li><li onclick="$('.opt_tips').hide();$('.url_opt #url').val('');$('.url_opt').show();"><i class="url"></i><span >跳转到网页</span></li></ul>
								</div>
							</div>
							<div class="msg_opt h">
								<div class="panel">
									<div class="top"><i class="text" selected="selected"></i><i class="image"></i><i class="voice"></i><i class="video"></i><i class="news"></i> </div>
										<div class="middle"></div>
									<div class="bottom"><i class="emotion"></i><span class="tips">您还可以输入<span class="remaining"></span>字</span></div>
								</div>
							<shiro:hasAnyRoles name="sys_admin,admin">	<div class="opt"><button class="btn mybtn btn-success save_btn">保存</button><button class="btn mybtn btn-info" onclick="$('.msg_opt').hide();$('.opt_tips').show();">返回</button></div> </shiro:hasAnyRoles>
								
							</div>
							<div class="url_opt h">
							<fieldset><label for="url">订阅者点击该子菜单会跳到以下链接</label><input id="url" name="url" value="" class="form-control"/> </fieldset>
							<div class="opt"><button class="btn mybtn btn-success save_btn">保存</button><button class="btn mybtn btn-info" onclick="$('.url_opt').hide();$('.opt_tips').show();">返回</button></div> 
							</div>
						</div>
					</div>
					<div class="clear"></div>

				</div>
				<div class="tips">
					<span class="myalert floatL h">菜单尚未发布</span> 编辑中的菜单需要进行发布才能更新到用户手机上
				</div>
			<shiro:hasAnyRoles name="sys_admin,admin">	<div class="opt"><button class="post btn mybtn btn-success publish" id="publish_btn">发布</button></div></shiro:hasAnyRoles>
			</div>
		</div>
		<textarea class="h"id="wechatJson"><c:out value="${wechatJson.description}"/></textarea>
		<div class="clear"></div>
	</div>

	<%@ include file="../common/footer.jsp"%>
	<script>
		$(document).ready(function(){
			snnu.wechat.custom_menu.list.init();
			snnu.wechat.custom_menu.list.initClickEvent();
		});
		$$namespace('snnu.wechat.custom_menu.list');
		snnu.wechat.custom_menu.list.data = {
			currentCustomMenu:null,
			wechatJson:'',
			maxId : 1,/*此方法后台多人操作生产自定义菜单时很可能会发生主键冲突，从后台判断*/
			firstLevelModel : '<li class="h" custom_menu_id=""><span class="title"></span><span class="sort h"><i class="asc"></i><i class="desc"></i></span><span class="opt"><i class="add"></i><i class="edit"></i><i class="del"></i> </span><ul class="second"></ul></li>',
			secondLevelModel : '<li class="h" custom_menu_id="1"><span class="title"></span><span class="sort h"><i class="asc"></i><i class="desc"></i></span><span class="opt"><i class="add unvisible"></i><i class="edit"></i><i class="del"></i> </span></li>'
		};
		snnu.wechat.custom_menu.list.init = function() {
			
			function initCustomMenu(){
				var maxId=1;
				if($('#wechatJson').text()){
				
				 var customMenus=JSON.parse($('#wechatJson').text());
				 for(var p in customMenus.button){
				 	var newFirstLevelMenu=$(snnu.wechat.custom_menu.list.data.firstLevelModel);
				 	if(maxId<parseInt(customMenus.button[p].id)){
				 		maxId=parseInt(customMenus.button[p].id);
				 	}
				 	newFirstLevelMenu.attr("custom_menu_id",customMenus.button[p].id);
				 	newFirstLevelMenu.attr("custom_menu_type",customMenus.button[p].type);
				 	newFirstLevelMenu.attr("custom_menu_key",customMenus.button[p].key);
				 	newFirstLevelMenu.attr("custom_menu_url",customMenus.button[p].url);
				 	$('.title',newFirstLevelMenu).html(customMenus.button[p].name);
				 	var subButton=customMenus.button[p].sub_button;
					for(var p1 in subButton){
						var newSecondLevelMenu=$(snnu.wechat.custom_menu.list.data.secondLevelModel);
						if(maxId<parseInt(subButton[p1].id)){
							maxId=parseInt(subButton[p1].id);
						}
						newSecondLevelMenu.attr("custom_menu_id",subButton[p1].id);
						newSecondLevelMenu.attr("custom_menu_type",subButton[p1].type);
						newSecondLevelMenu.attr("custom_menu_key",subButton[p1].key);
						newSecondLevelMenu.attr("custom_menu_url",subButton[p1].url);
						$('.title',newSecondLevelMenu).html(subButton[p1].name);
						
						newSecondLevelMenu.show();
						$('ul',newFirstLevelMenu).append(newSecondLevelMenu);
					}				 	
				 	newFirstLevelMenu.show();
				 	$('#cust_menu_list .first').append(newFirstLevelMenu);
				 }
				}
				snnu.wechat.custom_menu.list.data.maxId=maxId;
				$('#cust_menu_list .sort_save').click(snnu.wechat.custom_menu.list.sortSaveFunc);
				$('#cust_menu_list .sort_cancel').click(snnu.wechat.custom_menu.list.sortCancelFunc);
				$('#cust_menu_list .sort').click(snnu.wechat.custom_menu.list.sortFunc);
				
				$('#cust_menu_list .asc').click(snnu.wechat.custom_menu.list.ascFunc);
				$('#cust_menu_list .desc').click(snnu.wechat.custom_menu.list.descFunc);
				$('#cust_menu_list .add').click(snnu.wechat.custom_menu.list.addFunc);
				$('#cust_menu_list .edit').click(snnu.wechat.custom_menu.list.editFunc);
				$('#cust_menu_list .del').click(snnu.wechat.custom_menu.list.delFunc);
				$('#cust_menu_list .title').click(snnu.wechat.custom_menu.list.menuClickFunc);
			};
			initCustomMenu();
			
		};
		snnu.wechat.custom_menu.list.initClickEvent=function(){
			
			$('.msg_opt .save_btn').click(function(){
				var content;
				var msgType=$($('.msg_opt .top i[selected]')[0]).attr('class');
				var msgId='0';
				if(msgType=='text'){
					content=$('.msg_opt .middle .text').val();
					if(content.length==0||content.length>global.data.MaxTextInputLength){
						$$alert	({type:'error',content:'文本长度应在0到'+global.data.MaxTextInputLength+'字之间'});
						return;
					}
				}
				else if(msgType=='news'){
					msgId=$('.middle .message').attr('msg_id');
					if(!msgId){
						return;
					}
					var news=JSON.parse($('.middle .message .wechat_json_data').html());
					news.articles;
					for(var p in news.articles){
					
					
								if(!news.articles[p].url){
							//		news.articles[p].url=global.data.baseContextUrl+global.data.frontArticleUrl+'?id='+msgId+'&sudId='+p;
							}
					}
					var newsJson=JSON.stringify(news);
					content=(newsJson);
					
				}
				else /*if(msgType=='image'||msgType=='voice'||msgType=='video')*/{
					msgId=$('.msg_opt .middle .media').attr('media_id');
					if(!msgId){
						return;
					}
					var mediaWechatId=content=$('.msg_opt .middle .media').attr('media_wechat_id');
					var localUrl=$('.msg_opt .middle .media').attr('src').replace(/\/[0-9a-z]*/i,"");
					content=mediaWechatId+'##'+localUrl;
				//	content=$('.msg_opt .middle .media').attr('src').replace(/\/[0-9a-z]*/i,"");//去掉ContextPath
					
				}
				
				var currentItemId=snnu.wechat.custom_menu.list.data.currentCustomMenu.attr('custom_menu_id');
				snnu.wechat.custom_menu.list.data.currentCustomMenu.attr('custom_menu_type','click');
				
				
				snnu.wechat.custom_menu.list.data.currentCustomMenu.attr('custom_menu_key',msgType+'_'+currentItemId+'_'+msgId);
					$.post(
							'<c:url value="/system/admin/custom_menu/add.json"/>',
							{
								'id':currentItemId,
								'name':$('.title',snnu.wechat.custom_menu.list.data.currentCustomMenu).text(),
								'description':content,
								'wechatJson':snnu.wechat.custom_menu.list.createWechatJson()
							},
							function(result) {

								if (result.errCode == '0') {
									$$alert({
										content : '保存成功'
									});
									
									
									
								} else {
									$$alert({
										content : result.msg
									});
									
								}
								
							}, 'json').error(function() {
						event.data.floater.hide();
						alert('error');
					});
				
			});
			$('.url_opt .save_btn').click(function(){
			var urlStr=$('#url').val();
				
				if(!$$validatorVal({'value':urlStr,type:'url'})){
					$$alert	({type:'error',content:'url格式不正确'});
				}else{
					snnu.wechat.custom_menu.list.data.currentCustomMenu.attr('custom_menu_type','view');
					snnu.wechat.custom_menu.list.data.currentCustomMenu.attr('custom_menu_url',urlStr);
					$
							.post(
									'<c:url value="/system/admin/custom_menu/update_wechat_json.json"/>',
									{
						
										'wechatJson':snnu.wechat.custom_menu.list.createWechatJson()
									},
									function(result) {

										if (result.errCode == '0') {
											$$alert({
												content : '保存成功'
											});
											event.data.floater.hide();
											
											
										} else {
											$$alert({
												content : result.msg
											});
											
										}
										
									}, 'json').error(function() {
								event.data.floater.hide();
								alert('error');
							});
				}
			});
		};
		//发布按钮
		$('#publish_btn').click(function(){
			function validation(){
				var wechatJson=snnu.wechat.custom_menu.list.createWechatJson();
				var wechat=JSON.parse(wechatJson);
				var result={'errCode':'0'};
				if(!wechat.button||wechat.button.length==0){
					result.errCode='1';
					result.msg='尚未创建菜单';
					return result;
				}
				else{
					for(var p in wechat.button){
						if(!wechat.button[p].type){
							var subMenu=wechat.button[p].sub_button;
							if(subMenu&&subMenu.length>0){
								for(var p1 in subMenu){
									if(!subMenu[p1].type){
										result.errCode='1';
										result.msg=subMenu[p1].name+'尚未设置响应动作';
										return result;
									}
									
								}
							}
							else{
								result.errCode='1';
								result.msg=wechat.button[p].name+'尚未设置响应动作';
								return result;
							}
						}
						
					}
				}
				return result;
				
			}
			var result=validation();
			if(result.errCode=='1'){
					$$alert({type:'error',content:result.msg});
					return;
			}
			
			else{
				
				$
							.post(
									'<c:url value="/system/admin/custom_menu/publish.json"/>',
									{

										'wechatJson':snnu.wechat.custom_menu.list.createWechatJson()
									},
									function(result) {

										if (result.errCode == '0') {
											$$alert({
												content : '菜单发布成功'
											});
										
											
										} else {
											$$alert({
												content : result.msg
											});
											
										}
										
									}, 'json').error(function() {
								event.data.floater.hide();
								alert('error');
							});
			}
		});
		// 面板按钮
		$('.msg_opt .top i').click(function(event,init){
			$('.msg_opt .bottom .tips').hide();
		//	$('.msg_opt .middle').html('');
			var selId=snnu.wechat.custom_menu.list.data.currentCustomMenu.attr('custom_menu_id');
			var src=$(this);
			var type=$(this).attr('class');
			
			if(type=='text'){
				var newText=$('<textArea class="text"></textArea>');
				$$WechatTextInputCheck({selEle:newText,container:$('.msg_opt')});
				if(init=='init'){
					
					$(src).siblings().removeAttr('selected');
		
					
					$.post('<c:url value="/system/admin/custom_menu/item.json"/>',{id:selId},function(result){
						if(result.errCode=='0'){
							$('.msg_opt .middle').html('');
							newText.text(result.data.item.description?result.data.item.description:'');
							$('.msg_opt .middle').html(newText);
							$(src).attr('selected','selected');
						}
					},'json');
				}
				else{
					$('.msg_opt .middle').html(newText);
					$(src).siblings().removeAttr('selected');
		
					$(src).attr('selected','selected');
				}
				
			}else if(type=='news'){
				$(src).siblings().removeAttr('selected');
				
				if(init=='init'){
					$.post('<c:url value="/system/admin/custom_menu/item.json"/>',{id:selId},function(result){
							if(result.errCode=='0'){
								$('.msg_opt .middle').html('');
								var wechatJson=result.data.item.description?result.data.item.description:'';
							
								var articles=JSON.parse(wechatJson).articles;
								
								var newEle=$(global.data.eleModel['news'].main);
								$('.detail .title',newEle).text(articles[0].title);
								$('.detail .create_time',newEle).text("");
								$('.detail img',newEle).attr('src','${pageContext.request.contextPath}'+articles[0].picurl);
								$('.wechat_json_data',newEle).html(wechatJson);
								if(articles.length>1){
									
									for(var i=1;i<articles.length;i++){
										var newEleSub=$(global.data.eleModel['news'].sub);
										$('.title',newEleSub).text(articles[i].title);
										
										$('img',newEleSub).attr('src','${pageContext.request.contextPath}'+articles[i].picurl);
										newEle.append(newEleSub);
									}
								}
								$('.msg_opt .middle').append(newEle);
								$(src).attr('selected','selected');
							}
						},'json');
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
					$(src).siblings().removeAttr('selected');
					
					$.post('<c:url value="/system/admin/custom_menu/item.json"/>',{id:selId},function(result){
						if(result.errCode=='0'){
							$('.msg_opt .middle').html('');
							var source=result.data.item.description?result.data.item.description.split('##')[1]:'';
							var newEle=$(global.data.eleModel[type]);
							newEle.attr('src','${pageContext.request.contextPath}'+source);
							$('.msg_opt .middle').append(newEle);
							$(src).attr('selected','selected');
						}
					},'json');
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
	
		//菜单点击事件
		snnu.wechat.custom_menu.list.menuClickFunc=function(event){
			snnu.wechat.custom_menu.list.data.currentCustomMenu=$(event.currentTarget).parent()	;
			$('#cust_menu_list li').removeClass('selected');
			$('.cont_area').children().hide();
			if($(event.currentTarget).parent().parent().attr('class')=='first'&&$(event.currentTarget).parent().find('li').size()>0){
				
				$('.cont_area .forbidden_tips').show();
				
			}
			else{
				$(this).parent().addClass('selected');
				var type=$(event.currentTarget).parent().attr('custom_menu_type');
				
				if(!type){
					$('.cont_area .opt_tips').show();
				}
				else if(type=='view'){
					$('#url').val($(event.currentTarget).parent().attr('custom_menu_url'));
					$('.cont_area .url_opt').show();
				}
				else if(type=='click'){
					var msgTypeAndIdPair=$(event.currentTarget).parent().attr('custom_menu_key').split('_');
					
					var msgType=msgTypeAndIdPair[0];
					
					$('.msg_opt .top i.'+msgType).trigger('click',['init']);
					var customId=msgTypeAndIdPair[1];
					$('.cont_area .msg_opt').show();
				};
				
			};
		};
		snnu.wechat.custom_menu.list.createWechatJson = function() {
			var buttonArr = [];
			var firstLevelMenus = $('#cust_menu_list .first').children();
			$(firstLevelMenus).each(function() {
				if('false'==$(this).attr('creatable')){
					return;
				}
				var button = {
					id : $(this).attr('custom_menu_id'),
					name : $($('.title',this)[0]).text(),
					
				};
				var subButtonArr = [];
				$('li', $(this)).each(function() {
					if('false'==$(this).attr('creatable')){
						return;
					}
					
					var subButton = {
						id : $(this).attr('custom_menu_id'),
						name : $('.title',this).text(),
						type : $(this).attr('custom_menu_type'),
						key : $(this).attr('custom_menu_key'),
						url : $(this).attr('custom_menu_url')
					};
					subButtonArr.push(subButton);
				});
				button['sub_button'] = subButtonArr;
				if(subButtonArr.length==0){
					button.type =$(this).attr('custom_menu_type');
					button.key = $(this).attr('custom_menu_key');
					button.url= $(this).attr('custom_menu_url');
				}
				buttonArr.push(button);
			});
			var buttons = {
				'button' : buttonArr
			};
			
			return JSON.stringify(buttons);
		};
		snnu.wechat.custom_menu.list.sortSaveFunc = function(event) {
						var src=$(this);
						$.post(
							'<c:url value="/system/admin/custom_menu/sort.json"/>',
							{
								'wechatJson':snnu.wechat.custom_menu.list.createWechatJson()
							},
							function(result) {

								if (result.errCode == '0') {
									$$alert({
										content : '保存成功'
									});
									
									
									
								} else {
									$$alert({
										content : result.msg
									});
									
								}
								$(src).next().click();
							}, 'json').error(function() {
						event.data.floater.hide();
						alert('error');
					});
		};
		snnu.wechat.custom_menu.list.sortCancelFunc = function(event) {

			$(this).parent().hide();
			$('.first .sort').hide();
			$('.first .opt').show();
			
		};
		snnu.wechat.custom_menu.list.sortFunc = function(event) {
			var src=$(this);
			$(this).parent().prev().show();
			$('.first .opt').hide();
			$('.first .sort').show();
		};
		snnu.wechat.custom_menu.list.ascFunc = function(event) {
			var selEle=$($(this).parents('li')[0]);
			if(selEle.prev()!=null){
				selEle.prev().before(selEle);
			}
		};
		snnu.wechat.custom_menu.list.descFunc = function(event) {
			var selEle=$($(this).parents('li')[0]);
			if(selEle.next()!=null){
				selEle.next().after(selEle);
			}
		};
		snnu.wechat.custom_menu.list.addFunc = function(event) {
			function addValidation(selItem,selId){
				if(selId=='0'){
					if($('#cust_menu_list .first').children().size()>=3){
					$$alert({'type':'error','content':'一级菜单不能超过3个'});
					return false;
					}
				}
				else{
					if($('li',selItem).size()>=5){
					$$alert({'type':'error','content':'二级菜单不能超过5个'});
					return false;
					}
				}
				return true;
			}
			var selItem = $($(event.currentTarget).parents('li')[0]);
			var selId = selItem.attr('custom_menu_id');
			if(!addValidation(selItem,selId)){
				return;
			}
			var isFirstLevelMenu=selItem.attr('custom_menu_id')=='0';
			$$floater({
				
				content : '<div style="height:100px;width:250px;"><span>'+(isFirstLevelMenu?'一级菜单名称不多于4个汉字':'二级菜单名称不多于8个汉字')+'</span><span><input type="text" class="form-control" value="'
						+ $('.media_title', selItem).text() + '"/></span><div>',
				callback : function(event) {
					var newName = $('input', event.data.floater).val();
					var newId = ++snnu.wechat.custom_menu.list.data.maxId;
					var newEle = (selId == '0' ? $(snnu.wechat.custom_menu.list.data.firstLevelModel)
													: $(snnu.wechat.custom_menu.list.data.secondLevelModel));
											newEle
													.attr(
															'custom_menu_id',
															newId);
											$('.title', newEle)
													.text(
															newName);
											$('.asc', newEle)
													.click(
															snnu.wechat.custom_menu.list.ascFunc);
											$('.desc', newEle)
													.click(
															snnu.wechat.custom_menu.list.descFunc);
											$('.add', newEle)
													.click(
															snnu.wechat.custom_menu.list.addFunc);
											$('.edit', newEle)
													.click(
															snnu.wechat.custom_menu.list.editFunc);
											$('.del', newEle)
													.click(
															snnu.wechat.custom_menu.list.delFunc);
											$('.title', newEle)
													.click(
															snnu.wechat.custom_menu.list.menuClickFunc);
											if (selId == '0') {
												$('#cust_menu_list .first')
														.append(newEle);
											} else {
												$('ul', selItem).append(newEle);
											}
					$
							.post(
									'<c:url value="/system/admin/custom_menu/add.json"/>',
									{
										'id':newId,
										'parentId' : selId,
										'name' : newName,
										'wechatJson':snnu.wechat.custom_menu.list.createWechatJson()
									},
									function(result) {

										if (result.errCode == '0') {
											$$alert({
												content : '菜单创建成功'
											});
											//修改一级菜单属性
											if(!isFirstLevelMenu){
												selItem.removeAttr("custom_menu_type");
												selItem.removeAttr("custom_menu_key");
												selItem.removeAttr("custom_menu_url");
											}
											event.data.floater.hide();
											newEle.show();
											
										} else {
											$$alert({
												content : result.msg
											});
											$(selItem).remove();
										}
										event.data.floater.hide();
									}, 'json').error(function() {
								event.data.floater.hide();
								alert('error');
							});
				}
			});
		};

		
		snnu.wechat.custom_menu.list.editFunc = function(event) {
			var selItem = $($(event.currentTarget).parents('li')[0]);
			//		alert($(selItem[0]).attr('custom_menu_id'));
			var selId = selItem.attr('custom_menu_id');
			var oldName=$($('.title', selItem)[0]).text();
			var isFirstLevelMenu=selItem.parent().attr('class')=='first';
			$$floater({
				
				content : '<div style="height:100px;width:250px;"><span>'+(isFirstLevelMenu?'一级菜单名称不多于4个汉字':'二级菜单名称不多于8个汉字')+'</span><span><input type="text" class="form-control" value="'
						+ $('.media_title', selItem).text() + '"/></span><div>',
				callback : function(event) {
					var newName = $('input', event.data.floater).val();
					$($('.title', selItem)[0]).text(newName);
					
					$
							.post(
									'<c:url value="/system/admin/custom_menu/update_name.json"/>',
									{
										'id' : selId,
										'name' : newName,
										'wechatJson':snnu.wechat.custom_menu.list.createWechatJson()
									}, function(result) {

										if (result.errCode == '0') {
											$$alert({
												content : '菜单编辑成功'
											});
											event.data.floater.hide();
										//	$('.title', selItem).text(newName);

										} else {
										$($('.title', selItem)[0]).text(oldName);
											$$alert({
												content : result.msg
											});
										}
										event.data.floater.hide();
									}, 'json').error(function() {
								event.data.floater.hide();
								alert('error');
							});
				}
			});
		};
		snnu.wechat.custom_menu.list.delFunc = function(event) {
			function delValidation(selItem){
				if($(selItem).parent().attr('class')=='first'){
				   if($('li',selItem).size()>0){
				  		$$alert({type:'error',content:'请先删除关联的二级菜单'});
				  		return false;
				   }
				}
				return true;
			}
			var selItem = $($(event.currentTarget).parents('li')[0]);
				selItem.attr('creatable','false');
			var selId = selItem.attr('custom_menu_id');
			if(!delValidation(selItem)){
				return;
			}
			$$alertFloat({
				posRef : $(event.currentTarget),
				content : '确定要删除吗？',
				callback : function(event) {
					
					$
							.post(
									'<c:url value="/system/admin/custom_menu/delete.json"/>',
									{
										'id' : selId,
										'wechatJson':snnu.wechat.custom_menu.list.createWechatJson()
									}, function(result) {

										if (result.errCode == '0') {
											$$alert({
												content : '菜单删除成功'
											});
											selItem.remove();
										} else {
											$$alert({
												content : result.msg
											});
											selItem.removeAttr('creatable');
										}
										event.data.alertFloat.hide();
									}, 'json').error(function() {
								event.data.alertFloat.hide();
								alert('error');
							});
				}
			});
		};
		
	</script>

	
</body>
</html>