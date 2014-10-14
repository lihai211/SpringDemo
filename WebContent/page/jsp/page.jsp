<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<ul class="pagination">
	<li <c:if test="${page.curPage == 1}">class="disabled"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=1'>&laquo;</a></li>
	<li <c:if test="${page.curPage == 1}">class="disabled"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=${page.prePage}'>&lt;</a></li>
	<c:forEach items="${page.pageNumbers}" var="p">
		<li <c:if test="${page.curPage == p}">class="active"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=${p}'>${p}</a></li>
	</c:forEach>
	<li <c:if test="${page.curPage == page.totalPage}">class="disabled"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=${page.nextPage}'>&gt;</a></li>
	<li <c:if test="${page.curPage == page.totalPage}">class="disabled"</c:if>><a href='<%=basePath%>${requestScope["javax.servlet.forward.servlet_path"]}?pn=${page.totalPage}'>&raquo;</a></li>
</ul>
