package com.instasafe.nmapscanner;

import java.util.HashMap;

public class NMapHostHostNames extends HashMap<String, HashMap<String, String>> {

	public HashMap<String, String> getHostname() {
		return get("hostname");

	}

	public String getName() {
		return get("name").toString();
	}

	public String getType() {
		return get("type").toString();
	}

}
