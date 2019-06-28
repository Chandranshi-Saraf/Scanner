package com.instasafe.nmapscanner;

import java.util.List;

public class HostInfo {
	private String hostName;
	private String hostAddr;
	private String metaData;
	private List<PortInfo> portInfo;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostAddr() {
		return hostAddr;
	}

	public void setHostAddr(String hostAddr) {
		this.hostAddr = hostAddr;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public List<PortInfo> getPortInfo() {
		return portInfo;
	}

	public void setPortInfo(List<PortInfo> portInfo) {
		this.portInfo = portInfo;
	}

}
