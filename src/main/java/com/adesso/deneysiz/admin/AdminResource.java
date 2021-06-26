package com.adesso.deneysiz.admin;

import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminResource {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final JWTProvider jwtProvider;

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        if (userService.userExists(userDTO)) {
            return jwtProvider.createToken(userDTO);
        }
        return "false";
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO.getUserName(), bCryptPasswordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }
}
