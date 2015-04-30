<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="../common/taglib.jsp"%>
<%@ include file="../common/resources.jsp"%>

<link rel="stylesheet" href="<c:url value="/static/styles/broadcast/list.css"/>" />
</head>
<body>
	<%@include file="../common/header.jsp"%>

	<div id="container">
		<%@ include file="../common/sidebar.jsp"%>
		<div id="main">
			<div class="title">群发功能</div>
			<div class="type_list">
				<ul>
					<li ><a href="<c:url value="/system/admin/broadcast/index.html"/>">
							新建群发消息</a></li>
					<li class="current"><a href="<c:url value="/system/admin/broadcast/list.html"/>">
							已发送</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="mynav h">
				<ul>
					<li></li>
				</ul>
			</div>
				
				<div class="content">
					
				<table  id="user_list" class="table  table-hover table_underline" >
				<thead class="vh"><tr ><th width="*">内容</th><th width="100px">状态</th><th width="100px">日期</th><th width="100px">操作</th> </tr> </thead>
				<tbody>
				<c:forEach var="item" items="${pageModel.page.list }" varStatus="sta">
				<tr>
				<td class="detail"><c:choose><c:when test="${item.msgType==mediaType.text }">${item.description}</c:when><c:when test="${item.msgType==mediaType.image }"><img src="<c:url value="${item.description}"/>"/></c:when><c:when test="${item.msgType==mediaType.voice }"><audio src="<c:url value="${item.description}"/>" controls>您的浏览器不支持audio标签</audio></c:when> <c:when test="${item.msgType==mediaType.video }"><video src="<c:url value="${item.description}"/>" controls>您的浏览器不支持video标签</video></c:when><c:when test="${item.msgType==mediaType.news }"><div class="to_be_loaded h"><img src=""><span class="title"></span><div class="wechatJson h">${item.description}</div></div></c:when></c:choose> </td><td>${item.state==null||item.state==0?'已发送':'发送成功'}</td><td> <fmt:formatDate value="${item.createTime}" pattern="MM月dd日"/>  </td><td><div class="opt"><a href="javascript:void(0);" class="" ><i class="del" mass_id="${item.id}" title="删除"></i></a></div> </td>
				</tr>
				</c:forEach>
				</tbody>
				 </table>
				<div class="clear"></div>
				<div id="page"></div>
					
				</div>

		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>


	<script>
			$(document).ready(function(){
				$$pager({url:'<c:url value="/system/admin/broadcast/list.html"/>',
					currentPageNum:${pageModel.pageForm.pageNum},
					totalSize:${pageModel.page.count},
					pageSize:${pageModel.pageForm.pageSize},
					parentDiv:$('#page')
				});
				wechat.broadcast.list.postLoadFunc();
				wechat.broadcast.list.initBindFunc();
				
				
			});
			$$namespace('wechat.broadcast.list');
			wechat.broadcast.list.data={
		
			};
			wechat.broadcast.list.initBindFunc=function(){
				
			};
			wechat.broadcast.list.postLoadFunc=function(){
				$('.to_be_loaded').each(function(){
					var article=JSON.parse($('.wechatJson',$(this)).html()).articles[0];
					$('img',$(this)).attr('src','${pageContext.request.contextPath}'+article.picurl);
					$('.title',$(this)).text(article.title);
				});
				$('.to_be_loaded').show();
			};
			
	</script>
</body>
</html>