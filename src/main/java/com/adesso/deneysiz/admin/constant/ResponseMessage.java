package com.adesso.deneysiz.admin.constant;

public class ResponseMessage {
    public static final String USER_NOT_FOUND = "User Not Found";
    public static final String WRONG_CREDENTIALS = "Wrong Credentials";
    public static final String SUCCESS = "Success";
    public static final String ERROR_OCCURRED = "Error occured. Root cause is ";

    private ResponseMessage() {
        throw new IllegalAccessError();
    }
}
