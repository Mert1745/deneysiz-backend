package com.adesso.deneysiz.admin.service;

import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.integration.entity.Brand;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final BrandRepository brandRepository;

    public ResponseBuilder<AdminDTO> saveNewBrand(final Brand brand) {
        AdminDTO adminDTO = new AdminDTO();
        try {
            brandRepository.save(brand);
        } catch (Exception e) {
            adminDTO.setSuccess(false);
            return ResponseBuilder.<AdminDTO>getInstance().data(adminDTO).message("Error occured. Root cause is " + e.getMessage());
        }
        adminDTO.setSuccess(true);
        return ResponseBuilder.<AdminDTO>getInstance().data(adminDTO).message("Success");
    }

    public ResponseBuilder<List<Brand>> getAllBrands() {
        try {
            final List<Brand> all = brandRepository.findAll();
            return ResponseBuilder.<List<Brand>>getInstance().data(all).message("Success").status(HttpStatus.OK.value());
        } catch (Exception e) {
            return ResponseBuilder.<List<Brand>>getInstance()
                    .data(Collections.emptyList())
                    .message("Error occured. Root cause is " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public ResponseBuilder<AdminDTO> deleteBrandById(final Long id) {
        AdminDTO adminDTO = new AdminDTO();
        try {
            brandRepository.deleteById(id);
        } catch (Exception e) {
            adminDTO.setSuccess(false);
            return ResponseBuilder.<AdminDTO>getInstance().data(adminDTO).message("Error occured. Root cause is " + e.getMessage());
        }
        adminDTO.setSuccess(true);
        return ResponseBuilder.<AdminDTO>getInstance().data(adminDTO).message("Success");
    }
}
