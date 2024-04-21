package com.carsale.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carsale.mapper.ProductMapper;
import com.carsale.mapper.UserMapper;
import com.carsale.mapper.WarehouseMapper;
import com.carsale.pojo.Order;
import com.carsale.pojo.Supplier;
import com.carsale.response.autoLoginResponse;
import com.carsale.response.orderResponse;
import com.carsale.response.supplyResponse;
import com.carsale.service.OrderService;
import com.carsale.mapper.OrderMapper;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @author ASUS
* @description 针对表【order】的数据库操作Service实现
* @createDate 2024-04-09 14:59:47
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public Result orderPageSelect(Integer page, Integer pageSize) {
        // 获取订单总数
        Long count = orderMapper.selectOrderCount();

        // 分页查询订单列表
        List<Order> records = orderMapper.selectOrderPage((page - 1) * pageSize, pageSize);

        List<orderResponse> list = new ArrayList<>();
        for (Order record : records) {
            orderResponse orderResponse = new orderResponse();
            orderResponse.setId(record.getId());
            orderResponse.setProduct(productMapper.selectProductById(record.getProductid()));
            orderResponse.setUser(new autoLoginResponse(userMapper.selectById(record.getUserid())));
            orderResponse.setWarehouse(warehouseMapper.selectWarehouseById(record.getWarehouseid()));


            list.add(orderResponse);
        }

        Map data = new LinkedHashMap();
        data.put("tip","成功获取第"+page+"页"+pageSize+"共条数据");
        data.put("page",page);
        data.put("count",pageSize);
        data.put("pageTotal",(int)Math.ceil(count/pageSize));
        data.put("orderTotal",count);
        data.put("orderList",list);

        return Result.ok(data);
    }

}




