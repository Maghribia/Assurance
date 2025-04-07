package com.maghrebia.Devis.pdf;

import com.maghrebia.Devis.Devis;
import com.maghrebia.Devis.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/devis/pdf")
public class BasePdfExportController {

    @Autowired
    private DevisService devisService;

    @Autowired
    protected GenericPdfExportService pdfExportService;

    protected ResponseEntity<byte[]> exportToPdf(String filename, String title, String[] headers, List<Map<String, String>> data) {
        ByteArrayOutputStream outputStream = pdfExportService.generatePdf(title, headers, data);
        byte[] bytes = outputStream.toByteArray();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_PDF);
        responseHeaders.setContentDispositionFormData("attachment", filename);
        responseHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(bytes, responseHeaders, HttpStatus.OK);
    }
}
