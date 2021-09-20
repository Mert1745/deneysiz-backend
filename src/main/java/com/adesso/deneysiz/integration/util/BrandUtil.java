package com.adesso.deneysiz.integration.util;

import com.adesso.deneysiz.integration.domain.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.adesso.deneysiz.integration.domain.CertificateName.*;

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
                    .withParentCompany(brand.getParentCompany(), brand.isParentCompanySafe())
                    .withOfferInChina(brand.isOfferInChina())
                    .withSafe(brand.isSafe())
                    .withVegan(brand.isVegan())
                    .withHasVeganProduct(brand.isHasVeganProduct())
                    .withCategoryId(String.valueOf(getCategoryIdByCategoryName(brand.getCategory())))
                    .withCertificate(getCertificateList(brand.getCertificate()))
                    .withScore(getBrandScore(brand.isSafe(), brand.isParentCompanySafe(), brand.isVegan(), brand.isOfferInChina(), brand.isHasVeganProduct()))
                    .withText(brand.getText())
                    .withCreatedAt(brand.getCreatedAt())
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
                    .withParentCompany(brand.getParentCompany(), brand.isParentCompanySafe())
                    .withScore(getBrandScore(brand.isSafe(), brand.getParentCompany().isEmpty() ? null : brand.isParentCompanySafe(), brand.isVegan(), brand.isOfferInChina(), brand.isHasVeganProduct()))
                    .getBrandDTO();
            brandDTOList.add(brandDTO);
        }
        return brandDTOList;
    }


    private int getCategoryIdByCategoryName(final String categoryName) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getName().equals(categoryName))
                .collect(Collectors.toList())
                .get(0).getId();
    }

    private List<Certificate> getCertificateList(final String certificateNames) {
        //TODO mkose learn the last certificate name
        final List<String> allCertificateNames = Arrays.stream(CertificateName.values()).map(CertificateName::getName).collect(Collectors.toList());
        final String[] arrayOfCertificate = certificateNames.split(",");
        final Set<String> setOfCertificate = new HashSet<>(Arrays.asList(arrayOfCertificate));
        List<Certificate> allCertificates = new ArrayList<>();

        allCertificateNames.forEach(certificateName ->
                allCertificates.add(new Certificate(certificateName, setOfCertificate.contains(certificateName))));
        return allCertificates;
    }

    private int getBrandScore(boolean safe, Boolean parentCompanySafe, boolean vegan, boolean offerInChina, boolean hasVeganProduct) {
        int total = 0;
        total = total + (safe ? 4 : 0);
        total = total + (vegan ? 1 : 0);
        total = total + (offerInChina ? 1 : 0);
        total = total + (hasVeganProduct ? 1 : 0);
        if (parentCompanySafe == null) {
            return total + 3;
        }
        total = total + (parentCompanySafe ? 3 : 0);
        return total;
    }
}
