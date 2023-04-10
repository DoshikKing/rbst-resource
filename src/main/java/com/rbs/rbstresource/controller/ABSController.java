package com.rbs.rbstresource.controller;

import com.rbs.rbstresource.payload.request.ABSBillingData;
import com.rbs.rbstresource.payload.request.ABSClientData;
import com.rbs.rbstresource.service.BillingService;
import com.rbs.rbstresource.service.ClientService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
public class ABSController {

    private final ClientService clientService;
    private final BillingService billingService;

    @Autowired
    ABSController(ClientService clientService, BillingService billingService) {
        this.clientService = clientService;
        this.billingService = billingService;
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

    @PostMapping("insert/client")
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
            billingService.saveBill(absBillingData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't update bill! No such bill!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("insert/bill")
    @RolesAllowed("ROLE_ABS")
    public ResponseEntity<Object> insertBill(@Valid @RequestBody ABSBillingData absBillingData) {
        try {
            billingService.updateBill(absBillingData);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Can't insert client!", HttpStatus.BAD_REQUEST);
        }
    }
}
