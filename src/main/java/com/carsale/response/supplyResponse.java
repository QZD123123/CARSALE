package com.carsale.response;

import com.carsale.pojo.Product;
import com.carsale.pojo.Supplier;
import com.carsale.pojo.Warehouse;
import lombok.Data;

@Data
public class supplyResponse {
    private Integer id;
    private Integer quantity;
    private Supplier supplier;
    private Product product;
    private Warehouse warehouse;
}
