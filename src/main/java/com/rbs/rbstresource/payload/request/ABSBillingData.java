package com.rbs.rbstresource.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ABSBillingData {
    private Long id;
    private String billingName;
    private String comment;

    public String status;
    public Long clientId;
}
