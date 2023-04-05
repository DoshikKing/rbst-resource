package com.rbs.rbstresource.service;

import com.rbs.rbstresource.payload.response.CardData;
import com.rbs.rbstresource.service.ORMRepository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CardService {
    private final CardRepository cardDAO;

    @Autowired
    public CardService(CardRepository cardDAO){
        this.cardDAO = cardDAO;
    }

    public List<CardData> getCardList(String code){
        return cardDAO.findAllByCode(code).stream()
                .map(a -> new CardData(a.getId(), a.getCode(), a.getBalance(), a.getStatusTime(), a.getPaySystem().getType()))
                .toList();
    }

//    public void createCard(Card bankCard) {
//        log.info("Added new bank card {}", bankCard);
//        cardRepo.save(bankCard);
//    }
//
//    public Card getCardById(Long id) {
//        log.info("Found bank card by id {}", id);
//        return cardRepo.getReferenceById(id);
//    }
//
//    public Card getCardByCode(String code) {
//        log.info("Found bank card by code {}", code);
//        return cardRepo.findByCode(code);
//    }
//
//    @Deprecated
//    public void deleteCardById(Long id) {
//        log.warn("Trying to delete card with id {}", id);
//        try {
//            log.info("Deleted card with id {} successfully", id);
//            cardRepo.deleteById(id);
//        } catch (Exception e) {
//            log.error("Failed to delete card with id {}", id);
//            throw new IllegalStateException("Cant delete it!");
//        }
//    }
//
//    @Deprecated
//    public void deleteAllCards() {
//        log.info("Deleted all cards");
//        cardRepo.deleteAll();
//    }
//
//    public void updateCardById(float balance, Long id) {
//        cardRepo.setCardBalanceById(balance, id);
//    }
}
