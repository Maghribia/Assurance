package com.example.microservice_candidats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidateRepository;

    public Candidat addCandidat(Candidat candidat) {
        return candidateRepository.save(candidat);
    }

    public List<Candidat> getAllCandidats() {
        return candidateRepository.findAll();
    }

    public Optional<Candidat> getCandidatById(int id) {
        return candidateRepository.findById(id);
    }

    public Candidat updateCandidat(int id, Candidat newCandidat) {
        return candidateRepository.findById(id).map(existingCandidat -> {
            existingCandidat.setNom(newCandidat.getNom());
            existingCandidat.setPrenom(newCandidat.getPrenom());
            existingCandidat.setEmail(newCandidat.getEmail());
            return candidateRepository.save(existingCandidat);
        }).orElse(null);
    }

    public String deleteCandidat(int id) {
        if (candidateRepository.findById(id).isPresent()) {
            candidateRepository.deleteById(id);
            return "Candidat supprimé";
        } else {
            return "Candidat non trouvé";
        }
    }
}
