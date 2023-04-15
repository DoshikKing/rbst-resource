package com.rbs.rbstresource.service;

import com.rbs.rbstresource.component.Billing;
import com.rbs.rbstresource.payload.request.ABSBillingData;
import com.rbs.rbstresource.payload.response.BillingData;
import com.rbs.rbstresource.service.repository.BillingRepository;
import com.rbs.rbstresource.service.repository.ClientRepository;
import com.rbs.rbstresource.service.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            return billings.stream().map(bill -> new BillingData(bill.getId(), bill.getBillingName(), bill.getComment(), bill.getStatus().getStatusName(), bill.getAmount()))
            .toList();
        } else {
            return new ArrayList<>();
        }
    }

    public void updateBill(ABSBillingData new_bill_data) throws SQLException {
        var client = clientDAO.getReferenceById(new_bill_data.getClientId());
        var status = statusDAO.findByStatusName(new_bill_data.getStatus());
        var bill = billingDAO.findByClient(client);
        bill.setBillingName(new_bill_data.getBillingName());
        bill.setComment(new_bill_data.getComment());
        bill.setClient(client);
        bill.setStatus(status);
        bill.setAmount(new_bill_data.getAmount());
        billingDAO.save(bill);
    }

    public void saveBill(ABSBillingData bill) throws SQLException {
        var client = clientDAO.getReferenceById(bill.getClientId());
        var status = statusDAO.findByStatusName(bill.getStatus());
        billingDAO.save(new Billing(bill.getBillingName(), bill.getComment(), status, Optional.of(client), bill.getAmount()));
    }
}
