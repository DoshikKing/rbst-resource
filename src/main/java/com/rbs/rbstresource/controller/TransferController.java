package com.rbs.rbstresource.controller;

import com.rbs.rbstresource.exception.NotEnoughMoneyException;
import com.rbs.rbstresource.payload.request.TransferData;
import com.rbs.rbstresource.service.TransactionService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
public class TransferController {
    private final TransactionService transactionService;
    private String userId;

    @Autowired
    TransferController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer/from/account/to/card")
    @RolesAllowed("ROLE_User")
    public ResponseEntity<Object> makeTransferFromAccountToCard(JwtAuthenticationToken authentication, @RequestBody TransferData transferData) {
        this.userId = authentication.getTokenAttributes().get("sub").toString();
        try {
            transactionService.makeTransferFromAccountToCard(transferData.getDebit(), transferData.getCredit(), transferData.getAmount(), transferData.getDebitBank(), transferData.getCreditBank(), transferData.getComment(), userId);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (NotEnoughMoneyException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Недостаточно средств для перевода!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Ошибка перевода!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transfer/from/account/to/account")
    @RolesAllowed("ROLE_User")
    public ResponseEntity<Object> makeTransferFromAccountToAccount(JwtAuthenticationToken authentication, @RequestBody TransferData transferData) {
        this.userId = authentication.getTokenAttributes().get("sub").toString();
        try {
            transactionService.makeTransferFromAccountToAccount(transferData.getDebit(), transferData.getCredit(), transferData.getAmount(), transferData.getDebitBank(), transferData.getCreditBank(), transferData.getComment(), userId);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (NotEnoughMoneyException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Недостаточно средств для перевода!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Ошибка перевода!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transfer/from/card/to/card")
    @RolesAllowed("ROLE_User")
    public ResponseEntity<Object> makeTransferFromCardToCard(JwtAuthenticationToken authentication, @RequestBody TransferData transferData) {
        this.userId = authentication.getTokenAttributes().get("sub").toString();
        try {
            transactionService.makeTransferFromCardToCard(transferData.getDebit(), transferData.getCredit(), transferData.getAmount(), transferData.getDebitBank(), transferData.getCreditBank(), transferData.getComment(), userId);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (NotEnoughMoneyException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Недостаточно средств для перевода!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Ошибка перевода!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transfer/from/card/to/account")
    @RolesAllowed("ROLE_User")
    public ResponseEntity<Object> makeTransferFromCardToAccount(JwtAuthenticationToken authentication, @RequestBody TransferData transferData) {
        this.userId = authentication.getTokenAttributes().get("sub").toString();
        try {
            transactionService.makeTransferFromCardToAccount(transferData.getDebit(), transferData.getCredit(), transferData.getAmount(), transferData.getDebitBank(), transferData.getCreditBank(), transferData.getComment(), userId);
            return new ResponseEntity<>("Done.", HttpStatus.OK);
        } catch (NotEnoughMoneyException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Недостаточно средств для перевода!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (SQLException e) {
            log.error(e.toString());
            return new ResponseEntity<>("Ошибка перевода!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
