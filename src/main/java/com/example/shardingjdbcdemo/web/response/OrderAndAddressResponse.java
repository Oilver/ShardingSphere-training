package com.example.shardingjdbcdemo.web.response;

import lombok.Data;

@Data
public class OrderAndAddressResponse {

    private String addressId;

    private String orderId;

    private String addressName;
}
