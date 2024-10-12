package com.task2_2.exception;

import com.task2_2.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleMyCustomException(CustomException e) {
        return ResponseUtil.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAnotherCustomException(Exception e) {
        e.printStackTrace();
        return ResponseUtil.error();
    }
}
