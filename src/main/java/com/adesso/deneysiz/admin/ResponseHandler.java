package com.adesso.deneysiz.admin;

import com.adesso.deneysiz.admin.entity.AdminDTO;
import com.adesso.deneysiz.integration.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseHandler extends ResponseEntityExceptionHandler {

    //FIXME mkose this handler not working
    @ExceptionHandler(value = InsufficientAuthenticationException.class)
    public ResponseBuilder<AdminDTO> handleInsufficientBalanceException(InsufficientAuthenticationException ignored) {
        return ResponseBuilder.<AdminDTO>getInstance().status(HttpStatus.UNAUTHORIZED.value()).data(null).message("WRONG_CREDENTIALS");
    }

}