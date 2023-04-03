package com.rbs.rbstresource.service.ORMRepository;

import com.rbs.rbstresource.component.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientName(String client_name);
}
