<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<title>添加话题</title>
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/bootstrap.min.css"/>
	</head>
	<body>
		<!-- head begin -->
		<jsp:include page="head.jsp"/>
		<!-- head end -->
		<!-- body begin -->
		<div class="container-fluid">
			<table class="table table-striped">
				<tr>
					<td class="text-right text-primary h4 col-xs-2">标题</td>
					<td class="col-xs-10"><input type="text" name=""/></td>
				</tr>
				<tr>
					<td class="text-right text-primary h4">内容</td>
					<td><script id="container" name="content" type="text/plain"></script></td>
				</tr>
			</table>
		</div>
		<!-- body end -->
	</body>
	<script type="text/javascript" src="<%=basePath%>/resources/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript">
		var editor = UE.getEditor('container',
			{
				initialFrameWidth:800,
				initialFrameHeight:800
			}
		);
	</script>
</html>