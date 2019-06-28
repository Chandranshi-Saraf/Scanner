package com.instasafe.nmapscanner;

import java.util.HashMap;

public class Verbose extends HashMap<String, String> {
	public String getLevel() {
		return get("level").toString();
	}

}
