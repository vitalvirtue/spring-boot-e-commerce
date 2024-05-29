package dev.java.project.controllers;

import dev.java.project.dto.UserDTO;
import dev.java.project.model.User;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("public/register")
@AllArgsConstructor
public class RegisterController {

    @Autowired
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO,User.class);
        LocalDateTime dateTime = LocalDateTime.now();
        user.setCreatedAt(dateTime);
		user.setUpdatedAt(dateTime);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User newUser = userService.createUser(user);

        UserDTO newUserDTO = modelMapper.map(newUser,UserDTO.class);
        return ResponseEntity.ok(newUserDTO);
        
        
    }

    @GetMapping("/b")
    public ResponseEntity<?> registerUser() {
       
        return ResponseEntity.ok("newUserDTO");
        
        
    }
}
