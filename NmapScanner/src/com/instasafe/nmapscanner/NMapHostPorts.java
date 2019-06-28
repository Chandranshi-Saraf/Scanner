package com.instasafe.nmapscanner;

public class NMapHostPorts {

	private String portid;
	private NMapPortState state;
	private NMapPortService service;

	public NMapPortState getState() {
		return state;
	}


	public void setState(NMapPortState state) {
		this.state = state;
	}

	public NMapPortService getService() {
		return service;
	}

	public void setService(NMapPortService service) {
		this.service = service;
	}


	public String getPortid() {
		return portid;
	}


	public void setPortid(String portid) {
		this.portid = portid;
	}

}
