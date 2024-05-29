package dev.java.project.controllers;



import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

  

    @GetMapping("/home")
    public ResponseEntity<String> getMethodName(@RequestParam String param) {
        return ResponseEntity.ok("User registered successfully");
    }
    
    
}
