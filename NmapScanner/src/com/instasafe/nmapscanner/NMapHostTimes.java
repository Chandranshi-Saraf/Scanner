package com.instasafe.nmapscanner;

import java.util.HashMap;

public class NMapHostTimes extends HashMap<String, String> {
	public String getSrtt() {
		return get("srtt");
	}

	public String getRttVar() {
		return get("rttVar");
	}

	public String getTo() {
		return get("to");
	}
}
