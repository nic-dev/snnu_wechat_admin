$$namespace('global');
global.data={
	baseContextUrl:'http://localhost/veeportal',
	frontArticleUrl:'/wechat/detail.html',
	mobileArticleUrl:'/mobile/article.html',
	config:{'ueditorConfig':'0','text':'1','image':'2','voice':'3','video':'4','news':'5'},
	mediaTypeOrder:{'0':'ueditorConfig','1':'text','2':'image','3':'voice','4':'video','5':'news'},
	replyType:{subscribe:'1',auto:'2',keyword:'3',custom_keyword:'4'},
	eleModel:{image:'<img class="media" />',voice:'<audio class="media" controls>您的浏览器不支持audio标签</audio>',video:'<video class="media" controls>您的浏览器不支持audio标签</video>',news:{main:'<div msg_id="" class="message" selected="selected"><div class="detail"><div class="title"></div><div class="creat_time"></div><div class="cover"><img src=""></div></div><div class="wechat_json_data h"></div></div>',sub:'<div class="sub"><div class="title"></div><div class="cover"><img src=""/></div></div>'}},
	
	MaxTextInputLength:600
};