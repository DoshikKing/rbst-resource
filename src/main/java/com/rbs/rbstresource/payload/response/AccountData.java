package com.rbs.rbstresource.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountData {
    private Long id;
    private String accountNumber;
    private String status;
    private float balance;
}
