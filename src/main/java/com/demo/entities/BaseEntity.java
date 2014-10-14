package com.demo.entities;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.Gson;

public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = -8856788202937913576L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}
