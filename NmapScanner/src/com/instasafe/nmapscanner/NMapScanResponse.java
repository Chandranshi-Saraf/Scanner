package com.instasafe.nmapscanner;

import java.io.Serializable;
import java.util.List;

public class NMapScanResponse implements Serializable {

	private ScanInfo scaninfo;
	private Verbose verbose;
	private Debugging debugging;
	private RunStats runstats;
	private List<NMapHost> host;

	public ScanInfo getScaninfo() {
		return scaninfo;
	}

	public void setScaninfo(ScanInfo scaninfo) {
		this.scaninfo = scaninfo;
	}

	public Verbose getVerbose() {
		return verbose;
	}

	public void setVerbose(Verbose verbose) {
		this.verbose = verbose;
	}

	public Debugging getDebugging() {
		return debugging;
	}

	public void setDebugging(Debugging debugging) {
		this.debugging = debugging;
	}

	public RunStats getRunstats() {
		return runstats;
	}

	public void setRunstats(RunStats runstats) {
		this.runstats = runstats;
	}

	public List<NMapHost> getHost() {
		return host;
	}

	public void setHost(List<NMapHost> host) {
		this.host = host;
	}
}
