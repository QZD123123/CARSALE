package com.carsale.controller;

import com.carsale.pojo.User;
import com.carsale.response.ChangePassword;
import com.carsale.service.UserService;
import com.carsale.utils.Result;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}/order")
    public Result userOrder(@PathVariable String id){
        Result result = userService.userOrder(id);
        return result;
    }

    @GetMapping("")
    public Result userPageSelect(Integer page, Integer pageSize){
        System.out.println("page = " + page);
        System.out.println("pageSize = " + pageSize);
        if (page == null && pageSize == null) {
            Result result = userService.getAllUser();
            return result;
        }
        else {
            Result result = userService.userPageSelect(page, pageSize);
            return result;
        }
    }

    @GetMapping("{id}")
    public Result selectUserById(@PathVariable Integer id){
        Result result = userService.selectUserById(id);
        return result;
    }

    @PatchMapping("{id}")
    public Result updateUserById(@PathVariable Integer id, @RequestBody User user){
        Result result = userService.updateUserById(id,user);
        return result;
    }


    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN','USER')")
    @PatchMapping("{id}/avatar")
    public Result updateAvatar(@PathVariable Integer id, User user){
        Result result = userService.updateAvatar(id,user);
        return result;
    }

    @PreAuthorize("hasAnyAuthority('ROOT','ADMIN')")
    @DeleteMapping("{id}")
    public Result deleteUser(@PathVariable Integer id){
        Result result = userService.deleteUser(id);
        return result;
    }

    @GetMapping("phone/{phone}")
    public Result checkPhone(@PathVariable String phone){
        Result result = userService.checkPhone(phone);
        return result;
    }

    @PatchMapping("{id}/password")
    public Result updatePassword(@PathVariable String id, @RequestBody ChangePassword changePassword){
        Result result = userService.updatePassword(id,changePassword);
        return result;
    }

}
