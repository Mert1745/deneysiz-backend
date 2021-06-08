package com.adesso.deneysiz.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //TODO idea: define abstract base entity
    //TODO idea: set to passive instead of removing
    @Column(name = "name")
    private String name;
    @Column(name = "parent_company")
    private String parentCompany;
    @Column(name = "is_offer_in_china")
    private boolean isOfferInChina;
    @Column(name = "category")
    private String category;
    @Column(name = "is_parent_company_experiment")
    private boolean isParentCompanyExperiment;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "certificate")
    private String certificate;
    @Column(name = "is_experiment")
    private boolean isExperiment;
    @Column(name = "is_vegan")
    private boolean isVegan;
    @Column(name = "has_vegan_product")
    private boolean hasVeganProduct;

    public Brand() {
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentCompany='" + parentCompany + '\'' +
                ", isOfferInChina=" + isOfferInChina +
                ", category='" + category + '\'' +
                ", isParentCompanyExperiment=" + isParentCompanyExperiment +
                ", shopName=" + shopName +
                ", certificate='" + certificate + '\'' +
                ", isExperiment='" + isExperiment + '\'' +
                ", isVegan='" + isVegan + '\'' +
                ", hasVeganProduct='" + hasVeganProduct + '\'' +
                '}';
    }
}
