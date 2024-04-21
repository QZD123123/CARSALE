package com.carsale.service;

import com.carsale.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.carsale.utils.Result;

/**
* @author ASUS
* @description 针对表【order】的数据库操作Service
* @createDate 2024-04-09 14:59:47
*/
public interface OrderService extends IService<Order> {

    Result orderPageSelect(Integer page, Integer pageSize);
}
