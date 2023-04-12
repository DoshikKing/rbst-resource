package com.rbs.rbstresource.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ABSBillingData {
    private String billingName;
    private String comment;

    public String status;
    public Long clientId;
}
