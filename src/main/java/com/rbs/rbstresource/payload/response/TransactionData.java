package com.rbs.rbstresource.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class TransactionData {
    private String comment;
    private float amount;
    private Boolean isDebit;
    private Date transactionTime;
    private String code;
}
