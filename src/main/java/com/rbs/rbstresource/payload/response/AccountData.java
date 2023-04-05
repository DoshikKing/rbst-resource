package com.rbs.rbstresource.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class AccountData {

    private Long id;
    private String accountNumber;
    private Date statusTime;
    private float balance;
}
