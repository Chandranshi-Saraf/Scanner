package com.instasafe.nmapscanner;

import java.util.HashMap;

public class RunstatsFinished extends HashMap<String, String> {

	public String getTime() {
		return get("time").toString();
	}

	public String getTimeStr() {
		return get("timestr").toString();
	}

	public String getEllapsed() {
		return get("elapsed").toString();
	}

	public String getSummary() {
		return get("summary").toString();
	}

	public String getExit() {
		return get("exit").toString();
	}

}
