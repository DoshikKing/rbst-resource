package com.rbs.rbstresource.service;

import com.rbs.rbstresource.payload.response.BillingData;
import com.rbs.rbstresource.service.repository.BillingRepository;
import com.rbs.rbstresource.service.repository.CardRepository;
import com.rbs.rbstresource.service.repository.ClientRepository;
import com.rbs.rbstresource.service.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = {SQLException.class})
public class BillingService {

    private final ClientRepository clientDAO;

    @Autowired
    public BillingService(ClientRepository clientDAO){
        this.clientDAO = clientDAO;
    }

    public List<BillingData> getBillingList(Long userId) {
        var client = clientDAO.findByUserId(userId);
        var billings = client.getBillings();

        if(billings != null) {
            return billings.stream().map(a -> new BillingData(a.getId(), a.getBillingName(), a.getComment(), a.getStatus().getStatusName()))
            .toList();
        } else {
            return new ArrayList<>();
        }
    }
}
