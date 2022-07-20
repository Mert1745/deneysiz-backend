package com.adesso.deneysiz.integration.resource;

import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.BrandDTO;
import com.adesso.deneysiz.integration.domain.CategoryDTO;
import com.adesso.deneysiz.integration.domain.NameDTO;
import com.adesso.deneysiz.integration.service.BrandService;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.adesso.deneysiz.integration.constant.BrandUrlConstants.*;

@RestController
@RequestMapping(BRANDS)
@RequiredArgsConstructor
public class BrandResource {
    private final BrandService brandService;

    @PostMapping(BY_CATEGORY)
    public ResponseBuilder<List<BrandDTO>> getBrandsByCategory(@RequestBody CategoryDTO categoryDTO) {
        return brandService.getMappedBrands(categoryDTO.getCategoryId());
    }

//    @PostMapping(ADD)
//    public ResponseBuilder<List<Brand>> addNewBrand(@RequestBody Brand brand) {
//        return brandService.addNewBrand(brand);
//    }

    @PostMapping(SEARCH)
    public ResponseBuilder<List<BrandDTO>> searchByNameAndCategoryName(@RequestBody NameDTO nameDTO) {
        return brandService.searchByNameAndCategoryName(nameDTO.getQuery());
    }

    @PostMapping(LIST)
    public ResponseBuilder<List<BrandDTO>> listBrands(@RequestBody CategoryDTO categoryDTO) {
        return brandService.listMappedBrands(categoryDTO.getCategoryId());
    }

    @PostMapping(DETAIL)
    public ResponseBuilder<List<BrandDTO>> getBrandsById(@RequestBody Brand brand) {
        return brandService.getBrandsById(brand.getId());
    }
}
