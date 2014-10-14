package com.demo.web.interceptors;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.common.AppConfig;
import com.demo.util.DESTool;

public class ParamDecryptionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String> par = (Map<String, String>) request
				.getAttribute("org.springframework.web.servlet.HandlerMapping.uriTemplateVariables");
		Iterator<String> keys = par.keySet().iterator();
		while (keys.hasNext()) {
			String k = keys.next();
			String v = par.get(k);
			if (v.length() == 13) {// base32加密长度为13位
				par.put(k,
						DESTool.getInstance().decrypt(v,
								AppConfig.get("app.encryptparam")));
			} else {// 参数错误时，直接跳到404页面
				HttpServletResponse res = (HttpServletResponse) response;
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);// 设置状态码为404
				res.sendError(HttpServletResponse.SC_NOT_FOUND);// 设置错误信息为404
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

}
