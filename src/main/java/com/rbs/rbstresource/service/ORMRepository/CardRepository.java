package com.rbs.rbstresource.service.ORMRepository;

import com.rbs.rbstresource.component.Account;
import com.rbs.rbstresource.component.Card;
import com.rbs.rbstresource.component.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByClientAndCode(Client client, String code);
    Card findByCode(String code);
    @Modifying
    @Query(value = "update Card u set u.balance = :balance where u.id = :id")
    void updateCardSetBalanceForId(@Param("balance") float balance, @Param("id") Long id);

    @Modifying
    @Query(value = "update Card u set u.status = :status where u.id = :id")
    void updateCardSetStatusForId(@Param("status") Long status_id, @Param("id") Long id);
}
