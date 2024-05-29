package dev.java.project.Controllers;

import dev.java.project.model.Product;
import dev.java.project.Services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
  
    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts(HttpServletRequest request, @RequestParam(required = false) String category) {
        List<Product> products = productService.getProducts();
        if (category != null) {
            return products.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());
        }
        return products;
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        List<Product> products = productService.getProducts();
        List<String> categories = new ArrayList<>();
        for (Product product : products) {
            if (!categories.contains(product.getCategory())) {
                categories.add(product.getCategory());
            }
        }
        return categories;
    }

    @GetMapping("/categories2")
    public List<String> getCategories2() {
        List<Product> products = productService.getProducts();
        Map<String, Boolean> categories = new HashMap<>();
        for (Product product : products) {
            categories.put(product.getCategory(), true);
        }
        return new ArrayList<>(categories.keySet());
    }
}
