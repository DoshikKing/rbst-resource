package com.rbs.rbstresource.controller;

import com.rbs.rbstresource.payload.request.AbstractData;
import com.rbs.rbstresource.service.TransactionService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AbstractController {
    private final TransactionService transactionService;
    private String userId;

    @Autowired
    AbstractController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("get/card/abstract")
    @RolesAllowed("ROLE_User")
    public ResponseEntity<Object> getCardAbstractByCardCode(JwtAuthenticationToken authentication, @Valid @RequestBody AbstractData abstractData) {
        this.userId = authentication.getTokenAttributes().get("sub").toString();
        return new ResponseEntity<>(transactionService.getCardTransactionsList(userId, abstractData.getCode()), HttpStatus.OK);
    }

    @PostMapping("get/account/abstract")
    @RolesAllowed("ROLE_User")
    public ResponseEntity<Object> getAccountAbstractByAccountNumber(JwtAuthenticationToken authentication, @Valid @RequestBody AbstractData abstractData) {
        this.userId = authentication.getTokenAttributes().get("sub").toString();
        return new ResponseEntity<>(transactionService.getAccountTransactionsList(userId, abstractData.getCode()), HttpStatus.OK);
    }
}
