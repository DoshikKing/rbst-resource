package com.rbs.rbstresource.service.ORMRepository;

import com.rbs.rbstresource.component.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByAccountNumber(String code);
    Account findByAccountNumber(String account_number);
    Account findByAccountName(String account_name);
    Account findByClientId(Long id);
    @Modifying
    @Query("update Account u set u.balance = ?1  where u.id = ?2")
    void setAccountBalanceById(float summ, Long id);
}
