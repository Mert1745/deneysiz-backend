package com.adesso.deneysiz.integration.util;

import com.adesso.deneysiz.integration.domain.BrandDTO;
import com.adesso.deneysiz.integration.domain.Certificate;
import com.adesso.deneysiz.integration.domain.ParentCompany;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BrandDTOBuilder {
    public BrandDTO brandDTO;
    private static BrandDTOBuilder singletonBuilder = null;

    private BrandDTOBuilder() {
    }

    public BrandDTOBuilder newBrandDTO() {
        brandDTO = new BrandDTO();
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

    public BrandDTOBuilder withParentCompany(String parentCompanyName, Boolean parentCompanySafe) {
        ParentCompany parentCompany = new ParentCompany(parentCompanyName.isEmpty() ? null : parentCompanyName,
                parentCompanyName.isEmpty() ? null : parentCompanySafe);
        brandDTO.setParentCompany(parentCompany);
        return this;
    }

    public BrandDTOBuilder withOfferInChina(boolean offerInChina) {
        brandDTO.setOfferInChina(offerInChina);
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
        brandDTO.setVeganProduct(hasVeganProduct);
        return this;
    }

    public BrandDTOBuilder withCategoryId(List<String> categoryId) {
        brandDTO.setCategoryId(categoryId);
        return this;
    }

    public BrandDTOBuilder withCertificate(List<Certificate> certificateList) {
        brandDTO.setCertificates(certificateList);
        return this;
    }

    public BrandDTOBuilder withScore(int score) {
        brandDTO.setScore(score);
        return this;
    }

    public BrandDTOBuilder withText(String text) {
        brandDTO.setDescription(text);
        return this;
    }

    public BrandDTOBuilder withCreatedAt(Date createdAt) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(createdAt);
        brandDTO.setCreatedAt(date);
        return this;
    }
}
