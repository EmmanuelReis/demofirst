package com.santander.demo.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.santander.demo.app.domain.argument.request.ProductRequest;
import com.santander.demo.app.domain.argument.response.ProductResponse;
import com.santander.demo.app.domain.entity.Product;
import com.santander.demo.app.domain.mapper.BaseMapper;
import com.santander.demo.app.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final BaseMapper<Product, ProductResponse> productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product(request.getName());
        
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public Page<ProductResponse> getAllProducts(Optional<Integer> page, Optional<Integer> size) {
        return productRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(10))).map(productMapper::toResponse);
    }
}
