package com.carsale.response;

import com.carsale.mapper.ProductMapper;
import com.carsale.pojo.Product;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class ProductResponse {
    private Integer id;
    private String name;
    private String model;
    private Double price;
    private String introduce;
    private String poster;
    private Integer sales;

    public ProductResponse(){}

    public ProductResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.model = product.getModel();
        this.price = product.getPrice();
        this.introduce = product.getIntroduce();
        this.poster = product.getPoster();
        this.sales = 0;
    }
}
