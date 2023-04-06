package com.santander.demo.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.santander.demo.app.domain.argument.request.UserRequest;
import com.santander.demo.app.domain.argument.response.UserResponse;
import com.santander.demo.app.domain.entity.User;
import com.santander.demo.app.domain.mapper.BaseMapper;
import com.santander.demo.app.domain.repository.UserRepository;
import com.santander.demo.app.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    
    @Mock
    private UserRepository userRepository;

    @Spy
    private BaseMapper<User, UserResponse> userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @ParameterizedTest
    @ValueSource(strings = { "50014310007", "500.143.100-07" })
    @DisplayName("Deve criar um usuário com sucesso, para CPF formatado ou não, retorna sempre CPF formatado")
    public void testCreateUser(String cpf) throws Exception {
        String userName = "Emmanuel";
        UserRequest request = new UserRequest(cpf, userName);
        User user = User.create(cpf, userName);
        user.setId(1l);

        UserResponse expected = new UserResponse(1l, "500.143.100-07", userName);

        when(userRepository.save(any())).thenReturn(user);

        UserResponse response = userService.createUser(request);

        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getName(), response.getName());
        assertEquals(expected.getCpf(), response.getCpf());

        verify(userMapper).toResponse(any());
    }

    @ParameterizedTest
    @ValueSource(strings = { "50014310008", "500.143.100-08", "500.143.100-0", "500143100" })
    @DisplayName("Deve retornar exceção para CPF inválido")
    public void testGetAllUsers(String cpf) {
        String userName = "Emmanuel";
        UserRequest request = new UserRequest(cpf, userName);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> userService.createUser(request));

        assertEquals("CPF inválido", ex.getMessage());
    }
}
