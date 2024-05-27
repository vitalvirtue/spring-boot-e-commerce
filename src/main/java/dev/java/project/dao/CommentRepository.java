package dev.java.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.java.project.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
