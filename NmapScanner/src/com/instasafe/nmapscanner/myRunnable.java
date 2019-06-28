package com.instasafe.nmapscanner;

import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class myRunnable implements Runnable{
	private final String url;
	String fileName;
	String session;
	String logzap;
	String tempDir = System.getProperty("java.io.tmpdir");
	static List<ZapSite> zsite = new ArrayList<ZapSite>();
	static List<ZapSite> zapSite = new ArrayList<ZapSite>();
	myRunnable(String url, int i) throws  IOException {
		Date date= new Date();
		long time=date.getTime();
		fileName=tempDir+"/scan/log/output"+Long.toString(time)+".xml";
		session=tempDir+"/scan/log/session"+Long.toString(time);
		logzap=tempDir+"/scan/log/logzap"+Long.toString(time)+".log";
		File file = new File(fileName);
        file.createNewFile();
		this.url = url;
	}

	@Override
	public void run() {
		System.out.println("Zap is starting here");
		ZapScan sc= new ZapScan();		
		zsite=(sc.scan(fileName, url,logzap,session));
		for(int k=0; k<zsite.size();k++)
		{
			zapSite.add(zsite.get(k));
		}
	}

	

}
