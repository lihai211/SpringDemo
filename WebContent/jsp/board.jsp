<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
								<td>${t.topic_title}</td>
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
				<ul class="pagination">
					<li <c:if test="${page.curPage == 1}">class="disabled"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=1'>&laquo;</a></li>
					<li <c:if test="${page.curPage == 1}">class="disabled"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=${page.prePage}'>&lt;</a></li>
					<c:forEach items="${page.pageNumbers}" var="p">
						<li <c:if test="${page.curPage == p}">class="active"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=${p}'>${p}</a></li>
					</c:forEach>
					<li <c:if test="${page.curPage == page.totalPage}">class="disabled"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=${page.nextPage}'>&gt;</a></li>
				  	<li <c:if test="${page.curPage == page.totalPage}">class="disabled"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=${page.totalPage}'>&raquo;</a></li>
				</ul>
			</div>
		</div>
		<!-- body end -->
	</body>
</html>