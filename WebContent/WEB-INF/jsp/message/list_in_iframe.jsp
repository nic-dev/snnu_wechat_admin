<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet"
	href="<c:url value="/static/styles/message/list_in_iframe.css"/>" />
</head>
<body>

	<c:choose>
		<c:when test="${pageModel.page.count>0 }">
			<div id="iframe_container">

				<div id="main">


					<div class="content">
						<ul>
							<li class="li_col"><c:forEach items="${pageModel.page.list}"
									var="msg" varStatus="sta">
									<c:if test="${(sta.index%2)==0}">
										<div class="message" msg_id="${msg.id}"
											msg_wechat_id=${msg.mediaId }>
											<div class="detail">
												<div class="title">
													<c:out value="${msg.title}" />
												</div>
												<div class="creat_time">
													<fmt:formatDate value="${msg.createTime }" pattern="MM月dd日" />

												</div>
												<div class="cover">
													<c:choose>
														<c:when test="${msg.cover!='' }">
															<img src="<c:url value="${msg.cover }"/>" />
														</c:when>
														<c:otherwise>
															<span>暂无封面</span>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="wechat_json_data h"><c:out value="${msg.wechatJson }"/></div>
											<div class="sub_data h"><c:out value="${msg.subs }"/></div>

											<div class="h"></div>
											<div class="opt h">
												<span><a
													href="<c:url value="/portal/admin/article/edit.html?id=${msg.id }"/>"><i
														class="edit"></i> </a> </span> <span><i class="del"
													data-val="${msg.id}"></i> </span>
											</div>
											<div class="selected h">
												<div class="ok"></div>
											</div>
										</div>
									</c:if>
								</c:forEach></li>

							<li class="li_col"><c:forEach items="${pageModel.page.list}"
									var="msg" varStatus="sta">
									<c:if test="${(sta.index%2)==1}">
										<div class="message" msg_id="${msg.id}"
											msg_wechat_id=${msg.mediaId }>
											<div class="detail">
												<div class="title">
													<c:out value="${msg.title}" />
												</div>
												<div class="creat_time">
													<fmt:formatDate value="${msg.createTime }" pattern="MM月dd日" />
												</div>
												<div class="cover">
													<img src="<c:url value="${msg.cover }"/>" />
												</div>
											</div>
											<div class="wechat_json_data h"><c:out value="${msg.wechatJson }"/></div>
											<div class="sub_data h"><c:out value="${msg.subs }"/></div>
											<div class="h"></div>
											<div class="opt h">
												<span><a
													href="<c:url value="/portal/admin/article/edit.html?id=${msg.id }"/>"><i
														class="edit"></i> </a> </span> <span> <i class="del"
													data-val="${msg.id}"></i> </span>
											</div>
											<div class="selected h">
												<div class="ok"></div>
											</div>
										</div>
									</c:if>
								</c:forEach></li>

						</ul>
					</div>
					<div class="clear"></div>
					<div id="page"></div>
				</div>
			</div>
		</c:when>
		<c:otherwise>暂无素材</c:otherwise>
	</c:choose>
	<div id="selectedId"></div>



	<script>
			$(document).ready(function(){
				$$pager({url:'<c:url value="/portal/admin/article/list_in_iframe.html"/>',
					currentPageNum:${pageModel.pageForm.pageNum},
					totalSize:${pageModel.page.count},
					pageSize:${pageModel.pageForm.pageSize},
					parentDiv:$('#page')
				});
				wechat.message.initData();
				wechat.message.initBind();
			});
			
			$$namespace('wechat.message.list');
			wechat.message.list.data={'subModel':'<div class="sub"><div class="title"></div><div class="cover"><img src="" /></div></div>',
			};
			wechat.message.initData=function(){
				
				$('.sub_data').each(function(){
					var subsJson=$(this).html();
					if(subsJson){
						var subs=null;
						var newSub=null;
						var subsArea=$(this).next();
						try{
							subs=$.parseJSON(subsJson);
							for(var subIdx in subs){
								if(subIdx=='0'){
									continue;
								}
								newSub=$(wechat.message.list.data.subModel);
								$('.title',newSub).text(subs[subIdx].title);
								$('img',newSub).attr('src','${pageContext.request.contextPath}'+subs[subIdx].cover);
								subsArea.append(newSub);
							}
							subsArea.show();
							
							
						}
						catch(err){
							$('sys_error').html(err.toString);
						}
						
					}
				});
			};
			wechat.message.initBind=function(){
			$('.content .message').click(
				function(){
					
				//	$('.content .message').removeAttr('selected');
				//	$(this).attr('selected','selected');
				
					$('.content .message').removeClass('sel_item');
					$(this).addClass('sel_item');
					$('.content .selected').hide();
					$('.selected',$(this)).show();
				});
			};
	
		</script>

</body>
</html>