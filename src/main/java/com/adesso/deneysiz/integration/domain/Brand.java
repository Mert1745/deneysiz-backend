package com.adesso.deneysiz.integration.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //TODO idea: set to passive instead of removing
    @Column
    private String name;
    @Column
    private String parentCompany;
    @Column
    private boolean offerInChina;
    @Column
    private String[] category;
    @Column
    private boolean parentCompanySafe;
    @Column
    private String certificate;
    @Column
    private boolean isSafe;
    @Column
    private boolean vegan;
    @Column
    private boolean hasVeganProduct;
    @Column
    private String text;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date lastModified;
}
