package com.instasafe.nmapscanner;

import java.io.Serializable;

public class NmapRoot implements Serializable {
	private NMapScanResponse nmaprun;

	public NMapScanResponse getResponse() {
		return nmaprun;
	}

	public void setResponse(NMapScanResponse response) {
		this.nmaprun = response;
	}

}
