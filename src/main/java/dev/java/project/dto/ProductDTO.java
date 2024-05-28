package dev.java.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
