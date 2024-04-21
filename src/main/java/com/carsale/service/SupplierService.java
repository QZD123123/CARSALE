package com.carsale.service;

import com.carsale.pojo.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.carsale.utils.Result;

/**
* @author ASUS
* @description 针对表【supplier】的数据库操作Service
* @createDate 2024-04-09 14:59:47
*/
public interface SupplierService extends IService<Supplier> {

    Result createSupplier(String name, String phone, String company);

    Result supplierPageSelect(Integer page, Integer pageSize);

    Result selectSupplierById(Integer id);

    Result UpdateSupplierById(Integer id, String name, String phone, String company);

    Result deleteSupplierById(Integer id);
}
