package com.rbs.rbstresource.payload.request;

import lombok.Data;

@Data
public class TransferData {
    String debit;
    String credit;
    float amount;
    String debitBank;
    String creditBank;
    String comment;
}
