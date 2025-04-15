package com.maghrebia.Devis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevisSanteService {
    private final DevisSanteRepository devisSanteRepository;

    public DevisSante createDevisSante(DevisSante devisSante) {
        return devisSanteRepository.save(devisSante);
    }

    public List<DevisSante> getAllDevisSante() {
        return devisSanteRepository.findAll();
    }

    public Optional<DevisSante> getDevisSanteById(String id) {
        return devisSanteRepository.findById(id);
    }

    public DevisSante updateDevisSante(String id, DevisSante updateDevisSante) {
        Optional<DevisSante> existingDevisSante = devisSanteRepository.findById(id);
        if (existingDevisSante.isPresent()) {
            DevisSante devisSante = existingDevisSante.get();
            devisSante.setContactNom(updateDevisSante.getContactNom());
            devisSante.setContactPrenom(updateDevisSante.getContactPrenom());
            devisSante.setContactEmail(updateDevisSante.getContactEmail());
            devisSante.setContactSociete(updateDevisSante.getContactSociete());
            devisSante.setContactFonction(updateDevisSante.getContactFonction());
            devisSante.setContactTelephone(updateDevisSante.getContactTelephone());
            devisSante.setContactDateNaissance(updateDevisSante.getContactDateNaissance());
            return devisSanteRepository.save(devisSante);
        } else {
            return null;
        }
    }

    public void deleteDevisSante(String id) {
        devisSanteRepository.deleteById(id);
    }
}
