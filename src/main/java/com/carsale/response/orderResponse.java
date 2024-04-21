package com.carsale.response;

import com.carsale.pojo.Product;
import com.carsale.pojo.User;
import com.carsale.pojo.Warehouse;
import lombok.Data;

@Data
public class orderResponse {
    private Integer id;
    private Product product;
    private autoLoginResponse user;
    private Warehouse warehouse;
}
