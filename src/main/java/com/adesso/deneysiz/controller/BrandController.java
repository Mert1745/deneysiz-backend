package com.adesso.deneysiz.controller;

import com.adesso.deneysiz.entity.Brand;
import com.adesso.deneysiz.repository.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BrandController {
    BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping("/getBrands")
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> brands = brandRepository.findAll();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }
}
