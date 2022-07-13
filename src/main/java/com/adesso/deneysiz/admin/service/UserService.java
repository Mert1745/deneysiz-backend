package com.adesso.deneysiz.admin.service;

import com.adesso.deneysiz.admin.security.JWTProvider;
import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.repository.UserRepository;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.adesso.deneysiz.admin.response.CustomResponse.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;

    public ResponseBuilder<AdminDTO> getLoginResponse(final UserDTO userDTO) {
        final User user = userRepository.getByUserName(userDTO.getUserName());

        if (user == null) return getNotFoundResponse();
        if (!userMatches(userDTO, user)) return getUnauthorizedResponse();

        final String token = jwtProvider.createToken(user);
        return getSuccessResponse(new AdminDTO(token, Boolean.TRUE));
    }

    public ResponseBuilder<User> saveUser(final UserDTO userDTO) {
        final User user = getUser(userDTO);
        final User saved = userRepository.save(user);
        return getSuccessResponse(saved);
    }

    private User getUser(final UserDTO userDTO) {
        return new User(userDTO.getUserName(),
                bCryptPasswordEncoder.encode(userDTO.getPassword()), userDTO.getRole().toLowerCase());
    }

    private boolean userMatches(final UserDTO userDTO, final User user) {
        return bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword());
    }
}
