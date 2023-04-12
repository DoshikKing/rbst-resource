package com.rbs.rbstresource.service;
import com.rbs.rbstresource.component.Account;
import com.rbs.rbstresource.component.Card;
import com.rbs.rbstresource.component.Transaction;
import com.rbs.rbstresource.exception.NotEnoughMoneyException;
import com.rbs.rbstresource.payload.response.TransactionData;
import com.rbs.rbstresource.service.repository.AccountRepository;
import com.rbs.rbstresource.service.repository.CardRepository;
import com.rbs.rbstresource.service.repository.ClientRepository;
import com.rbs.rbstresource.service.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.sql.Date;

@Service
@Slf4j
@Transactional(rollbackFor = {NotEnoughMoneyException.class, SQLException.class}, isolation = Isolation.SERIALIZABLE)
public class TransactionService {
    private final TransactionRepository transactionDAO;
    private final AccountRepository accountDAO;
    private final CardRepository cardDAO;
    private final ClientRepository clientDAO;

    @Autowired
    public TransactionService(TransactionRepository transactionDAO, AccountRepository accountDAO, CardRepository cardDAO, ClientRepository clientDAO){
        this.clientDAO = clientDAO;
        this.accountDAO = accountDAO;
        this.cardDAO = cardDAO;
        this.transactionDAO = transactionDAO;
    }

    public void makeTransferToCard(String debit, String credit, float amount, String debitBank, String creditBank, String comment, String userId) throws NotEnoughMoneyException, SQLException {
        var client = clientDAO.findByUserId(userId);
        if(creditBank.equals(debitBank)){
            var from = accountDAO.findByClientAndAccountNumber(client, debit);
            var toCard = cardDAO.findByCode(credit);
            var to = toCard.getAccount();

            if (from.getBalance() >= amount) {
                makeTransfer(from, to, null, toCard, amount, comment);
                accountDAO.updateAccountSetBalanceForId(from.getBalance() - (amount), from.getId());
                accountDAO.updateAccountSetBalanceForId(to.getBalance() + (amount), to.getId());
                cardDAO.updateCardSetBalanceForId(toCard.getBalance() + (amount), toCard.getId());
            } else {
                throw new NotEnoughMoneyException(from.getAccountNumber());
            }
        }
    }

    public void makeTransferToAccount(String debit, String credit, float amount, String debitBank, String creditBank, String comment, String userId) throws NotEnoughMoneyException, SQLException {
        var client = clientDAO.findByUserId(userId);
        if(creditBank.equals(debitBank)){
            var from = accountDAO.findByClientAndAccountNumber(client, debit);
            var to = accountDAO.findByAccountNumber(credit);

            if (from.getBalance() >= amount) {
                makeTransfer(from, to, null, null, amount, comment);
                accountDAO.updateAccountSetBalanceForId(from.getBalance() - (amount), from.getId());
                accountDAO.updateAccountSetBalanceForId(to.getBalance() + (amount), to.getId());
            } else {
                throw new NotEnoughMoneyException(from.getAccountNumber());
            }
        }
    }

    public List<TransactionData> getCardTransactionsList(String userId, Long id)  {
        var client = clientDAO.findByUserId(userId);
        var card = cardDAO.findByClientAndId(client ,id);
        if (card != null)
        {
            return transactionDAO.findAllByCard(card).stream()
            .map(a -> new TransactionData(a.getComment(), a.getAmount(), a.getIsDebit(), a.getTransactionTime(), a.getCard().getCode()))
            .toList();
        } else {
            return new ArrayList<>();
        }
    }

    public List<TransactionData> getAccountTransactionsList(String userId, Long id) {
        var client = clientDAO.findByUserId(userId);
        var account = accountDAO.findByClientAndId(client, id);
        if (account != null)
        {
            return transactionDAO.findAllByAccount(account).stream()
            .map(a -> new TransactionData(a.getComment(), a.getAmount(), a.getIsDebit(), a.getTransactionTime(), a.getAccount().getAccountNumber()))
            .toList();
        } else {
            return new ArrayList<>();
        }
    }

    private void makeTransfer(Account from, Account to, Card fromCard, Card toCard, float amount, String comment) {
        java.util.Date time = new java.util.Date();
        Date date = new Date(time.getTime());
        String uuid = UUID.randomUUID().toString();

        Transaction transaction_debit = new Transaction();

        Transaction transaction_credit = new Transaction();

        //transaction.setCard(fromCard);
        transaction_debit.setAccount(from);
        transaction_debit.setAmount(amount);
        transaction_debit.setComment(comment);
        transaction_debit.setIsDebit(true);
        transaction_debit.setTransactionTime(date);
        transaction_debit.setTransactionGroup(uuid);
        transaction_debit.setStatus("commit");

        transactionDAO.save(transaction_debit);

        transaction_credit.setCard(toCard);
        transaction_credit.setAccount(to);
        transaction_credit.setAmount(amount);
        transaction_credit.setComment(comment);
        transaction_credit.setIsDebit(false);
        transaction_credit.setTransactionTime(date);
        transaction_credit.setTransactionGroup(uuid);
        transaction_credit.setStatus("commit");

        transactionDAO.save(transaction_credit);
    }
}
