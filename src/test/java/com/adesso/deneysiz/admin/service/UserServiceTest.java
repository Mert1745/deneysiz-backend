package com.adesso.deneysiz.admin.service;

import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.repository.UserRepository;
import com.adesso.deneysiz.admin.security.JWTProvider;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {
    private static final String TOKEN = "eyJ09.eyJz0.OBTL";

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private JWTProvider jwtProvider;

    @Test
    void getLoginResponse_success() {
        when(userRepository.getByUserName(any())).thenReturn(getUser());
        when(bCryptPasswordEncoder.matches(getUser().getPassword(), getUserDTO().getPassword())).thenReturn(Boolean.TRUE);
        when(jwtProvider.createToken(getUser())).thenReturn(TOKEN);

        ResponseBuilder<AdminDTO> responseBuilder = userService.getLoginResponse(getUserDTO());

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertTrue(responseBuilder.getData().getSuccess());
    }

    @Test
    void getLoginResponse_userNotFound() {
        when(userRepository.getByUserName(any())).thenReturn(null);

        ResponseBuilder<AdminDTO> responseBuilder = userService.getLoginResponse(getUserDTO());

        assertEquals(HttpStatus.NOT_FOUND.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertFalse(responseBuilder.getData().getSuccess());
    }

    @Test
    void getLoginResponse_wrongCredentials() {
        when(userRepository.getByUserName(any())).thenReturn(getUser());
        when(bCryptPasswordEncoder.matches(getUser().getPassword(), getUserDTO().getPassword())).thenReturn(Boolean.FALSE);

        ResponseBuilder<AdminDTO> responseBuilder = userService.getLoginResponse(getUserDTO());

        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertFalse(responseBuilder.getData().getSuccess());
    }

    @Test
    void saveUser() {
        when(userRepository.save(any())).thenReturn(getUser());

        ResponseBuilder<User> responseBuilder = userService.saveUser(getUserDTO());

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
    }

    private UserDTO getUserDTO () {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("admin");
        userDTO.setPassword("admin");
        userDTO.setRole("admin");
        return userDTO;
    }

    private User getUser () {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        return user;
    }
}