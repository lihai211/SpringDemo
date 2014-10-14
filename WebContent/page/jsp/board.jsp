<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="/tag/encryption" prefix="e" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/bootstrap.min.css"/>
		<title>${board.board_name}</title>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#addtopic").click(function(){
					window.location.href = getRootPath() + "/topic/addtopic";					
				});
			});
		</script>
	</head>
	<body>
		<!-- head begin -->
		<jsp:include page="head.jsp"/>
		<!-- head end -->
		<!-- body begin -->
		<div class="container-fluid">
			<div>
				<ol class="breadcrumb">
				  <li><a href="<%=basePath%>/">首页</a></li>
				  <li>${board.board_name}</li>
				</ol>
			</div>
			<p>
				<button class="btn btn-primary" id="addtopic"><span class="glyphicon glyphicon-plus"></span>发表新话题</button>
			</p>
			<table class="table table-condensed table-hover">
				<tr>
					<th class="col-md-6">标题</th>
					<th class="col-md-1">发表人</th>
					<th class="col-md-1">回复/浏览数</th>
					<th class="col-md-2">发表时间</th>
					<th class="col-md-2">最后回复时间</th>
				</tr>
				<c:choose>
					<c:when test="${topics.size() != 0}">
						<c:forEach var="t" items="${topics}">
							<tr>
								<td><a href="javascript:void(0);">${t.topic_title}</a></td>
								<td>${t.user.user_name}</td>
								<td>${t.topic_replies}/${t.topic_views}</td>
								<td><fmt:formatDate value="${t.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><fmt:formatDate value="${t.last_post}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<td colspan="5">暂时没有发表的话题</td>
					</c:otherwise>
				</c:choose>
			</table>
			<div class="text-center">
				<jsp:include page="page.jsp"/>
			</div>
		</div>
		<!-- body end -->
	</body>
</html>