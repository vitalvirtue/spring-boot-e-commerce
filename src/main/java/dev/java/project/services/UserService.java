package dev.java.project.services;

import dev.java.project.model.User;
import lombok.extern.slf4j.Slf4j;
import dev.java.project.dao.UserRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        try {
            
            
            User newUser = userRepository.save(user);
            
            log.info("User has been saved successfully with id = {}.", newUser.getId());
			return newUser;
            
        } catch (Exception e) {
            log.error("Error occurred while saving user: {}", e.getMessage());
			throw e;
            
		}
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
