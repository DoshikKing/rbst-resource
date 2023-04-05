package com.rbs.rbstresource.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class CardData {

    private Long id;
    private String code;
    private float amount;
    private Date statusTime;
    private String paySystem;
}
