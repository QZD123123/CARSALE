package com.carsale.controller;

import com.carsale.pojo.Supply;
import com.carsale.service.SupplyService;
import com.carsale.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @PostMapping("")
    public Result createSupply(@RequestBody Supply supply){
        Result result = supplyService.createSupply(supply);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @GetMapping("")
    public Result supplyPageSelect(Integer page, Integer pageSize){
        Result result = supplyService.supplyPageSelect(page,pageSize);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @GetMapping("{id}")
    public Result selectSupplyById(@PathVariable Integer id){
        Result result = supplyService.selectSupplyById(id);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @PatchMapping("{id}")
    public Result UpdateSupplyById(@PathVariable Integer id, @RequestBody Supply supply){
        Result result = supplyService.UpdateSupplyById(id,supply);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','ROOT')")
    @DeleteMapping("{id}")
    public Result deleteSupplyById(@PathVariable Integer id){
        Result result = supplyService.deleteSupplyById(id);
        return result;
    }
}
