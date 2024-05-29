package dev.java.project.services;

import dev.java.project.model.User;
import lombok.extern.slf4j.Slf4j;
import dev.java.project.dao.UserRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

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

    public User getUserByID(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }


    public boolean deleteUser(long id) {
		try {
			
             Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            if (!userRepository.existsById(id)) {
				log.warn("User not found with id: {}", id);
				return false;
			}

             if (authentication != null && authentication.isAuthenticated()){
                String userEmail = authentication.getName();
                User user = getUserByEmail(userEmail);
                if(user.getId() != id){
                    log.warn("Users only delete themself", id);
				    return false;
                }
             }
            
            
            else {
            
				userRepository.deleteById(id);
				log.info("User deleted successfully with id: {}", id);
			}
		} catch (Exception e) {
			log.error("An error occurred while deleting user with id: {}: {}", id, e.getMessage());
			throw e;
		}
		return true;
	}

    public User buyOrReturnProduct(User user) {
        
        User newUser = userRepository.save(user);
            
        log.info("User has been saved successfully with id = {}.", newUser.getId());
		return newUser;
    }


}
