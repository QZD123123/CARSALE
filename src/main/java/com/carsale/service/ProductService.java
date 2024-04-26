package com.carsale.service;

import com.carsale.pojo.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.carsale.utils.Result;

/**
* @author ASUS
* @description 针对表【product】的数据库操作Service
* @createDate 2024-04-09 14:59:47
*/
public interface ProductService extends IService<Product> {

    Result createProduct(Product product);

    Result productPageSelect(Integer page, Integer pageSize);

    Result selectProductById(Integer id);

    Result UpdateProductById(Integer id, String name, String model, Double price, String introduce);

    Result deleteProductById(Integer id);

    Result selectWarehouseByProductId(Integer id);

    Result getAllProduct();
}
