package com.adesso.deneysiz.integration.service;

import com.adesso.deneysiz.integration.domain.*;
import com.adesso.deneysiz.integration.repository.BrandRepository;
import com.adesso.deneysiz.integration.util.BrandUtil;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
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
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BrandServiceTest {

    @InjectMocks
    private BrandService brandService;

    @Mock
    private BrandUtil brandUtil;

    @Mock
    private BrandRepository brandRepository;

    @Test
    void getMappedBrands() {
        when(brandUtil.mapBrandsToBrandsDTO(any())).thenReturn(getMappedBrandList());
        when(brandUtil.getCategoryNameByCategoryId(Category.MAKEUP.getId())).thenReturn(Category.MAKEUP.getName());
        when(brandRepository.findAll()).thenReturn(getBrandList());

        ResponseBuilder<List<BrandDTO>> responseBuilder = brandService.getMappedBrands(Category.MAKEUP.getId());

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(1, responseBuilder.getData().size());
    }

    @Test
    void listMappedBrands() {
        when(brandUtil.mapBrandsToLessDetailedBrandDTO(any())).thenReturn(getMappedBrandList());
        when(brandUtil.getCategoryNameByCategoryId(Category.MAKEUP.getId())).thenReturn(Category.MAKEUP.getName());
        when(brandRepository.findAll()).thenReturn(getBrandList());

        ResponseBuilder<List<BrandDTO>> responseBuilder = brandService.listMappedBrands(Category.MAKEUP.getId());

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(1, responseBuilder.getData().size());
    }

    @Test
    void addNewBrand() {
        when(brandRepository.save(any())).thenReturn(getBrandList().get(0));

        ResponseBuilder<List<Brand>> responseBuilder = brandService.addNewBrand(getBrandList().get(0));

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(1, responseBuilder.getData().size());
    }

    @Test
    void addNewBrand_categoryNameNotValid() {
        Brand brand = getBrandList().get(0);
        brand.setCategory(new String[]{"I'm invalid category name"});

        ResponseBuilder<List<Brand>> responseBuilder = brandService.addNewBrand(brand);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBuilder.getStatus());
        assertNull(responseBuilder.getData());
    }

    @Test
    void searchByNameAndCategoryName() {
        when(brandUtil.mapBrandsToLessDetailedBrandDTO(any())).thenReturn(getMappedBrandList());
        when(brandRepository.findByNameContainingIgnoreCaseOrParentCompanyContainingIgnoreCase(any(), any()))
                .thenReturn(getBrandList());

        ResponseBuilder<List<BrandDTO>> responseBuilder = brandService.searchByNameAndCategoryName("a");

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(1, responseBuilder.getData().size());
    }

    @Test
    void getBrandsById() {
        when(brandRepository.findById(1L)).thenReturn(Optional.of(getBrandList().get(0)));
        when(brandUtil.mapBrandsToBrandsDTO(any())).thenReturn(getMappedBrandList());

        ResponseBuilder<List<BrandDTO>> responseBuilder = brandService.getBrandsById(1L);

        assertEquals(HttpStatus.OK.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(1, responseBuilder.getData().size());
    }
    @Test
    void getBrandsById_brandNotExists() {
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseBuilder<List<BrandDTO>> responseBuilder = brandService.getBrandsById(1L);

        assertEquals(HttpStatus.NOT_FOUND.value(), responseBuilder.getStatus());
        assertNotNull(responseBuilder.getData());
        assertEquals(0, responseBuilder.getData().size());
    }

    private List<Brand> getBrandList() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Marka Adı");
        brand.setCategory(new String[]{Category.MAKEUP.getName()});
        brand.setHasVeganProduct(Boolean.FALSE);
        brand.setParentCompany("Çatı Firma");
        brand.setParentCompanySafe(Boolean.TRUE);

        return Collections.singletonList(brand);
    }


    private List<BrandDTO> getMappedBrandList() {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(1L);
        brandDTO.setName("Marka Adı");
        brandDTO.setParentCompany(new ParentCompany("Çatı Firma", Boolean.FALSE));
        brandDTO.setCategoryId(List.of(String.valueOf(Category.MAKEUP.getId())));
        brandDTO.setVeganProduct(Boolean.FALSE);
        brandDTO.setSafe(Boolean.TRUE);
        brandDTO.setCertificates(List.of(new Certificate(CertificateName.LB.getName(), Boolean.TRUE)));

        return Collections.singletonList(brandDTO);
    }
}