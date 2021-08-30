package com.adesso.deneysiz.integration.util;

import com.adesso.deneysiz.integration.entity.Brand;
import com.adesso.deneysiz.integration.entity.BrandDTO;
import com.adesso.deneysiz.integration.entity.Category;
import com.adesso.deneysiz.integration.entity.Certificate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BrandUtil {
    public String getCategoryNameByCategoryId(final int categoryID) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getId() == categoryID)
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .get()
                .getName();
    }

    public List<BrandDTO> mapBrandsToBrandsDTO(final List<Brand> brandsByCategory) {
        List<BrandDTO> brandDTOList = new ArrayList<>();
        BrandDTO brandDTO;

        for (Brand brand : brandsByCategory) {
            brandDTO = BrandDTOBuilder.instance()
                    .newBrandDTO()
                    .withId(brand.getId())
                    .withName(brand.getName())
                    .withParentCompany(brand.getParentCompany())
                    .withOfferInChina(brand.isOfferInChina())
                    .withParentCompany(brand.getParentCompany())
                    .withParentCompanySafe(brand.isParentCompanySafe())
                    .withSafe(brand.isSafe())
                    .withVegan(brand.isVegan())
                    .withHasVeganProduct(brand.isHasVeganProduct())
                    .withCategoryId(String.valueOf(getCategoryIdByCategoryName(brand.getCategory())))
                    .withCertificate(getCertificateList(brand.getCertificate()))
                    .withScore(getBrandScore(brand.isSafe(), brand.isParentCompanySafe(), brand.isVegan(), brand.isOfferInChina()))
                    .getBrandDTO();

            brandDTOList.add(brandDTO);
        }

        return brandDTOList;
    }

    public List<BrandDTO> mapBrandsToLessDetailedBrandDTO(List<Brand> brands) {
        List<BrandDTO> brandDTOList = new ArrayList<>();
        BrandDTO brandDTO;

        for (Brand brand : brands) {
            brandDTO = BrandDTOBuilder.instance()
                    .newBrandDTO()
                    .withId(brand.getId())
                    .withName(brand.getName())
                    .withParentCompany(brand.getParentCompany())
                    .withScore(getBrandScore(brand.isSafe(), brand.isParentCompanySafe(), brand.isVegan(), brand.isOfferInChina()))
                    .getBrandDTO();
            brandDTOList.add(brandDTO);
        }
        return brandDTOList;
    }

    private List<String> getShopNameList(String shopNames) {
        return Arrays.asList(shopNames.split(","));
    }

    private int getCategoryIdByCategoryName(final String categoryName) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getName().equals(categoryName))
                .collect(Collectors.toList())
                .get(0).getId();
    }

    private List<Certificate> getCertificateList(final String certificateNames) {
        //TODO mkose learn the last certificate name
        final List<String> allCertificateNames = new ArrayList<>(Arrays.asList("LB", "Peta", "Unilever", "XXXX"));
        final String[] arrayOfCertificate = certificateNames.split(",");
        final Set<String> setOfCertificate = new HashSet<>(Arrays.asList(arrayOfCertificate));
        List<Certificate> allCertificates = new ArrayList<>();

        allCertificateNames.forEach(certificateName ->
                allCertificates.add(new Certificate(certificateName, setOfCertificate.contains(certificateName))));
        return allCertificates;
    }

    private int getBrandScore(boolean safe, boolean parentCompanySafe, boolean vegan, boolean offerInChina) {
        int total = 0;
        total = total + (safe ? 4 : 0);
        total = total + (parentCompanySafe ? 2 : 0);
        total = total + (vegan ? 2 : 0);
        total = total + (offerInChina ? 4 : 0);
        return total;
    }
}
