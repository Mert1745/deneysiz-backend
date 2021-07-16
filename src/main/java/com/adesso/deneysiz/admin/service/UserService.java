package com.adesso.deneysiz.admin.service;

import com.adesso.deneysiz.admin.JWTProvider;
import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.repository.UserRepository;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String USER_NOT_FOUND = "User Not Found!";
    private static final String WRONG_CREDENTIALS = "Wrong Credentials";
    private static final String SUCCESS = "Success";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;

    private boolean userExists(UserDTO userDTO) {
        User user = userRepository.getByUserName(userDTO.getUserName());
        if (user == null) {
            return false;
        }
        return bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword());
    }

    public ResponseBuilder<AdminDTO> getLoginResponse(UserDTO userDTO) {
        User user = userRepository.getByUserName(userDTO.getUserName());
        AdminDTO adminDTO = new AdminDTO();
        if (user == null) {
            adminDTO.setSuccess(false);
            return ResponseBuilder.<AdminDTO>getInstance().status(HttpStatus.NOT_FOUND.value()).data(adminDTO).message(USER_NOT_FOUND);
        }
        boolean matches = bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword());
        if (!matches) {
            adminDTO.setSuccess(false);
            return ResponseBuilder.<AdminDTO>getInstance().status(HttpStatus.UNAUTHORIZED.value()).data(adminDTO).message(WRONG_CREDENTIALS);
        }
        final String token = jwtProvider.createToken(user);
        adminDTO.setToken(token);
        adminDTO.setSuccess(true);
        return ResponseBuilder.<AdminDTO>getInstance().status(HttpStatus.OK.value()).data(adminDTO).message(SUCCESS);
    }

    public ResponseBuilder<User> saveUser(UserDTO userDTO) {
        final User user = new User(userDTO.getUserName(), bCryptPasswordEncoder.encode(userDTO.getPassword()), userDTO.getRole());
        return ResponseBuilder.<User>getInstance().data(user).message("New User Added Successfully").status(HttpStatus.OK.value());
    }
}
