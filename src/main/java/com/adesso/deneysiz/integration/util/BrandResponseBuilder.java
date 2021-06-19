package com.adesso.deneysiz.integration.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponseBuilder<T> {
    private int status;
    private T brands;
    private String message;

    private BrandResponseBuilder() {
    }

    public static <T> BrandResponseBuilder<T> getInstance() {
        return new BrandResponseBuilder<T>();
    }

    public BrandResponseBuilder<T> status(int status) {
        this.status = status;
        return this;
    }

    public BrandResponseBuilder<T> brands(T brands) {
        this.brands = brands;
        return this;
    }

    public BrandResponseBuilder<T> message(String message) {
        this.message = message;
        return this;
    }
}
