package com.adesso.deneysiz.controller;

import com.adesso.deneysiz.entity.BrandDTO;
import com.adesso.deneysiz.entity.CategoryDTO;
import com.adesso.deneysiz.service.BrandService;
import com.adesso.deneysiz.util.BrandResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/brandsByCategory")
    public BrandResponseBuilder<List<BrandDTO>> getBrandsByCategory(@RequestBody CategoryDTO categoryDTO) {
        final List<BrandDTO> mappedBrands;
        try {
            mappedBrands = brandService.getMappedBrands(categoryDTO.getCategoryId());
        } catch (Exception e) {
            return BrandResponseBuilder.<List<BrandDTO>>getInstance().
                    message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return BrandResponseBuilder.<List<BrandDTO>>getInstance().
                brands(mappedBrands)
                .status(HttpStatus.OK.value());
    }
}
