package com.rbs.rbstresource.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferData {
    String debit;
    String credit;
    float amount;
    String debitBank;
    String creditBank;
    String comment;
}
