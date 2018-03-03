package com.poc.fileoperation.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Map;

@Service
public class PdfGenerator implements FileGenerator {
  @Override
  public OutputStream writeToOutputStream(OutputStream outputStream, Map<String, String> data)
      throws DocumentException {
    Document document = new Document();
    PdfWriter.getInstance(document, outputStream);
    document.open();
    addContent(document, data);
    document.close();
    return outputStream;
  }

  private void addContent(Document document, Map<String, String> data) throws DocumentException {
    document.add(createTable(2, data));
  }

  private Element createTable(int numberOfColumns, Map<String, String> data) {
    PdfPTable pdfPTable = new PdfPTable(numberOfColumns);
    data.forEach((key, value) -> {
      pdfPTable.addCell(key);
      pdfPTable.addCell(value);
    });

    return pdfPTable;
  }
}
