package dev.java.project.Controllers;

import dev.java.project.model.Product;
import dev.java.project.services.ProductService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000") 
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
	public ResponseEntity<Optional<Product>> getProduct(@PathVariable(name = "id") long id) {
        
		return ResponseEntity.ok(productService.getProduct(id));
	}

}
