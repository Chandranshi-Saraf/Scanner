package com.instasafe.nmapscanner;

import java.util.List;

public class NMapHostPort {

	private List<NMapHostPorts> portlist;
	private NMapHostPorts portsingle;

	public List<NMapHostPorts> getPortlist() {
		return portlist;
	}

	public void setPortlist(List<NMapHostPorts> portlist) {
		this.portlist = portlist;
	}

	public NMapHostPorts getPortsingle() {
		return portsingle;
	}

	public void setPortsingle(NMapHostPorts portsingle) {
		this.portsingle = portsingle;
	}

}
