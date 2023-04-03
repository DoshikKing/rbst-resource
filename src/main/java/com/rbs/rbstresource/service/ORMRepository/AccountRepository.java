package com.rbs.rbstresource.service.ORMRepository;

import com.rbs.rbstresource.component.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String account_number);
    Account findByAccountName(String account_name);
    Account findByClientId(Long id);
    @Modifying
    @Query("update Account u set u.balance = ?1  where u.id = ?2")
    void setAccountInfoById(float summ, Long id);
}
