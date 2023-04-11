package com.rbs.rbstresource.controller;

import com.rbs.rbstresource.payload.request.ABSAccountData;
import com.rbs.rbstresource.payload.request.ABSBillingData;
import com.rbs.rbstresource.payload.request.ABSCardData;
import com.rbs.rbstresource.payload.request.ABSClientData;
import com.rbs.rbstresource.service.AccountService;
import com.rbs.rbstresource.service.BillingService;
import com.rbs.rbstresource.service.CardService;
import com.rbs.rbstresource.service.ClientService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
public class ABSController {
    private final ClientService clientService;
    private final BillingService billingService;
    private final AccountService accountService;
    private final CardService cardService;

    @Autowired
    ABSController(ClientService clientService, BillingService billingService, AccountService accountService, CardService cardService) {
        this.clientService = clientService;
        this.billingService = billingService;
        this.accountService = accountService;
        this.cardService = cardService;
    }

    @PostMapping("update/client")
    @RolesAllowed("ROLE_ABS")
    public ResponseEntity<Object> updateClient(@Valid @RequestBody ABSClientData absClientData) {
        try {
            clientService.updateClientById(absClientData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't update client! No such client!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("insert/client")
    @RolesAllowed("ROLE_ABS")
    public ResponseEntity<Object> insertClient(@Valid @RequestBody ABSClientData absClientData) {
        try {
            clientService.saveClient(absClientData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't insert client!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("update/bill")
    @RolesAllowed("ROLE_ABS")
    public ResponseEntity<Object> updateBill(@Valid @RequestBody ABSBillingData absBillingData) {
        try {
            billingService.updateBill(absBillingData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't update bill! No such bill!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("insert/bill")
    @RolesAllowed("ROLE_ABS")
    public ResponseEntity<Object> insertBill(@Valid @RequestBody ABSBillingData absBillingData) {
        try {
            billingService.saveBill(absBillingData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't insert bill!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("update/account")
    @RolesAllowed("ROLE_ABS")
    public ResponseEntity<Object> updateAccount(@Valid @RequestBody ABSAccountData absAccountData) {
        try {
            accountService.updateAccount(absAccountData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't update account! No such account!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("insert/account")
    @RolesAllowed("ROLE_ABS")
    public ResponseEntity<Object> insertAccount(@Valid @RequestBody ABSAccountData absAccountData) {
        try {
            accountService.saveAccount(absAccountData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't insert account!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("update/account")
    @RolesAllowed("ROLE_ABS")
    public ResponseEntity<Object> updateCard(@Valid @RequestBody ABSCardData absCardData) {
        try {
            cardService.updateCard(absCardData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't update card! No such card!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("insert/account")
    @RolesAllowed("ROLE_ABS")
    public ResponseEntity<Object> insertCard(@Valid @RequestBody ABSCardData absCardData) {
        try {
            cardService.saveCard(absCardData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't insert card!", HttpStatus.BAD_REQUEST);
        }
    }
}
