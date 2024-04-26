package com.carsale.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.carsale.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author ASUS
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-04-09 14:59:47
* @Entity com.carsale.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {


    User selectByPhone(String userPhone);

    User findByPhone(String phoneNumber);

    User checkPhone(String phone);


    List<User> getAllUser();

    String selectUserPhoneById(@Param("userId") Integer userId);

    String selectUserNameById(@Param("userId")Integer userId);
}




