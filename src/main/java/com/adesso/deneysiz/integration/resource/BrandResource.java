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
        final List<BrandDTO> mappedBrands;
        try {
            mappedBrands = brandService.getMappedBrands(categoryDTO.getCategoryId());
        } catch (Exception e) {
            return ResponseBuilder.<List<BrandDTO>>getInstance().
                    message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseBuilder.<List<BrandDTO>>getInstance().
                data(mappedBrands)
                .status(HttpStatus.OK.value())
                .message("Success");
    }

    @PostMapping("/add")
    public ResponseBuilder<Brand> getBrandsByCategory(@RequestBody Brand brand) {
        try {
            final Brand saved = brandService.addNewBrand(brand);
            return ResponseBuilder.<Brand>getInstance().
                    data(saved)
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
