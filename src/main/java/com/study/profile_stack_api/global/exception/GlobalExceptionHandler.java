package com.study.profile_stack_api.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔥 중복 이메일 예외 처리
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmail(DuplicateEmailException e) {

        Map<String, String> result = new HashMap<>();
        result.put("code", "DUPLICATE_EMAIL");
        result.put("message", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(result);
    }
}