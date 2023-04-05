package com.rbs.rbstresource.service;

import com.rbs.rbstresource.component.Card;
import com.rbs.rbstresource.service.ORMRepository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class CardService {
    private final CardRepository cardRepo;

    @Autowired
    public CardService(CardRepository cardRepo){
        this.cardRepo = cardRepo;
    }

    public void createCard(Card bankCard) {
        log.info("Added new bank card {}", bankCard);
        cardRepo.save(bankCard);
    }

    public Card getCardById(Long id) {
        log.info("Found bank card by id {}", id);
        return cardRepo.getReferenceById(id);
    }

    public Card getCardByCode(String code) {
        log.info("Found bank card by code {}", code);
        return cardRepo.findByCode(code);
    }

    @Deprecated
    public void deleteCardById(Long id) {
        log.warn("Trying to delete card with id {}", id);
        try {
            log.info("Deleted card with id {} successfully", id);
            cardRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Failed to delete card with id {}", id);
            throw new IllegalStateException("Cant delete it!");
        }
    }

    @Deprecated
    public void deleteAllCards() {
        log.info("Deleted all cards");
        cardRepo.deleteAll();
    }

    public void updateCardById(float balance, Long id) {
        cardRepo.setCardBalanceById(balance, id);
    }
}
