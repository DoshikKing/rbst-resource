package com.rbs.rbstresource.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardData {
    private Long id;
    private String code;
    private float amount;
    private String status;
    private String paySystem;
}
