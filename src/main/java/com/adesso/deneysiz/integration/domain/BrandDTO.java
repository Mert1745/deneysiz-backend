package com.adesso.deneysiz.integration.domain;

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
    private ParentCompany parentCompany;
    private Boolean offerInChina;
    private String categoryId;
    private List<Certificate> certificate;
    private Boolean isSafe;
    private Boolean vegan;
    private Boolean hasVeganProduct;
    private Integer score;
    private String text;
    private String createdAt;
}
