package com.maghrebia.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProduitService implements IServiceProduit {
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
    public Optional<Produit> getProduitById(int id) {
        return produitRepository.findById(id);
    }

    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(int id, Produit produitDetails) {
        return produitRepository.findById(id).map(produit -> {
            produit.setType(produitDetails.getType());
            produit.setDescription(produitDetails.getDescription());
            produit.setTarifs(produitDetails.getTarifs());
            return produitRepository.save(produit);
        }).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    public void deleteProduit(int id) {
        produitRepository.deleteById(id);
    }

}
