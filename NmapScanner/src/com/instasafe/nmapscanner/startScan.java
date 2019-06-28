package com.instasafe.nmapscanner;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class startScan {

	public void scan(String tmpDir,String input) throws Exception {
		String tempDir = System.getProperty("java.io.tmpdir");

		List<NiktoScanResponse> niktoResponse = new ArrayList<NiktoScanResponse>();
		List<HostInfo> hostList = new ArrayList<HostInfo>();
		List<ZapSite> zapSite = new ArrayList<ZapSite>();
		PortScanner sc = new NMapSanner();
		System.out.println("Enter the host to scan");
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String fileName = tempDir+"/scan/nmap-7.70/temp_output.xml";
		String outputPDF = tmpDir+"/scan/report.pdf";
		String para="";
		fileName=sc.scan(fileName, input,para);
			if(fileName!=null)
			{	ReportGenerator oj = new ReportGenerator();
				hostList = oj.generate(fileName, outputPDF, input);
				VulnerablityScan scan = new VulnerablityScan();
				niktoResponse = scan.niktoScan(hostList,tmpDir);
				zapSite = scan.zapSite;				
			}	
			startHere.progressBar.setString("Scan Complete. Writing Results to pdf.");	
		File file = new File(fileName);
		file.delete();
		pdfTest test = new pdfTest(tmpDir+"/scan/report.xml",niktoResponse, hostList, zapSite);					
		test.finalDoc.close();
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File(tempDir + "scan/Zap Report.pdf");
		        Desktop.getDesktop().open(myFile);
		        startHere.btnStartDownloads.setEnabled(true);
		    } catch (IOException ex) {
		    	 
		    	 String message="No Application Found for PDFS."+"\n"+"The PDF is saved in the location  "+tempDir + "scan/Zap Report.pdf";
		    	 JOptionPane.showMessageDialog(null,message );
		    	 startHere.btnStartDownloads.setEnabled(true);
		    }
		}
		

	}
}
