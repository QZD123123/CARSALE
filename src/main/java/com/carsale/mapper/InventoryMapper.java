package com.carsale.mapper;

import com.carsale.pojo.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author ASUS
* @description 针对表【inventory】的数据库操作Mapper
* @createDate 2024-04-09 14:59:47
* @Entity com.carsale.pojo.Inventory
*/
public interface InventoryMapper extends BaseMapper<Inventory> {

    int deleteInventoryByProductId(@Param("productId") Integer productId);

    void updateInventoryBySupplierId(@Param("supplierId") Integer supplierId);

    void deleteInventoryByWarehouseId(@Param("warehouseId")Integer warehouseId);
}




