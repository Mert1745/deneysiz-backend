package com.adesso.deneysiz.integration.util;

import com.adesso.deneysiz.integration.entity.BrandDTO;
import com.adesso.deneysiz.integration.entity.Certificate;

import java.util.List;

public class BrandDTOBuilder {
    public BrandDTO brandDTO;
    private static BrandDTOBuilder singletonBuilder = null;

    private BrandDTOBuilder() {
    }

    public BrandDTOBuilder newBrandDTO() {
        brandDTO =  new BrandDTO();
        return this;
    }

    public BrandDTO getBrandDTO() {
        return brandDTO;
    }

    public static BrandDTOBuilder instance() {
        if (singletonBuilder == null)
            singletonBuilder = new BrandDTOBuilder();
        return singletonBuilder;
    }

    public BrandDTOBuilder withId(Long id) {
        brandDTO.setId(id);
        return this;
    }

    public BrandDTOBuilder withName(String name) {
        brandDTO.setName(name);
        return this;
    }

    public BrandDTOBuilder withParentCompany(String parentCompany) {
        brandDTO.setParentCompany(parentCompany);
        return this;
    }

    public BrandDTOBuilder withOfferInChina(boolean offerInChina) {
        brandDTO.setOfferInChina(offerInChina);
        return this;
    }

    public BrandDTOBuilder withParentCompanySafe(boolean isParentCompanySafe) {
        brandDTO.setParentCompanySafe(isParentCompanySafe);
        return this;
    }

    public BrandDTOBuilder withSafe(boolean safe) {
        brandDTO.setSafe(safe);
        return this;
    }

    public BrandDTOBuilder withVegan(boolean vegan) {
        brandDTO.setVegan(vegan);
        return this;
    }

    public BrandDTOBuilder withHasVeganProduct(boolean hasVeganProduct) {
        brandDTO.setHasVeganProduct(hasVeganProduct);
        return this;
    }

    public BrandDTOBuilder withCategoryId(String categoryId) {
        brandDTO.setCategoryId(categoryId);
        return this;
    }

    public BrandDTOBuilder withCertificate(List<Certificate> certificateList) {
        brandDTO.setCertificate(certificateList);
        return this;
    }

    public BrandDTOBuilder withShopName(List<String> shopName) {
        brandDTO.setShopName(shopName);
        return this;
    }
}
