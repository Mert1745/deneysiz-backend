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

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandService {
    private final BrandRepository repository;
    private final BrandUtil brandUtil;

    public ResponseBuilder<List<BrandDTO>> getMappedBrands(int categoryID) {
        try {
            final String categoryName = brandUtil.getCategoryNameByCategoryId(categoryID);
            final List<Brand> brandsByCategory = categoryName.equals(Category.ALL.getName())
                    ? repository.findAll()
                    : repository.findBrandsByCategory(categoryName);
            final List<BrandDTO> mappedBrands = brandUtil.mapBrandsToBrandsDTO(brandsByCategory);
            return ResponseBuilder.<List<BrandDTO>>getInstance().
                    data(mappedBrands)
                    .status(HttpStatus.OK.value())
                    .message("Success");
        } catch (Exception e) {
            return ResponseBuilder.<List<BrandDTO>>getInstance().
                    message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public ResponseBuilder<Brand> addNewBrand(final Brand brand) {
        try {
            final Brand savedBrand = repository.save(brand);
            return ResponseBuilder.<Brand>getInstance().
                    data(savedBrand)
                    .status(HttpStatus.OK.value())
                    .message("Added Successfully");
        } catch (Exception e) {
            return ResponseBuilder.<Brand>getInstance().
                    data(null)
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Could not added. Error cause: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
