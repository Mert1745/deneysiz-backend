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
    private List<Certificate> certificates;
    private Boolean safe;
    private Boolean vegan;
    private Boolean veganProduct;
    private Integer score;
    private String description;
    private String createdAt;
}
