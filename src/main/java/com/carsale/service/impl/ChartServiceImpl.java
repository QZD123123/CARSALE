package com.carsale.service.impl;

import com.carsale.mapper.OrderMapper;
import com.carsale.pojo.User;
import com.carsale.service.ChartService;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.time.Year;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Result income() {
        Double income = orderMapper.selectIncome();

        Map data = new LinkedHashMap();
        data.put("tip","成功获取营业额");
        data.put("total",income);

        return Result.ok(data);
    }

    @Override
    public Result sales() {
        Integer sales = orderMapper.sales();

        Map data = new LinkedHashMap();
        data.put("tip","成功获取成交量");
        data.put("total",sales);

        return Result.ok(data);
    }

    @Override
    public Result warehouses() {
        Integer warehouse = orderMapper.warehouses();

        Map data = new LinkedHashMap();
        data.put("tip","成功获取仓库数");
        data.put("total",warehouse);

        return Result.ok(data);
    }

    @Override
    public Result users() {
        Integer users = orderMapper.users();

        Map data = new LinkedHashMap();
        data.put("tip","成功获取员工数");
        data.put("total",users);

        return Result.ok(data);
    }

    @Override
    public Result user() {
        List<Integer> userId = orderMapper.selectTopUserId();
        List<String> userName = orderMapper.selectTopUserName();

        int currentYear = Year.now().getValue();
        List<Integer> years = new ArrayList<>();
        for (int i = 4; i >= 0; i--) {
            years.add(currentYear - i);
        }

        List<List<Double>> source = new ArrayList<>();
        for (Integer id : userId) {
            List<Double> salesByYear = new ArrayList<>();
            for (Integer year : years) {
                Double sales = orderMapper.selectSalesByYearAndUserId(year, id);
                salesByYear.add(sales != null ? sales : 0); // 如果销售额为null，则默认为0
            }
            source.add(salesByYear);
        }

        Map data = new LinkedHashMap();
        data.put("tip","成功获取员工销售额排行榜");
        data.put("idList",userId);
        data.put("xList",userName);
        data.put("yList",years);
        data.put("source", source);

        return Result.ok(data);
    }

    @Override
    public Result car() {
        List<Integer> carId = orderMapper.selectTopCarId();
        List<String> carName = orderMapper.selectTopCarName();

        int currentYear = Year.now().getValue();
        List<Integer> years = new ArrayList<>();
        for (int i = 4; i >= 0; i--) {
            years.add(currentYear - i);
        }

        List<List<Integer>> source = new ArrayList<>();
        for (Integer id : carId) {
            List<Integer> salesByYear = new ArrayList<>();
            for (Integer year : years) {
                Integer sales = orderMapper.selectSalesByYearAndCarId(year, id);
                salesByYear.add(sales != null ? sales : 0); // 如果销售额为null，则默认为0
            }
            source.add(salesByYear);
        }

        Map data = new LinkedHashMap();
        data.put("tip","成功获取汽车热销榜");
        data.put("idList",carId);
        data.put("xList",carName);
        data.put("yList",years);
        data.put("source", source);

        return Result.ok(data);
    }
}
