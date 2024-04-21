package com.carsale.service;

import com.carsale.pojo.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.carsale.utils.Result;

/**
* @author ASUS
* @description 针对表【warehouse】的数据库操作Service
* @createDate 2024-04-09 14:59:47
*/
public interface WarehouseService extends IService<Warehouse> {

    Result createWarehouse(String location);

    Result warehousePageSelect(Integer page, Integer pageSize);

    Result selectWarehouseById(Integer id);

    Result UpdateWarehouseById(Integer id, String location);

    Result deleteWarehouseById(Integer id);

    Result selectWarehouseAndInventoryById(Integer id);
}
