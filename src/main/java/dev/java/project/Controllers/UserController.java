package dev.java.project.Controllers;

import dev.java.project.model.User;
import dev.java.project.services.ProductService;
import dev.java.project.services.UserService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/users")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final ProductService productService;

    @DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable(name = "id") long id) {
        
		return ResponseEntity.ok(userService.deleteUser(id));
	}

    @PutMapping("buy/{id}")
    public ResponseEntity<User> getProduct(@PathVariable Long id,
    @RequestBody Map<String, Long> requestBody) {
        Long productId = requestBody.get("productId");

        User user = userService.getUserByID(id);

        LocalDateTime dateTime = LocalDateTime.now();
        user.setPurchaseDate(dateTime);
        user.setProduct(productService.getProduct(productId).get());
        
        return ResponseEntity.ok(userService.buyOrReturnProduct(user));
    }


    @PutMapping("return/{id}")
    public ResponseEntity<User> returnProduct(@PathVariable Long id) {
        

        User user = userService.getUserByID(id);

        
        user.setPurchaseDate(null);
        user.setProduct(null);
        
        return ResponseEntity.ok(userService.buyOrReturnProduct(user));
    }

}
