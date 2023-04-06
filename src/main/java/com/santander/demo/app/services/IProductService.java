package com.santander.demo.app.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.santander.demo.app.domain.argument.request.ProductRequest;
import com.santander.demo.app.domain.argument.response.ProductResponse;

public interface IProductService {
    
    public ProductResponse createProduct(ProductRequest request) throws Exception;

    public Page<ProductResponse> getAllProducts(Optional<Integer> page, Optional<Integer> size);
}
