package com.adesso.deneysiz.admin.service;

import com.adesso.deneysiz.integration.entity.Brand;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final BrandRepository brandRepository;

    public boolean saveNewBrand(final Brand brand) {
        try {
            brandRepository.save(brand);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public boolean deleteBrandById(final Long id) {
        try {
            brandRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
