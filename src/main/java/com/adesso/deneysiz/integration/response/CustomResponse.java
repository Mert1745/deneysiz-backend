package com.adesso.deneysiz.integration.response;

import com.adesso.deneysiz.integration.constant.ResponseMessage;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CustomResponse {
    public static <T> ResponseBuilder<List<T>> getSuccessResponse(List<T> mappedBrands, HttpStatus httpStatus, String message) {
        return ResponseBuilder.<List<T>>getInstance().
                data(mappedBrands)
                .status(httpStatus.value())
                .message(message);
    }

    public static <T> ResponseBuilder<List<T>> getFailedError(List<T> type, Exception e) {
        return ResponseBuilder.<List<T>>getInstance().
                message(ResponseMessage.ERROR_MESSAGE + e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
