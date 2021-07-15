package com.adesso.deneysiz.integration.service;

import com.adesso.deneysiz.integration.entity.Brand;
import com.adesso.deneysiz.integration.entity.BrandDTO;
import com.adesso.deneysiz.integration.entity.Category;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import com.adesso.deneysiz.integration.util.BrandUtil;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandService {
    private static final String SUCCESS = "Success";
    private static final String SEARCH_SUCCESS = "Searched Successfully";
    private static final String ADDED_SUCCESSFULLY = "Added Successfully";
    private static final String ERROR_MESSAGE = "Error occured. Root cause is ";

    private final BrandRepository brandRepository;
    private final BrandUtil brandUtil;

    public ResponseBuilder<List<BrandDTO>> getMappedBrands(int categoryID) {
        try {
            final List<Brand> brandsByCategory = getBrandsByCategory(categoryID);
            final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToBrandsDTO(brandsByCategory);
            return getSuccessResponse(mappedBrands, SUCCESS);
        } catch (Exception e) {
            return getFailedError(Collections.singletonList(new BrandDTO()), e);
        }
    }

    public ResponseBuilder<List<BrandDTO>> listMappedBrands(int categoryID) {
        try {
            final List<Brand> brandsByCategory = getBrandsByCategory(categoryID);
            final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToLessDetailedBrandDTO(brandsByCategory);
            return getSuccessResponse(mappedBrands, SUCCESS);
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
            final Brand savedBrand = brandRepository.save(brand);
            return getSuccessResponse(Collections.singletonList(savedBrand), ADDED_SUCCESSFULLY);
        } catch (Exception e) {
            return getFailedError(Collections.singletonList(new Brand()), e);
        }
    }

    public ResponseBuilder<List<BrandDTO>> searchByNameAndCategoryName(final String query) {
        try {
            final List<Brand> brands = brandRepository.findByNameContainingIgnoreCaseOrParentCompanyContainingIgnoreCase(query, query);
            final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToLessDetailedBrandDTO(brands);
            return getSuccessResponse(mappedBrands, SEARCH_SUCCESS);
        } catch (Exception e) {
            return getFailedError(Collections.singletonList(new BrandDTO()), e);
        }
    }

    private <T> ResponseBuilder<List<T>> getSuccessResponse(List<T> mappedBrands, String message) {
        return ResponseBuilder.<List<T>>getInstance().
                data(mappedBrands)
                .status(HttpStatus.OK.value())
                .message(message);
    }

    private <T> ResponseBuilder<List<T>> getFailedError(List<T> type, Exception e) {
        return ResponseBuilder.<List<T>>getInstance().
                message(ERROR_MESSAGE + e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
