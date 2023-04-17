package com.rbs.rbstresource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.rbs.rbstresource.payload.response.AccountData;
import com.rbs.rbstresource.payload.response.BillingData;
import com.rbs.rbstresource.payload.response.TransactionData;
import com.rbs.rbstresource.service.AccountService;
import com.rbs.rbstresource.service.BillingService;
import com.rbs.rbstresource.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import com.rbs.rbstresource.payload.response.CardData;
import com.rbs.rbstresource.service.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class RbstResourceApplicationTests {

    @Mock
    CardService cardServiceMock;
    @Mock
    BillingService billingServiceMock;

    @Mock
    AccountService accountServiceMock;

    @Mock
    TransactionService transactionServiceMock;

    @Autowired
    CardService cardService;

    @Autowired
    BillingService billingService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Test
    public void testCardService() {
        List<CardData> dataMock = new ArrayList<>();
        dataMock.add(new CardData(Long.valueOf("1"), Long.valueOf("1"), "2003********0089", 440, "active", "MIR"));
        log.info("Mock card list: {}", dataMock);

        when(cardServiceMock.getCardList("33b51c0a-27e6-4386-bdf3-46dbdabbfe9c")).thenReturn(dataMock);

        List<CardData> dataReal = cardService.getCardList("33b51c0a-27e6-4386-bdf3-46dbdabbfe9c");
        log.info("Real card list: {}", dataReal);
        Assertions.assertEquals(dataMock, dataReal);
    }

    @Test
    public void testBillingService() {
        List<BillingData> dataMock = new ArrayList<>();
        dataMock.add(new BillingData(Long.valueOf("1"), "ЖКХ", "Оплатите ЖКХ", "not payed", (float)2538.5));
        log.info("Mock billing list: {}", dataMock);

        when(billingServiceMock.getBillingList("33b51c0a-27e6-4386-bdf3-46dbdabbfe9c")).thenReturn(dataMock);

        List<BillingData> dataReal = billingService.getBillingList("33b51c0a-27e6-4386-bdf3-46dbdabbfe9c");
        log.info("Real billing list: {}", dataReal);
        Assertions.assertEquals(dataMock, dataReal);
    }

    @Test
    public void testAccountService() {
        List<AccountData> dataMock = new ArrayList<>();
        dataMock.add(new AccountData(Long.valueOf("2"), "5689************0090", "active", 320));
        dataMock.add(new AccountData(Long.valueOf("1"), "4689************0007", "active", 440));
        log.info("Mock account list: {}", dataMock);

        when(accountServiceMock.getAccountList("33b51c0a-27e6-4386-bdf3-46dbdabbfe9c")).thenReturn(dataMock);

        List<AccountData> dataReal = accountService.getAccountList("33b51c0a-27e6-4386-bdf3-46dbdabbfe9c");
        log.info("Real account list: {}", dataReal);
        Assertions.assertEquals(dataMock, dataReal);
    }

    @Test
    public void testTransactionService() {
        List<TransactionData> dataMockCard = new ArrayList<>();
        dataMockCard.add(new TransactionData("test", 100, false, Date.valueOf("2023-04-12"), "2003********0089"));
        dataMockCard.add(new TransactionData("test", 10, false, Date.valueOf("2023-04-12"), "2003********0089"));
        dataMockCard.add(new TransactionData("testing", 50, false, Date.valueOf("2023-04-12"), "2003********0089"));
        dataMockCard.add(new TransactionData("from account to card", 1, false, Date.valueOf("2023-04-15"), "2003********0089"));
        log.info("Mock card transaction list: {}", dataMockCard);

        when(transactionServiceMock.getCardTransactionsList("33b51c0a-27e6-4386-bdf3-46dbdabbfe9c", Long.valueOf("1"))).thenReturn(dataMockCard);

        List<TransactionData> dataReal = transactionService.getCardTransactionsList("33b51c0a-27e6-4386-bdf3-46dbdabbfe9c", Long.valueOf("1"));
        log.info("Real card transaction list: {}", dataReal);
        Assertions.assertEquals(dataMockCard, dataReal);
    }

}
