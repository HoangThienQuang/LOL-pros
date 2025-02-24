package com.User_Service.User_Service.Exception;

import com.User_Service.User_Service.dto.ApiResponse;
import org.apache.kafka.shaded.com.google.protobuf.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> AppException(AppException e)
    {
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .code(e.getResponseCode().getCode())
                        .mess(e.getMessage())
                        .build()
        );
    }
}
