package com.adesso.deneysiz.admin;

import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.repository.UserRepository;
import com.adesso.deneysiz.integration.entity.Brand;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminResource {
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @PostMapping("/login")
    public AdminResponseBuilder login(@RequestBody UserDTO userDTO) {
        return userService.getLoginResponse(userDTO);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody UserDTO userDTO) {
        final User user = new User(userDTO.getUserName(), bCryptPasswordEncoder.encode(userDTO.getPassword()), userDTO.getRole());
        return userRepository.save(user);
    }

    @PostMapping("/addBrand")
    public boolean addBrand(@RequestBody Brand brand) {
        try {
            brandRepository.save(brand);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping("/getAllBrands")
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
