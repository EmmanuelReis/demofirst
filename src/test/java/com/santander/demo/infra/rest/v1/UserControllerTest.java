package com.santander.demo.infra.rest.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.demo.app.domain.argument.request.UserRequest;
import com.santander.demo.app.domain.argument.response.UserResponse;
import com.santander.demo.app.exception.CustomExceptionHandler;
import com.santander.demo.app.services.IUserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    
    @Mock
    private IUserService userService;

    @InjectMocks
    private UserController resource;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(resource)
			.setControllerAdvice(
				CustomExceptionHandler.class
			)
			.build();
	}

    @Test
	@DisplayName("Deve retornar status 400 ao ser enviado um body com campo inválido") 
	public void testInvalidRequest() throws Exception {
		UserRequest request = new UserRequest(null, null);

		mockMvc.perform(
			post("/v1/user")
			.content(mapper.writeValueAsString(request))
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(MockMvcResultMatchers.jsonPath("$.errors").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$.errors").isNotEmpty());
	}

	@Test
	@DisplayName("Deve retornar status 415 ao enviar media não suportada") 
	public void testInvalidMedia() throws Exception {
		UserRequest request = new UserRequest("500.143.100-07", "Emmanuel");
		
		mockMvc.perform(
			post("/v1/user")
			.content(mapper.writeValueAsString(request))
			.contentType(MediaType.APPLICATION_XML)
		)
		.andExpect(MockMvcResultMatchers.status().isUnsupportedMediaType());
	}

	@Test
	@DisplayName("Deve retornar status 405 a um verbo não suportado") 
	public void testNotFound() throws Exception {		
		mockMvc.perform(get("/v1/user"))
			.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
	}

	@Test
	@DisplayName("Deve retornar status 201 ao criar um usuário") 
	public void testCreateUser() throws Exception {
		UserRequest request = new UserRequest("500.143.100-08", "Emmanuel");
		UserResponse expected = new UserResponse(1l, "500.143.100-07", "Emmanuel");

		when(userService.createUser(any())).thenReturn(expected);
		
		mockMvc.perform(
			post("/v1/user")
			.content(mapper.writeValueAsString(request))
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expected.getId()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(expected.getCpf()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expected.getName()));
	}
}
