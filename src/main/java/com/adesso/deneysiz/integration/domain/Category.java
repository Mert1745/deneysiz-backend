package com.adesso.deneysiz.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    MAKEUP(0, "Makyaj"),
    HAIR_CARE(1, "Saç Bakım"),
    SKIN_AND_FACE_CARE(2, "Cilt ve Yüz Bakım"),
    PERFUME(3, "Deodorant ve Parfüm"),
    PERSONAL_HYGIENE_AND_CARE(4, "Kişisel Hijyen ve Bakım"),
    TEETH_CARE(5, "Ağız Ve Diş Bakım"),
    BABY_CARE(6, "Anne ve Bebek Bakım"),
    HOUSE_CARE(7, "Ev Bakım"),
    ALL(8, "Tüm Markalar"),
    ;

    private final int id;
    private final String name;
}
