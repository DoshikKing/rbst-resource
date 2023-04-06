package com.rbs.rbstresource.service.ORMRepository;

import com.rbs.rbstresource.component.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUserId(Long id);
}
