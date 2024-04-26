package com.carsale.mapper;

import com.carsale.pojo.Product;
import com.carsale.pojo.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ASUS
* @description 针对表【warehouse】的数据库操作Mapper
* @createDate 2024-04-09 14:59:47
* @Entity com.carsale.pojo.Warehouse
*/
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    List<Warehouse> selectWarehouseByProductId(@Param("productId") Integer productId);

    List<Product> selectWarehouseAndInventoryById(@Param("warehouseId") Integer warehouseId);

    Warehouse selectWarehouseById(@Param("warehouseId") Integer warehouseId);

    List<Warehouse> getAllWarehouse();

    String selectLocationById(@Param("warehouseId") Integer warehouseId);
}




