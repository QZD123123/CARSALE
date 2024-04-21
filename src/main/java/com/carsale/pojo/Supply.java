package com.carsale.pojo;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName supply
 */
@Data
public class Supply implements Serializable {
    @TableId
    private Integer id;

    private Integer quantity;

    private Integer supplierid;

    private Integer productid;

    private Integer warehouseid;

    private Date createtime;

    private static final long serialVersionUID = 1L;
}