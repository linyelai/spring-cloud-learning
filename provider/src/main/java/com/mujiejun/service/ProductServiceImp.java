package com.mujiejun.service;

import com.mujiejun.domain.Product;
import com.mujiejun.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp  implements  ProductService{

    @Autowired
    private ProductMapper productMapper;
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation =  Propagation.REQUIRED)
    public Product findProductById(Integer id) {
        return productMapper.findProductById(id);
    }

    @Override
    public int addProduct(Product product) {

        return productMapper.addProduct(product);
    }
}
