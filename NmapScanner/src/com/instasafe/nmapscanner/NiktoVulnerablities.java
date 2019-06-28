package com.instasafe.nmapscanner;

import java.io.Serializable;

public class NiktoVulnerablities implements Serializable {

	private String id;
	private String OSVDB;
	private String method;
	private String url;
	private String msg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOSVDB() {
		return OSVDB;
	}

	public void setOSVDB(String oSVDB) {
		OSVDB = oSVDB;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
