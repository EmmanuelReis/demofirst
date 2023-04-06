package com.santander.demo.infra.rest.v1;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santander.demo.app.domain.argument.request.ProductRequest;
import com.santander.demo.app.domain.argument.response.ProductResponse;
import com.santander.demo.app.services.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    
    private final IProductService productService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductResponse saveProduct(@Valid @RequestBody ProductRequest request) throws Exception {
        return productService.createProduct(request);
    }

    @GetMapping
    public Page<ProductResponse> getAllProducts(
        @RequestParam(name = "page") Optional<Integer> page, 
        @RequestParam(name = "size") Optional<Integer> size
    ) throws Exception {
        return productService.getAllProducts(page, size);
    }
}
