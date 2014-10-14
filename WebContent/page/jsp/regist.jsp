<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
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
			var regist = function(){};
			$(document).ready(function(){
				regist.tooptip($("#r_username"),"请输入用户名");
				regist.tooptip($("#r_password"),"请输入密码");
				regist.tooptip($("#r_password2"),"请输入密码");
				$("#r_username").blur(function(){
					regist.checkUserName(this);
				});
				$("#r_password").blur(function(){
					regist.checkpassword(this,$("#r_password2"));
					if($.trim($(this).val())!=''){
						regist.checkpassword2($("#r_password2"),this);
					}
				});
				$("#r_password2").blur(function(){
					regist.checkpassword2(this, $("#r_password"));
					if($.trim($(this).val())!=''){
						regist.checkpassword($("#r_password"),this);
					}
				});
				$("#submit").click(function(){
					var obj = this;
					$(obj).addClass("disabled");
		    		if($("#registform td.has-success").size() == $("#registform td input").size()){
						$.ajax({
							type: "POST",
						    url: getRootPath() + "/regist",
						    async: false,
						    data: {"ru" : $("#r_username").val(),"rp" : $("#r_password").val()},
						    dataType: "json",
						    timeout: 30000,
						    beforeSend: function (XMLHttpRequest) {
						    	regist.alertSuccess($("#alertmess"),"注册中...请等待......");
						    },
						    success: function(msg){
						    	if(msg.code==1){
						    		regist.alertSuccess($("#alertmess"),"注册成功，<span id='timespan'>5</span>秒后跳转.....");
						    		setInterval(function(){
						    			var ti = parseInt($.trim($("#timespan").html())) - 1;
						    			if(ti <= 0){
						    				toHome();
						    			}
						    			$("#timespan").html(ti);
						    		}, 1000);
						    	}else{
						    		regist.alertError($("#alertmess"),"请正确填写信息或等待信息完成验证");
						    		$(obj).removeClass("disabled");
						    		regist.checkUserName($("#r_username"));
						    		regist.checkpassword($("#r_password"),$("#r_password2"));
						    		regist.checkpassword2($("#r_password2"), $("#r_password"));
						    	}
						   	},
						   	error: function(XMLHttpRequest, textStatus, errorThrown){
						   		$(obj).removeClass("disabled");
						   		regist.alertError($("#alertmess"),"服务器繁忙，请稍后再试!");
						   	}
						});
					}else{
						regist.alertError($("#alertmess"),"请正确填写信息或等待信息完成验证");
						$(obj).removeClass("disabled");
						regist.checkUserName($("#r_username"));
			    		regist.checkpassword($("#r_password"),$("#r_password2"));
			    		regist.checkpassword2($("#r_password2"), $("#r_password"));
					}
				});
			});
			regist.alertSuccess=function(obj,msg){
				$(obj).addClass("alert");
				$(obj).removeClass("alert-danger");
				$(obj).addClass("alert-success");
				$(obj).html(msg);
				$(obj).show();
			};
			regist.alertError=function(obj,msg){
				$(obj).addClass("alert");
				$(obj).removeClass("alert-success");
				$(obj).addClass("alert-danger");
				$(obj).html(msg);
				$(obj).show();
			};
			regist.checkUserName=function(obj){
				var us = $.trim($(obj).val());
				if(us==''){
					regist.error(obj,"不能为空");
					return false;
				}
				if(us.length<5){
					regist.error(obj,"长度必须为5到16位");
					return false;
				}
				if(!/^[a-zA-Z][a-zA-Z0-9_-]{4,16}$/.test(us)){
					regist.error(obj,"必须以字母开头");
					return false;
				}
				$.ajax({
					type: "POST",
				    url: getRootPath() + "/check",
				    data: {"u" : us},
				    dataType: "json",
				    timeout: 30000,
				    success: function(msg){
				    	if(msg.code==true){
				    		regist.success(obj);
							//return true;
				    	}else{
				    		regist.error(obj,"用户名已存在");
							//return false;
				    	}
				   	},
				   	error: function(XMLHttpRequest, textStatus, errorThrown){
				   		regist.error(obj,"服务器繁忙，请稍后再试");
						//return false;
				   	}
				});
				regist.warning(obj,"正在验证用户名合法性");
				return false;
			};
			regist.checkpassword=function(obj,obj2){
				var pa = $.trim($(obj).val());
				if(pa==''){
					regist.error(obj,"不能为空");
					return false;
				}
				if(pa.length<6){
					regist.error(obj,"长度必须为6到16位");
					return false;
				}
				if(!/^[a-z0-9_-]{6,16}$/.test(pa)){
					regist.error(obj,"必须为字母数字符号");
					return false;
				}
				if($(obj2).val()!=''){
					if(pa!=$(obj2).val()){
						regist.error(obj,"两次密码必须一样");
						return false;
					}
				}
				regist.success(obj);
				return true;
			};
			regist.checkpassword2=function(obj,obj2){
				var pa = $.trim($(obj).val());
				if(pa==''){
					regist.error(obj,"不能为空");
					return false;
				}
				if(pa.length<6){
					regist.error(obj,"长度必须为6到16位");
					return false;
				}
				if(!/^[a-z0-9_-]{6,16}$/.test(pa)){
					regist.error(obj,"必须为字母数字符号");
					return false;
				}
				if(pa!=$(obj2).val()){
					regist.error(obj,"两次密码必须一样");
					return false;
				}
				regist.success(obj);
				return true;
			};
			regist.tooptip=function(obj,msg){
				$(obj).tooltip({title:msg,trigger:"hover",placement:"right"});
			};
			regist.warning=function(obj,msg){
				$(obj).parent("td").removeClass("has-warning");
				$(obj).parent("td").removeClass("has-success");
				$(obj).parent("td").removeClass("has-error");
				$(obj).parent("td").addClass("has-warning");
				$(obj).tooltip('destroy');
				$(obj).tooltip({title:msg,trigger:"manual",placement:"right"});
				$(obj).tooltip("show");
			};
			regist.error=function(obj,msg){
				$(obj).parent("td").removeClass("has-warning");
				$(obj).parent("td").removeClass("has-success");
				$(obj).parent("td").addClass("has-error");
				$(obj).tooltip('destroy');
				$(obj).tooltip({title:msg,trigger:"manual",placement:"right"});
				$(obj).tooltip("show");
			};
			regist.success=function(obj){
				$(obj).parent("td").removeClass("has-warning");
				$(obj).parent("td").removeClass("has-error");
				$(obj).parent("td").addClass("has-success");
				$(obj).tooltip('destroy');
			};
		</script>
	</head>
	<body>
		<!-- head begin -->
		<jsp:include page="head.jsp"/>
		<!-- head end -->
		<!-- body begin -->
		<div class="container">
			<form class="form-inline" id="registform">
				<div style="margin-bottom:40px;margin-top:80px;" class="text-info h1 text-center"><strong>欢迎注册</strong></div>
				<div id="alertmess" class="text-center" style="display:none;"></div>
				<table class="table table-striped">
					<tr>
						<td class="text-right text-primary h4 col-xs-5"><strong>用户名</strong></td>
						<td class="col-xs-7">
							<input id="r_username" name="ru" class="form-control" type="text" maxlength="16" />
							<p class="help-block">只限字母和数字,该用户名将用于登陆和显示</p>
						</td>
					</tr>
					<tr>
						<td class="text-right text-primary h4"><strong>密码</strong></td>
						<td>
							<input id="r_password" name="rp" class="form-control" type="password" maxlength="16" />
							<p class="help-block">请使用字母,数字,和符号的组合</p>
						</td>
					</tr>
					<tr>
						<td class="text-right text-primary h4"><strong>再次输入密码</strong></td>
						<td>
							<input id="r_password2" class="form-control" type="password" maxlength="16" />
							<p class="help-block">请使用字母,数字,和符号的组合</p>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<button id="submit" type="button" class="btn btn-success btn-lg" style="width:50%">注册</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- body end -->
	</body>
</html>