package com.adesso.deneysiz.integration.resource;

import com.adesso.deneysiz.integration.entity.Brand;
import com.adesso.deneysiz.integration.entity.BrandDTO;
import com.adesso.deneysiz.integration.entity.CategoryDTO;
import com.adesso.deneysiz.integration.entity.NameDTO;
import com.adesso.deneysiz.integration.service.BrandService;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandResource {
    private final BrandService brandService;

    @PostMapping("/byCategory")
    public ResponseBuilder<List<BrandDTO>> getBrandsByCategory(@RequestBody CategoryDTO categoryDTO) {
        return brandService.getMappedBrands(categoryDTO.getCategoryId());
    }

    @PostMapping("/add")
    public ResponseBuilder<List<Brand>> addNewBrand(@RequestBody Brand brand) {
        return brandService.addNewBrand(brand);
    }

    @PostMapping("/search")
    public ResponseBuilder<List<BrandDTO>> searchByNameAndCategoryName(@RequestBody NameDTO nameDTO) {
        return brandService.searchByNameAndCategoryName(nameDTO.getQuery());
    }

    @PostMapping("/list")
    public ResponseBuilder<List<BrandDTO>> listBrands(@RequestBody CategoryDTO categoryDTO) {
        return brandService.listMappedBrands(categoryDTO.getCategoryId());
    }

    @PostMapping("/detail")
    public ResponseBuilder<List<BrandDTO>> getBrandsById(@RequestBody Brand brand) {
        return brandService.getBrandsById(brand.getId());
    }
}
