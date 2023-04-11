package com.rbs.rbstresource.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ABSAccountData {
    private String accountNumber;
    private String accountName;
    private float balance;
    private Boolean isCredit;
    private Date openDate;
    private Date statusTime;
    private float limitPerDay;
    private String userId;
    public String tariffName;
    public String status;
}
