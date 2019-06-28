package com.instasafe.nmapscanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.itextpdf.text.pdf.codec.Base64.InputStream;

public class Main {

	private static String OS = System.getProperty("os.name").toLowerCase();
	static File dir;

	public static void main(String[] args) throws Exception {

		String property = "java.io.tmpdir";
		String tempDir = System.getProperty(property);

		new File(tempDir + "/scan").mkdirs();
		
		new File(tempDir + "/scan/log").mkdirs();
		 dir = new File(tempDir + "/scan/log");
		System.out.println("OS current temporary directory is " + tempDir);
		String s="Working OS is "+OS;
		s=s+"\n"+"OS current temporary directory is " + tempDir;
		try
		{
			logs.log.textArea.setText(s);
		}
		catch(Exception e)
		{}
		
		System.out.println(OS);
		startHere.progressBar.setString("Checking for Nmap");
		

		if (!ToolChecker.isNmapExists()) {
			startHere.progressBar.setString("Downloading Nmap");
			ToolDownloder.donwloadNmap();
			startHere.progressBar.setString("Unziping Nmap");
			ToolDownloder.unzip(tempDir + "/scan/nmap.zip");
			ToolChecker.isNmapExists();
		}

//		if (!ToolChecker.isNiktoExists()) {
//			ToolDownloder.donwloadNikto();
//			ToolChecker.isNiktoExists();
//		}
		startHere.progressBar.setString("Checking for Zap");
		if (!ToolChecker.isZapExists()) {
			startHere.progressBar.setString("Downloading Zap");
			ToolDownloder.donwloadZap();
			startHere.progressBar.setString("Unziping Zap");
			ToolDownloder.unzip(tempDir + "/scan/zap.zip");
			
			startHere.progressBar.setString("Downloading Zap Add-ons");
			Runnable runner3 = new Runnable() {
				public void run() {
					try {
						
						Process p;
						ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar",
								tempDir + "/scan/ZAP_2.8.0/zap-2.8.0.jar", "-addoninstallall", "-cmd");
//						String command="java -jar zaproxy/zap-2.7.0.jar -daemon -quickurl http://"+url+" -quickout "+fileName;
//						p = Runtime.getRuntime().exec("/bin/bash");
						processBuilder.directory(new File(tempDir + "/scan/ZAP_2.8.0"));
						p = processBuilder.start();
						
						BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
						System.out.println("br value is " + br);
						String s="";
						while ((s = br.readLine()) != null)
						{							
							
							startHere.progressBar.setString("Downloading Zap Add-ons...");
							for(int i=0; i<12974656; i++)
							{
								for(int j=0; j<12344567; j++)
								{
									continue;
								}
							}
							
						}
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
							
						p.waitFor();
						System.out.println("exit: " + p.exitValue());
						p.destroy();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			Thread t3 = new Thread(runner3, "Code Executer");
		    t3.start();
		    try {
		    	t3.join();
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
		}
		startHere.progressBar.setString("Download Completed");
		return;

	}
	
	 public static boolean deleteDirectory(File dir) {
	        if (dir.isDirectory()) {
	            File[] children = dir.listFiles();
	            for (int i = 0; i < children.length; i++) {
	                boolean success = deleteDirectory(children[i]);
	                if (!success) {
	                    return false;
	                }
	            }
	        }	        
	        System.out.println("removing file or directory : " + dir.getName());
	        return dir.delete();
	    }

}
