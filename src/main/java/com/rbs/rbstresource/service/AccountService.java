package com.rbs.rbstresource.service;

import com.rbs.rbstresource.payload.response.AccountData;
import com.rbs.rbstresource.service.ORMRepository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class AccountService {
    private final AccountRepository accountRepo;

    @Autowired
    public AccountService(AccountRepository accountRepo){
        this.accountRepo = accountRepo;
    }

    public List<AccountData> getAccountList(String code){
        return accountRepo.findAllByAccountNumber(code).stream()
                .map(a -> new AccountData(a.getId(), a.getAccountNumber(), a.getStatusTime(), a.getBalance()))
                .toList();
    }

//    public void createAccount(Account account) {
//        log.info("Added new account {}", account);
//        accountRepo.save(account);
//    }
//
//    public Account findAccountByAccountName(String name) {
//        log.info("Found account with name {}", name);
//        return accountRepo.findByAccountName(name);
//    }
//
//    public Account findAccountByAccountNumber(String number) {
//        log.info("Found account with number {}", number);
//        return accountRepo.findByAccountNumber(number);
//    }
//
//    public Account findAccountByClientId(Long id) {
//        log.info("Found account with client id {}", id);
//        return accountRepo.findByClientId(id);
//    }
//
//    public Account findAccountById(Long id) {
//        log.info("Found account with id {}", id);
//        return accountRepo.getReferenceById(id);
//    }
//
//    @Deprecated
//    public void deleteAccountById(Long id) {
//        log.warn("Trying to delete account with id {}", id);
//        try {
//            log.info("Deleted account with id {} successfully", id);
//            accountRepo.deleteById(id);
//        } catch (Exception e) {
//            log.error("Failed to delete account with id {}", id);
//            throw new IllegalStateException("Cant delete it!");
//        }
//    }
//
//    @Deprecated
//    public void deleteAllAccounts() {
//        log.info("Deleted all accounts");
//        accountRepo.deleteAll();
//    }
//
//    public void updateAccountById(float balance, Long id) {
//        accountRepo.setAccountBalanceById(balance, id);
//    }
}
