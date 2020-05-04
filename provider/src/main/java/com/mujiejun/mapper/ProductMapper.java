package com.mujiejun.mapper;

import com.mujiejun.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    public Product findProductById(@Param("id") Integer id);
}
