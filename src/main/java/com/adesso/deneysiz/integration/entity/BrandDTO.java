package com.adesso.deneysiz.integration.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BrandDTO {
    private Long id;
    private String name;
    private String parentCompany;
    private boolean offerInChina;
    private int category;
    private boolean parentCompanySafe;
    private List<String> shopName;
    private List<Certificate> certificate;
    private boolean isSafe;
    private boolean vegan;
    private boolean hasVeganProduct;
}
