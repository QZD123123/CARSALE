package com.carsale.mapper;

import com.carsale.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carsale.pojo.User;
import com.carsale.response.UserOrderResponse;
import com.carsale.response.UsernameAndUserIdResponse;
import com.carsale.utils.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ASUS
* @description 针对表【order】的数据库操作Mapper
* @createDate 2024-04-09 14:59:47
* @Entity com.carsale.pojo.Order
*/
public interface OrderMapper extends BaseMapper<Order> {

    Integer deleteOrderByProductId(@Param("productId") Integer productId);

    void deleteOrderByWarehouseId(@Param("warehouseId") Integer warehouseId);

    Long selectOrderCount();
    List<Order> selectOrderPage(@Param("page") Integer page, @Param("pageSize") Integer pageSize);

    Order selectOrderById(@Param("orderId") Integer orderId);

    void updateOrderById(@Param("TableOrder") Order TableOrder);

    void insertOrder(@Param("TableOrder") Order TableOrder);

    void deleteOrderById(@Param("id") Integer id);

    Double selectIncome();

    Integer sales();

    Integer warehouses();

    Integer users();

    List<Integer> selectTopUserId();

    List<String> selectTopUserName();

    Double selectSalesByYearAndUserId(@Param("year") Integer year, @Param("id") Integer id);

    List<Integer> selectTopCarId();

    List<String> selectTopCarName();

    List<String> selectTopCarModel();

    Integer selectSalesByYearAndCarId(@Param("year") Integer year, @Param("id") Integer id);


    List<UserOrderResponse> selectOrderByUserId(@Param("userId") String userId);

    Integer countOrderByUserId(@Param("userId") String userId);

    Double salesOrderByUserId(@Param("userId") String userId);

    Integer AverageCountOrderByUserId();

    Double AverageSalesOrderByUserId();

    void deleteOrderByUserId(@Param("userId") Integer userId);

    List<UsernameAndUserIdResponse> selectTopUserNameAndUserId();
}




