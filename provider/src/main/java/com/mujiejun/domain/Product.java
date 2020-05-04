package com.mujiejun.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {

    private Integer id;
    /** 商品名     */
    private String productName;
    private String productSpu;
    private String productMainImg;//商品主图
    private Integer shopId;//商店id
    private Date createTime;
    private Date updateTime;
    private Integer status;
    private BigDecimal discount;//折扣
    private Integer goodsCateId;//类别id
    private Integer goodsBrandId;//品牌id
}
