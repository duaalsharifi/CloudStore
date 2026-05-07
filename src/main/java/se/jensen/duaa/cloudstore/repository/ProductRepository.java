package se.jensen.duaa.cloudstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.duaa.cloudstore.model.Product;

//Detta gör att Spring Boot kan spara och hämta produkter i databasen.

public interface ProductRepository extends JpaRepository<Product, Long> {
}
