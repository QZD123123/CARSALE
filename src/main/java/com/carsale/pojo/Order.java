package com.carsale.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName order
 */
@Data
public class Order implements Serializable {
    @TableId
    private Integer id;

    private Integer productid;

    private Integer userid;

    private Integer warehouseid;

    private Date createtime;

    private static final long serialVersionUID = 1L;
}