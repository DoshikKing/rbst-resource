package com.rbs.rbstresource.service;
import com.rbs.rbstresource.component.Transaction;
import com.rbs.rbstresource.service.ORMRepository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Component
@Transactional
public class TransactionService {
    TransactionRepository transactionsRepo;

    @Autowired
    public TransactionService(TransactionRepository transactionsRepo){
        this.transactionsRepo = transactionsRepo;
    }

    public void addTransaction(Transaction debitTransaction, Transaction creditTransaction) {
        log.info("Added new debit transaction {}", debitTransaction);
        log.info("Added new credit transaction {}", creditTransaction);
        transactionsRepo.save(debitTransaction);
        transactionsRepo.save(creditTransaction);
    }

    public Transaction getTransactionByAccountId(Long id) {
        log.info("Found transaction by related account id");
        return transactionsRepo.findByAccountId(id);
    }

    public Transaction getTransactionByCardId(Long id) {
        log.info("Found transaction by related account id");
        return transactionsRepo.findByCardId(id);
    }

    public Transaction getTransactionByTransactionGroup(String transaction_group) {
        log.info("Found transaction by transaction group code");
        return transactionsRepo.findByTransactionGroup(transaction_group);
    }

    public Transaction getTransactionById(Long id) {
        log.info("Found transaction by id");
        return transactionsRepo.getReferenceById(id);
    }

    @Deprecated
    public void deleteTransactionById(Long id) {
        log.warn("Trying to delete transaction with id {}", id);
        try {
            log.info("Deleted transaction with id {} successfully", id);
            transactionsRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Failed to delete transaction with id", id);
            throw new IllegalStateException("Cant delete it!");
        }
    }
    @Deprecated
    public void deleteAllTransactions() {
        log.info("Deleted all transactions");
        transactionsRepo.deleteAll();
    }
}
