package com.rbs.rbstresource.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ABSClientData {
    private Long id;
    private String clientName;
    private String userId;
    public String status;
}
