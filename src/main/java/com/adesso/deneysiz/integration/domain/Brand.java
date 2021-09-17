package com.adesso.deneysiz.integration.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
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
    @Column(name = "certificate")
    private String certificate;
    @Column(name = "is_safe")
    private boolean isSafe;
    @Column(name = "vegan")
    private boolean vegan;
    @Column(name = "has_vegan_product")
    private boolean hasVeganProduct;
    @Column(name = "text")
    private String text;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    public Brand() {
    }
}
