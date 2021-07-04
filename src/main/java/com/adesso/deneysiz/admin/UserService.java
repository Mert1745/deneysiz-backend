package com.adesso.deneysiz.admin;

import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
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

    public AdminResponseBuilder getLoginResponse(UserDTO userDTO) {
        User user = userRepository.getByUserName(userDTO.getUserName());
        if (user == null) {
            AdminResponseBuilder.getInstance().status(HttpStatus.OK.value()).success(false);
        }
        boolean matches = bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword());
        if (!matches) {
            return AdminResponseBuilder.getInstance().status(HttpStatus.OK.value()).success(false);
        }
        final String token = jwtProvider.createToken(user);
        return AdminResponseBuilder.getInstance().status(HttpStatus.OK.value()).success(true).token(token);
    }

    public User saveUser(UserDTO userDTO) {
        final User user = new User(userDTO.getUserName(), bCryptPasswordEncoder.encode(userDTO.getPassword()), userDTO.getRole());
        return userRepository.save(user);
    }
}
