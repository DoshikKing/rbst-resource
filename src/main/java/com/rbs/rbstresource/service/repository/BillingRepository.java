package com.rbs.rbstresource.service.repository;

import com.rbs.rbstresource.component.Billing;
import com.rbs.rbstresource.component.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRepository extends JpaRepository<Billing, Long> {
}
