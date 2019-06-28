package com.instasafe.nmapscanner;

import java.util.HashMap;

public class ScanInfo extends HashMap<String, String> {
	public String getType() {
		return get("type").toString();
	}

	public String getProtocol() {
		return get("protocol").toString();
	}

	public String getNumServices() {
		return get("numservices").toString();
	}

	public String getServices() {
		return get("services").toString();
	}
}
