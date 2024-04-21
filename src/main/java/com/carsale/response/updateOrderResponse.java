package com.carsale.response;

import com.carsale.pojo.Order;
import com.carsale.pojo.Supply;
import lombok.Data;

import java.util.Date;

@Data
public class updateOrderResponse {
    private Integer id;

    private Integer productId;

    private Integer userId;

    private Integer warehouseId;

    private Date createTime;

    public updateOrderResponse(Order order) {
        this.id = order.getId();
        this.productId = order.getProductid();
        this.userId = order.getUserid();
        this.warehouseId = order.getWarehouseid();
    }
}
