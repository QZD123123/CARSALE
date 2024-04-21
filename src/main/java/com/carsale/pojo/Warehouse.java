package com.carsale.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName warehouse
 */
@Data
public class Warehouse implements Serializable {
    @TableId
    private Integer id;

    private String location;

    private static final long serialVersionUID = 1L;
}