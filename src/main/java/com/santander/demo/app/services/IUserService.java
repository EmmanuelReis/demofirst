package com.santander.demo.app.services;

import com.santander.demo.app.domain.argument.request.UserRequest;
import com.santander.demo.app.domain.argument.response.UserResponse;

public interface IUserService {
    
    public UserResponse createUser(UserRequest request) throws Exception;
}
