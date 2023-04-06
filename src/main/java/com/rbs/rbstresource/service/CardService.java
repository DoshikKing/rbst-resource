package com.rbs.rbstresource.service;

import com.rbs.rbstresource.payload.response.CardData;
import com.rbs.rbstresource.service.ORMRepository.AccountRepository;
import com.rbs.rbstresource.service.ORMRepository.CardRepository;
import com.rbs.rbstresource.service.ORMRepository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = {SQLException.class})
public class CardService {
    private final CardRepository cardDAO;
    private final AccountRepository accountDAO;
    private final StatusRepository statusDAO;

    @Autowired
    public CardService(CardRepository cardDAO, AccountRepository accountDAO, StatusRepository statusDAO){
        this.cardDAO = cardDAO;
        this.accountDAO = accountDAO;
        this.statusDAO = statusDAO;
    }

    public List<CardData> getCardList(String code) throws SQLException {
        return cardDAO.findAllByAccount(accountDAO.findByAccountNumber(code)).stream()
                .map(a -> new CardData(a.getId(), a.getCode(), a.getBalance(), a.getStatus().getStatusName(), a.getPaySystem().getType()))
                .toList();
    }

    public void updateCardStatus(Long id, String statusName) throws SQLException {
        cardDAO.updateCardSetStatusForId(statusDAO.findByStatusName(statusName).getId(), id);
    }
}
