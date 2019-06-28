package com.instasafe.nmapscanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ToolChecker {
	static String tempDir = System.getProperty("java.io.tmpdir");
	static Process p = null;

	public static boolean isNmapExists() {
		boolean status = false;
		if (OSChecker.isWindows()) {
			String command[] = {tempDir+"/scan/nmap-7.70/"+ "nmap.exe","-h" };
			String path = tempDir+"/scan/nmap-7.70";
			status = check(command, null);
			return status;
		} else if (OSChecker.isUnix()) {
			String command[] = { "nmap","--help" };
			String path = tempDir+"/scan/nmap-7.70";
			status = check(command, path);
			return status;
		} else if (OSChecker.isMAC()) {
			String command[] = { "nmap.sh","--help" };
			String path = tempDir+"/scan";
			status = check(command, path);
			return status;
		}
		System.out.println("Sttaus returned is for nmap  "+status);
		return status;
	}
	public static  boolean isNiktoExists() {
		boolean status = false;
		if (OSChecker.isWindows()) {
			String command[] = {tempDir +"/scan/nikto-2.1.5/"+ "nikto.bat", "-h" };
			String path = tempDir + "/scan";
			status = check(command, null);
			return status;
		} else if (OSChecker.isUnix()) {
			String command[] = { "nikto", "-h" };
			String path = tempDir + "/scan/nikto-master";
			status = check(command, path);
			System.out.println("Sttaus returned is for nikto "+status);
			return status;
		} else if (OSChecker.isMAC()) {
			String command[] = { "nikto.sh", "-h" };
			String path = tempDir + "/scan/MacNikto_1.2-Nikto_2.1.5.dmg";
			status = check(command, path);
			return status;
		}
		
		return status;
	}
	public static boolean isZapExists() {
		boolean status = false;
		String command[] = { "java", "-jar", "zap-2.8.0.jar", "-h","-cmd" };
		String path = tempDir + "/scan/ZAP_2.8.0";
		status = check(command, path);
		return status;
	}

	public static boolean check(String command[], String path) {
		try {
			String s;
			
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			if(path!=null)
				processBuilder.directory(new File(path));
			p = processBuilder.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			System.out.println("br value is " + br);
			while ((s = br.readLine()) != null)
			{
				System.out.println("line: " + s);
				String ss="";
				try
				{
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
				

				
			}
			p.waitFor();
			System.out.println("exit: " + p.exitValue());			
			if(p.exitValue()!=0)
			{
				
				return false;
			}
			p.destroy();
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		
		
	    return true;
		
	}
}