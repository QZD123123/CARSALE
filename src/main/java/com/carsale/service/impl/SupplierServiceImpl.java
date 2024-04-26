package com.carsale.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carsale.mapper.InventoryMapper;
import com.carsale.mapper.SupplyMapper;
import com.carsale.pojo.Product;
import com.carsale.pojo.Supplier;
import com.carsale.response.ProductResponse;
import com.carsale.service.SupplierService;
import com.carsale.mapper.SupplierMapper;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author ASUS
* @description 针对表【supplier】的数据库操作Service实现
* @createDate 2024-04-09 14:59:47
*/
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier>
    implements SupplierService{

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private SupplyMapper supplyMapper;

    @Override
    public Result createSupplier(Supplier supplier) {

        int i = supplierMapper.insert(supplier);

        Map data = new LinkedHashMap();
        data.put("tip","成功创建供应商");
        data.put("supplier",supplier);

        return Result.ok(data);
    }

    @Override
    public Result supplierPageSelect(Integer page, Integer pageSize) {
        Page<Map<String, Object>> mapPage = new Page<>(page, pageSize);

        IPage<Map<String, Object>> result = supplierMapper.selectMapsPage(mapPage,null);
        List<Map<String, Object>> records = result.getRecords();

        Long count = supplierMapper.selectCount(null);

        List list = new ArrayList<>();
        for (Map<String, Object> record : records) {
            Supplier supplier = new Supplier();
            supplier.setId((Integer) record.get("id"));
            supplier.setName((String) record.get("name"));
            supplier.setPhone((String) record.get("phone"));
            supplier.setCompany((String) record.get("company"));

            list.add(supplier);
        }

        Map data = new LinkedHashMap();
        data.put("tip","成功获取第"+page+"页"+pageSize+"共条数据");
        data.put("page",page);
        data.put("count",pageSize);
        data.put("pageTotal",(int)Math.ceil(count/pageSize));
        data.put("supplierTotal",count);
        data.put("supplierList",list);

        return Result.ok(data);
    }

    @Override
    public Result selectSupplierById(Integer id) {

        Supplier supplier = supplierMapper.selectById(id);

        Map data = new LinkedHashMap();
        data.put("tip","成功获取指定供应商");
        data.put("supplier",supplier);

        return Result.ok(data);
    }

    @Override
    public Result UpdateSupplierById(Integer id, Supplier supplier) {

        supplier.setId(id);
        supplierMapper.updateById(supplier);

        Map data = new LinkedHashMap();
        data.put("tip","成功修改供应商消息");
        data.put("supplier",supplier);

        return Result.ok(data);
    }

    @Override
    @Transactional
    public Result deleteSupplierById(Integer id) {
        //1.减去inventory库存表
        inventoryMapper.updateInventoryBySupplierId(id);
        //2.删去供应表中的记录
        supplyMapper.deleteSupplyBySupplierId(id);
        //3.删除供应商
        supplierMapper.deleteById(id);

        Map data = new LinkedHashMap();
        data.put("tip","成功删除供应商");
        return Result.ok(data);
    }

    @Override
    public Result getAllSupplier(Integer page, Integer pageSize) {
        List<Supplier> suppliers = supplierMapper.getAllSupplier();
        return Result.ok(suppliers);
    }
}




