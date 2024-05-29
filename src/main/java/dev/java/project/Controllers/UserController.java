package dev.java.project.Controllers;

import dev.java.project.dto.UserDTO;
import dev.java.project.model.Product;
import dev.java.project.model.User;
import dev.java.project.services.ProductService;
import dev.java.project.services.UserService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/users")
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

}
