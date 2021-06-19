package com.adesso.deneysiz.integration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Certificate {
    private String certificate;
    private boolean valid;
}
