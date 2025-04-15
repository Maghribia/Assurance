package com.maghrebia.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByType(String type);

    @Query("SELECT p FROM Produit p WHERE " +
            "LOWER(p.type) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.Description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(p.Tarifs AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.Image) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Produit> searchProduits(String keyword);
}

