package br.com.java.pdfsystem.maven;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class PrimeiroPdf {
	
	public PrimeiroPdf(String FraseAImprimir) {
		
		Document documentPDF = new Document();			
		
		try {
			PdfWriter.getInstance(documentPDF, new FileOutputStream("Relatorio1.pdf"));
			
			documentPDF.open();
			
			Paragraph paragrafoTeste = new Paragraph(FraseAImprimir);
			
			documentPDF.add(paragrafoTeste);
		}
		catch(DocumentException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		documentPDF.close();	
		
	}
}
