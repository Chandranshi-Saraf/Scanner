package com.instasafe.nmapscanner;

import java.util.HashMap;

public class RunstatsHosts extends HashMap<String, String> {

	public String getUp() {
		return get("up").toString();
	}

	public String getDown() {
		return get("down").toString();
	}

	public String getTotal() {
		return get("total").toString();
	}

}
