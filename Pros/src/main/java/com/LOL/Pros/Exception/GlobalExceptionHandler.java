package com.LOL.Pros.Exception;


import com.LOL.Pros.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> defaultException(Exception exception)
    {
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .code(ResponseCode.NOT_IMPLEMENT_EXCEPTION.getCode())
                        .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> AppException(AppException exception)
    {
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .code(exception.getResponseCode().getCode())
                        .message(exception.getMessage())
                        .build());
    }
}
