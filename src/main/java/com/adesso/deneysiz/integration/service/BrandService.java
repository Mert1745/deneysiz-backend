package com.adesso.deneysiz.integration.service;

import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.BrandDTO;
import com.adesso.deneysiz.integration.domain.Category;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import com.adesso.deneysiz.integration.util.BrandUtil;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.adesso.deneysiz.integration.response.ResponseHandler.*;

@RequiredArgsConstructor
@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandUtil brandUtil;

    public ResponseBuilder<List<BrandDTO>> getMappedBrands(int categoryID) {
        final List<Brand> brandsByCategory = getBrandsByCategory(categoryID);
        final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToBrandsDTO(brandsByCategory);
        return mappedBrands.size() == 0 ? getNotFoundResponse() : getSuccessResponse(mappedBrands);
    }

    public ResponseBuilder<List<BrandDTO>> listMappedBrands(int categoryID) {
        final List<Brand> brandsByCategory = getBrandsByCategory(categoryID);
        final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToLessDetailedBrandDTO(brandsByCategory);
        return mappedBrands.size() == 0 ? getNotFoundResponse() : getSuccessResponse(mappedBrands);
    }

    private List<Brand> getBrandsByCategory(int categoryID) {
        final String categoryName = brandUtil.getCategoryNameByCategoryId(categoryID);
        return categoryName.equals(Category.ALL.getName()) ? brandRepository.findAll()
                : brandRepository.findBrandsByCategory(categoryName);
    }

    public ResponseBuilder<List<Brand>> addNewBrand(final Brand brand) {
        List<Category> categoryList = Arrays.stream(Category.values())
                .filter(category -> category.getName().equals(brand.getCategory()))
                .collect(Collectors.toList());
        if (categoryList.isEmpty()) return getCategoryNameNotValidResponse();
        final Brand savedBrand = brandRepository.save(brand);
        return getSuccessResponse(Collections.singletonList(savedBrand));
    }

    public ResponseBuilder<List<BrandDTO>> searchByNameAndCategoryName(final String query) {
        final List<Brand> brands =
                brandRepository.findByNameContainingIgnoreCaseOrParentCompanyContainingIgnoreCase(query, query);
        final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToLessDetailedBrandDTO(brands);
        return mappedBrands.size() == 0 ? getNotFoundResponse() : getSuccessResponse(mappedBrands);
    }

    public ResponseBuilder<List<BrandDTO>> getBrandsById(final Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        return brand.isPresent()
                ? getSuccessResponse(brandUtil.mapBrandsToBrandsDTO(Collections.singletonList(brand.get())))
                : getNotFoundResponse();
    }
}
