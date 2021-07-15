package com.adesso.deneysiz.integration.resource;

import com.adesso.deneysiz.integration.entity.Brand;
import com.adesso.deneysiz.integration.entity.BrandDTO;
import com.adesso.deneysiz.integration.entity.CategoryDTO;
import com.adesso.deneysiz.integration.service.BrandService;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    public ResponseBuilder<Brand> getBrandsByCategory(@RequestBody Brand brand) {
        return brandService.addNewBrand(brand);
    }
}
