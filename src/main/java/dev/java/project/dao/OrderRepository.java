package dev.java.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.java.project.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
