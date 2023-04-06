package com.santander.demo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.santander.demo.app.domain.argument.request.UserRequest;
import com.santander.demo.app.domain.argument.response.UserResponse;
import com.santander.demo.app.domain.entity.User;
import com.santander.demo.app.domain.mapper.BaseMapper;
import com.santander.demo.app.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final BaseMapper<User, UserResponse> userMapper;

    @Override
    public UserResponse createUser(UserRequest request) throws Exception {
        User user = User.create(
            request.getCpf(),
            request.getName()
        );

        return userMapper.toResponse(userRepository.save(user));
    }
}
