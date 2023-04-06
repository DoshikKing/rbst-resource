package com.rbs.rbstresource.service;

import com.rbs.rbstresource.payload.response.AccountData;
import com.rbs.rbstresource.service.ORMRepository.AccountRepository;
import com.rbs.rbstresource.service.ORMRepository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = {SQLException.class})
public class AccountService {
    private final AccountRepository accountDAO;
    private final StatusRepository statusDAO;

    @Autowired
    public AccountService(AccountRepository accountDAO, StatusRepository statusDAO){
        this.accountDAO = accountDAO;
        this.statusDAO = statusDAO;
    }

    public List<AccountData> getAccountList(Long id) throws SQLException {
        return accountDAO.findAllByUserId(id).stream()
                .map(a -> new AccountData(a.getId(), a.getAccountNumber(), a.getStatus().getStatusName(), a.getBalance()))
                .toList();
    }

    public void updateAccountStatus(Long id, String statusName) throws SQLException {
        accountDAO.updateAccountSetStatusForId(statusDAO.findByStatusName(statusName).getId(), id);
    }
}
