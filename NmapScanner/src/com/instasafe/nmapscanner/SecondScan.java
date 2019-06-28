package com.instasafe.nmapscanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class SecondScan {

	public NiktoScanResponse scanForVulnerabilities(String host, String port) throws Exception {
		String tempDir = System.getProperty("java.io.tmpdir");
		 String fileName = "NiktoOutput11.xml";
			System.out.println("Nikto Scan Started");
			if(	OSChecker.isUnix())
			{				
			    String command[]= {"nikto", "-host ",host, "-p ",port," -o ",tempDir+"/scan/"+fileName," -Tuning 1 2 3 4 5"};
				String path=tempDir+"/scan/nikto-master";
				check(command,path);			
			}
			else if(OSChecker.isWindows())
			{			
				System.out.println(host);
				System.out.println(port);
				if(port.equalsIgnoreCase("53"))
						port="80";
				fileName=tempDir+"/scan/nikto-2.1.5/"+fileName;
			    String command[]= {"cmd.exe", "/c",tempDir +"/scan/nikto-2.1.5/"+"nikto.bat -host "+host+ " -p "+port+" -o "+fileName+" -Tuning 1 2 3 4 5"};	    
				String path=tempDir+"/scan/nikto-2.1.5/";
				check(command,path);
				
			}
			else if(OSChecker.isMAC())
			{			
			    String command[]= {"nikto.sh", "-host ",host, "-p ",port," -o ",fileName," -Tuning 1 2 3 4 5"};
				String path=tempDir+"/scan";
				check(command,path);			
			}
			
			String xml_data = new XMlReader().read(fileName);
			JSONObject obj = XML.toJSONObject(xml_data);
			String json=obj.toString();	
		Gson gson = null;
		System.out.println("JSON STRING IS:"+json);
//		JsonReader reader= new JsonReader(new FileReader(fileName));
		GsonBuilder builder = new GsonBuilder();
		gson = builder.setLenient().create();
        if(!(json.contains("\"vulnerabilities\":[{")))
        {
        	File file = new File(fileName);
        	System.out.println("file Path is"+file.getPath());
    		file.delete();  		
        	return null;
        }
		json = json.replace(",}", "}");
		json = json.replace(",]", "]");
		System.out.println("JSON ELEMENT IS:  "+json);
		NiktoScanResponse response = null;
		response = gson.fromJson(json, NiktoScanResponse.class);
		File file = new File(fileName);
		System.out.println("file Path is"+file.getPath());
		file.delete();
		return response;
	}
	public boolean check(String command[], String path) {
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
	private static String readLineByLineJava8(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
}
