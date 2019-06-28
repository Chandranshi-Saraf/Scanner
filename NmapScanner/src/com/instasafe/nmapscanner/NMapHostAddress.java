package com.instasafe.nmapscanner;

import java.util.HashMap;

public class NMapHostAddress extends HashMap<String, String> {
	public String getAddr() {
		return get("addr");
	}

	public String getAddrType() {
		return get("addrtype");
	}

}
