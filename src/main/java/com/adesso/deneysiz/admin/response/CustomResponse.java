package com.adesso.deneysiz.admin.response;

import com.adesso.deneysiz.admin.constant.ResponseMessage;
import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import org.springframework.http.HttpStatus;

public class CustomResponse {
    public static ResponseBuilder<AdminDTO> getNotFoundResponse() {
        return ResponseBuilder.<AdminDTO>getInstance()
                .status(HttpStatus.NOT_FOUND.value())
                .data(new AdminDTO())
                .message(ResponseMessage.USER_NOT_FOUND);
    }

    public static ResponseBuilder<AdminDTO> getUnauthorizedResponse() {
        return ResponseBuilder.<AdminDTO>getInstance()
                .status(HttpStatus.UNAUTHORIZED.value())
                .data(new AdminDTO())
                .message(ResponseMessage.WRONG_CREDENTIALS);
    }

    public static <T>ResponseBuilder<T> getSuccessResponse(T body) {
        return ResponseBuilder.<T>getInstance()
                .status(HttpStatus.OK.value())
                .data(body)
                .message(ResponseMessage.SUCCESS);
    }
}
