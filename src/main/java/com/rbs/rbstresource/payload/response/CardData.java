package com.rbs.rbstresource.payload.response;

import lombok.Data;

import java.sql.Date;

@Data
public class CardData {

    private Long id;
    private String code;
    private float summ;
    private Date statusTime;
    private String paySystem;
}
