package com.instasafe.nmapscanner;

import java.util.HashMap;

public class NMapPortService extends HashMap<String, String> {
	public String getName() {
		return get("name");
	}

	public String getExtraInfo() {
		return get("extrainfo");
	}

	public String getServiceFp() {
		return get("servicefp");
	}

	public String getMethod() {
		return get("method");
	}

	public String getConfig() {
		return get("conf");
	}
}
