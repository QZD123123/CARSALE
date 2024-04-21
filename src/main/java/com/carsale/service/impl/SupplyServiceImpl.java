package com.carsale.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carsale.mapper.ProductMapper;
import com.carsale.mapper.SupplierMapper;
import com.carsale.mapper.WarehouseMapper;
import com.carsale.pojo.Supplier;
import com.carsale.pojo.Supply;
import com.carsale.response.deleteSupplyResponse;
import com.carsale.response.supplyResponse;
import com.carsale.service.SupplyService;
import com.carsale.mapper.SupplyMapper;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @author ASUS
* @description 针对表【supply】的数据库操作Service实现
* @createDate 2024-04-09 14:59:47
*/
@Service
public class SupplyServiceImpl extends ServiceImpl<SupplyMapper, Supply>
    implements SupplyService{

    @Autowired
    private SupplyMapper supplyMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public Result createSupply(Integer quantity, Integer supplierId, Integer productId, Integer warehouseId) {
        Supply supply = new Supply();
        supply.setQuantity(quantity);
        supply.setSupplierid(supplierId);
        supply.setProductid(productId);
        supply.setWarehouseid(warehouseId);

        supplyMapper.insert(supply);

        Map data = new LinkedHashMap();
        data.put("tip","成功创建供应记录");
        data.put("supply",new deleteSupplyResponse(supply));

        return Result.ok(data);
    }

    @Override
    public Result supplyPageSelect(Integer page, Integer pageSize) {
        Page<Map<String, Object>> mapPage = new Page<>(page, pageSize);

        IPage<Map<String, Object>> result = supplyMapper.selectMapsPage(mapPage,null);
        List<Map<String, Object>> records = result.getRecords();

        Long count = supplyMapper.selectCount(null);

        List list = new ArrayList<>();
        for (Map<String, Object> record : records) {
            supplyResponse supplyResponse = new supplyResponse();
            supplyResponse.setId((Integer) record.get("id"));
            supplyResponse.setQuantity((Integer) record.get("quantity"));
            supplyResponse.setSupplier(supplierMapper.selectSupplierById((Integer) record.get("supplierid")));
            supplyResponse.setProduct(productMapper.selectProductById((Integer) record.get("productid")));
            supplyResponse.setWarehouse(warehouseMapper.selectWarehouseById((Integer) record.get("warehouseid")));

            list.add(supplyResponse);
        }

        Map data = new LinkedHashMap();
        data.put("tip","成功获取第"+page+"页"+pageSize+"共条数据");
        data.put("page",page);
        data.put("count",pageSize);
        data.put("pageTotal",(int)Math.ceil(count/pageSize));
        data.put("supplyTotal",count);
        data.put("supplyList",list);

        return Result.ok(data);
    }

    @Override
    public Result selectSupplyById(Integer id) {
        Supply supply = supplyMapper.selectById(id);

        supplyResponse supplyResponse = new supplyResponse();
        supplyResponse.setId(supply.getId());
        supplyResponse.setQuantity(supply.getQuantity());
        supplyResponse.setSupplier(supplierMapper.selectSupplierById(supply.getSupplierid()));
        supplyResponse.setProduct(productMapper.selectProductById(supply.getProductid()));
        supplyResponse.setWarehouse(warehouseMapper.selectWarehouseById(supply.getWarehouseid()));

        Map data = new LinkedHashMap();
        data.put("tip","成功获取指定供应记录");
        data.put("id",supplyResponse.getId());
        data.put("quantity",supplyResponse.getQuantity());
        data.put("supplier",supplyResponse.getSupplier());
        data.put("product",supplyResponse.getProduct());
        data.put("warehouse",supplyResponse.getWarehouse());

        return Result.ok(data);
    }

    @Override
    public Result UpdateSupplyById(Integer id, Supply supply) {
        supply.setId(id);
        supplyMapper.updateById(supply);
        Supply dbSupply = supplyMapper.selectById(id);

        Map data = new LinkedHashMap();
        data.put("tip","成功修改供应记录");
        data.put("supply",new deleteSupplyResponse(dbSupply));

        return Result.ok(data);
    }

    @Override
    public Result deleteSupplyById(Integer id) {
        Supply supply = supplyMapper.selectById(id);
        deleteSupplyResponse deleteSupplyResponse = new deleteSupplyResponse(supply);
        supplyMapper.deleteById(id);

        Map data = new LinkedHashMap();
        data.put("tip","成功删除供应记录");
        data.put("result",deleteSupplyResponse);

        return Result.ok(data);
    }


}




