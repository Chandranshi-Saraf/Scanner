package com.instasafe.nmapscanner;

import java.util.HashMap;

public class Debugging extends HashMap<String, String> {
	public String getLevel() {
		return get("level").toString();
	}

}
