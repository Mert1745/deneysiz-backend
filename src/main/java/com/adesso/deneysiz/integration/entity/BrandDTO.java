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
    private boolean offerInChina;
    private String categoryId;
    private boolean parentCompanySafe;
    private List<String> shopName;
    private List<Certificate> certificate;
    private boolean isSafe;
    private boolean vegan;
    private boolean hasVeganProduct;
    private int score;
}
