package com.maghrebia.Sinistre;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SinistreService {

    @Autowired
    private SinistreRepository sinistreRepository;
    private Sinistre sinistre;
    public Optional<Sinistre> findById(String id) {
        return sinistreRepository.findById(id);
    }

    public Sinistre addSinsitre(Sinistre sinistre) {
        return sinistreRepository.save(sinistre);
    }

    public List<Sinistre> getAllCredits() {
        return sinistreRepository.findAll();
    }

    public Sinistre updateSinistre(String id, Sinistre updatedSinistre) {
        Optional<Sinistre> existingSinistreOpt = sinistreRepository.findById(id);

        if (!existingSinistreOpt.isPresent()) {
            throw new RuntimeException("Sinistre introuvable avec l'ID : " + id);
        }

        Sinistre existingSinistre = existingSinistreOpt.get();

        // Mise à jour des champs s'ils ne sont pas nuls
        if (updatedSinistre.getDescription() != null) {
            existingSinistre.setDescription(updatedSinistre.getDescription());
        }
        if (updatedSinistre.getDateSinistre() != null) {
            existingSinistre.setDateSinistre(updatedSinistre.getDateSinistre());
        }
        if (updatedSinistre.getMontantRembourssement() != null) {
            existingSinistre.setMontantRembourssement(updatedSinistre.getMontantRembourssement());
        }
        if (updatedSinistre.getStatus() != null) {
            existingSinistre.setStatus(updatedSinistre.getStatus());
        }
        if (updatedSinistre.getType_prop() != null) {
            existingSinistre.setType_prop(updatedSinistre.getType_prop());
        }
        if (updatedSinistre.getImage() != null) {
            existingSinistre.setImage(updatedSinistre.getImage());
        }
        if (updatedSinistre.getImageData() != null) {
            existingSinistre.setImageData(updatedSinistre.getImageData());
        }

        return sinistreRepository.save(existingSinistre);
    }



    public void deleteSinistre(String id) {
        sinistreRepository.deleteById(id);
    }


}

