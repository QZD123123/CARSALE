package com.carsale.mapper;

import com.carsale.pojo.Supply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author ASUS
* @description 针对表【supply】的数据库操作Mapper
* @createDate 2024-04-09 14:59:47
* @Entity com.carsale.pojo.Supply
*/
public interface SupplyMapper extends BaseMapper<Supply> {

    int deleteSupplyByProductId(@Param("productId") Integer productId);

    void deleteSupplyBySupplierId(@Param("supplierId") Integer supplierId);

    void deleteSupplyByWarehouseId(@Param("warehouseId") Integer warehouseId);
}




