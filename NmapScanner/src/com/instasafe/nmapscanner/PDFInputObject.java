package com.instasafe.nmapscanner;

import java.util.HashMap;
import java.util.List;

public class PDFInputObject {

	private List<NMapHost> hosts;
	private HashMap<String, List<NMapHostPorts>> ports;
	private HashMap<String, List<NiktoVulnerablities>> vulnerabilities;
}
