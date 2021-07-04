package com.adesso.deneysiz.integration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    MAKEUP(0, "Makyaj"),
    PERFUME(1, "Parfüm"),
    SKIN_CARE(2, "Cilt Bakım"),
    NAIL_CARE(3, "Tırnak Bakım"),
    HAIR_CARE(4, "Saç Bakım"),
    HAIR_DYES(5, "Saç Boyaları"),
    SUN_CREAMS(6, "Güneş Kremleri"),
    BODY_CARE(7, "Vücut Bakımı"),
    ALL(8, "Tüm Markalar"),
    ;

    private final int id;
    private final String name;
}
