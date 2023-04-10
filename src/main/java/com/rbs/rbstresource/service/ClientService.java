package com.rbs.rbstresource.service;

import com.rbs.rbstresource.component.Client;

import com.rbs.rbstresource.payload.request.ABSClientData;
import com.rbs.rbstresource.service.repository.ClientRepository;
import com.rbs.rbstresource.service.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Slf4j
@Transactional
public class ClientService {
    private final ClientRepository clientDAO;
    private final StatusRepository statusDAO;

    @Autowired
    public ClientService(ClientRepository clientDAO, StatusRepository statusDAO){
        this.clientDAO = clientDAO;
        this.statusDAO = statusDAO;
    }

    public void updateClientById(ABSClientData absClientData) throws SQLException {
        var client = clientDAO.getReferenceById(absClientData.getId());
        client.setClientName(absClientData.getClientName());
        client.setUserId(absClientData.getUserId());
        client.setStatus(statusDAO.findByStatusName(absClientData.getStatus()));
        clientDAO.save(client);
    }

    public void saveClient(ABSClientData absClientData) throws SQLException {
        var client = new Client();
        client.setCards(null);
        client.setBillings(null);
        client.setAccounts(null);
        client.setUserId(absClientData.getUserId());
        client.setStatus(statusDAO.findByStatusName(absClientData.getStatus()));
        client.setClientName(absClientData.getClientName());
        clientDAO.save(client);
    }
}
