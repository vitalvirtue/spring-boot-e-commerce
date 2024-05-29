package dev.java.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.java.project.model.Product;
import dev.java.project.model.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
