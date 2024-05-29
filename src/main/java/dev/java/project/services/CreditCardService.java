package dev.java.project.services;

import dev.java.project.model.CreditCard;
import dev.java.project.model.User;
import lombok.extern.slf4j.Slf4j;
import dev.java.project.dao.CreditCardRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    

    public List<CreditCard> getAllCreditCards(Long userID) {
        return creditCardRepository.findByUserId(userID);
    }

    public CreditCard createCreditCard(CreditCard creditCard) {
        
        CreditCard newCreditCard = creditCardRepository.save(creditCard);
            
        log.info("User has been saved successfully with id = {}.", newCreditCard.getId());
		
        return newCreditCard;
       
    }

    public void deleteCreditCardById(Long creditCardId) {
        creditCardRepository.deleteById(creditCardId);
    }
}
