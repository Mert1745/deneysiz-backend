package com.adesso.deneysiz.admin.service;

import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.adesso.deneysiz.admin.response.CustomResponse.getFailedResponse;
import static com.adesso.deneysiz.admin.response.CustomResponse.getSuccessResponse;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final BrandRepository brandRepository;

    public ResponseBuilder<AdminDTO> saveNewBrand(final Brand brand) {
        AdminDTO adminDTO = new AdminDTO();
        //TODO mkose remove try/catch blocks
        try {
            brandRepository.save(brand);
        } catch (Exception e) {
            adminDTO.setSuccess(false);
            return getFailedResponse(adminDTO, e);
        }
        adminDTO.setSuccess(true);
        return getSuccessResponse(adminDTO);
    }

    public ResponseBuilder<List<Brand>> getAllBrands() {
        try {
            final List<Brand> all = brandRepository.findAll();
            return getSuccessResponse(all);
        } catch (Exception e) {
            return getFailedResponse(Collections.emptyList(), e);
        }
    }

    public ResponseBuilder<List<Brand>> getBrandById(final long id) {
        try {
            Optional<Brand> brandOptional = brandRepository.findById(id);
            return getSuccessResponse(Collections.singletonList(brandOptional.get()));
        } catch (Exception e) {
            return getFailedResponse(null, e);
        }
    }

    public ResponseBuilder<AdminDTO> deleteBrandById(final Long id) {
        AdminDTO adminDTO = new AdminDTO();
        try {
            brandRepository.deleteById(id);
        } catch (Exception e) {
            adminDTO.setSuccess(false);
            return getFailedResponse(adminDTO, e);
        }
        adminDTO.setSuccess(true);
        return getSuccessResponse(adminDTO);
    }
}
