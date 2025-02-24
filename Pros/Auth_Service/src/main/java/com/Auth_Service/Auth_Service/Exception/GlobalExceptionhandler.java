package com.Auth_Service.Auth_Service.Exception;


import com.Auth_Service.Auth_Service.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse> appException(AppException e)
    {
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                        .code(e.getResponseCode().getCode())
                        .mess(e.getMessage())
                .build());
    }
}
