package com.rbs.rbstresource.service.repository;

import com.rbs.rbstresource.component.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUserId(Long id);
}
