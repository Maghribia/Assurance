package com.maghrebia.Devis;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    // Create a new Devis
    public Devis createDevis(Devis devis) {
        return devisRepository.save(devis);
    }

    // Get all Devis
    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }

    // Get Devis by ID
    public Optional<Devis> getDevisById(String id) { // Updated parameter name
        return devisRepository.findById(id);
    }

    // Update Devis
    public Devis updateDevis(String id, Devis updatedDevis) { // Updated parameter name
        Optional<Devis> existingDevis = devisRepository.findById(id);

        if (existingDevis.isPresent()) {
            Devis devis = existingDevis.get();
            devis.setMontantEstime(updatedDevis.getMontantEstime());
            devis.setDateDemande(updatedDevis.getDateDemande());
            devis.setTypeAssurance(updatedDevis.getTypeAssurance());

            return devisRepository.save(devis);
        } else {
            throw new RuntimeException("Devis non trouvé avec l'ID : " + id);
        }
    }

    // Delete Devis
    public void deleteDevis(String id) { // Updated parameter name
        devisRepository.deleteById(id);
    }

    // Generate PDF
    public void generatePdf(HttpServletResponse response) throws IOException {
        List<Devis> devisList = devisRepository.findAll(); // Récupérer tous les devis

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=devis_list.pdf");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Ajouter un titre
            document.add(new Paragraph("Liste des Devis"));

            // Ajouter les devis
            for (Devis devis : devisList) {
                document.add(new Paragraph("Montant Estimé: " + devis.getMontantEstime()));
                document.add(new Paragraph("Date de Demande: " + devis.getDateDemande()));
                document.add(new Paragraph("Type d'Assurance: " + devis.getTypeAssurance()));
                document.add(new Paragraph("État du Devis: " + devis.getEtatDevis()));
                document.add(new Paragraph("\n")); // Saut de ligne
            }
        } catch (DocumentException e) {
            throw new IOException("Erreur lors de la génération du PDF", e);
        } finally {
            document.close();
        }
    }


}