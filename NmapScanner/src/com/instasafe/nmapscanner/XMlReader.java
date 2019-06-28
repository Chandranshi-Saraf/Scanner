package com.instasafe.nmapscanner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class XMlReader {
	public String read(String filePath)
			throws TransformerException, SAXException, IOException, ParserConfigurationException {
		File inputFile = new File(filePath);
		List<String> allLines = Files.readAllLines(inputFile.toPath());
		StringBuffer buffer = new StringBuffer();
		for (String line : allLines) {
			buffer.append(line); 
		}
		return buffer.toString();

	}

}
