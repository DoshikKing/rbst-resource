package com.rbs.rbstresource.controller;

import com.rbs.rbstresource.service.BillingService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BillingController {
    private final BillingService billingService;

    @Autowired
    BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping("get/billings")
    @RolesAllowed("ROLE_User")
    public ResponseEntity<Object> getBillings(JwtAuthenticationToken authentication) {
        var userId = authentication.getTokenAttributes().get("sub").toString();
        log.info("Retrieving info about billings for user : {}", userId);
        return new ResponseEntity<>(billingService.getBillingList(userId), HttpStatus.OK);
    }
}
