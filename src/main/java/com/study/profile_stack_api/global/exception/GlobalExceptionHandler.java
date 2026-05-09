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

    @ExceptionHandler(ProfileNotFoundException.class)
    public  ResponseEntity<Map<String, String>> handleProfileNotFound(ProfileNotFoundException e) {
        Map<String, String> result = new HashMap<>();
        result.put("code", "PROFILE_NOT_FOUND");
        result.put("message", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(result);
    }

    @ExceptionHandler(TechStackNotFoundException.class)
    public  ResponseEntity<Map<String, String>> handleTechStackNotFound(TechStackNotFoundException e) {

        Map<String, String> result = new HashMap<>();
        result.put("code", "TECH_STACK_NOT_FOUND");
        result.put("message", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(result);
    }
}