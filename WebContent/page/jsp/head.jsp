<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script type="text/javascript">
	$(document).ready(function(){
		initLanguage();
		$("#sellan").change(function(){
			var la = $(this).children("option:selected").val();
			if(la=="cn"){
				$.cookie('client_language','zh_CN');
			}else if(la=="en"){
				$.cookie('client_language','en_US');
			}
			reloadpage();
		});
		$("#register").click(function(){
			window.location.href = getRootPath() + '/regist';
		});
		$("#username").blur(function(){
			if($(this).val() != ''){
				login.success(this);
			}else{
				login.error(this,'请输入用户名');
			}
		});
		$("#password").blur(function(){
			if($(this).val() != ''){
				login.success(this);
			}else{
				login.error(this,'请输入密码');
			}
		});
		$("#login").click(function(){
			var obj = this;
			var u = $.trim($("#username").val());
			var p = $.trim($("#password").val());
			if(u == ''){
				login.error($("#username"),'请输入用户名');
				return;
			}
			if(p == ''){
				login.error($("#password"),'请输入密码');
				return;
			}
			$(obj).addClass("disabled");
			$(obj).html("登录中");
			$.ajax({
				type: "POST",
			    url: getRootPath() + "/login",
			    async: false,
			    data: {"u" : u, "p" : p},
			    dataType: "json",
			    timeout: 30000,
			    beforeSend: function (XMLHttpRequest) {
			    },
			    success: function(msg){
			    	if(msg.code==1){
			    		reloadpage();
			    	}else if(msg.code==2){
			    		alert("帐号已被锁定，请解锁后再登录");
			    		$(obj).removeClass("disabled");
			    		$(obj).html("登录");
			    	}else{
			    		if(u == ''){
			    			login.error($("#username"),'请输入用户名');
						}
						if(p == ''){
							login.error($("#password"),'请输入密码');
						}
			    		$(obj).removeClass("disabled");
			    		$(obj).html("登录");
			    	}
			   	},
			   	error: function(XMLHttpRequest, textStatus, errorThrown){
			   		$(obj).removeClass("disabled");
			   		$(obj).html("登录");
			   	}
			});
		});
	});
	var login = function(){};
	login.error=function(obj,msg){
		$(obj).parent("div").removeClass("has-warning");
		$(obj).parent("div").removeClass("has-success");
		$(obj).parent("div").addClass("has-error");
		$(obj).tooltip('destroy');
		$(obj).tooltip({title:msg,trigger:"manual",placement:"bottom"});
		$(obj).tooltip("show");
	};
	login.success=function(obj){
		 $(obj).parent("div").removeClass("has-error");
		 $(obj).tooltip('destroy');
	};
	function initLanguage(){
		if(typeof($.cookie('client_language')) != "undefined"){
			var la = $.cookie('client_language');
			if(la=="zh_CN"){
				$("#sellan option[value='cn']").attr("selected","selected");
			}else if(la=="en_US"){
				$("#sellan option[value='en']").attr("selected","selected");
			}
		}
	};
</script>
<div class="navbar navbar-static-top">
	<div class="navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="<%=basePath%>/">BBS</a>
			</div>
			<div class="navbar-form navbar-right">
				<select id="sellan" class="form-control">
					<option value="cn">cn</option>
					<option value="en">en</option>
				</select>
			</div>
	  		<div id="usermessage" class="navbar-form navbar-right">
		  		<c:choose>
		  			<c:when test="${sessionScope.user_context!=null}">
		  				<div class="">
		  					<span class="text-danger">欢迎您的到来,</span>
		  					<span style="margin-right:15px;"><a href="javascript:void(0);">${sessionScope.user_context.user_name}</a></span>
		  					<a href="<%=basePath%>/loginout" class="btn btn-primary">注销</a>
		  				</div>
		  			</c:when>
		  			<c:otherwise>
						<div class="form-inline">
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</span>
								<label class="sr-only" for="username"><s:message code="page.mess.userName"/></label>
								<input type="text" class="form-control" id="username" maxlength="16" placeholder='<s:message code="page.mess.loginUserName"/>'>
							</div>
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</span>
								<label class="sr-only" for="password"><s:message code="page.mess.password"/></label>
								<input type="password" class="form-control" id="password" maxlength="16" placeholder='<s:message code="page.mess.loginpassword"/>'>
							</div>
							<div class="btn-group btn-group-sm">
								<button id="login" type="button" class="btn btn-success"><s:message code="page.mess.login"/></button>
								<button id="register" type="button" class="btn btn-primary" onclick="javascript:void(0);"><s:message code="page.mess.regist"/></button>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>