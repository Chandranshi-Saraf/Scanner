package com.instasafe.nmapscanner;

import java.util.HashMap;

public class NMapPortScript extends HashMap<String, Object> {
	public String getID() {
		return get("id").toString();
	}

	public String getOutput() {
		return get("output").toString();
	}

}
