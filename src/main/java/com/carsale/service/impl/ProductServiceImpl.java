package com.carsale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carsale.mapper.*;
import com.carsale.pojo.Product;
import com.carsale.pojo.User;
import com.carsale.pojo.Warehouse;
import com.carsale.response.ProductResponse;
import com.carsale.response.countResponse;
import com.carsale.service.ProductService;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.carsale.utils.ResultCodeEnum.requested_resource_no_modified;

/**
* @author ASUS
* @description 针对表【product】的数据库操作Service实现
* @createDate 2024-04-09 14:59:47
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SupplyMapper supplyMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public Result createProduct(Product product) {
        int i = productMapper.insert(product);
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getName,product.getName())
                .eq(Product::getModel,product.getModel())
                .eq(Product::getPrice,product.getPrice())
                .eq(Product::getIntroduce,product.getIntroduce())
                .eq(Product::getPoster,product.getPoster());
        Product dbProduct = productMapper.selectOne(queryWrapper);
        Map data = new HashMap();
        if (i == 0) {
            data.put("tip","创建产品失败");
            return Result.ok(requested_resource_no_modified);
        }else{
            data.put("tip","成功创建产品");
            data.put("product",dbProduct);
            return Result.ok(data);
        }
    }

    @Override
    public Result productPageSelect(Integer page, Integer pageSize) {

        Page<Map<String, Object>> mapPage = new Page<>(page, pageSize);

        IPage<Map<String, Object>> result = productMapper.selectMapsPage(mapPage,null);
        List<Map<String, Object>> records = result.getRecords();

        Long count = productMapper.selectCount(null);

        List list = new ArrayList<>();
        for (Map<String, Object> record : records) {
            Product product = new Product();
            product.setId((Integer) record.get("id"));
            product.setName((String) record.get("name"));
            product.setModel((String) record.get("model"));
            product.setPrice((Double) record.get("price"));
            product.setIntroduce((String) record.get("introduce"));
            product.setPoster((String) record.get("poster"));

            ProductResponse productResponse = new ProductResponse(product);
            productResponse.setSales(productMapper.saleOfCarById((Integer) record.get("id")));
            list.add(productResponse);
        }

        Map data = new HashMap();
        data.put("tip","成功获取第"+page+"页"+pageSize+"共条数据");
        data.put("page",page);
        data.put("count",pageSize);
        data.put("pageTotal",(int)Math.ceil(count/pageSize));
        data.put("productTotal",count);
        data.put("source",list);

        return Result.ok(data);
    }

    @Override
    public Result selectProductById(Integer id) {

        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getId,id);
        Product product = productMapper.selectOne(queryWrapper);

        Map data = new HashMap();
        data.put("tip","成功获取指定产品");
        data.put("product",product);

        return Result.ok(data);
    }

    @Override
    public Result UpdateProductById(Integer id, String name, String model, Double price, String introduce) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setModel(model);
        product.setPrice(price);
        product.setIntroduce(introduce);
        productMapper.updateById(product);

        Map data = new HashMap();
        data.put("tip","成功更新产品信息");
        data.put("product",product);
        return Result.ok(data);
    }

    @Override
    @Transactional
    public Result deleteProductById(Integer id) {

        Map data = new LinkedHashMap();
        data.put("tip","成功删除产品");
        countResponse count = new countResponse();

        int orderCount = orderMapper.deleteOrderByProductId(id);
        count.setCount(orderCount);
        data.put("orderCount",count);

        int supplyCount = supplyMapper.deleteSupplyByProductId(id);
        count.setCount(supplyCount);
        data.put("supplyCount",count);

        int inventoryCount = inventoryMapper.deleteInventoryByProductId(id);
        count.setCount(inventoryCount);
        data.put("inventoryCount",count);

        int deleteProduct = productMapper.deleteById(id);

        return Result.ok(data);
    }

    @Override
    public Result selectWarehouseByProductId(Integer id) {
        List<Warehouse> warehouse = warehouseMapper.selectWarehouseByProductId(id);
        return Result.ok(warehouse);
    }

    @Override
    public Result getAllProduct() {
        List<Product> products = productMapper.getAllProduct();
        return Result.ok(products);
    }


}




