package com.carsale.controller;

import com.carsale.service.SupplierService;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @PostMapping("")
    public Result createSupplier(String name, String phone, String company){
        Result result = supplierService.createSupplier(name,phone,company);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @GetMapping("")
    public Result supplierPageSelect(Integer page, Integer pageSize){
        Result result = supplierService.supplierPageSelect(page,pageSize);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @GetMapping("{id}")
    public Result selectSupplierById(@PathVariable Integer id){
        Result result = supplierService.selectSupplierById(id);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @PatchMapping("{id}")
    public Result UpdateSupplierById(@PathVariable Integer id, String name, String phone, String company){
        Result result = supplierService.UpdateSupplierById(id,name,phone,company);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @DeleteMapping("{id}")
    public Result deleteProductById(@PathVariable Integer id){
        Result result = supplierService.deleteSupplierById(id);
        return result;
    }


}
