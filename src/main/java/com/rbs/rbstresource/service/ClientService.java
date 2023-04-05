package com.rbs.rbstresource.service;

import com.rbs.rbstresource.component.Client;
import com.rbs.rbstresource.service.ORMRepository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class ClientService {
    private final ClientRepository clientRepo;

    @Autowired
    public ClientService(ClientRepository clientRepo){
        this.clientRepo = clientRepo;
    }

    public Client findByClientName(String name) {
        log.info("Found client by name {}", name);
        return clientRepo.findByClientName(name);
    }

    public Client findById(Long id) {
        log.info("Found client by id {}", id);
        return clientRepo.getReferenceById(id);
    }
}
