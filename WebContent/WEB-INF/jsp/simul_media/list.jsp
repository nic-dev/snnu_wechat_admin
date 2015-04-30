<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%@include file="../common/taglib.jsp"%>
		<%@ include file="../common/resources.jsp"%>

		<link rel="stylesheet"
			href="<c:url value="/static/styles/simul_media/list.css"/>" />
	</head>
	<body>
		<%@include file="../common/header.jsp"%>

		<div id="container">
			<%@ include file="../common/sidebar.jsp"%>
			<div id="main">
				<div class="title">
					同步素材管理
				</div>
				<div class="type_list">
					<ul>
						<li >
							<a href="<c:url value="/system/admin/simul_message/list.html"/>"> 同步消息</a>
						</li>
						<li class="current">
							<a href="javascript:void(0);"> 同步音频</a>
						</li>
					
					</ul>
					<div class="clear"></div>
				</div>
				<div class="mynav">
					<ul>
						<li>
							同步素材列表（共${pageModel.page.count}个）
						</li>

					</ul>
				</div>
				<div class="content">
					<ul>
						<li class="li_col">
							<div class="pull">
						抓取条数  <select class="form-control inline_adapter"><option>1</option><option>2</option>
						<option>5</option></select> 条
						<button class="btn mybtn btn-success post" id="pull_medias_btn">同步</button>	</div>
						</li>						
					</ul>
					<div class="vpanel vpanel_simple clear">
					<table id="simul_media_list" class="table table-bordered table-hover">
				<thead><tr><th>名称</th><th>大小</th><th>创建时间</th><th>播放</th> </tr> </thead>
				<tbody>
				<c:forEach var="item" items="${pageModel.page.list }" varStatus="sta">
				<tr class="${sta.index%2==0?'success':'' }">
				<td><c:out value="${item.name}"/></td><td>${item.size/1024}K</td><td><fmt:formatDate value="${item.createTime }" pattern="MM月dd日" /></td><td> <audio class="media" src="<c:url value="${item.path}"/>"
										controls> 您的浏览器不支持 audio 标签。
									</audio></td>
				</tr>
				</c:forEach>
				</tbody>
				 </table>
				
					</div>
				</div>
				<div class="clear"></div>
				<div id="page"></div>
				
			</div>
			<div class="clear"></div>
		</div>

		<%@ include file="../common/footer.jsp"%>


		<script>
			$(document).ready(function(){
				$$pager({url:'<c:url value="/system/admin/simul_media/list.html?type=${param.type}"/>',
					currentPageNum:parseInt('${pageModel.pageForm.pageNum}'),
					totalSize:parseInt('${pageModel.page.count}'),
					pageSize:parseInt('${pageModel.pageForm.pageSize}'),
					parentDiv:$('#page')
				});
				wechat.media.initData();
			});
			
			$$namespace('wechat.media.list');
			wechat.media.list.data={'subModel':'<div class="sub"><div class="title"></div><div class="cover"><img src="" /></div></div>',
			};
			wechat.media.initData=function(){
				
				$('.simul_wechat_json').each(function(){
					var subsJson=$(this).html();
					if(subsJson){
						var subs=null;
						var newSub=null;
						var subsArea=$(this).next();
						try{
							subs=$.parseJSON(subsJson).multi_item;
							for(var subIdx in subs){
								if(subIdx=='0'){
									$(this).prev().attr('link',subs[subIdx].content_url);
								}
								else{
								newSub=$(wechat.media.list.data.subModel);
								newSub.attr('link',subs[subIdx].content_url);
								$('.title',newSub).text(subs[subIdx].title);
							
						//		alert(subs[subIdx].cover);
								$('img',newSub).attr('src',subs[subIdx].cover);
								subsArea.append(newSub);
								}
							}
							
							subsArea.show();
							
							
						}
						catch(err){
							$('sys_error').html(err.toString);
						}
						
					}
				});
				
				$('.detail,.sub').click(function(){
					
					window.open($(this).attr('link'));
				});
			};
			
			$('#pull_medias_btn').click(function(){
				var count=($(this).prev().val());
				$$loadingFloaterShow();
				$.post('<c:url value="/system/admin/simul_media/pull_wechat_medias.json"/>',{count:count,type:'${param.type}'},function(result){
					if(result.errCode='0'){
						$$alert({content:'素材同步成功',callback:function(){
							window.location='${pageContext.request.contextPath}/system/admin/simul_media/list.html';
						}});
					}
					else{
						$$alert({type:'error',content:'素材同步失败，请联系管理员'});
					}
					$$loadingFloaterHide();
				},'json').error(function(){
					$$alert({type:'error',content:'素材同步失败，请联系管理员'});
					$$loadingFloaterHide();
				});
			});
			$('.del').click(function(){
				var src=$(this);
				var id=$(this).attr('data-val');
				$$alertFloat({posRef:$(this).parent(),content:'您确定要删除吗？',callback:function(event){
					$.post('<c:url value="/system/admin/media/delete.json"/>',{'id':id},function(result){
						
						if(result.errCode=='0'){
						$$alert({content:'删除成功'});
						 src.parents('.media').hide();
						 
						}else{
						
						}
						event.data.alertFloat.hide();
					},'json').error(function(){event.data.alertFloat.hide();alert('error');});
				}});
			});
		</script>

	</body>
</html>