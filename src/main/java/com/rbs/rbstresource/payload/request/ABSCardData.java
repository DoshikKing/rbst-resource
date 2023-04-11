package com.rbs.rbstresource.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ABSCardData {
    private String code;
    private float balance;
    private float limitPerDay;
    public String tariffName;
    private Date statusTime;
    public String status;
    public String accountNumber;
    public String userId;
    public String paySystemName;
}
