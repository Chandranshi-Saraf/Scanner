package com.instasafe.nmapscanner;


public class NMapHost {
	private NMapHostTimes times;
	private NMapHostStatus status;
	private NMapHostAddress address;
	// private NMapHostHostNames hostnames;
	private NMapHostPort ports;

	public NMapHostTimes getTimes() {
		return times;
	}

	public void setTimes(NMapHostTimes times) {
		this.times = times;
	}

	public NMapHostStatus getStatus() {
		return status;
	}

	public void setStatus(NMapHostStatus status) {
		this.status = status;
	}

	public NMapHostAddress getAddress() {
		return address;
	}

	public void setAddress(NMapHostAddress address) {
		this.address = address;
	}

	public NMapHostPort getPort() {
		return ports;
	}

	public void setPort(NMapHostPort ports) {
		this.ports = ports;
	}

}
