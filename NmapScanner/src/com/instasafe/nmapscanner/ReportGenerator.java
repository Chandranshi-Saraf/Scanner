package com.instasafe.nmapscanner;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReportGenerator {

	public List<HostInfo> generate(String inputXmlFilePath, String outputPdfPath, String input) throws Exception {
		List<HostInfo> hostList = new ArrayList<HostInfo>();

		String xml_data = new XMlReader().read(inputXmlFilePath);
		JSONObject obj = XML.toJSONObject(xml_data);
		System.out.println(obj.toString());
		JsonParser p = new JsonParser();
		JsonElement json = p.parse(obj.toString());
		JsonObject nmapRun = json.getAsJsonObject().get("nmaprun").getAsJsonObject();

		if (nmapRun != null) {
			if (nmapRun.has("host")) {

				if (nmapRun.get("host").isJsonArray()) {
					// array

					for (JsonElement host : nmapRun.get("host").getAsJsonArray()) {

						HostInfo hostInfo = createHostInfo(host.getAsJsonObject());
						hostList.add(hostInfo);
						// pdfWriter.addHost(hostInfo);

					}
				} else {
					HostInfo hostInfo = createHostInfo(nmapRun.get("host").getAsJsonObject());
					hostList.add(hostInfo);
					// pdfWriter.addHost(hostInfo);

				}

			}
		}
		// pdfWriter.closeDocument();
		return hostList;
	}

	private HostInfo createHostInfo(JsonObject host) {
		HostInfo hostInfo = new HostInfo();
		hostInfo.setPortInfo(new ArrayList<PortInfo>());
		if (host.get("address").isJsonObject())
			hostInfo.setHostAddr(host.get("address").getAsJsonObject().get("addr").getAsString());
		else {
			hostInfo.setHostAddr(
					host.get("address").getAsJsonArray().get(0).getAsJsonObject().get("addr").getAsString());
		}
		System.out.println(host.get("hostnames").isJsonNull());
		if (host.get("hostnames") != null) {
			if (host.get("hostnames").isJsonObject()) {
				if (host.get("hostnames").getAsJsonObject().has("hostname")) {
					if (host.get("hostnames").getAsJsonObject().get("hostname").isJsonObject()) {
						if ((host.get("hostnames").getAsJsonObject().get("hostname").getAsJsonObject().has("name")))

						{
							hostInfo.setHostName(host.get("hostnames").getAsJsonObject().get("hostname")
									.getAsJsonObject().get("name").getAsString());

						} else
							hostInfo.setHostName(host.get("hostnames").getAsJsonObject().get("hostname").getAsString());

					}
				} else {
					hostInfo.setHostName(host.get("hostnames").getAsString());
				}

			}
			JsonObject ports = host.get("ports").getAsJsonObject();
			if (ports != null) {
				if (ports.has("port")) {
					if (ports.get("port").isJsonArray()) {
						// array
						for (JsonElement port : ports.get("port").getAsJsonArray()) {

							JsonObject portObject = port.getAsJsonObject();
							PortInfo portInfo = createPortInfo(portObject);
							setHostInfo(portInfo, hostInfo);
							hostInfo.getPortInfo().add(portInfo);

						}
					} else {
						JsonElement port = ports.get("port").getAsJsonObject();
						JsonObject portObject = port.getAsJsonObject();
						PortInfo portInfo = createPortInfo(portObject);
						setHostInfo(portInfo, hostInfo);
						hostInfo.getPortInfo().add(portInfo);
					}
				}
			}
		}
		return hostInfo;

	}

	private void setHostInfo(PortInfo portInfo, HostInfo hostInfo) {
		if (portInfo.getHostname() != null) {
			if (hostInfo.getHostName() == null || hostInfo.getHostName().trim().equalsIgnoreCase("")) {
				hostInfo.setHostName(portInfo.getHostname());
			}
		}
	}

	private PortInfo createPortInfo(JsonObject portObject) {
		PortInfo portInfo = new PortInfo();
		if (portObject.has("service")) {
			JsonObject serviceObject = portObject.get("service").getAsJsonObject();
			if (serviceObject.get("ostype") != null) {
				portInfo.setOsType(serviceObject.get("ostype").getAsString());
			}
			if (serviceObject.get("name") != null) {
				portInfo.setServiceName(serviceObject.get("name").getAsString());
			}
			if (serviceObject.get("extrainfo") != null) {
				portInfo.setExtraInfo(serviceObject.get("extrainfo").getAsString());
			}
			if (serviceObject.get("product") != null) {
				portInfo.setServiceDescription(serviceObject.get("product").getAsString());
			}
			if (serviceObject.get("servicefp") != null) {
				portInfo.setServiceFp(serviceObject.get("servicefp").getAsString());

			}

		}
		portInfo.setPort(portObject.get("portid").getAsString());
		portInfo.setProtocol(portObject.get("protocol").getAsString());
		portInfo.setState(portObject.get("state").getAsJsonObject().get("state").getAsString());
		return portInfo;

	}
}