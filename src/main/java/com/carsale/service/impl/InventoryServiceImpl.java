package com.carsale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carsale.pojo.Inventory;
import com.carsale.service.InventoryService;
import com.carsale.mapper.InventoryMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【inventory】的数据库操作Service实现
* @createDate 2024-04-09 14:59:47
*/
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory>
    implements InventoryService{

}




