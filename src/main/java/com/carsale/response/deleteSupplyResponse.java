package com.carsale.response;

import com.carsale.pojo.Supply;
import com.carsale.pojo.User;
import lombok.Data;

import java.util.Date;

@Data
public class deleteSupplyResponse {
    private Integer id;

    private Integer quantity;

    private Integer supplierId;

    private Integer productId;

    private Integer warehouseId;

    private Date createTime;


    public deleteSupplyResponse(Supply supply) {
        this.id = supply.getId();
        this.quantity = supply.getQuantity();
        this.supplierId = supply.getSupplierid();
        this.productId = supply.getProductid();
        this.warehouseId = supply.getWarehouseid();
    }
}
