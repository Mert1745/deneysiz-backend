package com.adesso.deneysiz.integration.service;

import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.BrandDTO;
import com.adesso.deneysiz.integration.domain.Category;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import com.adesso.deneysiz.integration.util.BrandUtil;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.adesso.deneysiz.integration.constant.ResponseMessage.*;
import static com.adesso.deneysiz.integration.response.CustomResponse.getFailedError;
import static com.adesso.deneysiz.integration.response.CustomResponse.getSuccessResponse;

@RequiredArgsConstructor
@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandUtil brandUtil;

    public ResponseBuilder<List<BrandDTO>> getMappedBrands(int categoryID) {
        try {
            final List<Brand> brandsByCategory = getBrandsByCategory(categoryID);
            final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToBrandsDTO(brandsByCategory);
            return mappedBrands.size() == 0
                    ? getSuccessResponse(mappedBrands, HttpStatus.NOT_FOUND, BRAND_NOT_FOUND)
                    : getSuccessResponse(mappedBrands, HttpStatus.OK, SEARCH_SUCCESS);
        } catch (Exception e) {
            return getFailedError(Collections.singletonList(new BrandDTO()), e);
        }
    }

    public ResponseBuilder<List<BrandDTO>> listMappedBrands(int categoryID) {
        try {
            final List<Brand> brandsByCategory = getBrandsByCategory(categoryID);
            final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToLessDetailedBrandDTO(brandsByCategory);
            return mappedBrands.size() == 0
                    ? getSuccessResponse(mappedBrands, HttpStatus.NOT_FOUND, BRAND_NOT_FOUND)
                    : getSuccessResponse(mappedBrands, HttpStatus.OK, SEARCH_SUCCESS);
        } catch (Exception e) {
            return getFailedError(Collections.singletonList(new BrandDTO()), e);
        }
    }

    private List<Brand> getBrandsByCategory(int categoryID) {
        final String categoryName = brandUtil.getCategoryNameByCategoryId(categoryID);
        return categoryName.equals(Category.ALL.getName())
                ? brandRepository.findAll()
                : brandRepository.findBrandsByCategory(categoryName);
    }

    public ResponseBuilder<List<Brand>> addNewBrand(final Brand brand) {
        try {
            List<Category> categoryList = Arrays.stream(Category.values())
                    .filter(category -> category.getName().equals(brand.getCategory()))
                    .collect(Collectors.toList());
            if (categoryList.isEmpty()) {
                return ResponseBuilder.<List<Brand>>getInstance().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Category Name is not valid");
            }
            final Brand savedBrand = brandRepository.save(brand);
            return getSuccessResponse(Collections.singletonList(savedBrand), HttpStatus.OK, ADDED_SUCCESSFULLY);
        } catch (Exception e) {
            return getFailedError(Collections.singletonList(new Brand()), e);
        }
    }

    public ResponseBuilder<List<BrandDTO>> searchByNameAndCategoryName(final String query) {
        try {
            final List<Brand> brands = brandRepository.findByNameContainingIgnoreCaseOrParentCompanyContainingIgnoreCase(query, query);
            final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToLessDetailedBrandDTO(brands);
            return mappedBrands.size() == 0
                    ? getSuccessResponse(mappedBrands, HttpStatus.NOT_FOUND, BRAND_NOT_FOUND)
                    : getSuccessResponse(mappedBrands, HttpStatus.OK, SEARCH_SUCCESS);
        } catch (Exception e) {
            return getFailedError(Collections.singletonList(new BrandDTO()), e);
        }
    }

    public ResponseBuilder<List<BrandDTO>> getBrandsById(final Long id) {
        try {
            Optional<Brand> brand = brandRepository.findById(id);
            return getSuccessResponse(brand.isPresent() ? brandUtil.mapBrandsToBrandsDTO(Collections.singletonList(brand.get())) :
                            Collections.singletonList(new BrandDTO()),
                    brand.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND,
                    brand.isPresent() ? SUCCESS : BRAND_NOT_FOUND);
        } catch (Exception e) {
            return getFailedError(Collections.singletonList(new BrandDTO()), e);
        }
    }
}
