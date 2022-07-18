package com.adesso.deneysiz.integration.util;

import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.BrandDTO;
import com.adesso.deneysiz.integration.domain.Category;
import com.adesso.deneysiz.integration.domain.CertificateName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class BrandUtilTest {

    @InjectMocks
    private BrandUtil brandUtil;

    @Test
    void getCategoryNameByCategoryId() {
        String categoryNameById = brandUtil.getCategoryNameByCategoryId(Category.BABY_CARE.getId());

        assertEquals(Category.BABY_CARE.getName(), categoryNameById);
    }

    @Test
    void mapBrandsToBrandsDTO() {
        List<BrandDTO> brandDTOList = brandUtil.mapBrandsToBrandsDTO(getBrandList());

        assertEquals(brandDTOList.size(), getBrandList().size());
        assertEquals(brandDTOList.get(0).getName(), "Marka Adı");
        assertEquals(brandDTOList.get(0).getParentCompany().getName(), "Çatı Firma");
        assertEquals(brandDTOList.get(0).getCertificates().get(0).getName(), CertificateName.LB.getName());
    }

    @Test
    void mapBrandsToLessDetailedBrandDTO() {
        List<BrandDTO> brandDTOList = brandUtil.mapBrandsToLessDetailedBrandDTO(getBrandList());

        assertEquals(brandDTOList.size(), getBrandList().size());
        assertEquals(brandDTOList.get(0).getName(), "Marka Adı");
        assertEquals(brandDTOList.get(0).getScore(), 4);
    }

    @Test
    void mapBrandsToLessDetailedBrandDTO_parentCompanyNull() {
        List<Brand> brandList = getBrandList();
        brandList.get(0).setParentCompany("");

        List<BrandDTO> brandDTOList = brandUtil.mapBrandsToLessDetailedBrandDTO(brandList);

        assertEquals(brandDTOList.size(), brandList.size());
        assertEquals(brandDTOList.get(0).getName(), "Marka Adı");
        assertEquals(brandDTOList.get(0).getScore(), 4);
    }

    private List<Brand> getBrandList() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Marka Adı");
        brand.setCategory(new String[]{Category.MAKEUP.getName()});
        brand.setCertificate(CertificateName.LB.getName());
        brand.setHasVeganProduct(Boolean.FALSE);
        brand.setParentCompany("Çatı Firma");
        brand.setParentCompanySafe(Boolean.TRUE);
        brand.setLastModified(new Date());

        return Collections.singletonList(brand);
    }
}