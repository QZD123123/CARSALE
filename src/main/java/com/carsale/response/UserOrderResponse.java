package com.carsale.response;

import lombok.Data;

@Data
public class UserOrderResponse {
    private String id;
    private String name;
    private String model;
    private String location;
    private String createtime;
}
