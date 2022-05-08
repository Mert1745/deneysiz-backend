package com.adesso.deneysiz.admin.resource;

import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.admin.entity.User;
import com.adesso.deneysiz.admin.entity.UserDTO;
import com.adesso.deneysiz.admin.service.AdminService;
import com.adesso.deneysiz.admin.service.UserService;
import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.BrandDTO;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.adesso.deneysiz.admin.constant.AdminUrlConstants.*;

@RestController
@RequestMapping(ADMIN)
@RequiredArgsConstructor
public class AdminResource {
    private final UserService userService;
    private final AdminService adminService;

    @PostMapping(LOGIN)
    public ResponseBuilder<AdminDTO> login(@RequestBody UserDTO userDTO) {
        return userService.getLoginResponse(userDTO);
    }

    @PostMapping(SAVE_USER)
    public ResponseBuilder<User> saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PostMapping(SAVE_BRAND)
    public ResponseBuilder<AdminDTO> saveBrand(@RequestBody Brand brand) {
        return adminService.saveNewBrand(brand);
    }

    @GetMapping(GET_ALL_BRANDS)
    public ResponseBuilder<List<Brand>> getAllBrands() {
        return adminService.getAllBrands();
    }

    @PostMapping(GET_BRANDS_BY_ID)
    public ResponseBuilder<List<Brand>> getBrandById(@RequestBody Brand brand) {
        return adminService.getBrandById(brand.getId());
    }

    @PostMapping(DELETE_BRANDS_BY_ID)
    public ResponseBuilder<AdminDTO> deleteBrandById(@RequestBody BrandDTO brandDTO) {
        return adminService.deleteBrandById(brandDTO.getId());
    }
}
