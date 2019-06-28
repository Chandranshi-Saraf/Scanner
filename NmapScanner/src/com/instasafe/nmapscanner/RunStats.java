package com.instasafe.nmapscanner;

public class RunStats implements java.io.Serializable {
	private RunstatsFinished finished;
	private RunstatsHosts hosts;

	public RunstatsFinished getFinished() {
		return finished;
	}

	public void setFinished(RunstatsFinished finished) {
		this.finished = finished;
	}

	public RunstatsHosts getHosts() {
		return hosts;
	}

	public void setHosts(RunstatsHosts hosts) {
		this.hosts = hosts;
	}

}
