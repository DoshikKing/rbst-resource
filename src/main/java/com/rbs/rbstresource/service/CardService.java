package com.rbs.rbstresource.service;

import com.rbs.rbstresource.component.Card;
import com.rbs.rbstresource.payload.request.ABSCardData;
import com.rbs.rbstresource.payload.response.CardData;
import com.rbs.rbstresource.service.repository.*;
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
    private final ClientRepository clientDAO;
    private final AccountRepository accountDAO;
    private final CardRepository cardDAO;
    private final StatusRepository statusDAO;
    private final TariffRepository tariffDAO;
    private final PaySystemRepository paySystemDAO;

    @Autowired
    public CardService(CardRepository cardDAO, StatusRepository statusDAO, ClientRepository clientDAO, AccountRepository accountDAO, TariffRepository tariffDAO, PaySystemRepository paySystemDAO){
        this.clientDAO = clientDAO;
        this.accountDAO = accountDAO;
        this.cardDAO = cardDAO;
        this.statusDAO = statusDAO;
        this.tariffDAO = tariffDAO;
        this.paySystemDAO = paySystemDAO;
    }

    public List<CardData> getCardList(String userId) {
        var client = clientDAO.findByUserId(userId);
        var cards = client.getCards();
        if(cards != null) {
            return cards.stream().map(a -> new CardData(a.getId(), a.getAccount().getId(), a.getCode(), a.getBalance(), a.getStatus().getStatusName(), a.getPaySystem().getType()))
            .toList();
        } else {
            return new ArrayList<>();
        }
    }

    public void updateCard(ABSCardData absCardData) throws SQLException {
        var client = clientDAO.findByUserId(absCardData.getUserId());
        var card = cardDAO.findByClientAndCode(client, absCardData.getCode());

        card.setClient(client);
        card.setCode(absCardData.getCode());
        card.setStatus(statusDAO.findByStatusName(absCardData.getStatus()));
        card.setLimitPerDay(absCardData.getLimitPerDay());
        card.setBalance(absCardData.getBalance());
        card.setStatusTime(absCardData.getStatusTime());
        card.setAccount(accountDAO.findByClientAndAccountNumber(client, absCardData.getAccountNumber()));
        card.setTariff(tariffDAO.findByTariffName(absCardData.getTariffName()));
        card.setPaySystem(paySystemDAO.findByType(absCardData.getPaySystemName()));

        cardDAO.save(card);
    }

    public void saveCard(ABSCardData absCardData) throws SQLException {
        var client = clientDAO.findByUserId(absCardData.getUserId());
        var card = new Card();

        card.setClient(client);
        card.setCode(absCardData.getCode());
        card.setStatus(statusDAO.findByStatusName(absCardData.getStatus()));
        card.setLimitPerDay(absCardData.getLimitPerDay());
        card.setBalance(absCardData.getBalance());
        card.setStatusTime(absCardData.getStatusTime());
        card.setAccount(accountDAO.findByClientAndAccountNumber(client, absCardData.getAccountNumber()));
        card.setTariff(tariffDAO.findByTariffName(absCardData.getTariffName()));
        card.setPaySystem(paySystemDAO.findByType(absCardData.getPaySystemName()));
        card.setTransactionsList(null);

        cardDAO.save(card);
    }

    public void updateCardStatus(Long id, String statusName) throws SQLException {
        cardDAO.updateCardSetStatusForId(statusDAO.findByStatusName(statusName).getId(), id);
    }
}
