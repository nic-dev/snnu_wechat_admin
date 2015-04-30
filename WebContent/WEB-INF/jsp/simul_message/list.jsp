<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%@include file="../common/taglib.jsp"%>
		<%@ include file="../common/resources.jsp"%>

		<link rel="stylesheet"
			href="<c:url value="/static/styles/simul_message/list.css"/>" />
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
						<li class="current">
							<a href="javascript:void(0);"> 同步消息</a>
						</li>
						<li >
							<a href="<c:url value="/system/admin/simul_media/list.html?type=3"/>"> 同步音频</a>
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
						抓取条数  <select class="form-control inline_adapter"><option>10</option><option>20</option>
						<option>40</option></select> 条
						<button class="btn mybtn btn-success post" id="pull_msgs_btn">同步</button>	</div>
							<c:forEach items="${pageModel.page.list}" var="msg" varStatus="sta">
								<c:if test="${(sta.index%3)==0}">
									<div class="message">
										<div class="detail">
											<div class="title">
												<c:out value="${msg.title}"/>
											</div>
											<div class="creat_time">
												<fmt:formatDate value="${msg.createTime }" pattern="MM月dd日" />

											</div>
											<div class="cover">
												<img src="<c:url value="${msg.cover }"/>" />
											</div>
										</div>
										<div class="simul_wechat_json h"><c:out value="${msg.simulWechatJson }"/></div>
										<div class="h"></div>
										<div class="opt h">
											<span><a
												href="<c:url value="/system/admin/message/edit.html?id=${msg.id }"/>"><i
													class="edit"></i> </a> </span>
											<span><i class="del" data-val="${msg.id}"></i>  </span>
										</div>
									</div>
								</c:if>
							</c:forEach>

						</li>
						<li class="li_col">
							<c:forEach items="${pageModel.page.list}" var="msg" varStatus="sta">
								<c:if test="${(sta.index%3)==1}">
									<div class="message">
										<div class="detail">
											<div class="title">
												<c:out value="${msg.title}"/>
											</div>
											<div class="creat_time">
												<fmt:formatDate value="${msg.createTime }" pattern="MM月dd日" />
											</div>
											<div class="cover">
												<img src="<c:url value="${msg.cover }"/>" />
											</div>
										</div>
										<div class="simul_wechat_json h"><c:out value="${msg.simulWechatJson }"/></div>
										<div class="h"></div>
										<div class="opt h">
											<span><a
												href="<c:url value="/system/admin/message/edit.html?id=${msg.id }"/>"><i
													class="edit"></i> </a> </span>
											<span> <i class="del" data-val="${msg.id}"></i> </span>
										</div>
									</div>
								</c:if>
							</c:forEach>

						</li>
						<li class="li_col">
							<c:forEach items="${pageModel.page.list}" var="msg" varStatus="sta">
								<c:if test="${(sta.index%3)==2}">
									<div class="message">
										<div class="detail">
											<div class="title">
												<c:out value="${msg.title}"/>
											</div>
											<div class="creat_time">
												<fmt:formatDate value="${msg.createTime }" pattern="MM月dd日" />
											</div>
											<div class="cover">
												<img src="<c:url value="${msg.cover }"/>" />
											</div>
										</div>
										<div class="simul_wechat_json h"><c:out value="${msg.simulWechatJson }"/></div>
										<div class="h"></div>
										<div class="opt h">
												<span><a
												href="<c:url value="/system/admin/message/edit.html?id=${msg.id }"/>"><i
													class="edit"></i>
											</a>
											</span>
											<span> <i class="del" data-val="${msg.id}"></i> </span>
										</div>
									</div>
								</c:if>
							</c:forEach>

						</li>

					</ul>
				</div>
				<div class="clear"></div>
				<div id="page"></div>
			</div>
		</div>

		<%@ include file="../common/footer.jsp"%>


		<script>
			$(document).ready(function(){
				$$pager({url:'<c:url value="/system/admin/simul_message/list.html"/>',
					currentPageNum:parseInt('${pageModel.pageForm.pageNum}'),
					totalSize:parseInt('${pageModel.page.count}'),
					pageSize:parseInt('${pageModel.pageForm.pageSize}'),
					parentDiv:$('#page')
				});
				wechat.message.initData();
			});
			
			$$namespace('wechat.message.list');
			wechat.message.list.data={'subModel':'<div class="sub"><div class="title"></div><div class="cover"><img src="" /></div></div>',
			};
			wechat.message.initData=function(){
				
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
								newSub=$(wechat.message.list.data.subModel);
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
			
			$('#pull_msgs_btn').click(function(){
				var count=($(this).prev().val());
				$$loadingFloaterShow();
				$.post('<c:url value="/system/admin/simul_message/pull_wechat_msgs.json"/>',{count:count},function(result){
					if(result.errCode='0'){
						$$alert({content:'素材同步成功',callback:function(){
							window.location='${pageContext.request.contextPath}/system/admin/simul_message/list.html';
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
					$.post('<c:url value="/system/admin/message/delete.json"/>',{'id':id},function(result){
						
						if(result.errCode=='0'){
						$$alert({content:'删除成功'});
						 src.parents('.message').hide();
						 
						}else{
						
						}
						event.data.alertFloat.hide();
					},'json').error(function(){event.data.alertFloat.hide();alert('error');});
				}});
			});
		</script>

	</body>
</html>