package dev.java.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private boolean isActive;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
