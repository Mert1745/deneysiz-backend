package com.adesso.deneysiz.admin.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AdminDTO {
    private String token;
    private Boolean success;
}
