package com.carsale.controller;

import com.carsale.mapper.OrderMapper;
import com.carsale.pojo.Order;
import com.carsale.pojo.Supply;
import com.carsale.service.OrderService;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @GetMapping("")
    public Result orderPageSelect(Integer page, Integer pageSize){
        Result result = orderService.orderPageSelect(page,pageSize);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @GetMapping("{id}")
    public Result selectOrderById(@PathVariable Integer id){
        Result result = orderService.selectOrderById(id);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @PatchMapping("{id}")
    public Result UpdateOrderById(@PathVariable Integer id,@RequestBody Integer productId, Integer userId, Integer warehouseId){
        Result result = orderService.UpdateOrderById(id,productId,userId,warehouseId);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @PostMapping("")
    public Result createOrder(@RequestBody Integer productId, Integer userId, Integer warehouseId){
        Result result = orderService.createOrder(productId,userId,warehouseId);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @DeleteMapping("{id}")
    public Result deleteOrderById(@PathVariable Integer id){
        Result result = orderService.deleteOrderById(id);
        return result;
    }


}
