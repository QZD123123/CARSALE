package com.carsale.mapper;

import com.carsale.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author ASUS
* @description 针对表【product】的数据库操作Mapper
* @createDate 2024-04-09 14:59:47
* @Entity com.carsale.pojo.Product
*/
public interface ProductMapper extends BaseMapper<Product> {

    Integer saleOfCarById(Integer id);

    Product selectProductById(@Param("productId") Integer productId);
}




