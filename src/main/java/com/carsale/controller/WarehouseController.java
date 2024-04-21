package com.carsale.controller;

import com.carsale.service.WarehouseService;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;


    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @PostMapping("")
    public Result createWarehouse(@RequestBody String location){
        Result result = warehouseService.createWarehouse(location);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @GetMapping("")
    public Result warehousePageSelect(Integer page, Integer pageSize){
        Result result = warehouseService.warehousePageSelect(page,pageSize);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @GetMapping("{id}")
    public Result selectWarehouseById(@PathVariable Integer id){
        Result result = warehouseService.selectWarehouseById(id);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @PatchMapping("{id}")
    public Result UpdateWarehouseById(@PathVariable Integer id, @RequestBody String location){
        Result result = warehouseService.UpdateWarehouseById(id,location);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @DeleteMapping("{id}")
    public Result deleteWarehouseById(@PathVariable Integer id){
        Result result = warehouseService.deleteWarehouseById(id);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @GetMapping("{id}/inventory")
    public Result selectWarehouseAndInventoryById(@PathVariable Integer id){
        Result result = warehouseService.selectWarehouseAndInventoryById(id);
        return result;
    }

}
