<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet"
	href="<c:url value="/static/styles/media/list_in_iframe.css"/>" />
</head>
<body>

	<c:choose>
	<c:when test="${pageModel.page.count>0 }">
	<div id="iframe_container">
		
		<div id="main">

			<div class="content">
				<ul id="media_list">
					<c:forEach items="${pageModel.page.list}" var="media"
						varStatus="sta">
						<li media_id="${media.id }" >
							<input type="radio" name="radio"  class=""/>


							<div class="media_title">
								<c:out value="${media.name}" />
							</div>
							<div class="size">
								<fmt:formatNumber value="${media.size/1024 }" pattern=".#" />
								k

							</div>
							<div class="date">
								<fmt:formatDate value="${media.createTime}" pattern="MM月dd日" />
							
							</div>
							<c:choose>
								<c:when test="${pageModel.pageForm.type==mediaType.image}">
									<img class="media" src="<c:url value="${media.path}"/>" media_wechat_id="${media.mediaId}" media_id="${media.id }" />
								</c:when>
								<c:when test="${pageModel.pageForm.type==mediaType.voice}">
									<audio class="media" src="<c:url value="${media.path}"/>"
										controls media_wechat_id="${media.mediaId}" media_id="${media.id }"> 您的浏览器不支持 audio 标签。
									</audio>
								</c:when>
								<c:when test="${pageModel.pageForm.type==mediaType.video}">
									<video src="<c:url value="${media.path}"/>" controls media_wechat_id="${media.mediaId}" media_id="${media.id }" ></video>
								</c:when>
							</c:choose>
						</li>
					</c:forEach>
					<li class="template h">
						<input type="radio" name="radio"  class=""/>
						<div class="media_title"></div>
						<div class="size">0k</div>
						<div class="date">00月00日</div>
						<c:choose>
							<c:when test="${pageModel.pageForm.type==mediaType.image}">
								<img class="media" src="<c:url value="${media.path}"/>" />
							</c:when>
							<c:when test="${pageModel.pageForm.type==mediaType.voice}">
								<audio class="media" src="<c:url value="${media.path}"/>"
									controls> 您的浏览器不支持 audio 标签。
								</audio>
							</c:when>
							<c:when test="${pageModel.pageForm.type==mediaType.video}">
								<video src="<c:url value="${media.path}"/>" controls></video>
							</c:when>
						</c:choose>
					</li>

					
				</ul>
				<div class="clear"></div>
				<div id="page"></div>
			</div>

		</div>
		
		
	</div>
	</c:when>
	<c:otherwise>暂无素材</c:otherwise>
	</c:choose>
	<div id="selectedId"></div>



	<script>
			$(document).ready(function(){
				$$pager({url:'<c:url value="/system/admin/media/list_embedded.html?type=${pageModel.pageForm.type}"/>',
					currentPageNum:parseInt('${pageModel.pageForm.pageNum}'),
					totalSize:parseInt('${pageModel.page.count}'),
					pageSize:parseInt('${pageModel.pageForm.pageSize}'),
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
			$('.content input[type="radio"]').click(
				function(){
					
					$('.content input[type="radio"]').removeAttr('checked');
					$(this).attr('checked','checked');
					$('.content input[type="radio"]').removeClass('checked');
					$(this).addClass('checked');
				});
			};
	
		</script>

</body>
</html>