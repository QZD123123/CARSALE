package com.carsale.controller;

import com.carsale.service.ProductService;
import com.carsale.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @PostMapping("")
    public Result createProduct(String name, String model, Double price ,String introduce){
        Result result = productService.createProduct(name,model,price,introduce);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @GetMapping("")
    public Result productPageSelect(Integer page, Integer pageSize){
        Result result = productService.productPageSelect(page,pageSize);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @GetMapping("{id}")
    public Result selectProductById(@PathVariable Integer id){
        Result result = productService.selectProductById(id);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @PatchMapping("{id}")
    public Result UpdateProductById(@PathVariable Integer id, String name, String model, Double price, String introduce){
        Result result = productService.UpdateProductById(id,name,model,price,introduce);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @DeleteMapping("{id}")
    public Result deleteProductById(@PathVariable Integer id){
        Result result = productService.deleteProductById(id);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @GetMapping("{id}/inventory")
    public Result selectWarehouseByProductId(@PathVariable Integer id){
        Result result = productService.selectWarehouseByProductId(id);
        return result;
    }

}
