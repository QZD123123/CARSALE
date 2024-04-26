package com.carsale.service;

import com.carsale.pojo.Supply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.carsale.utils.Result;

/**
* @author ASUS
* @description 针对表【supply】的数据库操作Service
* @createDate 2024-04-09 14:59:47
*/
public interface SupplyService extends IService<Supply> {

    Result createSupply(Supply supply);

    Result supplyPageSelect(Integer page, Integer pageSize);

    Result selectSupplyById(Integer id);

    Result UpdateSupplyById(Integer id, Supply supply);

    Result deleteSupplyById(Integer id);
}
