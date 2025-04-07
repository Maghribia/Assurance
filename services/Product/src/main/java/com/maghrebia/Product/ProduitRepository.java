package com.maghrebia.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
}
