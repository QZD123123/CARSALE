package com.carsale.mapper;

import com.carsale.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carsale.response.ProductNameAndValue;
import com.carsale.response.ProductYearAndSalesResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author ASUS
* @description 针对表【product】的数据库操作Mapper
* @createDate 2024-04-09 14:59:47
* @Entity com.carsale.pojo.Product
*/
public interface ProductMapper extends BaseMapper<Product> {

    Integer saleOfCarById(Integer id);

    Product selectProductById(@Param("productId") Integer productId);

    List<Product> getAllProduct();

    String selectProductBrandById(@Param("productId") Integer productId);

    String selectProductModelById(@Param("productId") Integer productId);


    List<ProductNameAndValue> selectProductNameAndValueById(@Param("productId") Integer productId);


    List<ProductYearAndSalesResponse> selectYearAndSalesById(Integer id);
}




