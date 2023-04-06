package com.santander.demo.app.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.santander.demo.app.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
