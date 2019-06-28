package com.instasafe.nmapscanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class headerFooterEvent extends PdfPageEventHelper {

	static String tempDir = System.getProperty("java.io.tmpdir");
	Image img;
	Image img2;

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		try {
			img = Image.getInstance(tempDir+"scan/image2.jpg");
			img2 = Image.getInstance(tempDir+"scan/image.jpg");
			img.setAlignment(Element.ALIGN_BOTTOM);
			// img.setAbsolutePosition(20, 800);
			img.setAbsolutePosition(30, 10);
			img.scalePercent(50);
			img2.setAlignment(Element.ALIGN_RIGHT);
			img2.setAbsolutePosition(0, 798);
			img2.scalePercent(50);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {

		int n = writer.getPageNumber();

		for (int i = 1; i <= n; i++) {

			if (i == 1) 
			{}
			else {

				try {
					writer.getDirectContentUnder().addImage(img);
					writer.getDirectContentUnder().addImage(img2);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}

			}

		}
	}
}
