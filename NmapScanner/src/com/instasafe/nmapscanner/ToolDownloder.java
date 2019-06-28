package com.instasafe.nmapscanner;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.swing.SwingUtilities;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

public class ToolDownloder {
	static String tempDir = System.getProperty("java.io.tmpdir");
	static Process p;

	public static boolean donwloadNmap() throws Exception {
		if (OSChecker.isMAC()) {
			try {
				URL website = new URL("https://nmap.org/dist/nmap-7.70.dmg");
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(tempDir + "/scan/nmap.dmg");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (OSChecker.isUnix()) {

			try {
				URL website = new URL("https://nmap.org/dist/nmap-7.70.tar.bz2");
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(tempDir + "/scan/nmap.tar.bz2");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
//			    FileInputStream in = new FileInputStream(tempDir+"/scan/nmap.tar.bz2");
//			    FileOutputStream out = new FileOutputStream(tempDir+"/scan/nmap.tar");
//			    BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(in);
//			    final byte[] buffer = new byte[2048];
//			    int n = 0;
//			    while (-1 != (n = bzIn.read(buffer))) {
//			      out.write(buffer, 0, n);
//			    }
//			    out.close();
//			    bzIn.close();
//			    File dest = new File(tempDir+"/scan");
//			    TarArchiveInputStream tarIn = new TarArchiveInputStream( new BufferedInputStream( new FileInputStream(tempDir+"/scan/nmap.tar")));
//			    TarArchiveEntry tarEntry = tarIn.getNextTarEntry();
//			    // tarIn is a TarArchiveInputStream
//			    while (tarEntry != null) {
//			    // create a file with the same name as the tarEntry
//			    File destPath = new File(dest, tarEntry.getName());
//			    if (tarEntry.isDirectory()) {
//			    destPath.mkdirs();
//			    } else {
//			    destPath.createNewFile();
//			    byte [] btoRead = new byte[2048];
//			    BufferedOutputStream bout =  new BufferedOutputStream(new FileOutputStream(destPath));		  
//			    int len;
//			    while((len = tarIn.read(btoRead)) != -1)
//			    {
//			    bout.write(btoRead,0,len);
//			    }
//			    bout.close();
//			    btoRead = null;
//			    }
//			    tarEntry = tarIn.getNextTarEntry();
//			    }
//			    tarIn.close();			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			

				String command[] = { "./configure" };
				String path = tempDir + "/scan/nmap-7.70";
				check(command, path);
				command[0] = "make";
				path = tempDir + "/scan/nmap-7.70";
				check(command, path);
				command[0] = "su root";
				path = tempDir + "/scan/nmap-7.70";
				check(command, path);
				command[0] = "make install";
				path = tempDir + "/scan/nmap-7.70";
				check(command, path);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (OSChecker.isWindows()) {
			
				URL url = new URL("https://nmap.org/dist/nmap-7.70-win32.zip");
				HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
				long completeFileSize = httpConnection.getContentLength();
				java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
				java.io.FileOutputStream fos = new java.io.FileOutputStream(tempDir + "/scan/nmap.zip");
				java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
				byte[] data = new byte[1024];	
					Runnable runner1 = new Runnable() {
						public void run() {
							try {
								int x=0;
								long downloadedFileSize =0;								
								while (( x = in.read(data, 0, 1024))> 0) {
									downloadedFileSize += x;

									// calculate progress
									final int currentProgress = (int) ((((double) downloadedFileSize)
											/ ((double) completeFileSize)) * 100);
									System.out.println(currentProgress);
									startHere.progressBar.setValue(currentProgress);
									startHere.progressBar.repaint();
									bout.write(data, 0, x);
								}
								bout.close();
								in.close();
								
							} catch (Exception e) {

								e.printStackTrace();
							}

						}
					};
					Thread t1 = new Thread(runner1, "Code Executer");
				    t1.start();
				    try {
				    	t1.join();
				    }
				    catch(Exception e)
				    {
				    	e.printStackTrace();
				    }

				        

			return true;
		}
		return false;
	}

	public static boolean donwloadNikto() {
		if (OSChecker.isMAC()) {
			try {
				URL website = new URL("\"https://www.informationgift.com/macnikto/builds/MacNikto_1.2-Nikto_2.1.5.dmg");
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(tempDir + "/scan/MacNikto_1.2-Nikto_2.1.5.dmg");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (OSChecker.isUnix()) {
			try {
				URL website = new URL("https://github.com/sullo/nikto/archive/master.zip");
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(tempDir + "/scan/master.zip");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				unzip(tempDir + "/scan/master.zip");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (OSChecker.isWindows()) {
			try {
				URL website = new URL("https://instasafe-builds.s3-us-west-2.amazonaws.com/nikto-2.1.5.zip");
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(tempDir + "/scan/nikto.zip");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				HttpURLConnection httpConnection = (HttpURLConnection) (website.openConnection());
				long completeFileSize = httpConnection.getContentLength();
				java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
				byte[] data = new byte[1024];
				long downloadedFileSize = 0;
				int x = 0;
				while ((x = in.read(data, 0, 1024)) >= 0) {
					downloadedFileSize += x;

					// calculate progress
					final int currentProgress = (int) ((((double) downloadedFileSize) / ((double) completeFileSize))
							* 100000d);

					// update progress bar
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							// startHere.progressBar.setValue(currentProgress);
						}
					});
				}

				in.close();
				fos.close();
				unzip(tempDir + "/scan/nikto.zip");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean donwloadZap() throws IOException {
		
		URL url = new URL("https://github.com/zaproxy/zaproxy/releases/download/v2.8.0/ZAP_2.8.0_Core.zip");
		HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
		long completeFileSize = httpConnection.getContentLength();
		java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
		java.io.FileOutputStream fos = new java.io.FileOutputStream(tempDir + "/scan/zap.zip");
		java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
		byte[] data = new byte[1024];	
			Runnable runner2 = new Runnable() {
				public void run() {
					try {
						int x=0;
						long downloadedFileSize =0;								
						while (( x = in.read(data, 0, 1024))> 0) {
							downloadedFileSize += x;

							// calculate progress
							final int currentProgress = (int) ((((double) downloadedFileSize)
									/ ((double) completeFileSize)) * 100);
							System.out.println(currentProgress);
							startHere.progressBar.setValue(currentProgress);
							startHere.progressBar.repaint();
							bout.write(data, 0, x);
						}
						bout.close();
						in.close();
						
					} catch (Exception e) {

						e.printStackTrace();
					}

				}
			};
			Thread t2 = new Thread(runner2, "Code Executer");
		    t2.start();
		    try {
		    	t2.join();
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }

		

		return true;
	}

	public static void unzip(String source) {
		try {
			ZipFile zipFile = new ZipFile(source);
			zipFile.extractAll(tempDir + "/scan");
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

	public static boolean check(String command[], String path) {
		try {
			String s;
			Process p = null;
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			processBuilder.directory(new File(path));
			p = processBuilder.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			System.out.println("br value is " + br);
			while ((s = br.readLine()) != null)
				System.out.println("line: " + s);
			p.waitFor();
			System.out.println("exit: " + p.exitValue());
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
