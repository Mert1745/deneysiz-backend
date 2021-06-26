package com.adesso.deneysiz.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponseBuilder {
    private int status;
    private boolean success;
    private String token;

    private AdminResponseBuilder() {
    }

    public static AdminResponseBuilder getInstance() {
        return new AdminResponseBuilder();
    }

    public AdminResponseBuilder status(int status) {
        this.status = status;
        return this;
    }

    public AdminResponseBuilder token(String token) {
        this.token = token;
        return this;
    }

    public AdminResponseBuilder success(boolean success) {
        this.success = success;
        return this;
    }
}
