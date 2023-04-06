package com.santander.demo.app.domain.mapper;

import org.mapstruct.Mapper;

import com.santander.demo.app.domain.argument.response.ProductResponse;
import com.santander.demo.app.domain.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductResponse> {

}
