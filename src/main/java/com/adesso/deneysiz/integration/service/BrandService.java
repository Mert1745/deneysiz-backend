package com.adesso.deneysiz.integration.service;

import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.BrandDTO;
import com.adesso.deneysiz.integration.domain.Category;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import com.adesso.deneysiz.integration.util.BrandUtil;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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
        final List<Brand> allBrands = brandRepository.findAll();

        if (categoryName.equals(Category.ALL.getName()))  return allBrands;

        return allBrands.stream()
                .filter(brand -> Arrays.asList(brand.getCategory()).contains(categoryName))
                .collect(Collectors.toList());
    }

    public ResponseBuilder<List<Brand>> addNewBrand(final Brand brand) {
        List<Category> categoryList = new ArrayList<>();

        Arrays.stream(brand.getCategory())
                .forEach(categoryName -> Arrays.stream(Category.values())
                .filter(category -> category.getName().equals(categoryName))
                .findFirst()
                .ifPresent(categoryList::add));

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
