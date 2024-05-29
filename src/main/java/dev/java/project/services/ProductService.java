package dev.java.project.services;

import dev.java.project.model.CreditCard;
import dev.java.project.model.Product;
import dev.java.project.model.User;
import lombok.extern.slf4j.Slf4j;
import dev.java.project.dao.CreditCardRepository;
import dev.java.project.dao.ProductRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    

    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

   
}