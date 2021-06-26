package com.adesso.deneysiz.admin;

import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public boolean userExists(UserDTO userDTO) {
        User user = userRepository.getByUserName(userDTO.getUserName());
        if (user == null) {
            return false;
        }

        return bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword());
    }
}
