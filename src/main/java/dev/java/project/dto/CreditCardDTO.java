package dev.java.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

    private Long id;
    private String cardNumber;
    private String cardholderName;
    private LocalDate expirationDate;
    private String cvv;
    private Long userId; // Kredi kartının kullanıcısının kimliği

}
