package com.maghrebia.Devis;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DevisEcoliaService {

    @Autowired
    private DevisEcoliaRepository devisEcoliaRepository;

    public DevisEcolia createDevisEcolia(DevisEcolia devisEcolia) {
        return devisEcoliaRepository.save(devisEcolia);
    }

    public List<DevisEcolia> getAllDevisEcolia() {
        return devisEcoliaRepository.findAll();
    }

    public Optional<DevisEcolia> getDevisEcoliaById(String id) {
        return devisEcoliaRepository.findById(id);
    }

    public DevisEcolia updateDevisEcolia(String id, DevisEcolia updateDevisEcolia) {
        Optional<DevisEcolia> existingDevisEcolia = devisEcoliaRepository.findById(id);
        if (existingDevisEcolia.isPresent()) {
            DevisEcolia devisEcolia = existingDevisEcolia.get();
            // Update the fields of devisEcolia with the values from updateDevisEcolia
            devisEcolia.setParentTuteurNom(updateDevisEcolia.getParentTuteurNom());
            devisEcolia.setParentTuteurPieceIdentite(updateDevisEcolia.getParentTuteurPieceIdentite());
            // Update other fields as needed
            return devisEcoliaRepository.save(devisEcolia);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteDevisEcolia(String id) {
        devisEcoliaRepository.deleteById(id);
    }

    public void generatePdf(HttpServletResponse response) throws IOException {
        // Implement the logic to generate a PDF for DevisEcolia
        // and write it to the HttpServletResponse
    }
}
