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
        List<String> carModel = orderMapper.selectTopCarModel();

        List<String> fullCarList = new ArrayList<>();
        for (int i = 0; i < carName.size(); i++) {
            fullCarList.add(carName.get(i) + "-" + carModel.get(i));
        }

        int currentYear = Year.now().getValue();
        List<Integer> years = new ArrayList<>();
        for (int i = 9; i >= 0; i--) {
            years.add(currentYear - i);
        }

        List<List<Object>> source = new ArrayList<>();
        List<Object> header = new ArrayList<>();
        header.add("sales"); // 添加销售额列
        header.add("product"); // 添加汽车名称列
        header.add("year"); // 添加年份列
        source.add(header); // 添加表头selectSalesByYearAndCarId

        // 获取每个年份每个产品的销量数据，并加入source
        for (int j = 0; j < years.size(); j++) {
            for (int i = 0; i < carId.size(); i++) {
                // 根据产品ID和年份查询销量数据，这里需要根据实际情况调整方法名和参数
                int sales = orderMapper.selectSalesByYearAndCarId( years.get(j),carId.get(i));
                // 创建包含销量、产品名称和年份的子列表
                List<Object> saleData = new ArrayList<>();
                saleData.add(sales);
                saleData.add(carName.get(i)+"-"+carModel.get(i));
                saleData.add(years.get(j));
                // 将销量数据加入source
                source.add(saleData);
            }
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("tip", "成功获取汽车热销榜");
        data.put("idList", carId);
        data.put("xList", fullCarList);
        data.put("yList", years);
        data.put("source", source);

        return Result.ok(data);
    }

}
