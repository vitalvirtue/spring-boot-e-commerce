package dev.java.project.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.java.project.model.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    
}