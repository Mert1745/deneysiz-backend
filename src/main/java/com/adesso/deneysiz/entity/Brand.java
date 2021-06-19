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
    @Column(name = "offer_in_china")
    private boolean offerInChina;
    @Column(name = "category")
    private String category;
    @Column(name = "parent_company_safe")
    private boolean parentCompanySafe;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "certificate")
    private String certificate;
    @Column(name = "is_safe")
    private boolean isSafe;
    @Column(name = "vegan")
    private boolean vegan;
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
                ", offerInChina=" + offerInChina +
                ", category='" + category + '\'' +
                ", parentCompanySafe=" + parentCompanySafe +
                ", shopName='" + shopName + '\'' +
                ", certificate='" + certificate + '\'' +
                ", isSafe=" + isSafe +
                ", vegan=" + vegan +
                ", hasVeganProduct=" + hasVeganProduct +
                '}';
    }
}
