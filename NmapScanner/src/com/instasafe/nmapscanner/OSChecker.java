package com.instasafe.nmapscanner;

public class OSChecker {
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean isMAC() {
		return (OS.indexOf("osx") >= 0);
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

}
