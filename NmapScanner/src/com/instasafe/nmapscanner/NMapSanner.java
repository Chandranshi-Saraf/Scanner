package com.instasafe.nmapscanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

//import com.itextpdf.text.*;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

public class NMapSanner implements PortScanner {

	com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);

	public String scan(String fileName, String cidr, String para) {
		System.out.println("FILE FOR NMAP IS " + fileName);
		String tempDir = System.getProperty("java.io.tmpdir");
		boolean status = false;
		if (OSChecker.isUnix()) {

			String command[] = { "nmap", "-A", "-T4", para,"--defeat-rst-ratelimit", "oX", fileName, " ", cidr };
//			String path=tempDir+"/scan/nmap";
			status = check(command, null);
			return fileName;

		} else if (OSChecker.isWindows()) {

			//fileName = tempDir + "/scan/nmap-7.70/" + fileName;
			String command[] = { tempDir +"/scan/nmap-7.70/" + "nmap.exe", "-A", "-T4", para,"--defeat-rst-ratelimit", "-oX", fileName, " ",cidr };
			String path = tempDir + "/scan/nmap-7.70";
			status = check(command,path);
			return fileName;

		} else if (OSChecker.isMAC()) {

			String command[] = { "nmap.sh", "-A", "-T4", para,"--defeat-rst-ratelimit", "oX", fileName, " ", cidr };
			String path = tempDir + "/scan/nmap";
			status = check(command, path);
			return fileName;

		}
		return fileName;

	}

	public boolean check(String command[], String path) {
		try {
			String s;
			Process p = null;
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			processBuilder.directory(new File(path));
			System.out.println("The current working directory is :" + processBuilder.directory());
			p = processBuilder.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			System.out.println("br value is " + br);
			while ((s = br.readLine()) != null)
			{
				startHere.progressBar.setString("NMAP  is Scanning for the open ports..");
				String ss="";
				try{
					ss=logPanel.textArea.getText();
				}
				catch(Exception e)
				{}
				ss=ss+"\n"+"line: "+s;
				try
				{
					logs.log.textArea.setText(ss);
				}
				catch(Exception e)
				{}
				System.out.println("line: " + s);
			}
			
			p.waitFor();
			System.out.println("exit: " + p.exitValue());
			if(p.exitValue()!=0)
			{
				// get the error stream of the process and print it
				
		         InputStream error = (InputStream)p.getErrorStream();
		         for (int i = 0; i < error.available(); i++) {
		        	 String ss="";
		        	 s=Integer.toString(error.read());
						try{
							ss=logPanel.textArea.getText();
						}
						catch(Exception e)
						{}
						ss=ss+"\n"+"line: "+s;
						try
						{
							logs.log.textArea.setText(ss);
						}
						catch(Exception e)
						{}
						System.out.println("line: " + s);
		         }
			}
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


}
