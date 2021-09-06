package com.adesso.deneysiz.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CertificateName {
    LB("LB"),
    PETA("Peta"),
    UNILEVER("Unilever"),
    XXXX("XXXX"),
    ;

    private final String name;
}
