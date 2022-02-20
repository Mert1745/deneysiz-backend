package com.adesso.deneysiz.integration.resource;


import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.BrandDTO;
import com.adesso.deneysiz.integration.domain.CategoryDTO;
import com.adesso.deneysiz.integration.domain.NameDTO;
import com.adesso.deneysiz.integration.service.BrandService;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BrandResourceTest {

    @InjectMocks
    private BrandResource brandResource;

    @Mock
    private BrandService brandService;

    private static ResponseBuilder<List<Brand>> responseBuilderBrand;
    private static ResponseBuilder<List<BrandDTO>> responseBuilderBrandDTO;

    @BeforeAll
    public static void setUp() {
        responseBuilderBrand = ResponseBuilder.<List<Brand>>getInstance().data(Collections.singletonList(new Brand()));
        responseBuilderBrandDTO = ResponseBuilder.<List<BrandDTO>>getInstance().data(Collections.singletonList(new BrandDTO()));
    }

    @Test
    public void addNewBrand() {
        when(brandService.addNewBrand(any())).thenReturn(responseBuilderBrand);

        ResponseBuilder<List<Brand>> listResponseBuilder = brandResource.addNewBrand(new Brand());

        Assertions.assertEquals(listResponseBuilder.getData().size(), 1);
    }

    @Test
    void getBrandsByCategory() {
        when(brandService.getMappedBrands(anyInt())).thenReturn(responseBuilderBrandDTO);

        ResponseBuilder<List<BrandDTO>> listResponseBuilder = brandResource.getBrandsByCategory(new CategoryDTO());

        Assertions.assertEquals(listResponseBuilder.getData().size(), 1);
    }

    @Test
    void searchByNameAndCategoryName() {
        when(brandService.searchByNameAndCategoryName(any())).thenReturn(responseBuilderBrandDTO);

        ResponseBuilder<List<BrandDTO>> listResponseBuilder = brandResource.searchByNameAndCategoryName(new NameDTO());

        Assertions.assertEquals(listResponseBuilder.getData().size(), 1);
    }

    @Test
    void listBrands() {
        when(brandService.listMappedBrands(anyInt())).thenReturn(responseBuilderBrandDTO);

        ResponseBuilder<List<BrandDTO>> listResponseBuilder = brandResource.listBrands(new CategoryDTO());

        Assertions.assertEquals(listResponseBuilder.getData().size(), 1);
    }

    @Test
    void getBrandsById() {
        when(brandService.getBrandsById(any())).thenReturn(responseBuilderBrandDTO);

        ResponseBuilder<List<BrandDTO>> listResponseBuilder = brandResource.getBrandsById(new Brand());

        Assertions.assertEquals(listResponseBuilder.getData().size(), 1);
    }
}