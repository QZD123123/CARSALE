package com.carsale.mapper;

import com.carsale.pojo.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ASUS
* @description 针对表【supplier】的数据库操作Mapper
* @createDate 2024-04-09 14:59:47
* @Entity com.carsale.pojo.Supplier
*/
public interface SupplierMapper extends BaseMapper<Supplier> {

    Supplier selectSupplierById(@Param("supplierId") Integer supplierId);

    List<Supplier> getAllSupplier();

    String selectCompanyById(@Param("supplierId") Integer supplierId);
}




