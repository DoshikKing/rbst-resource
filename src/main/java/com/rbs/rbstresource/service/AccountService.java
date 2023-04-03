package com.rbs.rbstresource.service;

import com.rbs.rbstresource.component.Account;
import com.rbs.rbstresource.service.ORMRepository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Component
@Transactional
public class AccountService {
    AccountRepository accountRepo;

    @Autowired
    public AccountService(AccountRepository accountRepo){
        this.accountRepo = accountRepo;
    }

    public void addAccount(Account account) {
        log.info("Added new account {}", account);
        accountRepo.save(account);
    }

    public Account findByAccountName(String name) {
        log.info("Found account with name {}", name);
        return accountRepo.findByAccountName(name);
    }

    public Account findByAccountNumber(String number) {
        log.info("Found account with number {}", number);
        return accountRepo.findByAccountNumber(number);
    }

    public Account findByClientId(Long id) {
        log.info("Found account with client id {}", id);
        return accountRepo.findByClientId(id);
    }

    public Account findById(Long id) {
        log.info("Found account with id {}", id);
        return accountRepo.getReferenceById(id);
    }

    @Deprecated
    public void deleteAccountById(Long id) {
        log.warn("Trying to delete account with id {}", id);
        try {
            log.info("Deleted account with id {} successfully", id);
            accountRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Failed to delete account with id {}", id);
            throw new IllegalStateException("Cant delete it!");
        }
    }

    @Deprecated
    public void deleteAllAccounts() {
        log.info("Deleted all accounts");
        accountRepo.deleteAll();
    }

    public void updateById(float balance, Long id) {
        accountRepo.setAccountInfoById(balance, id);
    }
}
