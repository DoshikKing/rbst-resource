package com.rbs.rbstresource.service.repository;

import com.rbs.rbstresource.component.PaySystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaySystemRepository extends JpaRepository<PaySystem, Long> {
    PaySystem findByType(String type);
}
