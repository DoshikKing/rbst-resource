package com.rbs.rbstresource.controller;

import com.rbs.rbstresource.payload.response.CardData;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class InfoController {

    @GetMapping("/list/cards")
    @RolesAllowed({"User"})
    public ResponseEntity<List<CardData>> getSimplePage(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
