package com.rbs.rbstresource.controller;

import com.rbs.rbstresource.service.AccountService;
import com.rbs.rbstresource.service.CardService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
public class InfoController {


    private final CardService cardService;

    private final AccountService accountService;

    @Autowired
    InfoController(AccountService accountService, CardService cardService){
        this.accountService = accountService;
        this.cardService = cardService;
    }

    @GetMapping("get/cards")
    @RolesAllowed("User")
    public ResponseEntity<Object> getCards(JwtAuthenticationToken authentication) {
        var userId = authentication.getTokenAttributes().get("sub").toString();
        return new ResponseEntity<>(cardService.getCardList(userId), HttpStatus.OK);
    }

    @GetMapping("get/accounts")
    @RolesAllowed("User")
    public ResponseEntity<Object> getAccounts(JwtAuthenticationToken authentication) {
        var userId = authentication.getTokenAttributes().get("sub").toString();
        return new ResponseEntity<>(accountService.getAccountList(userId), HttpStatus.OK);
    }

}
