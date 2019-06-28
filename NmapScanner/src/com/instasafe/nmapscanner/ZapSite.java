package com.instasafe.nmapscanner;

import java.util.List;

public class ZapSite {
	
	private String name;
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public int getMedium() {
		return medium;
	}
	public void setMedium(int medium) {
		this.medium = medium;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	}
	private String host;
	private String port;
	private String ssl;
	private int high;
	private int medium;
	private int low;
	private int info;
    private List<ZapAlertItem> alertitem;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getSsl() {
		return ssl;
	}
	public void setSsl(String ssl) {
		this.ssl = ssl;
	}
	public List<ZapAlertItem> getAlertitem() {
		return alertitem;
	}
	public void setAll()
	{
		high=0;
		medium=0;
		low=0;
		info=0;
		for(int i=0; i<alertitem.size(); i++)
		{
			if(alertitem.get(i).getRiskcode()==1)
			{
				low++;
			}
			if(alertitem.get(i).getRiskcode()==2)
			{
				medium++;
			}
			if(alertitem.get(i).getRiskcode()==3)
			{
				high++;
			}
			if(alertitem.get(i).getRiskcode()==0)
			{
				info++;
			}
		}
	}
	public void setAlertitem(List<ZapAlertItem> alertitem) {
		this.alertitem = alertitem;
		
	}
	public int getInfo() {
		return info;
	}
	public void setInfo(int info) {
		this.info = info;
	}
	

}
