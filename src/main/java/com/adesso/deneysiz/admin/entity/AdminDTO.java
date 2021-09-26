package com.adesso.deneysiz.admin.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private String token;
    private Boolean success = false;
}
