String.prototype.trim=function() {  
    return this.replace(/(^\s*)|(\s*$)/g,'');  
};
function $$namespace(str) {
	var arr = str.split(',');
	for ( var i = 0, len = arr.length; i < len; i++) {
		var arrJ = arr[i].split("."), parent = {};
		for ( var j = 0, jLen = arrJ.length; j < jLen; j++) {
			var name = arrJ[j], child = parent[name];
			j === 0 ? eval('(typeof ' + name + ')==="undefined"?(' + name
					+ '={}):"";parent=' + name)
					: (parent = parent[name] = (typeof child) === 'undefined' ? {}
							: child);
		}
		;
	}
};
function $$isIE() {
	return $.browser.msie;
}

function $$browserCheck() {
	var flag = false;
	if ($.browser.safari || $.browser.mozilla|| $.browser.chrome || $.browser.opera) {
		flag = true;
	} else if ($$isIE()) {
		var version = parseInt($.browser.version);// 6.0 7.0 8.0....

		if (version >= 8) {
			flag = true;
		}
	}

	if (!flag) {
		$$alert({
			type : 'error',
			content : '请使用Firefox、Chrome或IE8以上版本浏览器'
		});
	}
}
function $$alert(opt) {

	opt = {
		type : opt.type ? 'js_alert_' + opt.type : 'js_alert_tips',
		content : opt.content ? opt.content : '',
		extra : opt.extra ? opt.extra : {},
		callback : opt.callback
	};

	if ($('#' + opt.type).length == 0) {
		var alertDiv = $('<div class="alert alert-danger js_alert ' + opt.type
				+ '" id="' + opt.type + '"></div>');
		alertDiv.html(opt.content);
		alertDiv.appendTo($('body'));
	} else {

		$('#' + opt.type).html(opt.content);
		$('#' + opt.type).show();
	}
	setTimeout(function() {
		$('#' + opt.type).hide();
		if (opt.callback) {
			opt.callback(opt.extra);
		}
	}, 3000);

}

function $$ajaxFileUpload(event) {

	var fileElementId = $(this).attr("id");
	var fileElementIdSelector = "#" + fileElementId;
	
	var cln = $(this).clone();
	cln.removeAttr('value');
	if (event.data.divSelector) {
		$('.preview',event.data.divSelector).hide();
		$('.waiting',event.data.divSelector).show();
	}
	if (event.data.preFunc) {
		event.data.preFunc();
	}
	var callback = function(result, status, extra) {

		if (!event.data.cancelDefault) {
			$$ajaxFileDefaultFunc(result, status, extra);
		}
		if (event.data.successFunc) {
			event.data.successFunc(result, status, extra);
		}
	};
	
	$.ajaxFileUpload({
		url : event.data.url,
		secureuri : false,
		extra : {
			'divSelector' : event.data.divSelector,
			'action' : event.data.action,
			'context' : event.data.context,
			'targetId':'#'+fileElementId
		},
		fileElementId : fileElementId,
		dataType : "json",
		success : callback,
		error : function(dat, status, e) {
			// todo
		}
	});
	$(fileElementIdSelector).replaceWith(cln);
	// $(fileElementIdSelector).replaceWith('<input class="a" name="imageFile"
	// fileElementId="imageFile" type="file" />');;
	$(fileElementIdSelector).bind('change', event.data, $$ajaxFileUpload);
	return false;
}
// {extra.action 为媒体类型，程序逻辑中暂未用到}
function $$ajaxFileDefaultFunc(result, status, extra) {

	if (result.errCode == 0) {

		$('.preview',extra.divSelector).children('img').attr("src",
				extra.context + result.url);

		$('.preview',extra.divSelector).show();
	}
	$('.waiting',extra.divSelector).hide();
}
function $$alert(opt) {

	opt = {
		type : opt.type ? 'js_alert_' + opt.type : 'js_alert_tips',
		content : opt.content ? opt.content : '',
		extra : opt.extra ? opt.extra : {},
		callback : opt.callback
	};

	if ($('#' + opt.type).length == 0) {
		var alertDiv = $('<div class="alert alert-danger js_alert ' + opt.type
				+ '" id="' + opt.type + '"></div>');
		alertDiv.html(opt.content);
		alertDiv.appendTo($('body'));
	} else {

		$('#' + opt.type).html(opt.content);
		$('#' + opt.type).show();
	}
	setTimeout(function() {
		$('#' + opt.type).hide();
		if (opt.callback) {
			opt.callback(opt.extra);
		}
	}, 3000);

}
function $$floater(opt){
	if(!opt){
		return;
	}
	opt ={
		modal:true, //目前仅支持模态
		height:opt.height?opt.height:'300px',
		content:opt.content?opt.content:'',
		
		title:opt.title?opt.title:'提示框',
		width:opt.width?opt.width:'600px',
		padding:opt.padding?opt.padding:'0px',
		type:opt.type?opt.type:'custom_content',//可选值 'custom_content' 'selected_element'(页面片，用clone的方式来填充) 'text_input'
		textLimit:opt.textLimit?opt.textLimit:global.data.MaxTextInputLength,
		callback:opt.callback?opt.callback:null,
		cancelCallback:opt.cancelCallback?opt.cancelCallback:null
	};
	
	var floater=$('#js_floater');
	if(floater.size()==0){
		floater=$('<div class="js_floater h" id="js_floater"><div><div class="js_container"><div class="title"><span></span> <i class="close_icon"></i></div><div class="body_wrap"><div class="body"><div class="content"></div></div></div><div class="bottom"><button class="btn mybtn btn-success ok">确定</button><button class="btn mybtn btn-info back">返回</button></div></div></div></div>');
		
		$('body').append(floater);
		
	}
	var jsContainer=$('.js_container',floater)[0];
	$(jsContainer).css('width',opt.width);
	$('.content',floater).css('height',opt.height);
	$('.content',floater).css('padding',opt.padding);
	

	$(jsContainer).css({'top':$(window).height()+'px','left':$(window).width()+'px'});	
	$('.title span',floater).text(opt.title);
	var floaterTextInputModel='<div class="vpanel"><div class="vbody" style="height:200px;"><textarea class="vtextarea fill-x fill-y"></textarea></div><div class="vbottom"><i class="emotion"></i><span class="tips floatR">还可以输入<span class="remaining">'+opt.textLimit+'</span>字<span></div></div>';
	if(opt.type=='text_input'){
		$('.content',floater).html(floaterTextInputModel);
		$$WechatTextInputCheck ({selEle:$('.vtextarea',floater),container:floater,textLimit:opt.textLimit});
	}else if(opt.type=='custom_content'){
		$('.content',floater).html(opt.content);
	}else if(opt.type=='selected_element'){
		
	}
	$('.ok',floater).unbind();
	function _callback(data,event){
		if(opt.callback){
			opt.callback(data,event);
		}
	}
	if(opt.callback){
		$('.ok',floater).click({'floater':floater},_callback);
	}
	$('.back,.close_icon',floater).unbind();
	$('.back,.close_icon',floater).click(function(event){
		floater.hide();
		if(opt.cancelCallback){
			opt.cancelCallback(event);
		}
	});
	floater.show();
	var posTop=($(window).height()-$(jsContainer).height())/2;
	if(posTop<0){
		posTop=0;
	}
	var posLeft=($(window).width()-$(jsContainer).width())/2;
	if(posLeft<0){
		posLeft=0;
	}
	$(jsContainer).css({'top':posTop+'px','left':posLeft+'px'});	

	
}
function $$alertFloat(opt) {
	if (!opt.posRef) {
		return;
	}
	var alertFloat = $('.js_alert_float_layer');
	if (alertFloat.length == 0) {
		alertFloat = $('<div class="js_alert_float_layer"><i class="myarrow_top myarrow_top_out"></i><i class="myarrow_top myarrow_top_in"></i><div class="content"></div><div class="opt"><button class="btn btn-success js_ok">确定</button> <button class="btn btn-success js_cancel">取消</button></div></div>');
		alertFloat.appendTo($('body'));
	}
	

	var floatTop = $(opt.posRef).offset().top + $(opt.posRef).height() + 6;
	var floatLeft = $(opt.posRef).offset().left + $(opt.posRef).width() / 2
			- alertFloat.width() / 2;
	alertFloat.css({
		'left' : floatLeft + 'px',
		'top' : floatTop + 'px'
	});

	if (opt.content) {
		alertFloat.children('.content').html(opt.content);
	}
	alertFloat.show();
	alertFloat.find('.js_ok').unbind();
	alertFloat.find('.js_cancel').unbind();
	if (opt.callback) {
		alertFloat.find('.js_ok').click({
			'alertFloat' : alertFloat
		}, opt.callback);
	}
	alertFloat.find('.js_cancel').click(function() {
		alertFloat.hide();
	});

}

function $$resetForm(sel) {
	$('input[type=text]', sel).val("");
	$('input[type=hidden]', sel).val("");
	// $('input[type=checkbox]',sel).removeAttr("checked");
	$('textarea', sel).val("");
	// TODO more elements
}
function $$fillForm(json, form) {

	for ( var p in json) {

		if (!p) {
			continue;
		}
		var ele = $('[name=' + p + ']', form);// 暂未处理多个同名checkbox 以及多个同名radio

		if (ele) {

			ele = $(ele[0]);
			var type = ele.attr('type');
			if ('checkbox' == type) {
				if (ele.val() == json[p]) {
					ele.attr('checked', 'checked');
				} else {
					ele.removeAttr();
				}
			} else { // 处理input
				// type为text、password、hidden以及textarea，select等其它类型暂未处理
				ele.val(json[p]);
			}
		}
	}
}
function $$form2json(form) {
	var result = {};
	var items = form.serializeArray();
	for ( var idx in items) {

		result[items[idx].name] = items[idx].value;
	}

	return result;
}

function $$pageButtonClick(event) {// 为简单起见，未使用{pageNum:xxx,pageInput:yyy}的输入参数
	var pageData = $(event.currentTarget).attr('pageData');
	var page = parseInt(pageData);
	if (!page) {
		page = parseInt($(event.currentTarget).prev().val());
		if (!page) {
			return;
		}
	}
	var url = event.data.url;

	// 暂不做url解析,暂不处理锚点
	if (url.search('\\?') < 0) {
		url += '?';
	} else {
		url += '&';
	}

	url += 'pageSize=' + event.data.pageSize + '&pageNum=' + page;
	window.location = url;

}
function $$pager(opt) {
	opt = {
		url : opt.url,
		pageSize : opt.pageSize ? opt.pageSize : 10,
		currentPageNum : opt.currentPageNum ? opt.currentPageNum : 1,
		totalPageNum : opt.totalPageNum ? opt.totalPageNum : 0,
		totalSize : opt.totalSize ? opt.totalSize : 0,
		parentDiv : opt.parentDiv,
		type : 'simple'// 'simple':没有页面选择button 'whole':有1、2、3等页面选择button。暂未支持
	};
	if (!opt.totalSize || !opt.parentDiv) {
		return;
	}
	// 如果后台没有计算总页数
	if (!opt.totalPageNum) {
		opt.totalPageNum = Math.floor((opt.totalSize + (opt.pageSize - 1))
				/ opt.pageSize);
	}
	if (opt.totalPageNum == 1) {
		return;
	}
	var pageModel = '<div><div class="js_pager"><button class="prev"><i class="js_arrow_left"></i></button><span>1/5</span><button class="next"><i class="js_arrow_right"></i></button><input type="text" />  <button class="goto" >跳转</button></div></div>';
	var page = $(pageModel);
	if (opt.currentPageNum == 1) {
		$('.prev', page).hide();
	} else {
		$('.prev', page).attr('pageData', (opt.currentPageNum - 1));
	}
	if (opt.currentPageNum == opt.totalPageNum) {
		$('.next', page).hide();
	} else {
		$('.next', page).attr('pageData', (opt.currentPageNum + 1));
	}
	$('span', page).html(opt.currentPageNum + '/' + opt.totalPageNum);

	$('button', page).click({
		url : opt.url,
		pageSize : opt.pageSize
	}, $$pageButtonClick);
	$(opt.parentDiv).append(page);

	$(opt.parentDiv).show();
}
function $$uploadDisplayFix() {
	if ($$isIE()) {

		$('input.fix_upload').removeClass('hi');
	} else {
		$('button.fix_upload').removeClass('hi');
	}
}
function $$validator(def) {
	function selByName(name){
		return $('[name='+name+']')[0];
	}
	
	var result = {errCode:'0'};
	var flag=false;
	
	for ( var p in def) {
		
		if (def[p].type == 'regex') {

		} else if (def[p].type == 'string') {
			if(def[p].min&&$(selByName(p)).val().length<def[p].min){
				
				flag=true;
			}
			else if(def[p].max&&$(selByName(p)).val().length>def[p].max){
				
				flag=true;
			}

		} else if (def[p].type == 'number') {
			if(def[p].min&&parseInt($(selByName(p)).val())<def[p].min){
				flag=true;
			}
			else if(def[p].max&&parseInt($(selByName(p)).val())>def[p].max){
				flag=true;
			}
		}
		else if (def[p].type == 'condition') {
			if($(selByName(p)).val()!=$(selByName(def[p].target)).val()){
				flag=true;
				
			}
		}
		else if (def[p].type == 'notNull') {
			if(!$(selByName(p)).val()||$(selByName(p)).val()==''){
				flag=true;
				
			}
		}
		if(flag){
			result.errCode='1';
			result.msg=def[p].msg;
			break;
		}

	}
	return result;
}
function $$validatorVal(opt){
	if(!opt){
		return false;
	}
	if(opt.type=='url'){
		
		var re = new RegExp("^(http://){1}[/\\w.&#=\\?]+$","ig");
		return opt.value&&re.exec(opt.value);
	}
	return true;
}
function $$contains(target,element){
	for(var p in target){
		if( (target[p])==element){
			return true;
		}
	}
	return false;
}
function $$removeContext(url){
	return !url?'':url.replace(/\/\w+/i,'');
}
function $$initTab(){
	
	$('.vee_tab .top li').click(function(){
		$(this).siblings().removeClass('current');
		$(this).addClass('current');
		var selTab=$($(this).parents('.vee_tab')[0]);
		$('.body',selTab).children().hide();
		$('.body > div',selTab).filter('[tab_name='+$(this).attr('tab_target')+']').show();
	});
	$('.vee_tab .top li:eq(0)').click();
}
function $$initAjaxTab(opt){
	if(!opt){
		return;
	}
	$('.vee_tab .top li').click(function(){
		var targetName=$(this).attr('tab_target');
		var selTab=$($(this).parents('.vee_tab')[0]);
		$(this).siblings().removeClass('current');
		$(this).addClass('current');
		if(opt[targetName].preFunc){
			opt[targetName].preFunc();
		
		}
		$.post(opt[targetName].url,opt[targetName].params?opt[targetName].params:{},function(result){
			
			if(result.errCode=='0'){
				if(opt[targetName].callback){
					opt[targetName].callback(result);
				}
				
				$('.body',selTab).children().hide();
				$('.body > div',selTab).filter('[tab_name='+targetName+']').show();
			}
		},'json').error(function(){alert("发生错误");});
		
		var selTab=$($(this).parents('.vee_tab')[0]);
		
		
	});
	$('.vee_tab .top li:eq(0)').click();
}
function $$registerPostBtn(){
	
	$('button').click(function(){$(this).blur();});
	$('button.post').click(function(){
		var src=$(this);
		src.attr('disabled','disabled');
		function enablePost(){
			src.removeAttr('disabled');
		}
		setTimeout(enablePost, 5000);
	});
}
function $$registerPanelFoldBtn(vpanel){
	if(!vpanel){
		vpanel=$('.vpanel');
	}
	$('.vtitle i',vpanel).toggle(function(){
		$(this).parent().siblings().hide();
	},function(){
		$(this).parent().siblings().show();
	});
}
function $$foldPanel(vpanel){
	$('.vtitle i',vpanel).click();
}
function $$preloadLoadingFloater(){
	var loadingDiv='<div id="js_loading_floater" class="h"><img src="'+global.data.baseContextUrl+'/static/images/loading.gif"></div>';
	$('body').append($(loadingDiv));
}
function $$loadingFloaterShow(){
	$('#js_loading_floater').show();
}
function $$loadingFloaterHide(){
	$('#js_loading_floater').hide();
}
$(document).ready(function(){
	//$$initTab();
	$$registerPostBtn();
	$$registerPanelFoldBtn();
	$$preloadLoadingFloater();
});