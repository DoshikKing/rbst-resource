package com.rbs.rbstresource.controller;

import com.rbs.rbstresource.payload.response.CardData;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class RESTController {

    @GetMapping("/list/cards")
    @RolesAllowed({"User"})
    public ResponseEntity<List<CardData>> getSimplePage(){
        List<CardData> cards = new ArrayList<>();

        CardData cardData1 = new CardData();

        cardData1.setCode("12341234123");
        cardData1.setId(1L);
        cardData1.setSumm(321321);
        cardData1.setPaySystem("MIR");
        cardData1.setStatusTime(new Date(2023, 05, 12));

        CardData cardData2 = new CardData();

        cardData2.setCode("43746327643");
        cardData2.setId(2L);
        cardData2.setSumm(75847);
        cardData2.setPaySystem("VISA");
        cardData2.setStatusTime(new Date(2023, 06, 14));

        cards.add(cardData1);
        cards.add(cardData2);
        log.info("Sending info to client {}", cards);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

}
