package com.adesso.deneysiz.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CertificateName {
    LB("Leaping Bunny"),
    BWB("Beauty Without Bunnies"),
    VS("Vegan Society"),
    VL("V-Label"),
    ;

    private final String name;
}
