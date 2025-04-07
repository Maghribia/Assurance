package com.maghrebia.Product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TypeAssuranceService {


        @Autowired
        private TypeAssuranceRepository repository;

        // Ajouter un nouveau type d'assurance
        public TypeAssurance addTypeAssurance(TypeAssurance typeAssurance) {
            return repository.save(typeAssurance);
        }

        // Récupérer tous les types d'assurance
        public List<TypeAssurance> getAllTypeAssurances() {
            return repository.findAll();
        }

        // Récupérer un type d'assurance par son ID
        public Optional<TypeAssurance> getTypeAssuranceById(Long id) {
            return repository.findById(id);
        }

        // Mettre à jour un type d'assurance
        public TypeAssurance updateTypeAssurance(Long id, TypeAssurance typeAssuranceDetails) {
            return repository.findById(id).map(typeAssurance -> {
                typeAssurance.setType(typeAssuranceDetails.getType());
                return repository.save(typeAssurance);
            }).orElseThrow(() -> new RuntimeException("TypeAssurance non trouvé"));
        }

        // Supprimer un type d'assurance
        public void deleteTypeAssurance(Long id) {
            repository.deleteById(id);
        }
    }


