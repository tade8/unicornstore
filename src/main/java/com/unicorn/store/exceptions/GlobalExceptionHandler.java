package com.unicorn.store.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unicorn.store.response.APIResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({GenericException.class, NumberFormatException.class, JsonProcessingException.class})
    public ResponseEntity<?> handleException(RuntimeException exception) {
        APIResponse apiResponse = APIResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .isSuccessful(false)
                .data(exception.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
