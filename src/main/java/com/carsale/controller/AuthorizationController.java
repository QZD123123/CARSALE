package com.carsale.controller;

import com.alibaba.druid.util.StringUtils;
import com.carsale.pojo.User;
import com.carsale.service.UserService;
import com.carsale.utils.JwtHelper;
import com.carsale.utils.Result;
import com.carsale.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthorizationController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserService userService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("register")
    public Result register(@RequestBody User user){
        Result result = userService.register(user);
        return result;
    }

    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result = userService.login(user);
        System.out.println("result = " + result);
        return result;
    }

    @GetMapping("login")
    public Result AutoLogin(@RequestHeader String Authorization){
        Result result = userService.AutoLogin(Authorization);
        System.out.println("result = " + result);
        return result;
    }


}
