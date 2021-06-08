package com.adesso.deneysiz.controller;

import com.adesso.deneysiz.entity.Brand;
import com.adesso.deneysiz.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandRepository brandRepository;

    @GetMapping
    public ResponseEntity<Page<Brand>> getBrands(Pageable pageable) {
        //TODO mkose put Service layer
        Page<Brand> brands = brandRepository.findAll(pageable);
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }
}
