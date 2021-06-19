package com.adesso.deneysiz.util;

import com.adesso.deneysiz.entity.BrandDTO;
import com.adesso.deneysiz.entity.Certificate;

import java.util.List;

public class BrandDTOBuilder {
    private static final BrandDTO BRAND_DTO_INSTANCE = new BrandDTO();

    private BrandDTOBuilder() {
    }

    public BrandDTO getInstance() {
        return BRAND_DTO_INSTANCE;
    }

    public static BrandDTOBuilder instance() {
        return new BrandDTOBuilder();
    }

    public BrandDTOBuilder withId(Long id) {
        getInstance().setId(id);
        return this;
    }

    public BrandDTOBuilder withName(String name) {
        getInstance().setName(name);
        return this;
    }

    public BrandDTOBuilder withParentCompany(String parentCompany) {
        getInstance().setParentCompany(parentCompany);
        return this;
    }

    public BrandDTOBuilder withOfferInChina(boolean offerInChina) {
        getInstance().setOfferInChina(offerInChina);
        return this;
    }

    public BrandDTOBuilder withParentCompanySafe(boolean isParentCompanySafe) {
        getInstance().setParentCompanySafe(isParentCompanySafe);
        return this;
    }

    public BrandDTOBuilder withSafe(boolean safe) {
        getInstance().setSafe(safe);
        return this;
    }

    public BrandDTOBuilder withVegan(boolean vegan) {
        getInstance().setVegan(vegan);
        return this;
    }

    public BrandDTOBuilder withHasVeganProduct(boolean hasVeganProduct) {
        getInstance().setHasVeganProduct(hasVeganProduct);
        return this;
    }

    public BrandDTOBuilder withCategory(int category) {
        getInstance().setCategory(category);
        return this;
    }

    public BrandDTOBuilder withCertificate(List<Certificate> certificateList) {
        getInstance().setCertificate(certificateList);
        return this;
    }

    public BrandDTOBuilder withShopName(List<String> shopName) {
        getInstance().setShopName(shopName);
        return this;
    }
}
