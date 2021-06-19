package com.adesso.deneysiz.integration.service;

import com.adesso.deneysiz.integration.entity.Brand;
import com.adesso.deneysiz.integration.entity.BrandDTO;
import com.adesso.deneysiz.integration.entity.Category;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import com.adesso.deneysiz.integration.util.BrandUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandService {
    private final BrandRepository repository;
    private final BrandUtil brandUtil;

    public List<BrandDTO> getMappedBrands(int categoryID) {
        final String categoryName = brandUtil.getCategoryNameByCategoryId(categoryID);
        final List<Brand> brandsByCategory = categoryName.equals(Category.ALL.getName())
                ? repository.findAll()
                : repository.findBrandsByCategory(categoryName);
        return brandUtil.mapBrandsToBrandsDTO(brandsByCategory);
    }
}
