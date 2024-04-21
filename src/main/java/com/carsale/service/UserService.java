package com.carsale.service;

import com.carsale.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.carsale.utils.Result;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author ASUS
* @description 针对表【user】的数据库操作Service
* @createDate 2024-04-09 14:59:47
*/
public interface UserService extends IService<User> {


    Result register(User user);

    Result login(User user);

    Result AutoLogin(HttpServletRequest request);

    Result userPageSelect(Integer page, Integer pageSize);

    Result selectUserById(Integer id);

    Result updateUserById(Integer id, User user);

    Result updateAvatar(Integer id, User user);

    Result deleteUser(Integer id);


}
