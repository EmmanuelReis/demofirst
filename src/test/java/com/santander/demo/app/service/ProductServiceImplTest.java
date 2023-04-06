package com.santander.demo.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.santander.demo.app.domain.argument.request.ProductRequest;
import com.santander.demo.app.domain.argument.response.ProductResponse;
import com.santander.demo.app.domain.entity.Product;
import com.santander.demo.app.domain.mapper.BaseMapper;
import com.santander.demo.app.domain.repository.ProductRepository;
import com.santander.demo.app.services.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    
    @Mock
    private ProductRepository productRepository;

    @Spy
    private BaseMapper<Product, ProductResponse> productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Deve criar um produto com sucesso")
    public void testCreateProduct() {
        String cardName = "Visa Platinum";
        ProductRequest request = new ProductRequest(cardName);
        Product expected = new Product(cardName);
        expected.setId(1l);

        when(productRepository.save(any())).thenReturn(expected);

        ProductResponse response = productService.createProduct(request);

        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getName(), response.getName());

        verify(productMapper).toResponse(any());
    }

    @Test
    @DisplayName("Deve retornar uma página de produtos")
    public void testGetAllProducts() {
        String cardName = "Visa Platinum";
        Product product = new Product(cardName);
        product.setId(1l);

        Page<Product> expected = new PageImpl<>(List.of(product));

        when(productRepository.findAll(any(Pageable.class))).thenReturn(expected);

        Page<ProductResponse> response = productService.getAllProducts(Optional.empty(), Optional.empty());

        assertEquals(1, response.getTotalElements());
        assertEquals(1, response.getTotalPages());

        verify(productMapper).toResponse(any());
    }
}
