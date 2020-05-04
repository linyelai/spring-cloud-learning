package com.mujiejun.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mujiejun.domain.Product;
import com.mujiejun.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private String topic = "add-product";

    @Autowired
    private ProductService productService;

    @GetMapping("/findProductById")
    public Product findProductById(Integer id){
        Product product = productService.findProductById(id);
        return product;
    }

    @PostMapping("/product")
    public int addProduct(@RequestBody  Product product){

        ObjectMapper mapper = new  ObjectMapper();
        try {
          String productStr =   mapper.writeValueAsString(product);
            kafkaTemplate.send(topic,productStr);
        }catch (Exception e){
            e.printStackTrace();
        }

        return productService.addProduct(product);
    }
}
