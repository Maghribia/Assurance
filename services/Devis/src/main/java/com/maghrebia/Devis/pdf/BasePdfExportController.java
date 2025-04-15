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



}
