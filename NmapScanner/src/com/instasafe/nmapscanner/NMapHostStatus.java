package com.instasafe.nmapscanner;

import java.util.HashMap;

public class NMapHostStatus extends HashMap<String, String> {
	public String getReason() {
		return get("reason");
	}

	public String getState() {
		return get("state");
	}

	public String getReason_ttl() {
		return get("reason_ttl");
	}

}
