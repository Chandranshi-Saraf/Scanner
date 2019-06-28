package com.instasafe.nmapscanner;

import java.util.List;

public class ZapAlertItem {
	private String refer;
	private String alert;
	private int riskcode;
	private String confidence;
	private String riskdesc;
	private String desc;
	private List<ZapInstance> instances;
	private String count;
	private String solution;
	private String cweid;
	private String wascid;
	private String sourceid;

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public int getRiskcode() {
		return riskcode;
	}

	public void setRiskcode(int riskcode) {
		this.riskcode = riskcode;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getRiskdesc() {
		return riskdesc;
	}

	public void setRiskdesc(String riskdesc) {
		this.riskdesc = riskdesc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<ZapInstance> getInstances() {
		return instances;
	}

	public void setInstances(List<ZapInstance> instances) {
		this.instances = instances;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getCweid() {
		return cweid;
	}

	public void setCweid(String cweid) {
		this.cweid = cweid;
	}

	public String getWascid() {
		return wascid;
	}

	public void setWascid(String wascid) {
		this.wascid = wascid;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

}
