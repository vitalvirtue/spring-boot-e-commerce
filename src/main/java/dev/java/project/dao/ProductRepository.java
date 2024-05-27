package dev.java.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.java.project.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
