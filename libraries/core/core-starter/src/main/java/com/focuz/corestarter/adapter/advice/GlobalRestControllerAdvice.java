package com.focuz.corestarter.adapter.advice;

import com.focuz.corestarter.domain.entity.exception.ApplicationException;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalRestControllerAdvice {
    @ExceptionHandler(ApplicationException.class)
    ValueResponse<?> applicationException(ApplicationException applicationException) {
        return ValueResponse.fail(applicationException.getMessage(), applicationException.getErrorCode().getCode(), applicationException.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

        String message = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .filter(Objects::nonNull)
                .findFirst().orElse("");
        ValueResponse<?> valueResponse = ValueResponse.fail(message, "VALIDATION_FAIL", HttpStatus.BAD_REQUEST);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(valueResponse);
    }
}
