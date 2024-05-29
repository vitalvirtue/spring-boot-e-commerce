package dev.java.project.Services;

import dev.java.project.model.Product;
import dev.java.project.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
  
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }

    public List<String> getCategories() {
        List<Product> products = productRepository.findAll();
        List<String> categories = new ArrayList<>();
        for (Product product : products) {
            if (!categories.contains(product.getCategory())) {
                categories.add(product.getCategory());
            }
        }
        return categories;
    }

    public List<String> getCategories2() {
        List<Product> products = productRepository.findAll();
        Map<String, Boolean> categories = new HashMap<>();
        for (Product product : products) {
            categories.put(product.getCategory(), true);
        }
        return new ArrayList<>(categories.keySet());
    }
}
