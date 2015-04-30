var page={renderCallback:null};
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, '');
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
function $$alert(opt) {

	opt = {
		type : opt.type ? 'js_alert_' + opt.type : 'js_alert_tips',
		content : opt.content ? opt.content : '',
		extra : opt.extra ? opt.extra : {},
		timeout:opt.timeout?opt.timeout:5000,
		callback : opt.callback
	};

	if ($('#' + opt.type).length == 0) {
		var alertDiv = $('<div class="js_alert ' + opt.type
				+ '" id="' + opt.type + '"></div>');
		alertDiv.html(opt.content);
		alertDiv.appendTo($('body'));
	} else {

		$('#' + opt.type).html(opt.content);
		$('#' +opt.type).css({'top':'0px'});
		$('#' + opt.type).show();
	}
	setTimeout(function() {
		$('#' + opt.type).hide();
		if (opt.callback) {
			opt.callback(opt.extra);
		}
	}, opt.timeout);

}
function $$fixFormWidth(formSel) {
	$('input', $(formSel)).each(function() {
		$(this).width($(this).parent().width() - 50);// 避免计算，直接写死i元素宽度+inputpaddingleft宽度之和略大的一个数

	});

}
function $$initVmlist() {
	$('.vmlist i').toggle(function() {
		
		$(this).parent().siblings().hide();
		$(this).show().removeClass('unfold').addClass('fold');

	}, function() {
		
		$(this).parent().siblings().show();
		$(this).show().removeClass('fold').addClass('unfold');

	});
}
function $$foldVmlist(vmList,foldOpt){//foldOpt:'fold' 'unfold'
	var foldEle=$('.header i',$(vmList));
	if(foldEle.hasClass(foldOpt)){
		foldEle.click();
	}
}
function $$unfoldVmList(vmList){
	
}
function $$mobileDialog(opt) {

	if (!opt) {
		return;
	}
	opt = {
		title : opt.title ? opt.title : null,
		content : opt.content ? opt.content : null,
		contentImage:opt.contentImage?opt.contentImage:(opt.loading?config.baseContext+'/static/images/loading.gif':null),
		callback : opt.callback ? opt.callback : null,
		width : opt.width?opt.width:'90%',
		height : opt.height,
		top:opt.top,
		preventDefault:opt.preventDefault?opt.preventDefault:false,
		type : opt.type ? opt.type : 'confirm' // 'none' 'alert' 'confirm'
	};
	var dialogModel = '<div id="mobile_dialog" class="h"><div class="header"></div><div class="content"></div><div class="opt"><button class="vmbutton vmbutton-inline  col_4 ok  ">确定</button><button class="vmbutton vmbutton-inline  col_4 cancel">取消</button> </div> </div>';
	var dialog = $('#mobile_dialog')[0];

	if (!dialog) {

		dialog = $(dialogModel);
		$('body').prepend(dialog);
	}
	$(dialog).css({'width':opt.width});
	if (opt.title) {
		$('.header', dialog).text(opt.title);
	} else {
		$('.header', dialog).hide();
	}
	if (opt.content) {
		$('.content', dialog).html(opt.content);
	}
	else if(opt.contentImage){
		$('.content',dialog).html('<img src="'+opt.contentImage+'"/>');
	}
	if (opt.type == 'none') {
		$('.opt', dialog).hide();
	} else if (opt.type == 'alert') {
		$('.cancel', dialog).addClass('hi');
		$('.ok',dialog).unbind().click({dialog:dialog},function(event){
			if(!opt.preventDefault){
				$(dialog).hide();
			}
			if(opt.callback){
				opt.callback(event);
			}
		});
		$('.opt',dialog).show();
	}
	else{
		$('.cancel',dialog).unbind().click(function(){
			$(dialog).hide();
		});
		$('.ok',dialog).unbind();
		if(opt.callback){
			$('.ok',dialog).bind({dialog:dialog},opt.callback);
		}
		$('.opt',dialog).show();
		
	}
	// 避免手机水平、垂直转向 ，实时监测屏幕宽高
	var windowWidth = $(window).width();
	var windowHeight = $(window).height();
	var dialogWidth = $(dialog).width();
	var dialogHeight = $(dialog).height();
	var posX = (windowWidth - dialogWidth) / 2;
	if (posX < 0) {
		posX = 0;
	}
	var posY = (windowHeight - dialogHeight) / 2;
	if (posY < 0) {
		posY = 0;
	}
	$(dialog).css({
		'left' : posX + 'px',
		'top' : opt.top?opt.top:posY + 'px'
	});
	$(dialog).show();
}
function $$mobileDialogHide(){
	$('#mobile_dialog').hide();
}
function $$centerPos(eleId,parentId){
	var ele=$(eleId);
	var parent=parentId?$(parentId):$(window);
	var parentWidth = $(parent).width();
	var parentHeight = $(parent).height();
	var eleWidth = $(ele).outerWidth();
	var eleHeight = $(ele).outerHeight();
	var posX = (parentWidth - eleWidth) / 2;
	if (posX < 0) {
		posX = 0;
	}
	
	var posY = (parentHeight - eleHeight) / 2;
	if (posY < 0) {
		posY = 0;
	}
	$(ele).css({
		'position':'absolute',
		'left' : posX + 'px',
		'top' : posY + 'px'
	});
}
function $$renderHtml(){
//	var windowWidth = $(document).width();
//	var windowHeight = $(document).height();
//	$('body').css({'width':windowWidth+'px','height':windowHeight+'px'});
	
	if(page.renderCallback){
		
		page.renderCallback();
	}

	$('body').css({'opacity':'1'});
}
function $$preloadLoading(){
	$('body').append('<img id="preload_loading" class="h" src="'+config.baseContext+'/static/images/loading.gif">');
}
$(document).ready(function() {
	
	$$preloadLoading();
	$$renderHtml();
	$$initVmlist();
	// $('input').attr('hideFocus','true');
});