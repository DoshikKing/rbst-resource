package com.rbs.rbstresource.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardData {
    private Long id;
    private Long accountId;
    private String code;
    private float balance;
    private String status;
    private String paySystem;
}
