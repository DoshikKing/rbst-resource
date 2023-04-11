package com.rbs.rbstresource.service;

import com.rbs.rbstresource.component.Account;
import com.rbs.rbstresource.payload.request.ABSAccountData;
import com.rbs.rbstresource.payload.response.AccountData;
import com.rbs.rbstresource.service.repository.AccountRepository;
import com.rbs.rbstresource.service.repository.ClientRepository;
import com.rbs.rbstresource.service.repository.StatusRepository;
import com.rbs.rbstresource.service.repository.TariffRepository;
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
    private final ClientRepository clientDAO;
    private final AccountRepository accountDAO;
    private final StatusRepository statusDAO;
    private final TariffRepository tariffDAO;

    @Autowired
    public AccountService(AccountRepository accountDAO, StatusRepository statusDAO, ClientRepository clientDAO, TariffRepository tariffDAO){
        this.clientDAO = clientDAO;
        this.accountDAO = accountDAO;
        this.statusDAO = statusDAO;
        this.tariffDAO = tariffDAO;
    }

    public List<AccountData> getAccountList(String userId) {
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

    public void updateAccount(ABSAccountData absAccountData) throws SQLException {
        var client = clientDAO.findByUserId(absAccountData.getUserId());
        var account = accountDAO.findByClientAndAccountNumber(client, absAccountData.getAccountNumber());

        account.setAccountNumber(absAccountData.getAccountNumber());
        account.setAccountName(absAccountData.getAccountName());
        account.setBalance(absAccountData.getBalance());
        account.setIsCredit(absAccountData.getIsCredit());
        account.setLimitPerDay(absAccountData.getLimitPerDay());
        account.setOpenDate(absAccountData.getOpenDate());
        account.setStatus(statusDAO.findByStatusName(absAccountData.getStatus()));
        account.setStatusTime(absAccountData.getStatusTime());
        account.setTariff(tariffDAO.findByTariffName(absAccountData.getTariffName()));
        account.setClient(client);

        accountDAO.save(account);
    }

    public void saveAccount(ABSAccountData absAccountData) throws SQLException {
        var client = clientDAO.findByUserId(absAccountData.getUserId());
        var newAccount = new Account();

        newAccount.setAccountNumber(absAccountData.getAccountNumber());
        newAccount.setAccountName(absAccountData.getAccountName());
        newAccount.setBalance(absAccountData.getBalance());
        newAccount.setIsCredit(absAccountData.getIsCredit());
        newAccount.setLimitPerDay(absAccountData.getLimitPerDay());
        newAccount.setOpenDate(absAccountData.getOpenDate());
        newAccount.setStatus(statusDAO.findByStatusName(absAccountData.getStatus()));
        newAccount.setStatusTime(absAccountData.getStatusTime());
        newAccount.setTariff(tariffDAO.findByTariffName(absAccountData.getTariffName()));
        newAccount.setClient(client);
        newAccount.setCards(null);
        newAccount.setTransactions(null);

        accountDAO.save(newAccount);
    }

    public void updateAccountStatus(Long id, String statusName) throws SQLException {
        accountDAO.updateAccountSetStatusForId(statusDAO.findByStatusName(statusName).getId(), id);
    }
}
