package dev.java.project.controllers;

import dev.java.project.dao.UserRepository;
import dev.java.project.dto.CreditCardDTO;
import dev.java.project.dto.UserDTO;
import dev.java.project.model.CreditCard;
import dev.java.project.model.User;
import dev.java.project.services.CreditCardService;
import dev.java.project.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private UserRepository userRepository;
    
    

    @GetMapping("/{userId}")
    public ResponseEntity<List<CreditCard>> getAllCreditCards(@PathVariable Long userId) {
        List<CreditCard> creditCards = creditCardService.getAllCreditCards(userId);
        return ResponseEntity.ok(creditCards);
    }
    

    @PostMapping("/")
    public ResponseEntity<CreditCardDTO> createCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
        ModelMapper modelMapper = new ModelMapper();
        CreditCard creditCard = modelMapper.map(creditCardDTO, CreditCard.class);
        LocalDateTime dateTime = LocalDateTime.now();
        creditCard.setCreatedAt(dateTime);
        creditCard.setUpdatedAt(dateTime);

        User user = userRepository.findById(creditCardDTO.getUserId()).orElse(null); // Kullanıcıyı veritabanından al
        creditCard.setUser(user);

        CreditCard newCreditCard = creditCardService.createCreditCard(creditCard); // Kredi kartı oluştur
        CreditCardDTO newCreditCardDTO = modelMapper.map(newCreditCard, CreditCardDTO.class); // DTO'ya dönüştür

        return ResponseEntity.ok(newCreditCardDTO); // Oluşturulan kredi kartını yanıt olarak gönder
}

    @DeleteMapping("/{creditCardId}")
    public ResponseEntity<String> deleteCreditCard(@PathVariable Long creditCardId) {
        creditCardService.deleteCreditCardById(creditCardId);
        
        return ResponseEntity.ok(new String("The card was removed successfully"));
    }




}
