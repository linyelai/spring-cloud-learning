package com.mujiejun.controller;

import com.mujiejun.domain.Product;
import com.mujiejun.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/findProductById")
    public Product findProductById(Integer id){
        Product product = productService.findProductById(id);
        return product;
    }
}
