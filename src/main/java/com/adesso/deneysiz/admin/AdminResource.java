package com.adesso.deneysiz.admin;

import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.service.AdminService;
import com.adesso.deneysiz.admin.service.UserService;
import com.adesso.deneysiz.integration.entity.Brand;
import com.adesso.deneysiz.integration.entity.BrandDTO;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminResource {
    private final UserService userService;
    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseBuilder<AdminDTO> login(@RequestBody UserDTO userDTO) {
        return userService.getLoginResponse(userDTO);
    }

    @PostMapping("/saveUser")
    public ResponseBuilder<User> saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PostMapping("/addBrand")
    public ResponseBuilder<AdminDTO> addBrand(@RequestBody Brand brand) {
        return adminService.saveNewBrand(brand);
    }

    @GetMapping("/getAllBrands")
    public ResponseBuilder<List<Brand>> getAllBrands() {
        return adminService.getAllBrands();
    }

    @PostMapping("/deleteBrandById")
    public ResponseBuilder<AdminDTO> deleteBrandById(@RequestBody BrandDTO brandDTO) {
        return adminService.deleteBrandById(brandDTO.getId());
    }
}
