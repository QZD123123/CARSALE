package com.carsale.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carsale.mapper.OrderMapper;
import com.carsale.pojo.Order;
import com.carsale.pojo.User;
import com.carsale.response.UserOrderResponse;
import com.carsale.response.autoLoginResponse;
import com.carsale.response.loginResponse;
import com.carsale.response.registerResponse;
import com.carsale.service.UserService;
import com.carsale.mapper.UserMapper;
import com.carsale.utils.JwtHelper;
import com.carsale.utils.Result;
import com.carsale.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
* @author ASUS
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-04-09 14:59:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result userOrder(String id) {
        //指定用户总共售卖的订单数
        Integer count = orderMapper.countOrderByUserId(id);
        //指定用户总销售额
        Double sales = orderMapper.salesOrderByUserId(id);
        //所有用户的平均售卖的订单数
        Integer average_count = orderMapper.AverageCountOrderByUserId();
        //所有用户平均销售额
        Double average_sales = orderMapper.AverageSalesOrderByUserId();
        //指定用户订单信息
        List<UserOrderResponse> orders = orderMapper.selectOrderByUserId(id);


        Map data = new LinkedHashMap();
        data.put("count",count);
        data.put("sales",sales);
        data.put("average_count",average_count);
        data.put("average_sales",average_sales);
        data.put("source",orders);

        return Result.ok(data);

    }


//    @Override
//    public Result getAllUser() {
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        List<User> users = userMapper.selectList(queryWrapper);
//
//        List data = new ArrayList();
//        data.add(users);
//
//        return Result.ok(data);
//    }

    @Override
    public Result register(User user) {

        int rows = userMapper.insert(user);
        System.out.println("rows = " + rows);
        User dbUser = userMapper.selectById(user.getId());

        Map data = new HashMap();
        data.put("tip","注册成功");
        data.put("user",dbUser);

        return Result.ok(data);
    }

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone,user.getPhone());
        //根据手机号，在数据库找出对应的账号
        User dbuser = userMapper.selectOne(queryWrapper);

        //数据库中没有这个手机号----没注册过
        if (dbuser == null) {
            return Result.build(null,ResultCodeEnum.no_Resource);
        }

        System.out.println(!StringUtils.isEmpty(user.getPassword()));
        System.out.println(user.getPassword().equals(dbuser.getPassword()));

        if (!StringUtils.isEmpty(user.getPassword()) && user.getPassword().equals(dbuser.getPassword())) {
            String token = jwtHelper.createToken(Long.valueOf(dbuser.getId()));


            Map data = new LinkedHashMap();
            data.put("tip","登陆成功");
            data.put("user",dbuser);
            data.put("token",token);

            return Result.ok(data);
        }

        return Result.build(null,ResultCodeEnum.Request_failed);
    }

    @Override
    public Result AutoLogin(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        Long userId = jwtHelper.getUserId(token);
        User user = userMapper.selectById(userId);

        Map data = new HashMap();
        data.put("tip","自动登录成功");
        data.put("user",user);

        return Result.ok(data);
    }

    @Override
    public Result userPageSelect(Integer page, Integer pageSize) {

        Page<Map<String, Object>> mapPage = new Page<>(page, pageSize);
        //分页
        IPage<Map<String, Object>> result = userMapper.selectMapsPage(mapPage,null);
        List<Map<String, Object>> records = result.getRecords(); // 查询结果列表
        System.out.println("records = " + records);
        //count User 不导入queryWrapper
        //LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Long count = userMapper.selectCount(null);

        List list = new ArrayList<>();
        for (Map<String, Object> record : records) {
            User user = new User();
            user.setId((Integer) record.get("id"));
            user.setAvatar((String) record.get("avatar"));
            user.setUsername((String) record.get("username"));
            user.setPhone((String) record.get("phone"));
            user.setJoined_date((LocalDateTime) record.get("joined_date"));
            user.setAddress((String) record.get("address"));
            user.setRole((String) record.get("role"));
            list.add(user);
        }

        Map data = new LinkedHashMap();
        data.put("tip","成功获取第"+page+"页共"+pageSize+"条数据");
        data.put("page",page);
        data.put("count",pageSize);
        data.put("pageTotal",(int)Math.ceil(count/pageSize));
        data.put("userTotal",count);
        data.put("userList", list);

        return Result.ok(data);
    }

    @Override
    public Result selectUserById(Integer id){

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,id);
        User user = userMapper.selectOne(queryWrapper);

        Map data = new HashMap<>();
        data.put("tip","成功获取指定用户");
        data.put("user",user);

        return Result.ok(data);
    }

    @Override
    public Result updateUserById(Integer id, User user) {

        user.setId(id);
        userMapper.updateById(user);

        //在数据库找出这个用户
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getId,user.getId());
//        User selectOne = userMapper.selectOne(queryWrapper);

        Map data = new HashMap<>();
        data.put("tip","成功更新用户信息");
//        data.put("user",new loginResponse(selectOne));

        return Result.ok(data);
    }

    @Override
    public Result updateAvatar(Integer id, User user) {

        user.setId(id);
        userMapper.updateById(user);

        Map data = new HashMap();
        data.put("tip","成功修改用户头像");
        return Result.ok(data);
    }

    @Override
    public Result deleteUser(Integer id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,id);
        int i = userMapper.delete(queryWrapper);

        Map data = new LinkedHashMap();

        if(i==0){
            data.put("tip","删除用户失败");
        }
        if (i>0){
            System.out.println("删除用户"+id+"成功");
            data.put("tip","成功删除用户");
        }
        return Result.ok(data);
    }

    @Override
    public Result checkPhone(String phone) {
        User user = userMapper.checkPhone(phone);

        if(user==null){
            return Result.ok(false);
        }
        return Result.ok(true);
    }

}




