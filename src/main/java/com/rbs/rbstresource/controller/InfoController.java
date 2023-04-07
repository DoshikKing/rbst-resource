package com.rbs.rbstresource.controller;

import com.rbs.rbstresource.payload.response.CardData;
import com.rbs.rbstresource.service.CardService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class InfoController {


    private final CardService cardService;

    @Autowired
    InfoController(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping("/list/cards")
    @RolesAllowed({"User"})
    public ResponseEntity<List<CardData>> getSimplePage(){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
