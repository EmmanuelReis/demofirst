package com.santander.demo.infra.rest.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santander.demo.app.domain.argument.request.UserRequest;
import com.santander.demo.app.domain.argument.response.UserResponse;
import com.santander.demo.app.services.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    
    private final IUserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse saveUser(@Valid @RequestBody UserRequest request) throws Exception {
        return userService.createUser(request);
    }
}
