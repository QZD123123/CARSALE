package com.carsale.mapper;

import com.carsale.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carsale.utils.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ASUS
* @description 针对表【order】的数据库操作Mapper
* @createDate 2024-04-09 14:59:47
* @Entity com.carsale.pojo.Order
*/
public interface OrderMapper extends BaseMapper<Order> {

    Integer deleteOrderByProductId(@Param("productId") Integer productId);

    void deleteOrderByWarehouseId(@Param("warehouseId") Integer warehouseId);

    Long selectOrderCount();
    List<Order> selectOrderPage(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
}




