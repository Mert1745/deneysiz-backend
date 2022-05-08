package com.adesso.deneysiz.admin.resource;

import com.adesso.deneysiz.admin.constant.ResponseMessage;
import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.repository.UserRepository;
import com.adesso.deneysiz.admin.security.JWTProvider;
import com.adesso.deneysiz.admin.service.AdminService;
import com.adesso.deneysiz.admin.service.UserService;
import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.adesso.deneysiz.admin.constant.AdminUrlConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminResource.class)
class AdminResourceTest {
    private static final String TOKEN = "eyJ09.eyJz0.OBTL";
    private static final String LOGIN_RESPONSE = "{\"status\":200,\"data\":{\"token\":\"eyJ09.eyJz0.OBTL\",\"success\":true},\"message\":\"Success\"}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AdminService adminService;

    @MockBean
    private JWTProvider jwtProvider;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void login() throws Exception {
        ResponseBuilder<AdminDTO> responseBuilder = getResponseBuilder(new AdminDTO(TOKEN, Boolean.TRUE));
        when(userService.getLoginResponse(any())).thenReturn(responseBuilder);

        RequestBuilder requestBuilder = getRequestBuilder(ADMIN + LOGIN, new UserDTO());
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(LOGIN_RESPONSE));
    }

    @Test
    public void saveUser() throws Exception {
        ResponseBuilder<User> responseBuilder = getResponseBuilder(new User("userName", "pazz", "admin"));
        when(userService.saveUser(any())).thenReturn(responseBuilder);

        RequestBuilder requestBuilder = getRequestBuilder(ADMIN + SAVE_USER, new User());

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    @Disabled
    public void saveBrand() {
        ResponseBuilder<AdminDTO> responseBuilder = getResponseBuilder(new AdminDTO(TOKEN, Boolean.TRUE));
        when(adminService.saveNewBrand(any())).thenReturn(responseBuilder);

        RequestBuilder requestBuilder = getRequestBuilder(ADMIN + SAVE_BRAND, new Brand());

        //TODO mkose find a way to mock authorization/interceptor
//        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @NotNull
    private MockHttpServletRequestBuilder getRequestBuilder(String url, Object content) {
        return MockMvcRequestBuilders
                .post(url)
                .content(asString(content))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    private <T> ResponseBuilder<T> getResponseBuilder(T data) {
        return ResponseBuilder.<T>getInstance()
                .status(HttpStatus.OK.value())
                .data(data)
                .message(ResponseMessage.SUCCESS);
    }

    private String asString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}