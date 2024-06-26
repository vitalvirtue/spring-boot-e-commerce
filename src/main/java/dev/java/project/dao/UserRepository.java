package dev.java.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.java.project.model.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

	boolean existsByEmail(String email);

    
  
}
