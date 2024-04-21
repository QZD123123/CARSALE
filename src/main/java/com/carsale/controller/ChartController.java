package com.carsale.controller;

import com.carsale.service.ChartService;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("chart")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/total/income")
    public Result income(){
        Result result = chartService.income();
        return result;
    }

    @GetMapping("/total/sales")
    public Result sales(){
        Result result = chartService.sales();
        return result;
    }

    @GetMapping("/total/warehouses")
    public Result warehouses(){
        Result result = chartService.warehouses();
        return result;
    }

    @GetMapping("/total/users")
    public Result users(){
        Result result = chartService.users();
        return result;
    }

    @GetMapping("/ranking/user")
    public Result user(){
        Result result = chartService.user();
        return result;
    }

    @GetMapping("/ranking/car")
    public Result car(){
        Result result = chartService.car();
        return result;
    }

}
