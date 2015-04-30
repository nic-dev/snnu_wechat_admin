function $$WechatTextInputCheck (opt){
	opt={
			selEle:opt.selEle,
			container:opt.container,
			textLimit:opt.textLimit?opt.textLimit:global.data.MaxTextInputLength
			};
	if(!opt.selEle){
		return;
	}
	var remainingLength;

	$('.remaining',$(opt.container)).text(remainingLength);	
	$(opt.selEle).click(function(){
		remainingLength=opt.textLimit-$(opt.selEle).val().length;
		$('.remaining',$(opt.container)).text(remainingLength);	
		$('.tips',$(opt.container)).show();	
		
	});
	$(opt.selEle).bind('keyup',function(event){
		
		var currentTarget=event.currentTarget;

		remainingLength=opt.textLimit-$(currentTarget).val().length;
	//	alert(event.which);
		if(remainingLength<=0){
			//$(currentTarget).attr('disabled','disabled');
			if(event.which!=8&&event.which!=46) //8->backspace 46->delete
			{
				opt.selEle.val(opt.selEle.val().substring(0,opt.textLimit));
			}
		}
		
			$('.remaining',$(opt.container)).text(remainingLength);	
	});
	
	
}
function $$createAccessUrl(opt){
	if(!opt||!opt.id){
		return '';
	}
	return global.data.mobileArticleUrl+'?id='+opt.id;
}