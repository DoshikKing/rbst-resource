package com.rbs.rbstresource.service;

import com.rbs.rbstresource.payload.response.CardData;
import com.rbs.rbstresource.service.repository.CardRepository;
import com.rbs.rbstresource.service.repository.ClientRepository;
import com.rbs.rbstresource.service.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = {SQLException.class})
public class CardService {
    private final CardRepository cardDAO;
    private final StatusRepository statusDAO;
    private final ClientRepository clientDAO;

    @Autowired
    public CardService(CardRepository cardDAO, StatusRepository statusDAO, ClientRepository clientDAO){
        this.clientDAO = clientDAO;
        this.cardDAO = cardDAO;
        this.statusDAO = statusDAO;
    }

    public List<CardData> getCardList(Long userId) {
        var client = clientDAO.findByUserId(userId);
        var cards = client.getCards();
        if(cards != null) {
            return cards.stream().map(a -> new CardData(a.getId(), a.getCode(), a.getBalance(), a.getStatus().getStatusName(), a.getPaySystem().getType()))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }

    public void updateCardStatus(Long id, String statusName) throws SQLException {
        cardDAO.updateCardSetStatusForId(statusDAO.findByStatusName(statusName).getId(), id);
    }
}
