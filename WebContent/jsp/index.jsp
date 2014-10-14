<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/bootstrap.min.css"/>
		<title><s:message code="page.mess.title"/></title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<!-- head begin -->
		<jsp:include page="head.jsp"/>
		<!-- head end -->
		<!-- body begin -->
		<div class="container-fluid">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>版块名称</th>
						<th>版块简介</th>
						<th>主题贴数</th>
					</tr>
				</thead>
				<c:forEach items="${boards}" var="b">
					<tr>
						<td><a href="<%=basePath%>/board/list/<e:code value="${b.board_id}"/>">${b.board_name}</a></td>
						<td>${b.board_desc}</td>
						<td>${b.board_num}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- body end -->
	</body>
</html>