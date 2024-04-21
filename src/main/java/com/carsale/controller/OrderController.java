package com.carsale.controller;

import com.carsale.mapper.OrderMapper;
import com.carsale.service.OrderService;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
