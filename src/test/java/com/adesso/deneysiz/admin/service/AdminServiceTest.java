package com.adesso.deneysiz.admin.service;

import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.Category;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private BrandRepository brandRepository;

    @Test
    void saveNewBrand_success() {
        when(brandRepository.save(any())).thenReturn(new Brand());

        ResponseBuilder<AdminDTO> responseBuilder = adminService.saveNewBrand(new Brand());

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(Boolean.TRUE, responseBuilder.getData().getSuccess());
    }

    @Test
    void saveNewBrand_exception() {
        when(brandRepository.save(any())).thenThrow(new RuntimeException());

        ResponseBuilder<AdminDTO> responseBuilder = adminService.saveNewBrand(new Brand());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(Boolean.FALSE, responseBuilder.getData().getSuccess());
    }

    @Test
    void getAllBrands_success() {
        when(brandRepository.findAll()).thenReturn(Collections.singletonList(getBrand()));

        ResponseBuilder<List<Brand>> responseBuilder = adminService.getAllBrands();

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        Assertions.assertThat(responseBuilder.getData()).hasSize(1);
        Assertions.assertThat(responseBuilder.getData().get(0).getName()).isEqualTo("Marka Adı");
    }

    @Test
    void getAllBrands_exception() {
        when(brandRepository.findAll()).thenThrow(new RuntimeException());

        ResponseBuilder<List<Brand>> responseBuilder = adminService.getAllBrands();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        Assertions.assertThat(responseBuilder.getData()).hasSize(0);
    }

    @Test
    void getBrandById_success() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(getBrand()));

        ResponseBuilder<List<Brand>> responseBuilder = adminService.getBrandById(1L);

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        Assertions.assertThat(responseBuilder.getData()).hasSize(1);
        Assertions.assertThat(responseBuilder.getData().get(0).getName()).isEqualTo("Marka Adı");
    }

    @Test
    void getBrandById_exception() {
        when(brandRepository.findById(1L)).thenThrow(new RuntimeException());

        ResponseBuilder<List<Brand>> responseBuilder = adminService.getBrandById(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBuilder.getStatus());
        assertNull(responseBuilder.getData());
    }

    @Test
    void deleteBrandById_success() {
        ResponseBuilder<AdminDTO> responseBuilder = adminService.deleteBrandById(10234L);

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(Boolean.TRUE, responseBuilder.getData().getSuccess());
    }

    @Test
    void deleteBrandById_exception() {
        doThrow(new RuntimeException()).when(brandRepository).deleteById(1L);

        ResponseBuilder<AdminDTO> responseBuilder = adminService.deleteBrandById(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(Boolean.FALSE, responseBuilder.getData().getSuccess());
    }

    private Brand getBrand() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Marka Adı");
        brand.setCategory(new String[]{Category.MAKEUP.getName()});
        brand.setHasVeganProduct(Boolean.FALSE);
        brand.setParentCompany("Çatı Firma");
        brand.setParentCompanySafe(Boolean.TRUE);

        return brand;
    }
}