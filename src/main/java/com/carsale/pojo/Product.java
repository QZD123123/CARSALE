package com.carsale.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName product
 */
@Data
public class Product implements Serializable {
    @TableId
    private Integer id;

    private String name;

    private String model;

    private Double price;

    private String introduce;

    private String poster;

    private static final long serialVersionUID = 1L;
}