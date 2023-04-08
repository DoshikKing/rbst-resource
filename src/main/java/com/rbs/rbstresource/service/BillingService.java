package com.rbs.rbstresource.service;

import com.rbs.rbstresource.component.Billing;
import com.rbs.rbstresource.component.Status;
import com.rbs.rbstresource.payload.request.BillingRBSData;
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
    private final BillingRepository billingDAO;
    private final StatusRepository statusDAO;

    @Autowired
    public BillingService(ClientRepository clientDAO, BillingRepository billingDAO, StatusRepository statusDAO){
        this.clientDAO = clientDAO;
        this.billingDAO = billingDAO;
        this.statusDAO = statusDAO;
    }

    public List<BillingData> getBillingList(String userId) {
        var client = clientDAO.findByUserId(userId);
        var billings = client.getBillings();

        if(billings != null) {
            return billings.stream().map(a -> new BillingData(a.getId(), a.getBillingName(), a.getComment(), a.getStatus().getStatusName()))
            .toList();
        } else {
            return new ArrayList<>();
        }
    }

    public void saveBill(BillingRBSData billing) throws SQLException{
        var client = clientDAO.findById(billing.getClientId());
        var status = statusDAO.findByStatusName(billing.getStatus());
        billingDAO.save(new Billing(billing.getBillingName(), billing.getComment(), status, client));
    }
}
