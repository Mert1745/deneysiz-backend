package com.adesso.deneysiz.integration.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BrandDTO {
    private Long id;
    private String name;
    private String parentCompany;
    private Boolean offerInChina;
    private String categoryId;
    private Boolean parentCompanySafe;
    private List<String> shopName;
    private List<Certificate> certificate;
    private Boolean isSafe;
    private Boolean vegan;
    private Boolean hasVeganProduct;
    private int score;
}
