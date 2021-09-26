package com.adesso.deneysiz.integration.response;

import com.adesso.deneysiz.integration.constant.ResponseMessage;
import com.adesso.deneysiz.integration.domain.Brand;
import com.adesso.deneysiz.integration.domain.BrandDTO;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

import static com.adesso.deneysiz.integration.constant.ResponseMessage.*;

@ControllerAdvice(basePackages = "com.adesso.deneysiz.integration")
public class ResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBuilder<BrandDTO>> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.<BrandDTO>getInstance()
                        .message(ResponseMessage.ERROR_MESSAGE + ex.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    public static <T> ResponseBuilder<List<T>> getSuccessResponse(List<T> body) {
        return getResponse(body, HttpStatus.OK, SUCCESS);
    }

    public static ResponseBuilder<List<BrandDTO>> getNotFoundResponse() {
        return getResponse(Collections.emptyList(), HttpStatus.NOT_FOUND, BRAND_NOT_FOUND);
    }

    public static ResponseBuilder<List<Brand>> getCategoryNameNotValidResponse() {
        return getResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, CATEGORY_NAME_NOT_VALID);
    }

    public static <T> ResponseBuilder<List<T>> getResponse(List<T> body, HttpStatus httpStatus, String message) {
        return ResponseBuilder.<List<T>>getInstance()
                .data(body)
                .status(httpStatus.value())
                .message(message);
    }
}
