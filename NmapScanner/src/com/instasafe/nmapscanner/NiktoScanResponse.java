package com.instasafe.nmapscanner;

import java.io.Serializable;
import java.util.ArrayList;

public class NiktoScanResponse implements Serializable {

	private String host;
	private String ip;
	private String port;
	private String banner;
	private ArrayList<NiktoVulnerablities> vulnerabilities;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public ArrayList<NiktoVulnerablities> getVulnerabilities() {
		return vulnerabilities;
	}

	public void setVulnerabilities(ArrayList<NiktoVulnerablities> vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}

}
