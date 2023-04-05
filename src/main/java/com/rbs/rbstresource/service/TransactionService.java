package com.rbs.rbstresource.service;
import com.rbs.rbstresource.component.Account;
import com.rbs.rbstresource.component.Card;
import com.rbs.rbstresource.component.Transaction;
import com.rbs.rbstresource.payload.response.TransactionData;
import com.rbs.rbstresource.service.ORMRepository.AccountRepository;
import com.rbs.rbstresource.service.ORMRepository.CardRepository;
import com.rbs.rbstresource.service.ORMRepository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import java.sql.Date;

@Service
@Slf4j
@Transactional(rollbackFor = {SQLException.class})
public class TransactionService {
    private final TransactionRepository transactionDAO;
    private final AccountRepository accountDAO;
    private final CardRepository cardDAO;

    @Autowired
    public TransactionService(TransactionRepository transactionDAO, AccountRepository accountDAO, CardRepository cardDAO){
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
        this.cardDAO = cardDAO;
    }

    public void makeTransaction(String debit, String credit, float amount, String debitBank, String creditBank, String comment) throws SQLException {

        if(creditBank.equals("LOCAL") && debitBank.equals("LOCAL")){
            var from = accountDAO.findByAccountNumber(debit);
            if(credit.length() == 16){
                var toCard = cardDAO.findByCode(credit);
                var to = toCard.getAccount();

                makeTransfer(from, to, null, toCard, amount, comment);

                accountDAO.setAccountBalanceById(from.getBalance() - (amount), from.getId());
                accountDAO.setAccountBalanceById(to.getBalance() + (amount), to.getId());
                cardDAO.setCardBalanceById(toCard.getBalance() + (amount), toCard.getId());
            } else {
                var to = accountDAO.findByAccountNumber(credit);

                makeTransfer(from, to, null, null, amount, comment);

                accountDAO.setAccountBalanceById(from.getBalance() - (amount), from.getId());
                accountDAO.setAccountBalanceById(to.getBalance() + (amount), to.getId());
            }
        }
    }

    private void makeTransfer(Account from, Account to, Card fromCard, Card toCard, float amount, String comment) throws SQLException {
        java.util.Date time = new java.util.Date();
        Date date = new Date(time.getTime());
        String uuid = UUID.randomUUID().toString();

        Transaction transaction = new Transaction();

        transaction.setCard(fromCard);
        transaction.setAccount(from);
        transaction.setAmount(amount);
        transaction.setComment(comment);
        transaction.setIsDebit(true);
        transaction.setTransactionTime(date);
        transaction.setTransactionGroup(uuid);
        transaction.setStatus("commit");

        transactionDAO.save(transaction);

        transaction.setCard(toCard);
        transaction.setAccount(to);
        transaction.setAmount(amount);
        transaction.setComment(comment);
        transaction.setIsDebit(false);
        transaction.setTransactionTime(date);
        transaction.setTransactionGroup(uuid);
        transaction.setStatus("commit");

        transactionDAO.save(transaction);
    }

//    public void makeTransaction(Transaction debitTransaction, Transaction creditTransaction) {
//        log.info("Added new debit transaction {}", debitTransaction);
//        log.info("Added new credit transaction {}", creditTransaction);
//        transactionsRepo.save(debitTransaction);
//        transactionsRepo.save(creditTransaction);
//    }

    public List<TransactionData> getCardTransactionsList(String code){
        return transactionDAO.findAll().stream()
                .filter(a -> a.getCard() != null)
                .filter(a -> Objects.equals(a.getCard().getCode(), code))
                .map(a -> new TransactionData(String.valueOf(a.getAmount()), a.getAmount(), a.getIsDebit(), a.getTransactionTime(), a.getCard().getCode()))
                .toList();
    }

    public List<TransactionData> getAccountTransactionsList(String code){
        return transactionDAO.findAll().stream()
                .filter(a -> Objects.equals(a.getAccount().getAccountNumber(), code))
                .map(a -> new TransactionData(String.valueOf(a.getAmount()), a.getAmount(), a.getIsDebit(), a.getTransactionTime(), a.getAccount().getAccountNumber()))
                .toList();
    }

//    public Transaction getTransactionByAccountId(Long id) {
//        log.info("Found transaction by related account id");
//        return transactionDAO.findByAccountId(id);
//    }
//
//    public Transaction getTransactionByCardId(Long id) {
//        log.info("Found transaction by related account id");
//        return transactionDAO.findByCardId(id);
//    }
//
//    public Transaction getTransactionByTransactionGroup(String transaction_group) {
//        log.info("Found transaction by transaction group code");
//        return transactionDAO.findByTransactionGroup(transaction_group);
//    }
//
//    public Transaction getTransactionById(Long id) {
//        log.info("Found transaction by id");
//        return transactionDAO.getReferenceById(id);
//    }

//    @Deprecated
//    public void deleteTransactionById(Long id) {
//        log.warn("Trying to delete transaction with id {}", id);
//        try {
//            log.info("Deleted transaction with id {} successfully", id);
//            transactionDAO.deleteById(id);
//        } catch (Exception e) {
//            log.error("Failed to delete transaction with id {}", id);
//            throw new IllegalStateException("Cant delete it!");
//        }
//    }
//    @Deprecated
//    public void deleteAllTransactions() {
//        log.info("Deleted all transactions");
//        transactionDAO.deleteAll();
//    }
}
