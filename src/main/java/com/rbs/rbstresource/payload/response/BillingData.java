package com.rbs.rbstresource.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillingData {
    private Long id;
    private String billingName;
    private String comment;

    public String status;
    private float amount;
}
