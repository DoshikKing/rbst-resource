package com.rbs.rbstresource.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ABSClientData {
    private String clientName;
    private String userId;
    public String status;
}
