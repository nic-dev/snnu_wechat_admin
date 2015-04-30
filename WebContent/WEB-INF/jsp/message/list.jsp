<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%@include file="../common/taglib.jsp"%>
		<%@ include file="../common/resources.jsp"%>

		<link rel="stylesheet"
			href="<c:url value="/static/styles/message/list.css"/>" />
	</head>
	<body>
		<%@include file="../common/header.jsp"%>

		<div id="container">
			<%@ include file="../common/sidebar.jsp"%>
			<div id="main">
				<div class="title">
					素材管理
				</div>
				<div class="type_list">
					<ul>
						<li class="current">
							<a href="javascript:void(0);"> 图文消息</a>
						</li>
						<li>
							<a href="<c:url value="/system/admin/media/list.html?type=${mediaType.image}"/>"> 图片</a>
						</li>
						<li>
							<a href="<c:url value="/system/admin//media/list.html?type=${mediaType.voice}"/>"> 语音</a>
						</li>
						<li>
							<a href="<c:url value="/system/admin/media/list.html?type=${mediaType.video}"/>"> 视频</a>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="mynav">
					<ul>
						<li>
							图文消息列表（共${pageModel.page.count}个）
						</li>

					</ul>
				</div>
				<div class="content">
					<ul>
						<li class="li_col">
							<div class="add">
								<div class="hover h">
								<div class=" "><a href="<c:url value="/system/admin/message/add.html?type=1"/>" ><i class="add_single"></i><span>单图文消息</span></a></div>
								<div class=" "><a href="<c:url value="/system/admin/message/add.html?type=2"/>" ><i class="add_multi"></i><span>多图文消息</span></a></div>
								</div>
								<a href="<c:url value="/system/admin/message/single/add.html"/>" class="normal" >+</a>
							</div>
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
										<div class="sub_data h"><c:out value="${msg.subs }"/></div>
										<div class="h"></div>
										<div class="opt">
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
										<div class="sub_data h"><c:out value="${msg.subs }"/></div>
										<div class="h"></div>
										<div class="opt">
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
										<div class="sub_data h"><c:out value="${msg.subs }"/></div>
										<div class="h"></div>
										<div class="opt">
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
				$$pager({url:'<c:url value="/system/admin/message/list.html"/>',
					currentPageNum:${pageModel.pageForm.pageNum},
					totalSize:${pageModel.page.count},
					pageSize:${pageModel.pageForm.pageSize},
					parentDiv:$('#page')
				});
				wechat.message.initData();
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
			
			$('.add').mouseover(function(){
	
				$('.normal',$(this)).hide();
				$('.hover',$(this)).show();
				
			}).mouseout(function(){
				$('.hover',$(this)).hide();
				$('.normal',$(this)).show();
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