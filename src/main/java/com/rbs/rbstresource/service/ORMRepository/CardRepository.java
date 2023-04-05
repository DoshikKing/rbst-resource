package com.rbs.rbstresource.service.ORMRepository;

import com.rbs.rbstresource.component.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCode(String code);
    @Modifying
    @Query("update Card u set u.balance = ?1  where u.id = ?2")
    void setCardBalanceById(float balance, Long id);
}
