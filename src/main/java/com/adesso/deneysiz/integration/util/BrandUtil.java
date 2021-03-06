package com.adesso.deneysiz.integration.util;

import com.adesso.deneysiz.integration.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
            brandDTO = getBrandDTO(brand);
            brandDTOList.add(brandDTO);
        }

        return brandDTOList;
    }

    public List<BrandDTO> mapBrandsToLessDetailedBrandDTO(List<Brand> brands) {
        List<BrandDTO> brandDTOList = new ArrayList<>();
        BrandDTO brandDTO;

        for (Brand brand : brands) {
            brandDTO = getLessDetailedDTO(brand);
            brandDTOList.add(brandDTO);
        }
        return brandDTOList;
    }

    private BrandDTO getBrandDTO(Brand brand) {
        return BrandDTOBuilder.instance()
                .newBrandDTO()
                .withId(brand.getId())
                .withName(brand.getName())
                .withParentCompany(brand.getParentCompany(), brand.isParentCompanySafe())
                .withOfferInChina(brand.isOfferInChina())
                .withSafe(brand.isSafe())
                .withVegan(brand.isVegan())
                .withHasVeganProduct(brand.isHasVeganProduct())
                .withCategoryId(getCategoryIdArrayByCategoryName(brand.getCategory()))
                .withCertificate(getCertificateList(brand.getCertificate()))
                .withScore(getBrandScore(brand.isSafe(), brand.isParentCompanySafe(), brand.isVegan(), brand.isOfferInChina(), brand.isHasVeganProduct(), brand.getParentCompany()))
                .withText(brand.getText())
                .withCreatedAt(brand.getLastModified())
                .getBrandDTO();
    }

    private BrandDTO getLessDetailedDTO(Brand brand) {
        return BrandDTOBuilder.instance()
                .newBrandDTO()
                .withId(brand.getId())
                .withName(brand.getName())
                .withParentCompany(brand.getParentCompany(), brand.isParentCompanySafe())
                .withScore(getBrandScore(brand.isSafe(), brand.getParentCompany().isEmpty() ? null : brand.isParentCompanySafe(), brand.isVegan(), brand.isOfferInChina(), brand.isHasVeganProduct(), brand.getParentCompany()))
                .getBrandDTO();
    }


    private ArrayList<String> getCategoryIdArrayByCategoryName(final String[] categoryNameArray) {
        ArrayList<String> categoryIdList = new ArrayList<>();

        Arrays.stream(categoryNameArray)
                .forEach(categoryName -> Arrays.stream(Category.values())
                .filter(category -> category.getName().equals(categoryName))
                .findFirst()
                .ifPresent(category -> categoryIdList.add(String.valueOf(category.getId()))));

        return categoryIdList;
    }

    private List<Certificate> getCertificateList(final String certificateNames) {
        final List<String> allCertificateNames = Arrays.stream(CertificateName.values()).map(CertificateName::getName).collect(Collectors.toList());
        final String[] arrayOfCertificate = certificateNames.split(",");
        final Set<String> setOfCertificate = new HashSet<>(Arrays.asList(arrayOfCertificate));
        List<Certificate> allCertificates = new ArrayList<>();

        allCertificateNames.forEach(certificateName ->
                allCertificates.add(new Certificate(certificateName, setOfCertificate.contains(certificateName))));
        return allCertificates;
    }

    private int getBrandScore(boolean safe, Boolean parentCompanySafe,
                              boolean vegan, boolean offerInChina,
                              boolean hasVeganProduct, String parentCompany) {
        int total = 0;
        total = total + (safe ? 4 : 0);
        total = total + (vegan ? 1 : 0);
        total = total + (offerInChina ? 0 : 1);
        total = total + (hasVeganProduct ? 1 : 0);
        if (!StringUtils.hasText(parentCompany)) {
            return total + 3;
        }
        total = total + (parentCompanySafe ? 3 : 0);
        return total;
    }
}
