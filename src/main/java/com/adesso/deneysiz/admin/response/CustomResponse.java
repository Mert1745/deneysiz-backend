package com.adesso.deneysiz.admin.response;

import com.adesso.deneysiz.admin.constant.ResponseMessage;
import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import org.springframework.http.HttpStatus;

import static com.adesso.deneysiz.admin.constant.ResponseMessage.ERROR_OCCURRED;

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

    public static <T> ResponseBuilder<T> getFailedResponse(T data, Exception e) {
        return ResponseBuilder.<T>getInstance()
                .data(data)
                .message(ERROR_OCCURRED + e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static <T>ResponseBuilder<T> getSuccessResponse(T body) {
        return ResponseBuilder.<T>getInstance()
                .status(HttpStatus.OK.value())
                .data(body)
                .message(ResponseMessage.SUCCESS);
    }
}
