package com.rbs.rbstresource.payload.request;

import lombok.Data;

@Data
public class TransferData {
    Long debit;
    Long credit;
    float amount;
    String debitBank;
    String creditBank;
    String comment;
}
