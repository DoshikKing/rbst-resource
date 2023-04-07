package com.rbs.rbstresource.service;

import com.rbs.rbstresource.payload.response.AccountData;
import com.rbs.rbstresource.service.repository.AccountRepository;
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
public class AccountService {
    private final AccountRepository accountDAO;
    private final StatusRepository statusDAO;
    private final ClientRepository clientDAO;

    @Autowired
    public AccountService(AccountRepository accountDAO, StatusRepository statusDAO, ClientRepository clientDAO){
        this.clientDAO = clientDAO;
        this.accountDAO = accountDAO;
        this.statusDAO = statusDAO;
    }

    public List<AccountData> getAccountList(Long userId) {
        var client = clientDAO.findByUserId(userId);
        var accounts = client.getAccounts();
        if(accounts != null) {
            return accounts.stream()
                    .map(a -> new AccountData(a.getId(), a.getAccountNumber(), a.getStatus().getStatusName(), a.getBalance()))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }

    public void updateAccountStatus(Long id, String statusName) throws SQLException {
        accountDAO.updateAccountSetStatusForId(statusDAO.findByStatusName(statusName).getId(), id);
    }
}
