package com.demo.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.demo.common.AppConfig;
import com.demo.util.DESTool;

public class EncryptionTag extends SimpleTagSupport {
	private String value;

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspContext()
				.getOut()
				.print(DESTool.getInstance().encrypt(value,
						AppConfig.get("app.encryptparam")));
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
