package com.santander.demo.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santander.demo.app.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
