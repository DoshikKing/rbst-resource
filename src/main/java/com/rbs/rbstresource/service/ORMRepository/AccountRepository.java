package com.rbs.rbstresource.service.ORMRepository;

import com.rbs.rbstresource.component.Account;
import com.rbs.rbstresource.component.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByUserId(Long id);
    Account findByAccountNumber(String account_number);
    Account findByAccountName(String account_name);
    Account findByClientId(Long id);
    @Modifying
    @Query(value = "update Account u set u.balance = :balance where u.id = :id")
    void updateAccountSetBalanceForId(@Param("balance") float balance, @Param("id") Long id);
    @Modifying
    @Query(value = "update Account u set u.status = :status where u.id = :id")
    void updateAccountSetStatusForId(@Param("status") Long status_id, @Param("id") Long id);
}
