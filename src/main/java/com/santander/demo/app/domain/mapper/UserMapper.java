package com.santander.demo.app.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.santander.demo.app.domain.argument.response.UserResponse;
import com.santander.demo.app.domain.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserResponse> {
 
    @Mapping(target = "cpf", expression = "java(entity.getCpf().formatted())")
    UserResponse toResponse(User entity);
}
