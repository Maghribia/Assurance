package com.maghrebia.Devis.pdf;

import com.maghrebia.Devis.Devis;
import org.springframework.stereotype.Service;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
 // Assuming this is your Devis model class
import java.io.ByteArrayOutputStream;
import java.util.List;  // Assuming this is your Devis model class
import java.util.Map;


@Service
public class GenericPdfExportService {
    public ByteArrayOutputStream generatePdf(String title, String[] headers, List<Map<String, String>> data) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titleParagraph = new Paragraph(title, titleFont);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(titleParagraph);
            document.add(Chunk.NEWLINE);

            // Create table
            PdfPTable table = new PdfPTable(headers.length);
            table.setWidthPercentage(100);

            // Table headers
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
            }

            // Table data
            for (Map<String, String> row : data) {
                for (String header : headers) {
                    table.addCell(row.getOrDefault(header, ""));
                }
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return out;
    }
}