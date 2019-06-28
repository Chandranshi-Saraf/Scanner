package com.instasafe.nmapscanner;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class pdfTest {

	static String tempDir = System.getProperty("java.io.tmpdir");

	com.itextpdf.text.Document finalDoc;
	List<NiktoScanResponse> niktoResponse = new ArrayList<NiktoScanResponse>();
	List<HostInfo> hostList = new ArrayList<HostInfo>();
	List<ZapSite> zapSite = new ArrayList<ZapSite>();
	HostInfo host = new HostInfo();
	NiktoScanResponse response = new NiktoScanResponse();
	ZapSite site = new ZapSite();
	PortInfo port = new PortInfo();

	public pdfTest(String input, List<NiktoScanResponse> niktoResponse, List<HostInfo> hostList,
			List<ZapSite> zapSite) {
		this.niktoResponse = niktoResponse;
		this.hostList = hostList;
		this.zapSite = zapSite;
		finalDoc = new Document(PageSize.A4, 20, 20, 38, 60);
		finalDoc.setMargins(0, 0, 0, 0);
		finalDoc.addTitle("Vulnerablity Scan Report");
//		host=hostList.get(0);
		finalDoc.open();
		donwload();
		try {
			PdfWriter writer = PdfWriter.getInstance(finalDoc, new FileOutputStream(tempDir + "scan/Zap Report.pdf"));
			headerFooterEvent event = new headerFooterEvent();
			writer.setPageEvent(event);
			System.out.println("Instance created");
			finalDoc.open();
			finalDoc.newPage();
			finalDoc.setMargins(0, 0, 0, 0);
			Image img = Image.getInstance(tempDir + "scan/instasafe-icon.png");
			img.scalePercent(150);
			img.setAbsolutePosition(100, 400);
			finalDoc.add(img);
//			PdfReader origin = new PdfReader("https://www.dropbox.com/s/4r04vhj2ess5g30/firstPage.pdf?dl=0");
//			PdfImportedPage page=writer.getImportedPage(origin, 1);
//			Image img1 =Image.getInstance(page);
//			finalDoc.add(table);
//			PdfSmartCopy copy = new PdfSmartCopy(finalDoc, new FileOutputStream("Zap Report.pdf"));
//			copy.addPage(page);
//			finalDoc.add(img1);
			finalDoc.setMargins(20, 20, 38, 60);
			finalDoc.newPage();
			Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 22, new BaseColor(26, 35, 126));
			Paragraph titlePara = new Paragraph("LIMITATIONS ON DISCLOSURE & USE OF THIS REPORT", font);
			finalDoc.add(titlePara);
			finalDoc.add(Chunk.NEWLINE);
//			finalDoc.add(Chunk.NEWLINE);
			font = FontFactory.getFont(FontFactory.TIMES, 12);
			Paragraph para = new Paragraph(
					"The information contained within this report is considered proprietary and confidential.Inappropriate and unauthorized disclosure of this report or portions of it could result in significant damage or loss for the company or their clients. This report should be distributed to individuals on a Need-to-Know basis only. Paper copies should be locked up when not in use. Electronic copies should be stored offline and protected appropriately.This report contains information concerning potential vulnerabilities of testing servers on and methods of exploiting them. \n"
							+ "\n"
							+ "INSTASAFE recommends that special precautions be taken to protect the confidentiality of both this document and the information contained herein. INSTASAFE has retained and secured a copy of the report for customer reference. Security assessment is an uncertain process, based upon past experiences, currently available information, and known threats. It should be understood that all information systems, which by their nature are dependent on human beings, are vulnerable to some degree. \n"
							+ "\n"
							+ "Therefore, while INSTASAFE considers the major security vulnerabilities of the analyzed application to have been identified, there can be no assurance that any exercise of this nature will identify all possible vulnerabilities or propose exhaustive and operationally viable recommendations to mitigate those exposures. In addition, the analysis set forth herein is based on the technologies and known threats as of the date of this report. As technologies and risks change over time, the vulnerabilities associated with the operation of the applications are described in this report, as well as the actions necessary to reduce the exposure to such vulnerabilities will also change. \n"
							+ "\n"
							+ "INSTASAFE makes no undertaking to supplement or update this report on the basis of changed circumstances or facts of which INSTASAFE becomes aware after the date hereof, absent a specific written agreement to perform supplemental or updated analysis.\n"
							+ "",
					font);
			finalDoc.add(para);
			thirdPage();
			boolean foo = false;
			for (int i = 0; i < hostList.size(); i++) {
				host = hostList.get(i);
				Summary();
				for (int j = 0; j < host.getPortInfo().size(); j++) {

					port = host.getPortInfo().get(j);
					for (int k = 0; k < zapSite.size(); k++) {
						foo = false;
						if (host.getHostAddr().equalsIgnoreCase(zapSite.get(k).getHost()) && hostList.get(i)
								.getPortInfo().get(j).getPort().equalsIgnoreCase(zapSite.get(k).getPort())) {
							site = zapSite.get(k);
							foo = true;
						}

//						if (host.getHostAddr().equalsIgnoreCase(niktoResponse.get(k).getIp())
//								&& host.getPortInfo().get(j).getPort().equalsIgnoreCase(niktoResponse.get(k).getPort()))
//							response = niktoResponse.get(k);
					}
					if (foo)
						resultDetails();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	private PdfPCell createCell(String content, String fontFactory, int fontSize, int red, int green, int blue) {
		Font font = FontFactory.getFont(fontFactory, fontSize);
		PdfPCell pdfCell = new PdfPCell(new Paragraph(content, font));
//		pdfCell.setBorder(1);
//		pdfCell.setBorderColor(BaseColor.BLACK);
		pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfCell.setPadding(5);
		pdfCell.setBackgroundColor(new BaseColor(red, green, blue));

		return pdfCell;
	}

	private PdfPCell createCell2(String content, String fontFactory, int fontSize, int red, int green, int blue) {
		Font font = FontFactory.getFont(fontFactory, fontSize);
		PdfPCell pdfCell = new PdfPCell(new Paragraph(content, font));
		pdfCell.setBorder(1);
		pdfCell.setBorderColor(BaseColor.WHITE);
		pdfCell.setPadding(5);
		pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfCell.setBackgroundColor(new BaseColor(red, green, blue));

		return pdfCell;
	}

	private void thirdPage() throws DocumentException {
//		
//		for (int j = 0; j < hostList.size(); j++) {
//			if (zapSite.get(i).getHost().equalsIgnoreCase(hostList.get(j).getHostAddr())) {

//				cell = createCell2(Integer.toString( hostList.get(j).getPortInfo().size()), FontFactory.TIMES, 12, 255, 255,
//						255);
//				table4.addCell(cell);
		finalDoc.newPage();
		Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 22, new BaseColor(26, 35, 126));
		Paragraph titlePara = new Paragraph("Engagement Scope:", font);

		finalDoc.add(titlePara);
		finalDoc.add(Chunk.NEWLINE);
		font = FontFactory.getFont(FontFactory.TIMES, 12);
		Paragraph para = new Paragraph(
				"The project scope covers Vulnerability Assessment for the above IPs\n"
						+ "The vulnerability information details after the initial scans are as given below:\n" + "",
				font);
		finalDoc.add(para);
		PdfPTable table = new PdfPTable(7);
		PdfPCell cell = createCell("IP", FontFactory.TIMES_BOLD, 12, 255, 255, 255);
		table.addCell(cell);
		cell = createCell("High", FontFactory.TIMES_BOLD, 12, 244, 67, 54);
		table.addCell(cell);
		cell = createCell("Medium", FontFactory.TIMES_BOLD, 12, 255, 235, 59);
		table.addCell(cell);
		cell = createCell("Low", FontFactory.TIMES_BOLD, 12, 0, 230, 118);
		table.addCell(cell);
		cell = createCell("Info", FontFactory.TIMES_BOLD, 12, 0, 188, 212);
		table.addCell(cell);
		cell = createCell(" Ports", FontFactory.TIMES_BOLD, 12, 255, 255, 255);
		table.addCell(cell);
		cell = createCell("Total", FontFactory.TIMES_BOLD, 12, 158, 158, 158);
		table.addCell(cell);
		finalDoc.add(Chunk.NEWLINE);
//		System.out.println("ZapSite is here: " + zapSite.get(0).getHost());
		for (int i = 0; i < hostList.size(); i++) {
			int high = 0;
			int med = 0;
			int low = 0;
			int info = 0;
			cell = createCell(hostList.get(i).getHostAddr(), FontFactory.TIMES_BOLD, 12, 255, 255, 255);
			table.addCell(cell);
			for (int j = 0; j < hostList.get(i).getPortInfo().size(); j++) {

				for (int k = 0; k < zapSite.size(); k++) {
					if (hostList.get(i).getHostAddr().equalsIgnoreCase(zapSite.get(k).getHost()) && hostList.get(i)
							.getPortInfo().get(j).getPort().equalsIgnoreCase(zapSite.get(k).getPort())) {
						high = high + (zapSite.get(k).getHigh());
						med = med + zapSite.get(k).getMedium();
						low = low + zapSite.get(k).getLow();
						info = info + zapSite.get(k).getInfo();
					}
				}
			}
			cell = createCell(Integer.toString(high), FontFactory.TIMES_BOLD, 12, 255, 255, 255);
			table.addCell(cell);
			System.out.println(Integer.toString(med));
			cell = createCell(Integer.toString(med), FontFactory.TIMES_BOLD, 12, 255, 255, 255);
			table.addCell(cell);
			System.out.println(Integer.toString(med));
			cell = createCell(Integer.toString(low), FontFactory.TIMES_BOLD, 12, 255, 255, 255);
			table.addCell(cell);
			cell = createCell(Integer.toString(info), FontFactory.TIMES_BOLD, 12, 255, 255, 255);
			table.addCell(cell);

			cell = createCell(Integer.toString(hostList.get(i).getPortInfo().size()), FontFactory.TIMES_BOLD, 12, 255,
					255, 255);
			table.addCell(cell);
			cell = createCell(Integer.toString(high + med + low + info), FontFactory.TIMES_BOLD, 12, 255, 255, 255);
			table.addCell(cell);

		}
		finalDoc.add(table);

	}

	private void Summary() throws DocumentException {
		finalDoc.newPage();
		PdfPCell title = createCell2(host.getHostAddr(), FontFactory.HELVETICA, 12, 92, 107, 192);
		PdfPTable table2 = new PdfPTable(1);
		table2.setWidthPercentage(100);
		table2.addCell(title);
		table2.setWidthPercentage(100);
		finalDoc.add(table2);
		Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 22, new BaseColor(26, 35, 126));
		Paragraph titlePara = new Paragraph("\n", font);
		finalDoc.add(titlePara);
		titlePara = new Paragraph("Report Analysis: ", font);
		finalDoc.add(titlePara);
		titlePara = new Paragraph("\n", font);
		font = FontFactory.getFont(FontFactory.HELVETICA, 12);
		finalDoc.add(titlePara);
		int red = 197;
		int green = 202;
		int blue = 233;
		PdfPCell port = createCell2("Port Number", FontFactory.HELVETICA, 12, red, green, blue);
		PdfPCell protocol = createCell2("Protocol", FontFactory.HELVETICA, 12, red, green, blue);
		PdfPCell osType = createCell2("OS Type", FontFactory.HELVETICA, 12, red, green, blue);
		PdfPCell serviceName = createCell2("Service name", FontFactory.HELVETICA, 12, red, green, blue);
		PdfPCell state = createCell2("State", FontFactory.HELVETICA, 12, red, green, blue);
		PdfPCell extraInfo = createCell2("Extra Info", FontFactory.HELVETICA, 12, red, green, blue);
		PdfPTable table3 = new PdfPTable(6);
		table3.addCell(port);
		table3.addCell(protocol);
		table3.addCell(state);
		table3.addCell(osType);
		table3.addCell(serviceName);
		table3.addCell(extraInfo);
		table3.setWidthPercentage(100);
		red = 255;
		green = 255;
		blue = 255;
		int flag=0;		
		for (int i = 0; i < host.getPortInfo().size(); i++) {
			
			if(host.getPortInfo().size()==0)
			{
				flag=0;
				break;
			}
			flag=1;
			PortInfo portInfo = host.getPortInfo().get(i);
			port = createCell2(portInfo.getPort(), FontFactory.TIMES, 12, red, green, blue);
			protocol = createCell2(portInfo.getProtocol(), FontFactory.TIMES, 12, red, green, blue);
			osType = createCell2(portInfo.getOsType(), FontFactory.TIMES, 12, red, green, blue);
			serviceName = createCell2(portInfo.getServiceName(), FontFactory.TIMES, 12, red, green, blue);
			state = createCell2(
					(((portInfo.getState() == null) ? "" : portInfo.getState()) + " "
							+ ((portInfo.getServiceDescription() == null) ? ""
									: (portInfo.getServiceDescription().length() > 20) ? ""
											: portInfo.getServiceDescription())),
					FontFactory.TIMES, 12, red, green, blue);
			extraInfo = createCell2(
					(((portInfo.getExtraInfo() == null) ? "" : portInfo.getExtraInfo()) + " "
							+ ((portInfo.getServiceFp() == null) ? ""
									: (portInfo.getServiceFp().length() > 20) ? "" : portInfo.getServiceFp())),
					FontFactory.TIMES, 12, red, green, blue);
			table3.addCell(port);
			table3.addCell(protocol);
			table3.addCell(state);
			table3.addCell(osType);
			table3.addCell(serviceName);
			table3.addCell(extraInfo);
			table3.completeRow();
		}
		if(flag==1)
		{
			finalDoc.add(table3);
			
		}
		else
			return;
			
		font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 22, new BaseColor(26, 35, 126));
		titlePara = new Paragraph("Result Summary: ", font);
		finalDoc.add(titlePara);
		titlePara = new Paragraph("\n", font);
		finalDoc.add(titlePara);
		PdfPTable table4 = new PdfPTable(5);
		table4.setWidthPercentage(100);
		PdfPCell cell = createCell2("High", FontFactory.HELVETICA, 12, 197, 202, 233);
		table4.addCell(cell);
		cell = createCell2("Medium", FontFactory.HELVETICA, 12, 197, 202, 233);
		table4.addCell(cell);
		cell = createCell2("Low", FontFactory.HELVETICA, 12, 197, 202, 233);
		table4.addCell(cell);
		cell = createCell2("Info", FontFactory.HELVETICA, 12, 197, 202, 233);
		table4.addCell(cell);
		cell = createCell2(" Ports", FontFactory.HELVETICA, 12, 197, 202, 233);
		table4.addCell(cell);
		String str = cell.toString();
		System.out.println("String here is:" + str);
		int high = 0;
		int med = 0;
		int low = 0;
		int info = 0;
		for (int j = 0; j < host.getPortInfo().size(); j++) {

			for (int k = 0; k < zapSite.size(); k++) {
				if (host.getHostAddr().equalsIgnoreCase(zapSite.get(k).getHost())
						&& host.getPortInfo().get(j).getPort().equalsIgnoreCase(zapSite.get(k).getPort())) {
					high = high + (zapSite.get(k).getHigh());
					med = med + zapSite.get(k).getMedium();
					low = low + zapSite.get(k).getLow();
					info = info + zapSite.get(k).getInfo();
				}
			}
		}
		cell = createCell2(Integer.toString(high), FontFactory.HELVETICA, 12, 255, 255, 255);
		table4.addCell(cell);
		cell = createCell2(Integer.toString(med), FontFactory.HELVETICA, 12, 255, 255, 255);
		table4.addCell(cell);
		cell = createCell2(Integer.toString(low), FontFactory.HELVETICA, 12, 255, 255, 255);
		table4.addCell(cell);
		cell = createCell2(Integer.toString(info), FontFactory.HELVETICA, 12, 255, 255, 255);
		table4.addCell(cell);
		cell = createCell2(Integer.toString(host.getPortInfo().size()), FontFactory.HELVETICA, 12, 255, 255, 255);
		table4.addCell(cell);

		finalDoc.add(table4);
//		table3.setHeaderRows(1);

	}

	private void resultDetails() throws DocumentException {

		finalDoc.newPage();
		Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 22, new BaseColor(26, 35, 126));
		finalDoc.newPage();
		Paragraph titlePara = new Paragraph("                                    Result Details ", font);
		finalDoc.add(titlePara);
		titlePara = new Paragraph("\n", font);
		finalDoc.add(titlePara);

		System.out.println(site.getPort());
		PdfPCell cell = createCell2(port.getPort() + "/" + port.getProtocol(), FontFactory.HELVETICA_BOLD, 12, 197, 202,
				233);
		font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 22, new BaseColor(26, 35, 126));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPTable table = new PdfPTable(1);
		table.addCell(cell);
		table.setWidthPercentage(100);
		finalDoc.add(table);
		font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 16, new BaseColor(0, 0, 0));
		titlePara = new Paragraph("Service Information:  " + port.getServiceName(), font);
		finalDoc.add(titlePara);
//		titlePara = new Paragraph("Service Description:  " + response.getBanner(), font);
//		finalDoc.add(titlePara);
		finalDoc.add(Chunk.NEWLINE);
		table.deleteBodyRows();
		for (int i = 0; i < site.getAlertitem().size(); i++) {
			finalDoc.add(Chunk.NEWLINE);
			ZapAlertItem alert = site.getAlertitem().get(i);
			font = FontFactory.getFont(FontFactory.TIMES_ITALIC, 18, new BaseColor(26, 35, 126));
			cell = createCell2(alert.getAlert(), FontFactory.TIMES_ITALIC, 18, 255, 255, 255);
			table.addCell(cell);
			finalDoc.add(table);
//			titlePara = new Paragraph(alert.getAlert(), font);
//			titlePara.setSpacingBefore(15);
//			finalDoc.add(titlePara);	
			table.deleteBodyRows();
			if (alert.getRiskdesc().length() > 5) {
				cell = createCell2("Risk Factor", FontFactory.HELVETICA, 14, 197, 202, 233);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);
				finalDoc.add(table);
				font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, new BaseColor(0, 0, 0));
				String para = alert.getRiskdesc();
				para = para.replace("<p>", "");
				para = para.replace("</p>", "\n");
				titlePara = new Paragraph(para, font);
				finalDoc.add(titlePara);
				titlePara = new Paragraph("\n", font);
				finalDoc.add(titlePara);
				table.deleteBodyRows();

			}
			if (alert.getInstances() != null) {
				cell = createCell2("Number of Instance", FontFactory.HELVETICA, 14, 197, 202, 233);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);
				finalDoc.add(table);
				font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, new BaseColor(0, 0, 0));
				String para = alert.getCount();
				para = para.replace("<p>", "");
				para = para.replace("</p>", "\n");
				titlePara = new Paragraph(para, font);
				finalDoc.add(titlePara);
				titlePara = new Paragraph("\n", font);
				finalDoc.add(titlePara);
				table.deleteBodyRows();

			}
			if (alert.getDesc().length() > 5) {
				cell = createCell2("Description", FontFactory.HELVETICA, 14, 197, 202, 233);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);
				finalDoc.add(table);
				font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, new BaseColor(0, 0, 0));
				String para = alert.getDesc();
				para = para.replace("<p>", "");
				para = para.replace("</p>", "\n");
				titlePara = new Paragraph(para, font);
				finalDoc.add(titlePara);
				titlePara = new Paragraph("\n", font);
				finalDoc.add(titlePara);
				table.deleteBodyRows();

			}
			if (alert.getRefer().length() > 5) {
				cell = createCell2("Reference ", FontFactory.HELVETICA, 14, 197, 202, 233);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);
				finalDoc.add(table);
				font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, new BaseColor(0, 0, 0));
				String para = alert.getRefer();
				para = para.replace("<p>", "");
				para = para.replace("</p>", "\n");
				titlePara = new Paragraph(para, font);
				finalDoc.add(titlePara);
				titlePara = new Paragraph("\n", font);
				finalDoc.add(titlePara);
				table.deleteBodyRows();

			}
			if (alert.getSolution().length() > 5) {
				cell = createCell2("Suggested Solution", FontFactory.HELVETICA, 14, 197, 202, 233);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);
				finalDoc.add(table);
				font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, new BaseColor(0, 0, 0));
				String para = alert.getSolution();
				para = para.replace("<p>", "");
				para = para.replace("</p>", "\n");
				titlePara = new Paragraph(para, font);
				finalDoc.add(titlePara);
				titlePara = new Paragraph("\n", font);
				finalDoc.add(titlePara);
				table.deleteBodyRows();
				finalDoc.add(Chunk.NEWLINE);

			}

		}

	}

	public static boolean donwload() {

		try {
			URL website = new URL("https://instasafe-builds.s3-us-west-2.amazonaws.com/scanner.zip");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(tempDir + "/scan/scanner.zip");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			unzip(tempDir + "/scan/scanner.zip");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public static void unzip(String source) {
		try {
			ZipFile zipFile = new ZipFile(source);
			zipFile.extractAll(tempDir + "/scan");
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
}